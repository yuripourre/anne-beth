package com.harium.annebeth.laundry.wire;

public class Wire {

    private Knob a;
    private int ai;

    public Knob b;
    public int bi;

    public Wire() {}

    public Wire(Knob a, int ai, Knob b, int bi) {
        this.a = a;
        this.ai = ai;
        this.b = b;
        this.bi = bi;
    }

}
