package com.harium.annebeth.laundry.wire;

import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.animation.OnCompleteListener;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

import java.util.ArrayList;
import java.util.List;

public class WirePuzzle extends Application {

    private ImageLayer background;
    private Knob source;
    private Knob knobA;
    private Knob knobB;
    private Knob knobC;
    private Knob end;

    private WireRed sourceA;
    private WireRed abR;
    private WireRed sourceBRed;
    private WireRed bEnd;

    private List<Wire> wires = new ArrayList<>();

    public WirePuzzle(int w, int h) {
        super(w, h);
    }

    public void load() {
        Jukebox.init();
        background = new ImageLayer("wire/circuit_bg.png");

        initKnobs();
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

        sourceA = new WireRed(source, 2, knobA, 0);
        sourceA.setOn(new ImageLayer(240, 102, "wire/sa_on.png"));
        sourceA.setOff(new ImageLayer(240, 102, "wire/sa_off.png"));

        sourceBRed = new WireRed(source, 3, knobB, 0);
        sourceBRed.setOn(new ImageLayer(324, 273, "wire/wire_h_on.png"));
        sourceBRed.setOff(new ImageLayer(324, 273, "wire/wire_h_off.png"));

        bEnd = new WireRed(knobB, 3, end, 0);
        bEnd.setOn(new ImageLayer(602, 273, "wire/wire_h_on.png"));
        bEnd.setOff(new ImageLayer(602, 273, "wire/wire_h_off.png"));

        abR = new WireRed(knobA, 4, knobB, 2);
        abR.setOn(new ImageLayer(518,103, "wire/abr_on.png"));
        abR.setOff(new ImageLayer(518,103, "wire/abr_off.png"));
    }

    public void draw(Graphics g) {
        background.simpleDraw(g);
        drawWires(g);
        drawKnobs(g);
    }

    private void drawWires(Graphics g) {
        sourceA.draw(g);
        sourceBRed.draw(g);
        abR.draw(g);
        bEnd.draw(g);
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
        //source.updateWires();

        if (sourceA.wire.isConnected()) {
            knobA.setChargedRed(true);

            if (abR.wire.isConnected()) {
                abR.wire.setCharged(true);
            } else {
                abR.wire.setCharged(false);
            }
        } else {
            knobA.setChargedRed(false);
        }

        if (abR.wire.isCharged()) {
            if (knobA.isChargedRed()) {
                knobB.setChargedRed(true);
            }

            if (abR.wire.isConnected()) {
                bEnd.wire.setCharged(true);
            }
        }

        /*if (sourceBRed.wire.isConnected()) {
            if (bEnd.wire.isConnected()) {
                bEnd.wire.setCharged(true);
            } else {
                bEnd.wire.setCharged(false);
            }
        }*/
    }


}
