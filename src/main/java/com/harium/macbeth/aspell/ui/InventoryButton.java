package com.harium.macbeth.aspell.ui;

import com.harium.etyl.commons.layer.GeometricLayer;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;
import com.harium.macbeth.aspell.object.PickupableObject;

public class InventoryButton {

    public ImageLayer layer;
    public PickupableObject object;

    public InventoryButton(int x, int y) {
        layer = new ImageLayer(x, y, "inventory.png");
    }

    public void draw(Graphics g) {
        layer.draw(g);
    }

    public void drawObject(Graphics g) {
        ImageLayer layer = object.getInventoryLayer();
        layer.simpleDraw(g);
    }

    public GeometricLayer getLayer() {
        return layer;
    }

    public PickupableObject getObject() {
        return object;
    }

    public void setObject(PickupableObject object) {
        this.object = object;

        ImageLayer objectInventoryLayer = this.object.getInventoryLayer();
        int centerX = layer.getX() + layer.getW() / 2;
        objectInventoryLayer.setX(centerX - objectInventoryLayer.getW() / 2);

        int baseY = this.layer.getY() + 96;
        objectInventoryLayer.setY(baseY - objectInventoryLayer.getH());
    }
}
