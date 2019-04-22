package com.harium.macbeth.aspell.object;

import com.harium.etyl.layer.ImageLayer;
import com.harium.macbeth.aspell.i18n.LanguageManager;

import static com.harium.macbeth.aspell.i18n.Dictionary.STOOL;

public class Stool extends PickupableObject {

    public Stool(int x, int y) {
        super(LanguageManager.objectName(STOOL), x, y, 60, 32);
        layer = new ImageLayer("objects/stool.png");
        inventoryLayer = new ImageLayer("objects/stool.png");
    }

}
