package com.harium.annebeth.laundry;

import android.app.Activity;
import android.content.SharedPreferences;
import com.harium.annebeth.core.Context;
import com.harium.annebeth.core.Interaction;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.core.player.Player;
import com.harium.annebeth.core.state.GameState;
import com.harium.annebeth.core.state.GameStateHandler;
import com.harium.annebeth.core.ui.*;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;

import static com.harium.annebeth.laundry.i18n.Dictionary.GOOD_MORNING;
import static com.harium.annebeth.laundry.i18n.Dictionary.LAUNDRY_DAY;

public class InGame extends Application implements GameStateHandler {

    //public static final int BOTTOM_BAR = 410;
    private static final String PREFERENCES_FILE = "ANNE_BETH_LAUNDRY";

    Player player;
    DialogManager dialogManager;
    SkillManager skillManager;
    ActionUIManager actionUiManager;
    InventoryManager inventoryManager;
    SceneManager sceneManager;
    SharedPreferences preferences;

    GameState state;

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
        inventoryManager = new InventoryManager(w, h, actionUiManager);

        Jukebox.turnMusicOff();
        Jukebox.turnSoundOff();

        preferences = getPreferences();
        state = new GameState(preferences);

        state.save(LaundryStateHandler.INVENTORY0, Dictionary.CLOTHES_PILE);
        state.save(LaundryStateHandler.INVENTORY1, Dictionary.SOCK);
        state.save(LaundryStateHandler.INVENTORY2, Dictionary.LEMON);
        state.save(LaundryStateHandler.INVENTORY2, Dictionary.SHOYU);

        LaundryStateHandler.load(this, state);

        Jukebox.turnMusicOn();
        Jukebox.turnSoundOn();

        initAnimation();
        Jukebox.playNormalMusic();
    }

    private SharedPreferences getPreferences() {
        Activity activity = (Activity) session.get("ANDROID_ACTIVITY");
        return activity.getSharedPreferences(PREFERENCES_FILE, android.content.Context.MODE_PRIVATE);
        //return new SharedPreferencesImpl(PREFERENCES_FILE);
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
            Context.setInteraction(Interaction.OPEN);
        } else if (event.isKeyUp(KeyEvent.VK_C)) {
            Context.setInteraction(Interaction.CLOSE);
        } else if (event.isKeyUp(KeyEvent.VK_P)) {
            Context.setInteraction(Interaction.PICK_UP);
        } else if (event.isKeyUp(KeyEvent.VK_U)) {
            Context.setInteraction(Interaction.USE);
        } else if (event.isKeyUp(KeyEvent.VK_L)) {
            Context.setInteraction(Interaction.LOOK_AT);
        }/* else if (event.isKeyUp(KeyEvent.VK_W)) {
            Context.interaction = Interaction.WALK;
        }*/

        /*if (event.isKeyUp(KeyEvent.VK_F2)) {
            save(state);
        }
        if (event.isKeyUp(KeyEvent.VK_F3)) {
            // Re init game
            load(state);
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

    @Override
    public void save(GameState state) {
        LaundryStateHandler.save(this, state);
    }

    @Override
    public void load(GameState state) {
        LaundryStateHandler.load(this, state);
    }
}
