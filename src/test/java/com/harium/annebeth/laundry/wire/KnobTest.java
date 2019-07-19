package com.harium.annebeth.laundry.wire;

import org.junit.Assert;
import org.junit.Test;

public class KnobTest {

    @Test
    public void testRotate() {
        Knob knob = new Knob();
        knob.setConnections(true, false, true, false, true, false);
        knob.rotate();

        boolean[] conn = knob.getConnections();
        boolean[] expected = new boolean[]{false, true, false, true, false, true};
        Assert.assertArrayEquals(expected, conn);
    }

    @Test
    public void testChargedRed() {
        Knob source = new Knob();
        source.setConnections(true, false, true, false, true, false);
        source.setChargedRed(true);
        source.setChargedBlack(false);

        Knob a = new Knob();
        a.setConnections(true, false, false, false, false, false);
    }

}
