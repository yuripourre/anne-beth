package com.harium.annebeth.aspell.core.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.core.object.base.PickupableObject;
import com.harium.etyl.layer.ImageLayer;

public class Shoyu extends PickupableObject {

    public Shoyu(int x, int y) {
        super(LanguageManager.objectName(Dictionary.SHOYU), x, y, 80, 40);
        layer = new ImageLayer(x, y, w, h, "objects/shoyu.png");
        inventoryLayer = new ImageLayer("objects/shoyu.png");
    }

}
