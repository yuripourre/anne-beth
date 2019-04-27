package com.harium.annebeth.aspell;

import com.harium.annebeth.aspell.sound.Jukebox;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

import java.util.ArrayList;
import java.util.List;

public class Credits extends Application {

    public static final int SHADOW_SIZE = 2;
    public static final Color SHADOW_COLOR = Color.BLACK;
    public static final Color TEXT_COLOR = Color.WHITE;

    private float offset = 0;
    private int spacing = 20;
    private int speed = 1;
    private int speedFast = 3;
    private ImageLayer splash;

    private List<String> credits = new ArrayList<String>();

    public Credits(int w, int h) {
        super(w, h);
        offset = h / 2;
    }

    public void load() {
        Jukebox.init();
        splash = new ImageLayer("screen/loading.png");
        Jukebox.playMenuMusic();

        credits.add("Thank you for playing!");
        credits.add("");
        credits.add("");
        credits.add("");
        credits.add("");
        credits.add("Art");
        credits.add("");
        credits.add("doudoulolita");
        credits.add("Original Woman / Witch sprites");
        credits.add("https://opengameart.org/content/pixie");
        credits.add("");
        credits.add("PurpleHeart");
        credits.add("House Objects");
        credits.add("https://opengameart.org/content/ph64-pixel-pack-100s-of-sideview-assets");
        credits.add("");
        credits.add("captainluna");
        credits.add("Cactus Flower");
        credits.add("http://lunamatic.net/172/cactus-flower/");
        credits.add("");
        credits.add("GarGar");
        credits.add("Original sleepy cat");
        credits.add("https://www.deviantart.com/gargar/art/Sleeping-cat-pixel-art-55626013");
        credits.add("");
        credits.add("Kenney");
        credits.add("UI");
        credits.add("https://opengameart.org/content/pixel-ui-pack-750-assets");
        credits.add("");
        credits.add("GraphicMama Team");
        credits.add("Original Splash Screen");
        credits.add("https://pixabay.com/illustrations/witch-cute-friendly-cat-adorable-1456313/");
        credits.add("");
        credits.add("Font");
        credits.add("Press Start 2P");
        credits.add("https://www.dafont.com/press-start-2p.font");
        credits.add("");
        credits.add("");
        credits.add("Sound Effects");
        credits.add("");
        credits.add("");
        credits.add("SubspaceAudio");
        credits.add("https://opengameart.org/content/512-sound-effects-8-bit-styl");
        credits.add("");
        credits.add("");
        credits.add("Musics");
        credits.add("");
        credits.add("");
        credits.add("CleytonKauffman");
        credits.add("Melody Town Theme");
        credits.add("https://opengameart.org/content/midi-town-theme-melody");
        credits.add("");
        credits.add("Komiku");
        credits.add("Facing it");
        credits.add("https://opengameart.org/content/facing-it");
        credits.add("");
        credits.add("jestar");
        credits.add("A Simple Trifle");
        credits.add("https://opengameart.org/content/a-simple-trifle");
        credits.add("");
        credits.add("");
        credits.add("Tools");
        credits.add("");
        credits.add("");
        credits.add("Pixelator");
        credits.add("http://pixelatorapp.com/");
        credits.add("");
        credits.add("");
        credits.add("This game was made during LibGDX Jam");
        credits.add("Special thanks to LibGDX and everyone in the Discord Channel");
    }

    public void draw(Graphics g) {
        splash.simpleDraw(g);

        int i = 0;
        for (String sentence : credits) {
            if (sentence.isEmpty()) {
                i++;
            }

            float ty = offset + spacing * i;
            g.setColor(SHADOW_COLOR);
            g.drawStringX(sentence, ty);
            g.setColor(TEXT_COLOR);
            g.drawStringX(sentence, -SHADOW_SIZE, ty - SHADOW_SIZE);
            i++;
        }
    }

    @Override
    public void update(long now) {
        super.update(now);
        offset -= speed;

        if (offset < -2000) {
            nextApplication = new MainMenu(w, h);
        }
    }

    @Override
    public void updateMouse(PointerEvent event) {
        super.updateMouse(event);

        if (event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
            if (speed != speedFast) {
                speed = speedFast;
            }
        }
    }
}
