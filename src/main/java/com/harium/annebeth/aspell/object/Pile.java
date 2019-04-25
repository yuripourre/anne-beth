package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.object.base.BaseObject;
import com.harium.annebeth.aspell.object.base.PickupableObject;
import com.harium.annebeth.aspell.sound.Jukebox;
import com.harium.annebeth.aspell.ui.DialogManager;
import com.harium.annebeth.aspell.ui.InventoryManager;
import com.harium.etyl.layer.ImageLayer;

public class Pile extends PickupableObject {

    public Pile(int x, int y) {
        super(LanguageManager.objectName(Dictionary.CLOTHES_PILE), x, y, 90, 60);
        layer = new ImageLayer(x, y, w, h, "objects/pile.png");
        inventoryLayer = new ImageLayer("objects/pile.png");
    }

    @Override
    public void onUse(BaseObject with) {
        if (with.name.equals(LanguageManager.objectName(Dictionary.WASHER))) {
            Washer washer = (Washer) with;
            if (washer.isOpen()) {
                Jukebox.playUse();
                washer.hasPile = true;
                visible = false;
                InventoryManager.remove(this.name);
            } else {
                DialogManager.addDialog("The washer should be open first.");
            }
        }
    }

}
