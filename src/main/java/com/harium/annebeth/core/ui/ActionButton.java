package com.harium.annebeth.core.ui;

import com.harium.annebeth.core.Interaction;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.commons.layer.GeometricLayer;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class ActionButton {

    Interaction interaction;
    ImageLayer layer;
    ImageLayer label;

    boolean disabled = false;

    public ActionButton(Interaction interaction) {
        this.interaction = interaction;
        layer = new ImageLayer(0, 0, "ui/button.png");
        //label = new ImageLayer();*/
    }

    public void draw(Graphics g) {
        if (disabled) {
            return;
        }
        layer.draw(g);
        g.setColor(Color.WHITE);
        g.drawString(LanguageManager.asWord(interaction), layer);
    }

    public GeometricLayer getLayer() {
        return layer;
    }

}
