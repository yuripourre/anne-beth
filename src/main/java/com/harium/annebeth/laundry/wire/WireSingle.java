package com.harium.annebeth.laundry.wire;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public abstract class WireSingle {

    Wire wire;
    ImageLayer layerOn;
    ImageLayer layerOff;

    public WireSingle(Wire wire) {
        this.wire = wire;
    }

    public WireSingle(Knob a, int ai, Knob b, int bi) {
        this.wire = new Wire(a, ai, b, bi);
    }

    public void draw(Graphics g) {
        // TODO REMOVE
        if (layerOn == null || layerOff == null) {
            return;
        }

        if (isCharged() && wire.isConnected()) {
            layerOn.simpleDraw(g);
        } else {
            layerOff.simpleDraw(g);
        }
    }

    protected abstract boolean isCharged();

    public void setOn(ImageLayer layer) {
        layerOn = layer;
    }

    public void setOff(ImageLayer layer) {
        layerOff = layer;
    }
}
