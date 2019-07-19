package com.harium.annebeth.laundry.object;

import com.harium.annebeth.core.object.OpenableObject;
import com.harium.annebeth.core.object.PickupableObject;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class Refrigerator extends OpenableObject {

    private boolean wasOpen = false;
    public Lemon lemon = null;

    public Refrigerator(int x, int y) {
        super(LanguageManager.objectName(Dictionary.REFRIGERATOR), x, y, 108, 168);
        layer = new ImageLayer(x, y, w, h, "objects/fridge.png");
        openLayer = new ImageLayer(x, y, w, h, "objects/fridge_open.png");
    }

    @Override
    public void draw(Graphics g) {
        if (!visible) {
            return;
        }
        if (!isOpen) {
            layer.draw(g);
        } else {
            openLayer.draw(g);
        }
    }

    public void add(Lemon lemon) {
        this.lemon = lemon;
        lemon.visible = false;
        lemon.setPosition(x - 10, y + 170);
    }

    @Override
    public void onOpen() {
        super.onOpen();
        if (!wasOpen) {
            if (lemon != null) {
                lemon.visible = true;
            }
            wasOpen = true;
        }
    }

    @Override
    public void turnUpsideDown() {
        super.turnUpsideDown();
        if (lemon != null) {
            lemon.setPosition(x - 10, y + 270);
        }
    }

    @Override
    public void turnNormal() {
        super.turnNormal();
        if (lemon != null) {
            lemon.setPosition(x - 10, y + 170);
        }
    }

    public boolean wasOpen() {
        return wasOpen;
    }

    public void hideLemon() {
        lemon.visible = false;
    }
}
