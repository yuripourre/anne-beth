package com.harium.annebeth.laundry.core.ui;

import com.harium.annebeth.laundry.core.object.base.PickupableObject;
import com.harium.etyl.commons.layer.GeometricLayer;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class InventoryButton {

    public ImageLayer layer;
    public PickupableObject object;

    public InventoryButton(int x, int y) {
        layer = new ImageLayer(x, y, 128, 128, "ui/inventory.png");
    }

    public void draw(Graphics g, int x, int y) {
        layer.simpleDraw(g, layer.getX() + x, layer.getY() + y);
    }

    public void drawObject(Graphics g, int x, int y) {
        ImageLayer layer = object.getInventoryLayer();
        layer.simpleDraw(g, layer.getX() + x, layer.getY() + y);
    }

    public GeometricLayer getLayer() {
        return layer;
    }

    public PickupableObject getObject() {
        return object;
    }

    public void setObject(PickupableObject object) {
        if (object == null) {
            return;
        }
        this.object = object;

        ImageLayer objectInventoryLayer = this.object.getInventoryLayer();
        int centerX = layer.getX() + layer.getW() / 2;
        objectInventoryLayer.setX(centerX - objectInventoryLayer.getW() / 2);

        int baseY = this.layer.getY() + 96;
        objectInventoryLayer.setY(baseY - objectInventoryLayer.getH());
    }

    public void updatePosition(int x, int y) {
        layer.setX(x);
        layer.setY(y);
        setObject(object);
    }
}
