package com.harium.macbeth.aspell;

import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.macbeth.aspell.core.Context;
import com.harium.macbeth.aspell.object.Lemon;
import com.harium.macbeth.aspell.object.Soap;
import com.harium.macbeth.aspell.object.Softener;
import com.harium.macbeth.aspell.object.Stool;
import com.harium.macbeth.aspell.player.Player;
import com.harium.macbeth.aspell.ui.DialogManager;
import com.harium.macbeth.aspell.ui.InventoryManager;
import com.harium.macbeth.aspell.ui.SceneManager;
import com.harium.macbeth.aspell.ui.UIManager;

public class InGame extends Application {

    public static final int BOTTOM_BAR = 410;
    
    Player player;
    DialogManager dialogManager;
    UIManager uiManager;
    InventoryManager inventoryManager;
    SceneManager sceneManager;

    public InGame(int w, int h) {
        super(w, h);
    }

    public void load() {
        dialogManager = new DialogManager();
        player = new Player(0, 80);

        sceneManager = new SceneManager();
        uiManager = new UIManager();
        inventoryManager = new InventoryManager();

        InventoryManager.pickup(new Lemon(0, 0));
        InventoryManager.pickup(new Stool(0, 0));
        InventoryManager.pickup(new Lemon(0, 0));
        InventoryManager.pickup(new Softener(0, 0));
        InventoryManager.pickup(new Soap(0, 0));
        InventoryManager.pickup(new Lemon(0, 0));
        InventoryManager.pickup(new Soap(0, 0));
    }

    @Override
    public void update(long now) {
        player.update(now);
        dialogManager.update(now);
    }

    public void draw(Graphics g) {
        uiManager.draw(g);
        inventoryManager.draw(g);

        sceneManager.draw(g);
        player.draw(g);
        sceneManager.drawForeground(g);

        dialogManager.draw(g);
        Context.draw(g);
    }

    @Override
    public void updateMouse(PointerEvent event) {
        super.updateMouse(event);

        if (event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
            if (event.getY() < BOTTOM_BAR) {
                sceneManager.updateMouse(event, player);
            } else {
                inventoryManager.updateMouse(event);
                uiManager.updateMouse(event);
            }
        }
    }
}
