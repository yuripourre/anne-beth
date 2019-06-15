package com.harium.annebeth.laundry.wire;

import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class WirePuzzle extends Application {

    private ImageLayer background;
    private Knob source;
    private Knob knobA;
    private Knob knobB;
    private Knob knobC;
    private Knob end;

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

        knobA = new Knob();
        knobA.setLayer(new ImageLayer(440 + offset, 33 + offset, "wire/knob_a.png"));

        knobB = new Knob();
        knobB.setLayer(new ImageLayer(440 + offset, 213 + offset, "wire/knob_b.png"));

        knobC = new Knob();
        knobC.setLayer(new ImageLayer(440 + offset, 393 + offset, "wire/knob_c.png"));

        end = new Knob();
        end.setLayer(new ImageLayer(714 + offset, 213 + offset, "wire/knob_e.png"));
    }

    public void draw(Graphics g) {
        background.simpleDraw(g);
        drawWires(g);
        drawKnobs(g);
    }

    private void drawWires(Graphics g) {

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

    @Override
    public void updateMouse(PointerEvent event) {
        super.updateMouse(event);

        if (event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
            if(checkKnob(source, event)) {
                return;
            }
            if(checkKnob(end, event)){
                return;
            }
            if(checkKnob(knobA, event)) {
                return;
            }
            if(checkKnob(knobB, event)) {
                return;
            }
            if (checkKnob(knobC, event)) {
                return;
            }
        }
    }

    private boolean checkKnob(Knob knob, PointerEvent event) {
        if (knob.collide(event.getX(), event.getY())) {
            if (!knob.isRotating()) {
                Jukebox.playKnob();
                knob.rotateAnimation();
            }

            return true;
        }
        return false;
    }
}
