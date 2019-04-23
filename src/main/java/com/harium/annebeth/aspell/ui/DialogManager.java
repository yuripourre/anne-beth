package com.harium.annebeth.aspell.ui;

import com.harium.annebeth.aspell.core.Interaction;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Font;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.loader.FontLoader;

import java.util.ArrayList;
import java.util.List;

import static com.harium.annebeth.aspell.core.Context.*;

public class DialogManager {

    public static final float FONT_SIZE = 18f;
    public static final Color SHADOW_COLOR = Color.BLACK;
    public static final Color FONT_COLOR = Color.WHITE;
    static long DIALOG_DELAY = 3000;

    static List<String> dialogs = new ArrayList<String>();
    String currentDialog = "";

    long lastStart = 0;

    private Font font;

    public DialogManager() {
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
            int offset = 2;
            g.drawStringX(currentDialog, dialogHeight);
            g.setColor(FONT_COLOR);
            g.drawStringX(currentDialog, -offset, dialogHeight - offset);
        }

        drawBottomSentence(g);
    }

    private void drawBottomSentence(Graphics g) {
        if (interaction == Interaction.WALK && object == NULL_OBJECT) {
            return;
        }

        String sentence = asWord(interaction);

        if (object != NULL_OBJECT) {
            sentence += " " + object.name;
            if (interaction == Interaction.USE && object.canUse) {
                sentence += " with";
                if (with != NULL_OBJECT) {
                    sentence += " " + with.name;
                }
            }
        }

        // Draw action
        g.drawStringX(sentence, CONTEXT_SENTENCE);
    }

    public static void addDialog(String sentence) {
        dialogs.add(sentence);
    }
}
