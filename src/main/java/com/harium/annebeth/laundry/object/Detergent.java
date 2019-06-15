package com.harium.annebeth.laundry.object;

import com.harium.annebeth.core.object.BaseObject;
import com.harium.annebeth.core.object.HighObject;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.layer.ImageLayer;

public class Detergent extends HighObject {

    public Detergent(int x, int y) {
        super(LanguageManager.objectName(Dictionary.SOAP), x, y, 25, 40);
        layer = new ImageLayer(x, y, w, h, "objects/detergent.png");
        inventoryLayer = new ImageLayer(0, 0, 49, 80, "objects/detergent_inv.png");
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
        } else {
            cantUse();
        }
    }

}
