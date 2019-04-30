package com.harium.annebeth.laundry;

import com.harium.annebeth.laundry.i18n.LanguageManager;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Font;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;
import com.harium.etyl.loader.FontLoader;

import java.util.Locale;

public class MainMenu extends Application {

    Font font;
    boolean setFont = false;
    private ImageLayer splash;

    public MainMenu(int w, int h) {
        super(w, h);
        if (Game.loadingScreen == null) {
            Game.loadingScreen = new LoadingScreen(w, h);
        }
        loadApplication = Game.loadingScreen;
    }

    public void load() {
        loadApplication.load();
        font = FontLoader.getInstance().loadFont("PressStart2P.ttf");
        String lang = Locale.getDefault().toString();
        LanguageManager.init(lang);
        //LanguageManager.init("pt_BR");
        Jukebox.init();
        splash = new ImageLayer("screen/splash.png");
        Jukebox.playMenuMusic();
    }

    public void draw(Graphics g) {
        if (!setFont && font != null) {
            g.setFont(font);
            setFont = true;
        }
        splash.simpleDraw(g);
    }


    @Override
    public void updateMouse(PointerEvent event) {
        super.updateMouse(event);

        if (event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
            Jukebox.stopMusics();
            nextApplication = new InGame(w, h);
        }
    }
}
