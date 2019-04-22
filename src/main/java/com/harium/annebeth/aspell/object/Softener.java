package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.etyl.layer.ImageLayer;

public class Softener extends PickupableObject {

    public Softener(int x, int y) {
        super(LanguageManager.objectName(Dictionary.SOFTENER), x, y, 36, 44);
        layer = new ImageLayer("objects/softener.png");
        inventoryLayer = new ImageLayer("objects/softener.png");
    }

}
