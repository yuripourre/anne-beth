package com.harium.annebeth.aspell.object.base;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;


public class DecorativeObject extends BaseObject {

    protected ImageLayer layer;

    public DecorativeObject(String name, int x, int y, int w, int h) {
        super(name, x, y, w, h);
    }

    @Override
    public void draw(Graphics g) {
        if (!visible) {
            return;
        }
        layer.draw(g, x, y);
    }

}
