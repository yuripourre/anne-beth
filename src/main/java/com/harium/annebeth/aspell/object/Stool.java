package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.object.base.PickupableObject;
import com.harium.etyl.layer.ImageLayer;

public class Stool extends PickupableObject {

    public Stool(int x, int y) {
        super(LanguageManager.objectName(Dictionary.STOOL), x, y, 60, 32);
        layer = new ImageLayer("objects/stool.png");
        inventoryLayer = new ImageLayer("objects/stool.png");
    }

}
