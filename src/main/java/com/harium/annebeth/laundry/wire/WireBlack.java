package com.harium.annebeth.laundry.wire;

public class WireBlack extends WireSingle {

    public WireBlack(Wire wire) {
        super(wire);
    }

    public WireBlack(Knob a, int ai, Knob b, int bi) {
        super(a, ai, b, bi);
    }

    protected boolean isCharged() {
        return wire.a.isChargedBlack();
    }

}
