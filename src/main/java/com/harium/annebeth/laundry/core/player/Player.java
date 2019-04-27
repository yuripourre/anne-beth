package com.harium.annebeth.laundry.core.player;

import com.harium.annebeth.laundry.core.Context;
import com.harium.annebeth.laundry.core.object.base.BaseObject;
import com.harium.annebeth.laundry.core.ui.SceneManager;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.AnimatedLayer;

public class Player {

    public static final int WIDTH = 140;
    public static final int HEIGHT = 190;

    public static final int ANIMATION_SPEED = 130;

    public static final int WALK_SPEED = 4;

    BaseObject targetX = Context.NULL_OBJECT;

    int center;

    AnimatedLayer layer;

    PlayerState state = PlayerState.IDLE;

    SceneManager sceneManager;

    // Pick up animation logic
    boolean countPick = false;
    boolean countStarted = false;
    long pickTime = 0;
    long pickDelay = 800;

    public Player(int x, int y, SceneManager sceneManager) {
        this.sceneManager = sceneManager;
        center = sceneManager.w / 2;
        layer = new AnimatedLayer(center - WIDTH / 2, y, WIDTH, HEIGHT, "player/new-witch.png");
        idle();
    }

    private void idle() {
        state = PlayerState.IDLE;
        layer.setSrcY(0);
        layer.setNeedleY(0);
        layer.setNeedleX(0);
        layer.setSpeed(ANIMATION_SPEED);
        layer.setFrames(1);
        layer.resetAnimation();
    }

    public void pickDown() {
        idle();
        countPick = true;
        pickDelay = 300;
        layer.setSrcX(WIDTH);
        layer.setNeedleX(WIDTH);
        state = PlayerState.PICK_DOWN;
    }

    public void pickMedium() {
        idle();
        countPick = true;
        pickDelay = 200;
        layer.setSrcX(WIDTH * 2);
        layer.setNeedleX(WIDTH * 2);
        state = PlayerState.PICK_MEDIUM;
    }

    public void pickHigh() {
        idle();
        pickDelay = 500;
        countPick = true;
        layer.setSrcX(WIDTH * 3);
        layer.setNeedleX(WIDTH * 3);
        state = PlayerState.PICK_HIGH;
    }

    private void walkRight() {
        state = PlayerState.WALKING_RIGHT;
        layer.setSrcY(HEIGHT);
        layer.setNeedleY(HEIGHT);
        layer.setSpeed(ANIMATION_SPEED);
        layer.setFrames(4);
        layer.resetAnimation();
    }

    private void walkLeft() {
        state = PlayerState.WALKING_LEFT;
        layer.setSrcY(HEIGHT * 2);
        layer.setNeedleY(HEIGHT * 2);
        layer.setSpeed(ANIMATION_SPEED);
        layer.setFrames(4);
        layer.resetAnimation();
    }

    public void update(long now) {
        if (countPick) {
            if (!countStarted) {
                pickTime = now;
                countStarted = true;
            } else {
                if (pickTime + pickDelay < now) {
                    idle();
                    countPick = false;
                    countStarted = false;
                }
            }
        }

        if (targetX != Context.NULL_OBJECT) {
            reachTarget();
        }

        layer.animate(now);
    }

    private void reachTarget() {
        if (state == PlayerState.WALKING_LEFT) {
            if (targetX.centerX() < center && sceneManager.x < 0) {
                offset(WALK_SPEED);
            } else {
                reached();
            }
        } else if (state == PlayerState.WALKING_RIGHT) {
            if (targetX.centerX() > center && sceneManager.x > -1652) {
                offset(-WALK_SPEED);
            } else {
                reached();
            }
        }
    }

    private void offset(int i) {
        sceneManager.offset(i);
    }

    private void reached() {
        // TODO Idle or pick up
        idle();
        targetX = Context.NULL_OBJECT;
        Context.reachObject(this);
    }

    public void setTarget(BaseObject object) {
        if (targetX != object) {
            if (object.centerX() >= sceneManager.w / 2) {
                walkRight();
            } else if (object.centerX() < sceneManager.w / 2) {
                walkLeft();
            }
            targetX = object;
        }
    }

    public void draw(Graphics g) {
        layer.draw(g);
    }

    public PlayerState getState() {
        return state;
    }
}
