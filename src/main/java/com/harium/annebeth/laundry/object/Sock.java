package com.harium.annebeth.laundry.object;

import com.harium.annebeth.core.object.PickLevel;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.core.object.BaseObject;
import com.harium.annebeth.core.object.PickupableObject;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.annebeth.core.ui.DialogManager;
import com.harium.annebeth.core.ui.InventoryManager;
import com.harium.etyl.layer.ImageLayer;

public class Sock extends PickupableObject {

    public Sock(int x, int y) {
        super(LanguageManager.objectName(Dictionary.SOCK), x, y, 80, 40);
        layer = new ImageLayer(x, y, w, h, "objects/redsock.png");
        inventoryLayer = new ImageLayer("objects/redsock.png");
    }

    @Override
    public void onUse(BaseObject with) {
        if (Washer.isWasher(with)) {
            Washer washer = (Washer) with;
            if (washer.isOpen()) {
                Jukebox.playUse();
                washer.hasSock = true;
                visible = false;
                InventoryManager.remove(this.getName());
            } else {
                DialogManager.addDialog("The washer should be open first.");
            }
        } else {
            cantUse();
        }
    }

    @Override
    public PickLevel onPickUp() {
        super.onPickUp();
        return PickLevel.DOWN;
    }
}
