package com.harium.macbeth.aspell.object;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;
import com.harium.macbeth.aspell.ui.InventoryManager;

public class Lemon extends PickupableObject {

    public ImageLayer layer;

    public Lemon(int x, int y) {
        super("lemon", x, y, 48, 40);
        layer = new ImageLayer("objects/lemon.png");
        inventoryLayer = new ImageLayer("objects/lemon.png");
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
