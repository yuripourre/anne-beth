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

    @Override
    public PickLevel onPickUp() {
        super.onPickUp();
        return PickLevel.DOWN;
    }

    @Override
    public void onLook() {
        DialogManager.addDialog("It is a huge pile of clothes.");
    }
}
