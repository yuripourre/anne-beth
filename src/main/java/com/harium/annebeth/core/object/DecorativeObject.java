package com.harium.annebeth.core.object;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;


public class DecorativeObject extends BaseObject {

    public ImageLayer layer;

    public DecorativeObject(String name, int x, int y, int w, int h) {
        super(name, x, y, w, h);
    }

    @Override
    public void draw(Graphics g) {
        if (!visible) {
            return;
        }
        layer.draw(g);
    }

    @Override
    public void turnUpsideDown() {
        super.turnUpsideDown();
        layer.setY(y);
        layer.setScaleY(-1);
    }

    @Override
    public void turnNormal() {
        super.turnNormal();
        layer.setY(y);
        layer.setScaleY(1);
    }

    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        layer.setLocation(x, y);
    }

}
