package com.harium.annebeth.aspell;

import com.harium.annebeth.aspell.core.player.Player;
import com.harium.annebeth.aspell.sound.Jukebox;
import com.harium.annebeth.aspell.core.ui.ActionUIManager;
import com.harium.annebeth.aspell.core.ui.DialogManager;
import com.harium.annebeth.aspell.core.ui.InventoryManager;
import com.harium.annebeth.aspell.core.ui.SceneManager;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.event.KeyEvent;
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
        dialogManager = new DialogManager(w, h);
        sceneManager = new SceneManager(w, h);
        player = new Player(322, 240, sceneManager);

        actionUiManager = new ActionUIManager();
        inventoryManager = new InventoryManager();

        /*for(int i=0;i<90;i++) {
            loading = i;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/

        Jukebox.playNormalMusic();
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

    @Override
    public void updateKeyboard(KeyEvent event) {
        super.updateKeyboard(event);
        if (event.isKeyUp(KeyEvent.VK_1)) {
            sceneManager.turnWorldUpsideDown();
        }
        if (event.isKeyUp(KeyEvent.VK_2)) {
            sceneManager.turnWorldNormal();
        }

        if (event.isKeyUp(KeyEvent.VK_SPACE)) {
            SceneManager.DEBUG_MODE = !SceneManager.DEBUG_MODE;
        }
    }
}
