package com.harium.annebeth.aspell;

import com.harium.annebeth.aspell.object.*;
import com.harium.annebeth.aspell.player.Player;
import com.harium.annebeth.aspell.sound.Jukebox;
import com.harium.annebeth.aspell.ui.ActionUIManager;
import com.harium.annebeth.aspell.ui.DialogManager;
import com.harium.annebeth.aspell.ui.InventoryManager;
import com.harium.annebeth.aspell.ui.SceneManager;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;

public class InGame extends Application {

    public static final int BOTTOM_BAR = 410;

    Player player;
    DialogManager dialogManager;
    ActionUIManager actionUiManager;
    InventoryManager inventoryManager;
    SceneManager sceneManager;

    public InGame(int w, int h) {
        super(w, h);
    }

    public void load() {
        Jukebox.init();
        dialogManager = new DialogManager();
        player = new Player(0, 80);

        sceneManager = new SceneManager();
        actionUiManager = new ActionUIManager();
        inventoryManager = new InventoryManager();

        InventoryManager.pickup(new Sock(0, 0));
        InventoryManager.pickup(new Lemon(0, 0));
        InventoryManager.pickup(new Stool(0, 0));
        InventoryManager.pickup(new Softener(0, 0));
        InventoryManager.pickup(new Detergent(0, 0));
        //Jukebox.playNormalMusic();
    }

    @Override
    public void update(long now) {
        player.update(now);
        dialogManager.update(now);
        sceneManager.update(now);
    }

    public void draw(Graphics g) {
        actionUiManager.draw(g);
        inventoryManager.draw(g);

        sceneManager.draw(g);
        player.draw(g);
        sceneManager.drawForeground(g);

        dialogManager.draw(g);
    }

    @Override
    public void updateMouse(PointerEvent event) {
        super.updateMouse(event);

        if (event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
            if (event.getY() < BOTTOM_BAR) {
                sceneManager.updateMouse(event, player);
            } else {
                inventoryManager.updateMouse(event);
                actionUiManager.updateMouse(event);
            }
        }
    }
}
