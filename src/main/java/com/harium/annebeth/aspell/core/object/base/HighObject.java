package com.harium.annebeth.aspell.core.object.base;

import com.harium.annebeth.aspell.core.ui.DialogManager;
import com.harium.annebeth.aspell.core.ui.InventoryManager;

public class HighObject extends PickupableObject {

    public HighObject(String name, int x, int y, int w, int h) {
        super(name, x, y, w, h);
    }

    @Override
    public PickLevel onPickUp() {
        if (!InventoryManager.has("stool")) {
            DialogManager.addDialog("I can't reach it");
            return PickLevel.NONE;
        } else {
            super.onPickUp();
        }

        return PickLevel.HIGH;
    }
}
