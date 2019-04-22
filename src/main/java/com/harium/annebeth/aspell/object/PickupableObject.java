package com.harium.annebeth.aspell.object;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;
import com.harium.annebeth.aspell.ui.InventoryManager;


public class PickupableObject extends BaseObject {

    public ImageLayer layer;
    protected ImageLayer inventoryLayer;

    public PickupableObject(String name, int x, int y, int w, int h) {
        super(name, x, y, w, h);
        canPickup = true;
    }

    public ImageLayer getInventoryLayer() {
        return inventoryLayer;
    }

    @Override
    public void draw(Graphics g) {
        if (disabled) {
            return;
        }
        layer.simpleDraw(g, x, y);
    }

    @Override
    public void onPickUp() {
        InventoryManager.pickup(this);
        disabled = true;
    }
}
