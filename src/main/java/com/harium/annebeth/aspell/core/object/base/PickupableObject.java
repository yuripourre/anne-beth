package com.harium.annebeth.aspell.core.object.base;

import com.harium.annebeth.aspell.sound.Jukebox;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;
import com.harium.annebeth.aspell.core.ui.InventoryManager;


public class PickupableObject extends DecorativeObject {

    public boolean shouldRemove = false;
    protected ImageLayer inventoryLayer;

    public PickupableObject(String name, int x, int y, int w, int h) {
        super(name, x, y, w, h);
        canPickup = true;
        canUse = true;
    }

    public ImageLayer getInventoryLayer() {
        return inventoryLayer;
    }

    @Override
    public void draw(Graphics g) {
        if (!visible) {
            return;
        }
        layer.draw(g);
    }

    @Override
    public void onPickUp() {
        visible = false;
        InventoryManager.pickup(this);
        Jukebox.playPickup();
    }
}
