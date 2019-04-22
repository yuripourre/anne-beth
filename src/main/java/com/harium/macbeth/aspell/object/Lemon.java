package com.harium.macbeth.aspell.object;

import com.harium.etyl.layer.ImageLayer;
import com.harium.macbeth.aspell.i18n.LanguageManager;

import static com.harium.macbeth.aspell.i18n.Dictionary.LEMON;

public class Lemon extends PickupableObject {

    public Lemon(int x, int y) {
        super(LanguageManager.objectName(LEMON), x, y, 48, 40);
        layer = new ImageLayer("objects/lemon.png");
        inventoryLayer = new ImageLayer("objects/lemon.png");
    }

}
