package com.harium.annebeth.laundry.core.object;

import com.harium.annebeth.laundry.core.object.base.DecorativeObject;
import com.harium.annebeth.laundry.core.ui.DialogManager;
import com.harium.annebeth.laundry.i18n.LanguageManager;
import com.harium.etyl.layer.ImageLayer;

import static com.harium.annebeth.laundry.i18n.Dictionary.JIMMY_PLANT;
import static com.harium.annebeth.laundry.i18n.Dictionary.JIMMY_PLANT_LOOK_AT;

public class CactusJimmy extends DecorativeObject {

    public CactusJimmy(int x, int y) {
        super(LanguageManager.objectName(JIMMY_PLANT), x, y, 80, 152);
        layer = new ImageLayer(x, y, w, h, "objects/jimmy_cactus.png");
    }

    @Override
    public void onLook() {
        DialogManager.addDialog(LanguageManager.sentence(JIMMY_PLANT_LOOK_AT, this));
    }
}
