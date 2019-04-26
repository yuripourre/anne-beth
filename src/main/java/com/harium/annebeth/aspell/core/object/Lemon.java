package com.harium.annebeth.aspell.core.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.core.object.base.PickupableObject;
import com.harium.etyl.layer.ImageLayer;

public class Lemon extends PickupableObject {

    public Lemon(int x, int y) {
        super(LanguageManager.objectName(Dictionary.LEMON), x, y, 27, 23);
        layer = new ImageLayer(x, y, w, h, "objects/lemon.png");
        inventoryLayer = new ImageLayer("objects/lemon.png");
    }

    public Lemon() {
        this(0, 0);
    }

}
