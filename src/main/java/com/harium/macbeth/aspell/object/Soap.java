package com.harium.macbeth.aspell.object;

import com.harium.etyl.layer.ImageLayer;
import com.harium.macbeth.aspell.i18n.LanguageManager;

import static com.harium.macbeth.aspell.i18n.Dictionary.SOAP;

public class Soap extends PickupableObject {

    public Soap(int x, int y) {
        super(LanguageManager.objectName(SOAP), x, y, 36, 44);
        layer = new ImageLayer("objects/soap.png");
        inventoryLayer = new ImageLayer("objects/soap.png");
    }

}
