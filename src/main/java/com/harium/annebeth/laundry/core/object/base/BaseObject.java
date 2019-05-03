package com.harium.annebeth.laundry.core.object.base;

import com.harium.annebeth.laundry.core.Turnable;
import com.harium.annebeth.laundry.core.ui.DialogHelper;
import com.harium.annebeth.laundry.core.ui.DialogManager;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.laundry.i18n.LanguageManager;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.core.graphics.Graphics;

import static com.harium.annebeth.laundry.core.ui.SceneManager.ROOM_HEIGHT;
import static com.harium.annebeth.laundry.core.ui.SceneManager.ROOM_OFFSET;


public class BaseObject implements Turnable {

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
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.CANT_DO_THAT));
        } else {
            Jukebox.playOpen();
        }

    }

    public void onClose() {
        if (!canClose) {
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.CANT_DO_THAT));
        } else {
            Jukebox.playOpen();
        }
    }

    public PickLevel onPickUp() {
        if (!canPickup) {
            negativeDialog();
        } else {
            Jukebox.playPickup();
        }
        return PickLevel.NONE;
    }

    public void onUse(BaseObject with) {
        if (!canUse) {
            negativeDialog();
        } else {
            Jukebox.playUse();
        }
    }

    protected void negativeDialog() {
        String sentence = DialogHelper.negativeSentence();
        DialogManager.addDialog(sentence);
    }

    public void onPull() {
        if (!canPull) {
            negativeDialog();
        } else {
            Jukebox.playPickup();
        }
    }

    public void onLook() {
        DialogManager.addDialog(LanguageManager.sentence(Dictionary.STANDARD_LOOK_AT, this));
    }

    public int centerX() {
        return x + w / 2;
    }

    public void turnUpsideDown() {
        // I have no idea where 44 comes from
        // int offset = ROOM_HEIGHT + ROOM_OFFSET * 2 - originalY - h;
        int offset = ROOM_HEIGHT + ROOM_OFFSET + 44 - originalY - h;
        y = offset;
    }

    public void turnNormal() {
        y = originalY;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void offset(int x, int y) {
        setPosition(this.x + x, this.y + y);
    }
}
