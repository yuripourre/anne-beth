package com.harium.annebeth.aspell.core.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.core.object.base.BaseObject;
import com.harium.annebeth.aspell.core.object.base.HighObject;
import com.harium.annebeth.aspell.sound.Jukebox;
import com.harium.annebeth.aspell.core.ui.DialogManager;
import com.harium.etyl.layer.ImageLayer;

public class Detergent extends HighObject {

    public Detergent(int x, int y) {
        super(LanguageManager.objectName(Dictionary.SOAP), x, y, 25, 40);
        layer = new ImageLayer(x, y, w, h, "objects/detergent.png");
        inventoryLayer = new ImageLayer(x, y, "objects/detergent_inv.png");
    }

    @Override
    public void onUse(BaseObject with) {
        if (with.name.equals(LanguageManager.objectName(Dictionary.WASHER))) {
            Washer washer = (Washer) with;
            //if (washer.isOpen() && !washer.hasDetergent) {
                washer.hasDetergent = true;
                Jukebox.playUse();
            //} else {
            //    DialogManager.addDialog("The washer should be open first.");
            //}
        }
    }

}
