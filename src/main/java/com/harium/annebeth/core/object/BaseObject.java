package com.harium.annebeth.core.object;

import com.harium.annebeth.core.Turnable;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.core.ui.DialogHelper;
import com.harium.annebeth.core.ui.DialogManager;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.core.graphics.Graphics;

import static com.harium.annebeth.core.ui.SceneManager.ROOM_HEIGHT;
import static com.harium.annebeth.core.ui.SceneManager.ROOM_OFFSET;


public class BaseObject implements Turnable {

    private String name;

    public boolean visible = true;
    public boolean disabled = false;

    public boolean canOpen = false;
    public boolean canClose = false;
    public boolean canLook = false;
    public boolean canUse = false;
    public boolean canPickup = false;
    public boolean canPull = false;

    protected boolean isOpen = false;

    public int x;
    public int y;
    public int w;
    public int h;

    protected int border = 1;
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

    public String getName() {
        return name;
    }

    public void onOpen() {
        if (!canOpen) {
            Jukebox.playCannot();
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.CANT_DO_THAT));
        } else {
            Jukebox.playOpen();
        }
    }

    public void onClose() {
        if (!canClose) {
            Jukebox.playCannot();
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.CANT_DO_THAT));
        } else {
            Jukebox.playOpen();
        }
    }

    public PickLevel onPickUp() {
        if (!canPickup) {
            Jukebox.playCannot();
            negativeDialog();
        } else {
            Jukebox.playPickup();
        }
        return PickLevel.NONE;
    }

    public void onUse(BaseObject with) {
        if (!canUse) {
            cantUse();
        } else {
            Jukebox.playUse();
        }
    }

    protected void cantUse() {
        Jukebox.playCannot();
        negativeDialog();
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

    public boolean collide(int x, int y) {
        return (this.x - border < x && this.x + this.w + border > x &&
                this.y - border < y && this.y + this.h + border > y);
    }

    public boolean isOpen() {
        return isOpen;
    }
}
