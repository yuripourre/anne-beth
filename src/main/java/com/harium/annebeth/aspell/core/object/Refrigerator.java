package com.harium.annebeth.aspell.core.object;

import com.harium.annebeth.aspell.core.object.base.OpenableObject;
import com.harium.annebeth.aspell.core.object.base.PickupableObject;
import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class Refrigerator extends OpenableObject {

    private PickupableObject lemon = null;

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

    public void add(PickupableObject lemon) {
        this.lemon = lemon;
        lemon.visible = false;
        lemon.setPosition(x - 10, y + 170);
    }

    @Override
    public void onOpen() {
        super.onOpen();
        if (lemon != null) {
            lemon.visible = true;
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
}
