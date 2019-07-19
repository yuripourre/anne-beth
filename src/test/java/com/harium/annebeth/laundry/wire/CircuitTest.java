package com.harium.annebeth.laundry.wire;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CircuitTest {

    Circuit circuit;
    Knob start, a, b, c, end;
    Wire sourceA, sourceB, ab, endA, endB;

    @Before
    public void setUp() {
        circuit = new Circuit();

        start = mock(Knob.class);
        a = mock(Knob.class);
        b = mock(Knob.class);
        c = mock(Knob.class);
        end = mock(Knob.class);

        Knob[] knobs = new Knob[5];
        knobs[Circuit.A] = start;
        knobs[Circuit.B] = a;
        knobs[Circuit.C] = b;
        knobs[Circuit.E] = c;
        knobs[Circuit.F] = end;

        sourceA = new Wire(start, 0, a, 0);
        sourceB = new Wire(start, 1, b, 1);
        ab = new Wire(a, 2, b, 2);
        endA = new Wire(a, 3, end, 3);
        endB = new Wire(b, 4, end, 4);

        Wire[] wires = new Wire[5];
        wires[Circuit.A] = sourceA;
        wires[Circuit.B] = sourceB;
        wires[Circuit.C] = ab;
        wires[Circuit.E] = endA;
        wires[Circuit.F] = endB;

        circuit.knobs = knobs;
        circuit.wires = wires;
    }

    @Test
    public void testUpdateSourceC() {
        allConnected(start);
        allConnected(a);
        allConnected(b);
        allConnected(c);
        allConnected(end);

        Assert.assertFalse(ab.isCharged());
        circuit.updateWires();
        Assert.assertTrue(ab.isCharged());
    }

    private void allConnected(Knob knob) {
        when(knob.getConnections()).thenReturn(new boolean[]{true, true, true, true, true, true});
    }

    private void setupConnections(Knob knob, boolean[] connections) {
        when(knob.getConnections()).thenReturn(connections);
    }

}
