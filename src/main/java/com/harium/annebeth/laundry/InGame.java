package com.harium.annebeth.laundry;

import com.harium.annebeth.core.Context;
import com.harium.annebeth.core.Interaction;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.core.player.Player;
import com.harium.annebeth.core.ui.*;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;

import static com.harium.annebeth.laundry.i18n.Dictionary.GOOD_MORNING;
import static com.harium.annebeth.laundry.i18n.Dictionary.LAUNDRY_DAY;

public class InGame extends Application {

    //public static final int BOTTOM_BAR = 410;

    Player player;
    DialogManager dialogManager;
    SkillManager skillManager;
    ActionUIManager actionUiManager;
    InventoryManager inventoryManager;
    SceneManager sceneManager;

    public InGame(int w, int h) {
        super(w, h);
        if (Game.loadingScreen == null) {
            Game.loadingScreen = new LoadingScreen(w, h);
        }
        loadApplication = Game.loadingScreen;
    }

    public void load() {
        Jukebox.init();

        dialogManager = new DialogManager(w, h);
        actionUiManager = new ActionUIManager(w, h);
        sceneManager = new SceneManager(w, h, actionUiManager);
        player = new Player(322, 200, sceneManager);

        skillManager = new SkillManager(w, h);
        inventoryManager = new InventoryManager(w, h);

        initAnimation();
        Jukebox.playNormalMusic();
    }

    private void initAnimation() {
        DialogManager.addDialog(LanguageManager.sentence(GOOD_MORNING));
        DialogManager.addDialog(LanguageManager.sentence(LAUNDRY_DAY));
    }

    @Override
    public void update(long now) {
        player.update(now);
        dialogManager.update(now);
        sceneManager.update(now);
        inventoryManager.update(now);

        if (sceneManager.isGameOver()) {
            nextApplication = new Credits(w, h);
        }
    }

    public void draw(Graphics g) {
        inventoryManager.draw(g);

        sceneManager.draw(g);
        player.draw(g);
        sceneManager.drawForeground(g);

        actionUiManager.draw(g);

        skillManager.draw(g);
        dialogManager.draw(g);
    }

    @Override
    public void updateMouse(PointerEvent event) {
        super.updateMouse(event);

        if (event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
            if (event.getY() < InventoryManager.INVENTORY_BAR_Y) {
                actionUiManager.updateMouse(event);
                sceneManager.updateMouse(event, player);
            } else {
                inventoryManager.updateMouse(event);
            }
        }
    }

    @Override
    public void updateKeyboard(KeyEvent event) {
        super.updateKeyboard(event);

        if (event.isKeyUp(KeyEvent.VK_O)) {
            Context.interaction = Interaction.OPEN;
        } else if (event.isKeyUp(KeyEvent.VK_C)) {
            Context.interaction = Interaction.CLOSE;
        } else if (event.isKeyUp(KeyEvent.VK_P)) {
            Context.interaction = Interaction.PICK_UP;
        } else if (event.isKeyUp(KeyEvent.VK_U)) {
            Context.interaction = Interaction.USE;
        } else if (event.isKeyUp(KeyEvent.VK_L)) {
            Context.interaction = Interaction.LOOK_AT;
        }/* else if (event.isKeyUp(KeyEvent.VK_W)) {
            Context.interaction = Interaction.WALK;
        }*/

        // Debug Only
        /*if (event.isKeyUp(KeyEvent.VK_1)) {
            sceneManager.turnWorldUpsideDown();
        }
        if (event.isKeyUp(KeyEvent.VK_2)) {
            sceneManager.turnWorldNormal();
        }

        if (event.isKeyUp(KeyEvent.VK_SPACE)) {
            SceneManager.DEBUG_MODE = !SceneManager.DEBUG_MODE;
        }*/
    }
}
