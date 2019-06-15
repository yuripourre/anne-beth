package com.harium.annebeth.core.ui;

import com.harium.annebeth.core.Interaction;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Font;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.loader.FontLoader;

import java.util.ArrayList;
import java.util.List;

import static com.harium.annebeth.core.Context.*;

public class DialogManager {

    public static final float FONT_SIZE = 18f;
    public static final int SHADOW_SIZE = 2;
    public static final Color SHADOW_COLOR = Color.BLACK;
    public static final Color FONT_COLOR = Color.WHITE;
    static long DIALOG_DELAY = 2500;

    static List<String> dialogs = new ArrayList<String>();
    String currentDialog = "";

    long lastStart = 0;

    private Font font;

    public DialogManager(int w, int h) {
        dialogs.add("");
        font = FontLoader.getInstance().loadFont("PressStart2P.ttf");
    }

    public void update(long now) {
        if (!dialogs.isEmpty()) {
            if (lastStart == 0) {
                currentDialog = dialogs.get(0);
                lastStart = now;
            } else if (lastStart + DIALOG_DELAY < now) {
                nextDialog(now);
            }
        }
    }

    private void nextDialog(long now) {
        dialogs.remove(0);

        if (!dialogs.isEmpty()) {
            currentDialog = dialogs.get(0);
            lastStart = now;
        } else {
            lastStart = 0;
            currentDialog = "";
        }
    }

    public void draw(Graphics g) {
        g.setFont(font);
        g.setFontSize(FONT_SIZE);
        g.setColor(SHADOW_COLOR);
        if (!currentDialog.isEmpty()) {
            int dialogHeight = 200;
            drawWithShadow(g, dialogHeight, currentDialog);
            g.setColor(SHADOW_COLOR);
        }

        drawBottomSentence(g);
    }

    private void drawWithShadow(Graphics g, int dialogHeight, String sentence) {
        g.drawStringX(sentence, dialogHeight);
        g.setColor(FONT_COLOR);
        g.drawStringX(sentence, -SHADOW_SIZE, dialogHeight - SHADOW_SIZE);
    }

    private void drawBottomSentence(Graphics g) {
        //if (interaction == Interaction.NONE) {
        //    return;
        //}

        String sentence = asWord(interaction);

        if (interaction == Interaction.WALK) {
            sentence += " " + LanguageManager.sentence(Dictionary.TO);
        }

        if (object != NULL_OBJECT && object != null) {
            sentence += " " + object.name;
            if (interaction == Interaction.USE && object.canUse) {
                sentence += " " + LanguageManager.sentence(Dictionary.WITH);
                if (with != NULL_OBJECT) {
                    sentence += " " + with.name;
                }
            }
        }

        // Draw action
        drawWithShadow(g, CONTEXT_SENTENCE, sentence);
    }

    public static void addDialog(String sentence) {
        dialogs.add(sentence);
    }
}
