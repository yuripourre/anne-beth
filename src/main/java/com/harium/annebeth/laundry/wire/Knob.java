package com.harium.annebeth.laundry.wire;

public class Knob {

    private boolean[] connections = new boolean[6];
    private Wire[] wires = new Wire[6];
    private boolean chargedRed = false;
    private boolean chargedBlack = false;

    private static final Wire NULL_WIRE = new Wire();

    public Knob() {
        for (int i = 0; i < wires.length; i++) {
            wires[i] = NULL_WIRE;
        }
    }

    public void rotate() {
        boolean first = connections[0];
        for (int i = 1; i < connections.length; i++) {
            int index = i - 1;
            connections[index] = connections[i];
            Wire w = wires[index];
            if (w == NULL_WIRE) {
                continue;
            }
            boolean status = connections[index];

            Knob end = w.b;
            if (end.getConnections()[w.bi]) {
                if (status) {
                    end.setChargedRed(isChargedRed());
                    end.setChargedBlack(isChargedBlack());
                } else {
                    end.setChargedRed(false);
                    end.setChargedBlack(false);
                }
            }
        }
        connections[connections.length - 1] = first;
    }

    public void setConnections(boolean... values) {
        int count = 0;
        for (boolean b : values) {
            this.connections[count] = b;
            count++;
        }
    }

    public void connect(int i, Knob a, int ac) {
        wires[i] = new Wire(this, i, a, ac);
        if (connections[i] && a.connections[ac]) {
            if (chargedRed) {
                a.setChargedRed(true);
            } else if (chargedBlack) {
                a.setChargedBlack(true);
            }
        }
    }

    public boolean[] getConnections() {
        return connections;
    }

    public boolean isChargedRed() {
        return chargedRed;
    }

    public void setChargedRed(boolean chargedRed) {
        this.chargedRed = chargedRed;
    }

    public boolean isChargedBlack() {
        return chargedBlack;
    }

    public void setChargedBlack(boolean chargedBlack) {
        this.chargedBlack = chargedBlack;
    }
}
