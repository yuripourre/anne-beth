package com.harium.annebeth.laundry.wire;

import com.harium.etyl.commons.collision.CollisionDetector;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.commons.interpolation.Interpolator;
import com.harium.etyl.core.animation.Animation;
import com.harium.etyl.core.animation.OnCompleteListener;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class Knob {

    private static final long DURATION = 300;
    public static final int HALF_SIZE = 71;
    private boolean[] connections = new boolean[6];

    private boolean chargedRed = false;
    private boolean chargedBlack = false;

    private static final Wire NULL_WIRE = new Wire();
    private ImageLayer layer;
    private int step = 0;
    private boolean rotating = false;
    float nextAngle = 0;

    public Knob() {}

    public void rotate() {
        step++;
        step %= connections.length;

        boolean last = connections[connections.length-1];
        for (int i = connections.length-1; i > 0; i--) {
            connections[i] = connections[i-1];
        }
        connections[0] = last;
    }

    public void setConnections(boolean... values) {
        int count = 0;
        for (boolean b : values) {
            this.connections[count] = b;
            count++;
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

    public ImageLayer getLayer() {
        return layer;
    }

    public void setLayer(ImageLayer layer) {
        this.layer = layer;
    }

    public void draw(Graphics g) {
        layer.draw(g);

        int wireRadius = 6;
        int offsetX = -70;
        int lx = layer.getX();
        int ly = layer.getY();
        int cx = lx + layer.getW();
        int cy = ly + layer.getH();

        // Upper Wires
        color(g, 0);
        g.fillCircle(cx - 71 + offsetX, cy - 71, wireRadius);
        color(g, 1);
        g.fillCircle(cx - 42 + offsetX, ly - 8, wireRadius);
        color(g, 2);
        g.fillCircle(cx + 42 + offsetX, ly - 8, wireRadius);
        color(g, 3);
        g.fillCircle(cx + 71 + offsetX, cy - 71, wireRadius);
        color(g, 4);
        g.fillCircle(cx + 42 + offsetX, cy, wireRadius);
        color(g, 5);
        g.fillCircle(cx - 42 + offsetX, cy, wireRadius);

    }

    private void color(Graphics g, int index) {
        if (connections[index]) {
            g.setColor(Color.CYAN);
        } else {
            g.setColor(Color.BLACK);
        }
    }

    public boolean collide(int x, int y) {
        return CollisionDetector.collideCirclePoint(layer.getX() + HALF_SIZE, layer.getY() + HALF_SIZE, HALF_SIZE, x, y);
    }

    public void rotateAnimation(OnCompleteListener onCompleteListener) {
        if (rotating) {
            return;
        }

        rotating = true;

        float angle = (float) layer.getAngle();
        int interval = (int) ((layer.getAngle() + 60) / 60);
        nextAngle = interval * 60;

        Animation.animate(layer).rotate().during(DURATION).from(angle).to(nextAngle).interpolate(Interpolator.REVERSE_QUADRATIC).onFinish(onCompleteListener).start();
    }

    public void finishRotation() {
        layer.setAngle(nextAngle);
        rotate();
        rotating = false;
    }

    public boolean isRotating() {
        return rotating;
    }
}
