package com.harium.macbeth.aspell.ui;

import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.macbeth.aspell.core.Context;

import static com.harium.macbeth.aspell.core.Interaction.*;

public class UIManager {

    private ActionButton open;
    private ActionButton close;
    private ActionButton lookat;
    private ActionButton use;
    private ActionButton pickup;
    private ActionButton pull;

    public UIManager() {
        int x = 38;
        int y = 424;
        int ox = 178;
        int oy = 70;

        open = new ActionButton(OPEN, x, y);
        close = new ActionButton(CLOSE, x, y + oy);
        lookat = new ActionButton(LOOK_AT, x + ox, y);
        use = new ActionButton(USE, x + ox, y + oy);
        pickup = new ActionButton(PICK_UP, x + ox * 2, y);
        pull = new ActionButton(PULL, x + ox * 2, y + oy);
    }

    public void draw(Graphics g) {
        open.draw(g);
        close.draw(g);
        lookat.draw(g);
        use.draw(g);
        pickup.draw(g);
        pull.draw(g);
    }

    public void updateMouse(PointerEvent event) {
        checkOpen(open, event);
        checkOpen(close, event);
        checkOpen(lookat, event);
        checkOpen(use, event);
        checkOpen(pickup, event);
        checkOpen(pull, event);
    }

    private void checkOpen(ActionButton object, PointerEvent event) {
        int x = object.layer.getX();
        int y = object.layer.getY();
        int w = object.layer.getW();
        int h = object.layer.getH();

        if (x < event.getX() && x + w > event.getX() &&
                y < event.getY() && y + h > event.getY()) {
            Context.interaction = object.interaction;
        }
    }
}
