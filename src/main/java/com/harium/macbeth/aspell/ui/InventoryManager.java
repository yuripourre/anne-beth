package com.harium.macbeth.aspell.ui;

import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.macbeth.aspell.core.Context;
import com.harium.macbeth.aspell.core.Interaction;
import com.harium.macbeth.aspell.object.PickupableObject;

public class InventoryManager {

    private static InventoryButton[] slots;

    private static int usedSlots = 0;

    {
        slots = new InventoryButton[3];
        int x = 588;
        int y = 424;
        int ox = 148;

        for (int i = 0; i < 3; i++) {
            slots[i] = new InventoryButton(x + ox * i, y);
        }
    }

    public static void pickup(PickupableObject object) {
        InventoryButton slot = nextSlot();
        slot.setObject(object);
        usedSlots++;
    }

    private static InventoryButton nextSlot() {
        return slots[usedSlots];
    }

    public void draw(Graphics g) {
        int count = 0;
        for (InventoryButton button : slots) {
            button.draw(g);
            if (count < usedSlots) {
                // Draw slot content
                button.drawObject(g);
            }
            count++;
        }
    }

    public void updateMouse(PointerEvent event) {
        for (InventoryButton button : slots) {
            checkCollide(button, event);
        }
    }

    private void checkCollide(InventoryButton button, PointerEvent event) {
        int x = button.layer.getX();
        int y = button.layer.getY();
        int w = button.layer.getW();
        int h = button.layer.getH();

        if (x < event.getX() && x + w > event.getX() &&
                y < event.getY() && y + h > event.getY()) {
            Context.interaction = Interaction.USE;
            Context.object = button.object;
        }
    }
}
