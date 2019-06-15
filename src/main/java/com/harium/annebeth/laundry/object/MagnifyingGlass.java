package com.harium.annebeth.laundry.object;

import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.core.object.BaseObject;
import com.harium.annebeth.core.object.PickupableObject;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.etyl.layer.ImageLayer;

public class MagnifyingGlass extends PickupableObject {

    public MagnifyingGlass() {
        super(LanguageManager.objectName(Dictionary.ACTION_LOOK_AT), 0, 0, 50, 56);
        inventoryLayer = new ImageLayer(0, 0, 50, 56, "objects/magnifying-glass-inv.png");
        layer = inventoryLayer;
    }

    @Override
    public void onUse(BaseObject with) {
        if (!inInventory) {
            negativeDialog();
            return;
        }
    }

}
