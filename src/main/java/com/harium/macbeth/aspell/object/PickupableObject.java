package com.harium.macbeth.aspell.object;

import com.harium.etyl.layer.ImageLayer;
import com.harium.macbeth.aspell.ui.InventoryManager;


public class PickupableObject extends BaseObject {

    protected ImageLayer inventoryLayer;

    public PickupableObject(String name, int x, int y, int w, int h) {
        super(name, x, y, w, h);
        canPickup = true;
    }

    public ImageLayer getInventoryLayer() {
        return inventoryLayer;
    }

    @Override
    public void onPickUp() {
        InventoryManager.pickup(this);
    }
}
