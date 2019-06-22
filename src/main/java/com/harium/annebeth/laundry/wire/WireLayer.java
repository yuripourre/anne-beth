package com.harium.annebeth.laundry.wire;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class WireLayer {

    Wire wire;
    ImageLayer layerOn;
    ImageLayer layerOff;

    public WireLayer(Wire wire) {
        this.wire = wire;
    }

    public WireLayer(Knob a, int ai, Knob b, int bi) {
        this.wire = new Wire(a, ai, b, bi);
    }

    public void draw(Graphics g) {
        // TODO REMOVE
        if (layerOn == null || layerOff == null) {
            return;
        }

        if (wire.isCharged() && wire.isConnected()) {
            layerOn.simpleDraw(g);
        } else {
            layerOff.simpleDraw(g);
        }
    }

    public void setOn(ImageLayer layer) {
        layerOn = layer;
    }

    public void setOff(ImageLayer layer) {
        layerOff = layer;
    }
}
