package com.harium.annebeth.aspell;

import com.harium.annebeth.aspell.sound.Jukebox;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class MainMenu extends Application {

    private ImageLayer splash;

    public MainMenu(int w, int h) {
        super(w, h);
    }

    public void load() {
        Jukebox.init();
        splash = new ImageLayer("splash.png");
        Jukebox.playMenuMusic();
    }

    public void draw(Graphics g) {
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
