package com.harium.annebeth.laundry.wire;

public class WireRed extends WireSingle {

    public WireRed(Wire wire) {
        super(wire);
    }

    public WireRed(Knob a, int ai, Knob b, int bi) {
        super(a, ai, b, bi);
    }

    protected boolean isCharged() {
        return wire.a.isChargedRed();
    }

}
