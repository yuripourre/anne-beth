package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.object.base.OpenableObject;
import com.harium.annebeth.aspell.object.base.PickupableObject;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class Lemon extends PickupableObject {

    private OpenableObject container;

    public Lemon(int x, int y, OpenableObject container) {
        super(LanguageManager.objectName(Dictionary.LEMON), x, y, 48, 40);
        this.container = container;
        layer = new ImageLayer(x, y, w, h, "objects/lemon.png");
        inventoryLayer = new ImageLayer("objects/lemon.png");
    }

    @Override
    public void draw(Graphics g) {
        if (!visible) {
            return;
        }
        if (container.isOpen()) {
            layer.draw(g, x, y);
        }
    }
}
