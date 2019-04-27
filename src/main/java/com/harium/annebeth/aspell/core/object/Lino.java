package com.harium.annebeth.aspell.core.object;

import com.harium.annebeth.aspell.core.object.base.DecorativeObject;
import com.harium.annebeth.aspell.core.ui.DialogManager;
import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.etyl.layer.ImageLayer;

public class Lino extends DecorativeObject {

    public Lino(int x, int y) {
        super(LanguageManager.objectName(Dictionary.LINO), x, y, 60, 26);
        this.originalY = y;
        layer = new ImageLayer(x, y, w, h, "objects/lino.png");
    }

    @Override
    public void onLook() {
        DialogManager.addDialog("Hi Lino!");
        DialogManager.addDialog("Guess what, he is sleeping.");
    }
}
