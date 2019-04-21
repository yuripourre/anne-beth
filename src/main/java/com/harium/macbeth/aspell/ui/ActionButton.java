package com.harium.macbeth.aspell.ui;

import com.harium.etyl.commons.layer.GeometricLayer;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;
import com.harium.macbeth.aspell.core.Interaction;

public class ActionButton {

    Interaction interaction;
    ImageLayer layer;
    ImageLayer label;

    public ActionButton(Interaction interaction, int x, int y) {
        this.interaction = interaction;
        layer = new ImageLayer(x, y, "button.png");
        //label = new ImageLayer();*/
    }

    public void draw(Graphics g) {
        layer.draw(g);
    }

    public GeometricLayer getLayer() {
        return layer;
    }

}
