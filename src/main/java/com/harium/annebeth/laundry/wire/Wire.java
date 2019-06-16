package com.harium.annebeth.laundry.wire;

public class Wire {

    public Knob a;
    private int ai;

    public Knob b;
    public int bi;

    private boolean charged = false;

    public Wire() {
    }

    public Wire(Knob a, int ai, Knob b, int bi) {
        this.a = a;
        this.ai = ai;
        this.b = b;
        this.bi = bi;
    }

    public boolean isConnected() {
        return a.getConnections()[ai] && b.getConnections()[bi];
    }

    public void setCharged(boolean charged) {
        this.charged = charged;
    }

    public boolean isCharged() {
        return charged;
    }
}
