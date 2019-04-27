package com.harium.annebeth.laundry.core.ui;

import com.harium.annebeth.laundry.core.Interaction;
import com.harium.annebeth.laundry.i18n.LanguageManager;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.commons.layer.GeometricLayer;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class ActionButton {

    Interaction interaction;
    ImageLayer layer;
    ImageLayer label;

    public ActionButton(Interaction interaction, int x, int y) {
        this.interaction = interaction;
        layer = new ImageLayer(x, y, "ui/button.png");
        //label = new ImageLayer();*/
    }

    public void draw(Graphics g) {
        layer.draw(g);
        g.setColor(Color.WHITE);
        g.drawString(LanguageManager.asWord(interaction), layer);
    }

    public GeometricLayer getLayer() {
        return layer;
    }

}
