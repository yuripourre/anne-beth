package com.harium.annebeth.laundry.wire;

import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.animation.OnCompleteListener;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class WirePuzzle extends Application {

    private ImageLayer background;

    private Knob[] knobs;
    private Wire[] red;
    private Wire[] black;

    private Knob source;
    private Knob knobA;
    private Knob knobB;
    private Knob knobC;
    private Knob end;

    // Red Layer
    private WireLayer sourceA;
    private WireLayer sourceBRed;
    private WireLayer abLeft;
    private WireLayer abRight;
    private WireLayer aEnd;
    private WireLayer bEndRed;

    // Black Layer
    private WireLayer sourceC;
    private WireLayer sourceBBlack;
    private WireLayer bcLeft;
    private WireLayer bcRight;
    private WireLayer bEndBlack;
    private WireLayer cEnd;

    private Circuit redCircuit;
    private Circuit blackCircuit;

    public WirePuzzle(int w, int h) {
        super(w, h);
    }

    public void load() {
        Jukebox.init();
        background = new ImageLayer("wire/circuit_bg.png");

        initKnobs();
        initRedCircuit();
        initBlackCircuit();

        updateWires();
    }

    private void initKnobs() {
        int offset = 4;

        source = new Knob();
        source.setLayer(new ImageLayer(164 + offset, 213 + offset, "wire/knob_s.png"));
        source.setConnections(true, false, true, false, true, false);
        source.setChargedRed(true);
        source.setChargedBlack(true);

        knobA = new Knob();
        knobA.setLayer(new ImageLayer(440 + offset, 33 + offset, "wire/knob_a.png"));
        knobA.setConnections(true, true, true, false, false, false);

        knobB = new Knob();
        knobB.setLayer(new ImageLayer(440 + offset, 213 + offset, "wire/knob_b.png"));
        knobB.setConnections(true, true, false, false, false, false);

        knobC = new Knob();
        knobC.setLayer(new ImageLayer(440 + offset, 393 + offset, "wire/knob_c.png"));
        knobC.setConnections(true, true, true, true, false, false);

        end = new Knob();
        end.setLayer(new ImageLayer(714 + offset, 213 + offset, "wire/knob_e.png"));
        end.setConnections(true, true, false, false, true, false);

        knobs = new Knob[5];
        knobs[0] = source;
        knobs[1] = knobA;
        knobs[2] = knobB;
        knobs[3] = knobC;
        knobs[4] = end;
    }

    private void initRedCircuit() {
        sourceA = new WireLayer(source, 2, knobA, 0);
        sourceA.setOn(new ImageLayer(240, 102, "wire/sa_on.png"));
        sourceA.setOff(new ImageLayer(240, 102, "wire/sa_off.png"));

        sourceBRed = new WireLayer(source, 3, knobB, 0);
        sourceBRed.setOn(new ImageLayer(324, 273, "wire/wire_h_on.png"));
        sourceBRed.setOff(new ImageLayer(324, 273, "wire/wire_h_off.png"));

        abLeft = new WireLayer(knobA, 5, knobB, 1);
        //abLeft.setOn(new ImageLayer(318,103, "wire/abr_on.png"));
        //abLeft.setOff(new ImageLayer(318,103, "wire/abr_off.png"));

        abRight = new WireLayer(knobA, 4, knobB, 2);
        abRight.setOn(new ImageLayer(518,103, "wire/abr_on.png"));
        abRight.setOff(new ImageLayer(518,103, "wire/abr_off.png"));

        aEnd = new WireLayer(knobA, 3, end, 1);
        aEnd.setOn(new ImageLayer(518,103, "wire/abr_on.png"));
        aEnd.setOff(new ImageLayer(518,103, "wire/abr_off.png"));

        bEndRed = new WireLayer(knobB, 3, end, 0);
        bEndRed.setOn(new ImageLayer(602, 273, "wire/wire_h_on.png"));
        bEndRed.setOff(new ImageLayer(602, 273, "wire/wire_h_off.png"));

        red = new Wire[6];
        red[Circuit.A] = sourceA.wire;
        red[Circuit.B] = sourceBRed.wire;
        red[Circuit.C] = abLeft.wire;
        red[Circuit.D] = abRight.wire;
        red[Circuit.E] = aEnd.wire;
        red[Circuit.F] = bEndRed.wire;

        redCircuit = new Circuit();
        redCircuit.setKnobs(knobs);
        redCircuit.setWires(red);
    }

    private void initBlackCircuit() {
        // Same pins as sourceBRed
        sourceBBlack = new WireLayer(source, 3, knobB, 0);

        sourceC = new WireLayer(source, 4, knobC, 0);

        bcLeft = new WireLayer(knobB, 5, knobC, 1);

        bcRight = new WireLayer(knobB, 4, knobC, 2);

        bEndBlack = new WireLayer(knobB, 4, end, 0);

        cEnd = new WireLayer(knobC, 3, end, 5);

        black = new Wire[6];
        black[Circuit.A] = sourceBBlack.wire;
        black[Circuit.B] = sourceC.wire;
        black[Circuit.C] = bcLeft.wire;
        black[Circuit.D] = bcRight.wire;
        black[Circuit.E] = bEndBlack.wire;
        black[Circuit.F] = cEnd.wire;

        blackCircuit = new Circuit();
        blackCircuit.setKnobs(knobs);
        blackCircuit.setWires(black);
    }

    public void draw(Graphics g) {
        background.simpleDraw(g);
        drawWires(g);
        drawKnobs(g);
    }

    private void drawWires(Graphics g) {
        sourceA.draw(g);
        sourceBRed.draw(g);
        abLeft.draw(g);
        bEndRed.draw(g);
    }

    private void drawKnobs(Graphics g) {
        source.draw(g);
        end.draw(g);
        knobA.draw(g);
        knobB.draw(g);
        knobC.draw(g);
    }

    @Override
    public void update(long now) {
        super.update(now);
    }

    long lastRotation = 0;

    @Override
    public void updateMouse(PointerEvent event) {
        super.updateMouse(event);

        if (event.getTimestamp() < lastRotation + 400) {
            return;
        }

        if (event.isButtonUp(MouseEvent.MOUSE_BUTTON_LEFT)) {
            if (checkKnob(source, event)) {
                return;
            }
            if (checkKnob(end, event)) {
                return;
            }
            if (checkKnob(knobA, event)) {
                return;
            }
            if (checkKnob(knobB, event)) {
                return;
            }
            if (checkKnob(knobC, event)) {
                return;
            }
        }
    }

    private boolean checkKnob(final Knob knob, PointerEvent event) {
        if (knob.collide(event.getX(), event.getY()) && !knob.isRotating()) {
            lastRotation = event.getTimestamp();

            Jukebox.playKnob();
            knob.rotateAnimation(new OnCompleteListener() {
                @Override
                public void onComplete(long l) {
                    knob.finishRotation();
                    updateWires();
                }
            });
            return true;
        }
        return false;
    }

    private void updateWires() {
        redCircuit.updateWires();
        blackCircuit.updateWires();
    }

}
