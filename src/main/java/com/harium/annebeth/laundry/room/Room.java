package com.harium.annebeth.laundry.room;

import com.harium.annebeth.core.Turnable;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public abstract class Room implements Turnable {

    int x, y;
    ImageLayer layer;

    public Room(int x, int y, int w, int h, String path) {
        this.x = x;
        this.y = y;
        layer = new ImageLayer(x, y, w, h, path);
    }

    public void draw(Graphics g) {
        layer.draw(g);
    }

    @Override
    public void turnUpsideDown() {
        //layer.setY(y);
        layer.setScaleY(-1);
    }

    @Override
    public void turnNormal() {
        //layer.setY(y);
        layer.setScaleY(1);
    }

    public void offset(int x, int y) {
        layer.offset(x, y);
    }
}
