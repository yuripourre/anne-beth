package com.harium.annebeth.laundry.object;

import com.harium.annebeth.core.object.DecorativeObject;
import com.harium.annebeth.core.ui.DialogManager;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.etyl.layer.ImageLayer;

public class Lino extends DecorativeObject {

    public Lino(int x, int y) {
        super(LanguageManager.objectName(Dictionary.LINO), x, y, 60, 26);
        this.originalY = y;
        layer = new ImageLayer(x, y, w, h, "objects/lino.png");
    }

    @Override
    public void onLook() {
        DialogManager.addDialog(LanguageManager.sentence(Dictionary.LINO_GREETINGS, this));
        DialogManager.addDialog(LanguageManager.sentence(Dictionary.LINO_SLEEPING));
    }
}
