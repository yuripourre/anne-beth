package com.harium.annebeth.aspell.ui;

import com.harium.annebeth.aspell.core.Context;
import com.harium.annebeth.aspell.core.Interaction;
import com.harium.annebeth.aspell.object.PickupableObject;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.event.PointerState;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {

    private static final int ROW = 3;
    public static final int ROW_OFFSET = 138;
    private int offsetY = 0;
    private int cursor = 0;

    private static final int ARROW_X = 542;
    private static final int x = 598;
    private static final int y = 424;

    private ImageLayer upArrow;
    private ImageLayer downArrow;
    private static List<InventoryButton> slots = new ArrayList<InventoryButton>(3);
    private static int usedSlots = 0;

    public InventoryManager() {
        upArrow = new ImageLayer(ARROW_X, 420, 48, 64, "ui/arrow_up.png");
        downArrow = new ImageLayer(ARROW_X, 490, 48, 64, "ui/arrow_down.png");

        for (int i = 0; i < ROW; i++) {
            addSlot();
        }
    }

    private static void addSlot() {
        int ox = ROW_OFFSET;
        int oy = ox;

        int i = slots.size() % ROW;
        int j = slots.size() / ROW;

        slots.add(new InventoryButton(x + ox * i, y + oy * j));
    }

    public static void pickup(PickupableObject object) {
        InventoryButton slot = nextSlot();
        slot.setObject(object);
        usedSlots++;
    }

    private static InventoryButton nextSlot() {
        if (usedSlots == slots.size()) {
            addSlot();
        }

        return slots.get(usedSlots);
    }

    public void draw(Graphics g) {

        if (usedSlots > ROW) {
            if (cursor > 0) {
                upArrow.simpleDraw(g);
            }
            if ((cursor+1) * ROW < usedSlots) {
                downArrow.simpleDraw(g);
            }
        }

        int count = 0;
        for (InventoryButton button : slots) {
            button.draw(g, 0, offsetY);
            if (count < usedSlots) {
                // Draw slot content
                button.drawObject(g, 0, offsetY);
            }
            count++;
        }
    }

    public void updateMouse(PointerEvent event) {
        if (event.getX() < ARROW_X) {
            return;
        }

        if (event.getState() == PointerState.PRESSED) {
            if (usedSlots > ROW) {
                if (checkCollide(upArrow, event)) {
                    if (cursor > 0) {
                        offsetY += ROW_OFFSET;
                        cursor--;
                    }
                } else if (checkCollide(downArrow, event)) {
                    if ((cursor + 1) * ROW < usedSlots) {
                        offsetY -= ROW_OFFSET;
                        cursor++;
                    }
                }
            }

            for (InventoryButton button : slots) {
                if (button.layer.getY() < y) {
                    continue;
                }
                checkCollide(button, event);
            }
        }
    }

    private void checkCollide(InventoryButton button, PointerEvent event) {
        if (checkCollide(button.layer, event)) {
            Context.interaction = Interaction.USE;
            Context.object = button.object;
        }
    }

    private boolean checkCollide(ImageLayer layer, PointerEvent event) {
        int x = layer.getX();
        int y = layer.getY();
        int w = layer.getW();
        int h = layer.getH();

        return (x < event.getX() && x + w > event.getX() &&
                y < event.getY() && y + h > event.getY());
    }
}
