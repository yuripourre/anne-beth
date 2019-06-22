package com.harium.annebeth.laundry.wire;

public class Circuit {

    public static final int A = 0;
    public static final int B = 1;
    public static final int C = 2;
    public static final int D = 3;
    public static final int E = 4;
    public static final int F = 5;

    protected Knob[] knobs;
    protected Wire[] wires;

    public void updateWires() {
        wires[A].setCharged(wires[A].isConnected());
        wires[B].setCharged(wires[B].isConnected());
        wires[C].setCharged(connected(C) && charged(A, B));
        wires[D].setCharged(connected(D) && charged(A, B));
        wires[E].setCharged(connected(E) && charged(A, C, D));
        wires[F].setCharged(connected(F) && charged(B, C, D));
    }

    private boolean connected(int index) {
        return wires[index].isConnected();
    }

    private boolean charged(int... list) {
        for (int i = 0; i < list.length; i++) {
            int index = list[i];
            if (wires[index].isCharged()) {
                return true;
            }
        }
        return false;
    }

    public void setWires(Wire[] wires) {
        this.wires = wires;
    }

    public Wire[] getWires() {
        return wires;
    }

    public Knob[] getKnobs() {
        return knobs;
    }

    public void setKnobs(Knob[] knobs) {
        this.knobs = knobs;
    }
}
