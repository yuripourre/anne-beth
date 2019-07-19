package com.harium.annebeth.core.player;

import com.harium.annebeth.core.Context;
import com.harium.annebeth.core.object.BaseObject;
import com.harium.annebeth.core.ui.SceneManager;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.AnimatedLayer;

public class Player {

    public static final int WIDTH = 140;
    public static final int HEIGHT = 190;

    public static final int ANIMATION_SPEED = 130;

    public static final int WALK_SPEED = 4;
    public static final int RIGHT_OFFSET = -1652;
    public static final int LEFT_OFFSET = 0;

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
            walkMove();
        }

        layer.animate(now);
    }

    private void walkMove() {
        if (state == PlayerState.WALKING_LEFT) {
            int layerCenter = layer.getX() + WIDTH / 2;
            // Anne Beth at maximum right of screen
            if (sceneManager.x <= RIGHT_OFFSET) {
                // Walk left to be in the default situation
                if (layerCenter < center) {
                    offsetScene(WALK_SPEED);
                } else {
                    layer.offsetX(-WALK_SPEED);
                    if (targetX.centerX() > center) {
                        if (layerCenter < targetX.centerX()) {
                            reached();
                        }
                    }
                }
            } else if (sceneManager.x < LEFT_OFFSET) {// Default Situation
                if (targetX.centerX() < center) {
                    if (layerCenter > center) {
                        layer.offsetX(-WALK_SPEED);
                    } else {
                        offsetScene(WALK_SPEED);
                    }
                } else {
                    reached();
                }

            } else {
                // Anne Beth at maximum left of screen
                if (layerCenter > targetX.centerX()) {
                    layer.offsetX(-WALK_SPEED);
                } else {
                    reached();
                }
            }
        } else if (state == PlayerState.WALKING_RIGHT) {
            int layerCenter = layer.getX() + WIDTH / 2;

            // Anne Beth at maximum right of screen
            if (sceneManager.x <= RIGHT_OFFSET) {
                if (layerCenter < targetX.centerX()) {
                    layer.offsetX(WALK_SPEED);
                } else {
                    reached();
                }
            } else if (sceneManager.x < LEFT_OFFSET) {// Default Situation
                if (targetX.centerX() > center) {
                    if (layerCenter < center) {
                        layer.offsetX(WALK_SPEED);
                    } else {
                        offsetScene(-WALK_SPEED);
                    }
                } else {
                    reached();
                }
            } else {
                // Anne Beth at maximum left of screen
                if (layerCenter > center) {
                    offsetScene(-WALK_SPEED);
                } else {
                    layer.offsetX(WALK_SPEED);
                    if (targetX.centerX() < center) {
                        if (layerCenter > targetX.centerX()) {
                            reached();
                        }
                    }
                }
            }
        }
    }

    private void offsetScene(int i) {
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
            if (object.centerX() >= layer.getX()) {
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

    public PlayerState getState() {
        return state;
    }

    public int getX() {
        return layer.getX();
    }

    public int getY() {
        return layer.getY();
    }

    public void setPosition(int px, int py) {
        layer.setLocation(px, py);
    }
}
