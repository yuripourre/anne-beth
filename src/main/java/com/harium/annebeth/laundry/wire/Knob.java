package com.harium.annebeth.laundry.wire;

import com.harium.etyl.commons.collision.CollisionDetector;
import com.harium.etyl.commons.interpolation.Interpolator;
import com.harium.etyl.core.animation.Animation;
import com.harium.etyl.core.animation.OnCompleteListener;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class Knob {

    public static final int HALF_SIZE = 71;
    private boolean[] connections = new boolean[6];
    private Wire[] wires = new Wire[6];
    private boolean chargedRed = false;
    private boolean chargedBlack = false;

    private static final Wire NULL_WIRE = new Wire();
    private ImageLayer layer;
    private int step = 0;
    private boolean rotating = false;

    public Knob() {
        for (int i = 0; i < wires.length; i++) {
            wires[i] = NULL_WIRE;
        }
    }

    public void rotate() {
        step++;
        step %= connections.length;

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

    public ImageLayer getLayer() {
        return layer;
    }

    public void setLayer(ImageLayer layer) {
        this.layer = layer;
    }

    public void draw(Graphics g) {
        layer.draw(g);
    }

    public boolean collide(int x, int y) {
        return CollisionDetector.collideCirclePoint(layer.getX() + HALF_SIZE, layer.getY() + HALF_SIZE, HALF_SIZE, x, y);
    }

    public void rotateAnimation() {
        if (rotating) {
            return;
        }
        rotating = true;

        float angle = (float) layer.getAngle();

        int interval = (int) ((layer.getAngle() + 60) / 60);
        float nextAngle = interval * 60;

        Animation.animate(layer).rotate(300).from(angle).to(nextAngle).interpolate(Interpolator.REVERSE_QUADRATIC).onFinish(new OnCompleteListener() {
            @Override
            public void onComplete(long l) {
                rotating = false;
                rotate();
            }
        }).start();
    }

    public boolean isRotating() {
        return rotating;
    }
}
