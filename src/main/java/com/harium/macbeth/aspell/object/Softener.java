package com.harium.macbeth.aspell.object;

import com.harium.etyl.layer.ImageLayer;
import com.harium.macbeth.aspell.i18n.LanguageManager;

import static com.harium.macbeth.aspell.i18n.Dictionary.SOAP;
import static com.harium.macbeth.aspell.i18n.Dictionary.SOFTENER;

public class Softener extends PickupableObject {

    public Softener(int x, int y) {
        super(LanguageManager.objectName(SOFTENER), x, y, 36, 44);
        layer = new ImageLayer("objects/softener.png");
        inventoryLayer = new ImageLayer("objects/softener.png");
    }

}
