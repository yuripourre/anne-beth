package com.harium.annebeth.laundry.core.object;

import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.core.object.DecorativeObject;
import com.harium.etyl.layer.ImageLayer;

public class Television extends DecorativeObject {

    public Television(int x, int y) {
        super(LanguageManager.objectName(Dictionary.TV), x, y, 124, 108);
        this.originalY = y;
        layer = new ImageLayer(x, y, w, h, "objects/television.png");
    }

}
