package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.etyl.layer.ImageLayer;

public class Soap extends PickupableObject {

    public Soap(int x, int y) {
        super(LanguageManager.objectName(Dictionary.SOAP), x, y, 36, 44);
        layer = new ImageLayer("objects/soap.png");
        inventoryLayer = new ImageLayer("objects/soap.png");
    }

}
