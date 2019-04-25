package com.harium.annebeth.aspell.object.base;

import com.harium.annebeth.aspell.sound.Jukebox;
import com.harium.annebeth.aspell.ui.DialogHelper;
import com.harium.annebeth.aspell.ui.DialogManager;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Graphics;

import static com.harium.annebeth.aspell.ui.SceneManager.ROOM_HEIGHT;
import static com.harium.annebeth.aspell.ui.SceneManager.ROOM_OFFSET;


public class BaseObject {

    public String name;

    public boolean visible = true;
    public boolean disabled = false;

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

    protected int originalY = 0;

    public BaseObject(String name) {
        this.name = name;
    }

    public BaseObject(String name, int x, int y, int w, int h) {
        this(name);
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.originalY = y;
    }

    public void draw(Graphics g) {

    }

    public void onOpen() {
        if (!canOpen) {
            DialogManager.addDialog("I can't do that.");
        } else {
            Jukebox.playOpen();
        }

    }

    public void onClose() {
        if (!canClose) {
            DialogManager.addDialog("I can't do that.");
        } else {
            Jukebox.playOpen();
        }
    }

    public void onPickUp() {
        if (!canPickup) {
            String sentence = DialogHelper.negativeSentence();
            DialogManager.addDialog(sentence);
        } else {
            Jukebox.playPickup();
        }
    }

    public void onUse(BaseObject with) {
        if (!canUse) {
            String sentence = DialogHelper.negativeSentence();
            DialogManager.addDialog(sentence);
        } else {
            Jukebox.playUse();
        }
    }

    public void onPull() {
        if (!canPull) {
            String sentence = DialogHelper.negativeSentence();
            DialogManager.addDialog(sentence);
        } else {
            Jukebox.playPickup();
        }
    }

    public void onLook() {
        DialogManager.addDialog("It is just a " + name + ".");
    }

    public int centerX() {
        return x + w / 2;
    }

    public void turnUpsideDown() {
        int offset = ROOM_HEIGHT + ROOM_OFFSET - originalY - h;
        y = offset;
    }

    public void turnNormal() {
        y = originalY;
    }

    protected void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
