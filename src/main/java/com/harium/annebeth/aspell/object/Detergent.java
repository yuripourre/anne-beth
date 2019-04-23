package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.object.base.BaseObject;
import com.harium.annebeth.aspell.object.base.HighObject;
import com.harium.annebeth.aspell.ui.DialogManager;
import com.harium.etyl.layer.ImageLayer;

public class Detergent extends HighObject {

    public Detergent(int x, int y) {
        super(LanguageManager.objectName(Dictionary.SOAP), x, y, 49, 80);
        layer = new ImageLayer("objects/detergent.png");
        inventoryLayer = new ImageLayer("objects/detergent.png");
    }

    @Override
    public void onUse(BaseObject with) {
        if (with.name.equals(LanguageManager.objectName(Dictionary.WASHER))) {
            Washer washer = (Washer) with;
            if (washer.isOpen()) {
                washer.hasDetergent = true;
            } else {
                DialogManager.addDialog("The washer should be open first.");
            }
        }
    }

}
