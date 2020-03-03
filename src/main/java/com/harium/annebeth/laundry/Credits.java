package com.harium.annebeth.laundry;

import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.laundry.sound.Jukebox;
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
        LanguageManager.loadCredits();

        credits.add("");
        credits.add("");
        credits.add("");
        credits.add("");
        credits.add("");
        credits.add("");
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_THANK_YOU));
        credits.add("");
        credits.add("");
        credits.add("");
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_ART));
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_ORIGINAL_WITCH));
        //credits.add("https://opengameart.org/content/pixie");
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_ORIGINAL_HOUSE));
        //credits.add("https://opengameart.org/content/ph64-pixel-pack-100s-of-sideview-assets");
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_CACTUS_FLOWER));
        //credits.add("http://lunamatic.net/172/cactus-flower/");
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_ORIGINAL_CAT));
        //credits.add("https://www.deviantart.com/gargar/art/Sleeping-cat-pixel-art-55626013");
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_MAGNIFYING_GLASS));
        //credits.add("https://opengameart.org/content/magnifying-glass-0");
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_UI));
        //credits.add("https://opengameart.org/content/pixel-ui-pack-750-assets");
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_SPLASH));
        //credits.add("https://pixabay.com/illustrations/witch-cute-friendly-cat-adorable-1456313/");
        credits.add("");
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_FONT));
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_FONT_NAME));
        //credits.add("https://www.dafont.com/press-start-2p.font");
        credits.add("");
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_SFX));
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_SFX_CREATOR));
        //credits.add("https://opengameart.org/content/512-sound-effects-8-bit-styl");
        credits.add("");
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_MUSIC));
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_MUSIC_HOUSE));
        //credits.add("https://opengameart.org/content/midi-town-theme-melody");
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_MUSIC_UPSIDE_DOWN));
        //credits.add("https://opengameart.org/content/facing-it");
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_MUSIC_MENU));
        //credits.add("https://opengameart.org/content/a-simple-trifle");
        credits.add("");
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_TOOLS));
        credits.add("");
        credits.add("Pixelator");
        //credits.add("http://pixelatorapp.com/");
        credits.add("");
        credits.add("LibGDX");
        credits.add("");
        credits.add("Etyl");
        credits.add("");
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_BETA_TESTERS));
        credits.add("");
        credits.add("Wander Aquino");
        credits.add("");
        credits.add("");
        credits.add("");
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_MADE_BY));
        credits.add("");
        credits.add("Harium");
        credits.add("");
        credits.add("");
        credits.add("");
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_SPECIAL_THANKS));
        credits.add("");
        credits.add(LanguageManager.sentence(Dictionary.CREDITS_THANKS_DISCORD));
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
            g.drawStringX(sentence, (int) ty);
            g.setColor(TEXT_COLOR);
            g.drawStringX(sentence, -SHADOW_SIZE, (int) ty - SHADOW_SIZE);
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
