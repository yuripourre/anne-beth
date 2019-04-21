package com.harium.macbeth.aspell.ui;

import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.List;

public class DialogManager {

    static long DIALOG_DELAY = 3000;

    static List<String> dialogs = new ArrayList<String>();
    String currentDialog = "";

    long lastStart = 0;

    public DialogManager() {
        dialogs.add("");
    }

    public void update(long now) {
        if (!dialogs.isEmpty()) {
            if (lastStart==0) {
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
        g.setColor(Color.BLACK);
        if (!currentDialog.isEmpty()) {
            g.drawStringX(currentDialog, 200);
        }
    }

    public static void addDialog(String sentence) {
        dialogs.add(sentence);
    }
}
