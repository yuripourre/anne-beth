package com.harium.annebeth.aspell.player;

import com.harium.annebeth.aspell.core.Context;
import com.harium.annebeth.aspell.object.base.BaseObject;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.AnimatedLayer;

public class Player {
    public static final int WIDTH = 32 * 4;
    public static final int HEIGHT = 52 * 4;

    private static final int UNDEFINED = -1;
    public static final int WALK_SPEED = 4;

    BaseObject targetX = Context.NULL_OBJECT;

    int center = WIDTH / 2;

    AnimatedLayer layer;

    PlayerState state = PlayerState.IDLE;

    public Player(int x, int y) {
        layer = new AnimatedLayer(x, y, WIDTH, HEIGHT, "player/neo-witch-4x.png");
        idle();
    }

    private void idle() {
        state = PlayerState.IDLE;
        layer.setSrcY(0);
        layer.setNeedleY(0);
        layer.setSpeed(200);
        layer.setFrames(1);
        layer.resetAnimation();
    }

    private void walkRight() {
        state = PlayerState.WALKING_RIGHT;
        layer.setSrcY(HEIGHT);
        layer.setNeedleY(HEIGHT);
        layer.setSpeed(200);
        layer.setFrames(4);
        layer.resetAnimation();
    }

    private void walkLeft() {
        state = PlayerState.WALKING_LEFT;
        layer.setSrcY(HEIGHT * 2);
        layer.setNeedleY(HEIGHT * 2);
        layer.setSpeed(200);
        layer.setFrames(4);
        layer.resetAnimation();
    }

    public void update(long now) {
        if (targetX != Context.NULL_OBJECT) {
            reachTarget();
        }
        layer.animate(now);
    }

    private void reachTarget() {
        if (state == PlayerState.WALKING_LEFT) {
            if (layer.getX() + center > targetX.centerX()) {
                layer.offsetX(-WALK_SPEED);
            } else {
                reached();

            }
        } else if (state == PlayerState.WALKING_RIGHT) {
            if (layer.getX() + center < targetX.centerX()) {
                layer.offsetX(WALK_SPEED);
            } else {
                reached();
            }
        }
    }

    private void reached() {
        idle();
        targetX = Context.NULL_OBJECT;
        Context.reachObject();
    }

    public void setTarget(BaseObject object) {
        if (targetX != object) {
            if (object.centerX() > layer.getX()) {
                walkRight();
            } else if (object.centerX() < layer.getX()) {
                walkLeft();
            }
            targetX = object;
        }
    }

    public void draw(Graphics g) {
        layer.draw(g);
    }
}
