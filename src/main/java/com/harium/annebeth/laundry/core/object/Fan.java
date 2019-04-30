package com.harium.annebeth.laundry.core.object;

import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.laundry.i18n.LanguageManager;
import com.harium.annebeth.laundry.core.object.base.DecorativeObject;
import com.harium.annebeth.laundry.core.ui.DialogManager;
import com.harium.etyl.layer.ImageLayer;

public class Fan extends DecorativeObject {

    public Fan(int x, int y) {
        super(LanguageManager.objectName(Dictionary.FAN), x, y, 148, 52);
        layer = new ImageLayer(x, y, w, h, "objects/fan.png");
    }

}
