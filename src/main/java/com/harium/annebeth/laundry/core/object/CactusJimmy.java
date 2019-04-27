package com.harium.annebeth.laundry.core.object;

import com.harium.annebeth.laundry.core.object.base.DecorativeObject;
import com.harium.annebeth.laundry.core.ui.DialogManager;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.laundry.i18n.LanguageManager;
import com.harium.etyl.layer.ImageLayer;

public class CactusJimmy extends DecorativeObject {

    boolean looked = false;

    public CactusJimmy(int x, int y) {
        super(LanguageManager.objectName(Dictionary.JIMMY_PLANT), x, y, 80, 152);
        layer = new ImageLayer(x, y, w, h, "objects/jimmy_cactus.png");
    }

    @Override
    public void onLook() {
        DialogManager.addDialog("Jimmy the Plant.");
    }
}
