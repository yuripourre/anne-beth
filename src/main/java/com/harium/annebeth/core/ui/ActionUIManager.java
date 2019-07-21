package com.harium.annebeth.core.ui;

import com.harium.annebeth.core.Context;
import com.harium.annebeth.core.object.BaseObject;
import com.harium.annebeth.core.object.PickupableObject;
import com.harium.annebeth.core.player.Player;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;

import static com.harium.annebeth.core.Interaction.*;

public class ActionUIManager {

    private int w, h;
    private int BUTTON_WIDTH = 160;
    private int BUTTON_HEIGHT = 50;

    public static Player player;
    private BaseObject object;

    private ActionButton lookat;
    private ActionButton open;
    private ActionButton close;
    private ActionButton use;
    private ActionButton useWith;
    private ActionButton pickup;

    boolean inventory = false;
    private static boolean drawMenu = false;

    public ActionUIManager(int w, int h) {
        this.w = w;
        this.h = h;

        lookat = new ActionButton(LOOK_AT);
        open = new ActionButton(OPEN);
        close = new ActionButton(CLOSE);
        use = new ActionButton(USE);
        useWith = new ActionButton(USE_WITH);
        pickup = new ActionButton(PICK_UP);
    }

    public void openMenu(Player player, BaseObject object) {
        int cx = object.x + object.w / 2;
        int cy = object.y + object.h / 2;

        this.player = player;
        this.object = object;
        drawMenu = true;
        inventory = false;

        int verticalOffset = 74;
        int horizontalOffset = 20;

        useWith.disabled = true;
        if (object.canOpen) {
            use.disabled = false;
            pickup.disabled = true;
            lookat.disabled = false;

            lookat.layer.setLocation(cx - BUTTON_WIDTH / 2, cy - verticalOffset);
            use.layer.setLocation(cx + BUTTON_WIDTH - horizontalOffset * 6, cy + verticalOffset - BUTTON_HEIGHT);

            if (object.isOpen()) {
                open.disabled = true;
                close.disabled = false;
                close.layer.setLocation(cx - BUTTON_WIDTH - horizontalOffset * 2, cy + verticalOffset - BUTTON_HEIGHT);
            } else {
                open.disabled = false;
                close.disabled = true;
                open.layer.setLocation(cx - BUTTON_WIDTH - horizontalOffset * 2, cy + verticalOffset - BUTTON_HEIGHT);
            }
        } else {
            open.disabled = true;
            close.disabled = true;
            use.disabled = true;
            pickup.disabled = false;
            lookat.disabled = false;

            //final int ANIMATION_DELAY = 300;
            //Animation.animate(pickup.layer).move().from(cx-BUTTON_WIDTH/2,cy - BUTTON_HEIGHT / 2).to(cx - BUTTON_WIDTH - horizontalOffset * 2, cy - BUTTON_HEIGHT / 2).during(ANIMATION_DELAY).start();
            //Animation.animate(lookat.layer).move().from(cx-BUTTON_WIDTH/2,cy - BUTTON_HEIGHT / 2).to(cx + BUTTON_WIDTH - horizontalOffset * 6, cy - BUTTON_HEIGHT / 2).during(ANIMATION_DELAY).start();

            //lookat.layer.setLocation(cx - BUTTON_WIDTH / 2, cy - verticalOffset);
            //pickup.layer.setLocation(cx - BUTTON_WIDTH / 2, cy - BUTTON_HEIGHT - horizontalOffset);
            lookat.layer.setLocation(cx + BUTTON_WIDTH - horizontalOffset * 6, cy - BUTTON_HEIGHT / 2);
            pickup.layer.setLocation(cx - BUTTON_WIDTH - horizontalOffset * 2, cy - BUTTON_HEIGHT / 2);
        }
    }

    public void openInventoryMenu(Player player, PickupableObject object, int cx, int cy) {
        this.player = player;
        this.object = object;
        drawMenu = true;
        inventory = true;

        int verticalOffset = 74;
        int horizontalOffset = 20;

        open.disabled = true;
        close.disabled = true;
        pickup.disabled = true;
        use.disabled = true;
        useWith.disabled = false;
        lookat.disabled = false;

        int my = cy - BUTTON_HEIGHT - horizontalOffset*2;

        int offset = 0;
        int ix = cx - BUTTON_WIDTH - horizontalOffset * 2;
        if (ix < -10) {
            offset = 10 - ix;
            lookat.layer.setLocation(offset + cx - BUTTON_WIDTH + horizontalOffset * 2, my);
            useWith.layer.setLocation(offset + cx - BUTTON_WIDTH - horizontalOffset * 2, my - BUTTON_HEIGHT);
        } else if (ix < 10) {
            offset = -ix;
            lookat.layer.setLocation(offset + cx + BUTTON_WIDTH - horizontalOffset * 7, my);
            useWith.layer.setLocation(offset + cx - BUTTON_WIDTH - horizontalOffset, my);
        } else {
            lookat.layer.setLocation(offset + cx + BUTTON_WIDTH - horizontalOffset * 7, my);
            useWith.layer.setLocation(offset + cx - BUTTON_WIDTH - horizontalOffset, my);
        }


    }

    public void draw(Graphics g) {
        if (!drawMenu) {
            return;
        }

        lookat.draw(g);
        pickup.draw(g);
        open.draw(g);
        close.draw(g);
        use.draw(g);
        useWith.draw(g);
    }

    public void updateMouse(PointerEvent event) {
        if (!drawMenu) {
            return;
        }

        /*if (!event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
            return;
        }*/

        boolean collide = false;

        collide |= checkCollide(lookat, event);
        collide |= checkCollide(open, event);
        collide |= checkCollide(close, event);
        collide |= checkCollide(use, event);
        collide |= checkCollide(useWith, event);
        collide |= checkCollide(pickup, event);

        if (!collide) {
            Context.resetObject();
        }

        hideMenu();
    }

    public static void defineTarget(Player player, BaseObject object) {
        player.setTarget(object);
        Context.changeObject(object);
    }

    private boolean checkCollide(ActionButton button, PointerEvent event) {
        if (button.disabled) {
            return false;
        }
        int x = button.layer.getX();
        int y = button.layer.getY();
        int w = button.layer.getW();
        int h = button.layer.getH();

        if (x < event.getX() && x + w > event.getX() &&
                y < event.getY() && y + h > event.getY()) {
            Context.setInteraction(button.interaction);
            if (!inventory) {
                defineTarget(player, object);
            } else {
                //Context.changeObject(object);
                if (button.interaction == LOOK_AT) {
                    object.onLook();
                    Context.setInteraction(MENU);
                }
            }
            return true;
        }

        return false;
    }

    public static void hideMenu() {
        drawMenu = false;
    }

}
