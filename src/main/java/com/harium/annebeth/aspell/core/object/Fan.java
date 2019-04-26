package com.harium.annebeth.aspell.core.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.core.object.base.DecorativeObject;
import com.harium.annebeth.aspell.core.ui.DialogManager;
import com.harium.etyl.layer.ImageLayer;

public class Fan extends DecorativeObject {

    boolean looked = false;

    public Fan(int x, int y) {
        super(LanguageManager.objectName(Dictionary.FAN), x, y, 148, 52);
        layer = new ImageLayer(x, y, w, h, "objects/fan.png");
    }

    @Override
    public void onLook() {
        if (!looked) {
            looked = true;
            DialogManager.addDialog("Whoa, this fan needs to be cleaned.");
            // Change the name
            name = "dirty fan";
        } else {
            super.onLook();
        }
    }

    @Override
    public void turnUpsideDown() {
        super.turnUpsideDown();
        // Special case
        y += 40;
        layer.setY(y);
        layer.setScaleY(-1);
    }

    @Override
    public void turnNormal() {
        super.turnNormal();
        layer.setY(y);
        layer.setScaleY(1);
    }
}
