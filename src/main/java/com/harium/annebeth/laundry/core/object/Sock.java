package com.harium.annebeth.laundry.core.object;

import com.harium.annebeth.laundry.core.object.base.PickLevel;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.laundry.i18n.LanguageManager;
import com.harium.annebeth.laundry.core.object.base.BaseObject;
import com.harium.annebeth.laundry.core.object.base.PickupableObject;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.annebeth.laundry.core.ui.DialogManager;
import com.harium.annebeth.laundry.core.ui.InventoryManager;
import com.harium.etyl.layer.ImageLayer;

public class Sock extends PickupableObject {

    public Sock(int x, int y) {
        super(LanguageManager.objectName(Dictionary.SOCK), x, y, 80, 40);
        layer = new ImageLayer(x, y, w, h, "objects/redsock.png");
        inventoryLayer = new ImageLayer("objects/redsock.png");
    }

    @Override
    public void onUse(BaseObject with) {
        if (with.name.equals(LanguageManager.objectName(Dictionary.WASHER))) {
            Washer washer = (Washer) with;
            if (washer.isOpen()) {
                Jukebox.playUse();
                washer.hasSock = true;
                visible = false;
                InventoryManager.remove(this.name);
            } else {
                DialogManager.addDialog("The washer should be open first.");
            }
        }
    }

    @Override
    public PickLevel onPickUp() {
        super.onPickUp();
        return PickLevel.DOWN;
    }
}
