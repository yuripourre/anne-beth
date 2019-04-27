package com.harium.annebeth.laundry.core.object;

import com.harium.annebeth.laundry.core.object.base.DecorativeObject;
import com.harium.annebeth.laundry.core.ui.DialogManager;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.laundry.i18n.LanguageManager;
import com.harium.etyl.layer.ImageLayer;

public class Cactus extends DecorativeObject {

    boolean looked = false;

    public Cactus(int x, int y) {
        super(LanguageManager.objectName(Dictionary.CACTUS), x, y, 88, 165);
        layer = new ImageLayer(x, y, w, h, "objects/cactus.png");
    }

    @Override
    public void onLook() {
        if (!looked) {
            looked = true;
            DialogManager.addDialog("It is just a cactus.");
            DialogManager.addDialog("Oh, wait, is it a flower?");
        } else {
            super.onLook();
        }
    }

}
