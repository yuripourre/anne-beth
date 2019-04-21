package com.harium.macbeth.aspell.object;

import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.macbeth.aspell.ui.DialogHelper;
import com.harium.macbeth.aspell.ui.DialogManager;


public class BaseObject {

    public String name;

    public boolean canOpen = false;
    public boolean canClose = false;
    public boolean canLook = false;
    public boolean canUse = false;
    public boolean canPickup = false;
    public boolean canPull = false;

    public int x;
    public int y;
    public int w;
    public int h;

    public BaseObject(String name) {
        this.name = name;
    }

    public BaseObject(String name, int x, int y, int w, int h) {
        this(name);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, w, h);
    }


    public void onOpen() {
        if (!canOpen) {
            DialogManager.addDialog("I can't do that.");
        }
    }

    public void onClose() {
        if (!canClose) {
            DialogManager.addDialog("I can't do that.");
        }
    }

    public void onPickUp() {
        if (!canPickup) {
            String sentence = DialogHelper.negativeSentence();
            DialogManager.addDialog(sentence);
        }
    }

    public void onUse(BaseObject with) {
        if (!canUse) {
            String sentence = DialogHelper.negativeSentence();
            DialogManager.addDialog(sentence);
        }
    }

    public void onPull() {
        if (!canPull) {
            String sentence = DialogHelper.negativeSentence();
            DialogManager.addDialog(sentence);
        }
    }

    public void onLook() {
        /*if(!canLook) {
            return;
        }*/
        // Thoughts about the object
        DialogManager.addDialog("It is just a " + name + ".");
    }

    public int centerX() {
        return x + w / 2;
    }

}
