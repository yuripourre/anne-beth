package com.harium.annebeth.laundry.core.object;

import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.core.object.DecorativeObject;
import com.harium.etyl.layer.ImageLayer;

public class Fan extends DecorativeObject {

    public Fan(int x, int y) {
        super(LanguageManager.objectName(Dictionary.FAN), x, y, 148, 52);
        layer = new ImageLayer(x, y, w, h, "objects/fan.png");
    }

}
