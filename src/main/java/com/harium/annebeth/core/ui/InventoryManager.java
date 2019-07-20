package com.harium.annebeth.core.ui;

import com.harium.annebeth.core.object.PickupableObject;
import com.harium.annebeth.core.ui.inventory.InventoryButton;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.event.PointerState;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class InventoryManager {

    public static final int INVENTORY_BAR_Y = 410;

    private static int offsetY = 0;
    private static int cursor = 0;

    private static final int ROW = 8;
    private static final int ROW_OFFSET = InventoryButton.SIZE + 6;
    private static final int ARROW_X = 542;
    private static final int x = 12;
    private static final int y = 430;
    private final int w;
    private final int h;

    private static List<InventoryButton> slots = new ArrayList<InventoryButton>(3);
    private static int usedSlots = 0;

    public static boolean shouldRemove = false;
    private ActionUIManager actionUIManager;
    //private MagnifyingGlass magnifyingGlass;

    public InventoryManager(int w, int h, ActionUIManager actionUIManager) {
        this.w = w;
        this.h = h;
        this.actionUIManager = actionUIManager;

        for (int i = 0; i < ROW; i++) {
            addSlot();
        }

        // Start the game with a glass
        /*magnifyingGlass = new MagnifyingGlass();
        pickup(magnifyingGlass);*/
    }

    public void update(long now) {
        if (shouldRemove) {

            Iterator<InventoryButton> iterator = slots.iterator();
            while (iterator.hasNext()) {
                InventoryButton button = iterator.next();
                if (button.object != null) {
                    if (button.object.shouldRemove) {
                        iterator.remove();
                        usedSlots--;
                    }
                }
            }
            updateSlotPositions();
            shouldRemove = false;
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
        object.visible = false;
        object.inInventory = true;
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

    public static boolean has(String item) {
        for (InventoryButton button : slots) {
            if (button.object != null) {
                if (button.object.name.equals(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String get(int index) {
        if (slots.get(index).object != null) {
            return slots.get(index).object.name;
        }

        return "";
    }

    public static void remove(String item) {
        Iterator<InventoryButton> iterator = slots.iterator();
        while (iterator.hasNext()) {
            InventoryButton button = iterator.next();
            if (button.object != null) {
                if (button.object.name.equals(item)) {
                    iterator.remove();
                    usedSlots--;
                    break;
                }
            }
        }

        // Update slot positions
        updateSlotPositions();
    }

    private static void updateSlotPositions() {
        Iterator<InventoryButton> iterator;
        int ox = ROW_OFFSET;
        int oy = ox;
        int p = 0;

        iterator = slots.iterator();
        while (iterator.hasNext()) {
            InventoryButton button = iterator.next();

            int i = p % ROW;
            int j = p / ROW;

            button.updatePosition(x + ox * i, y + oy * j);

            p++;
        }

        // Fill Row
        if (slots.size() < ROW) {
            addSlot();
        } else if (slots.size() % ROW == 0) {
            offsetY = 0;
            cursor = 0;
        }
    }

    public void draw(Graphics g) {
        drawBackground(g);
        /*if (usedSlots > ROW) {
            if (cursor > 0) {
                upArrow.simpleDraw(g);
            }
            if ((cursor + 1) * ROW < usedSlots) {
                downArrow.simpleDraw(g);
            }
        }*/

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
        /*if (event.getX() < ARROW_X) {
            return;
        }*/

        if (event.getState() == PointerState.PRESSED) {
            /*if (usedSlots > ROW) {
                if (checkCollideArrow(upArrow, event)) {
                    if (cursor > 0) {
                        offsetY += ROW_OFFSET;
                        cursor--;
                    }
                } else if (checkCollideArrow(downArrow, event)) {
                    if ((cursor + 1) * ROW < usedSlots) {
                        offsetY -= ROW_OFFSET;
                        cursor++;
                    }
                }
            }*/

            Iterator<InventoryButton> it = slots.iterator();
            while (it.hasNext()) {
                InventoryButton button = it.next();
                /*if (button.object!=null) {
                    if (button.object.shouldRemove) {
                        it.remove();
                        continue;
                    }
                }*/

                if (button.layer.getY() < y) {
                    continue;
                }
                checkCollide(button, event);
            }
        }
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(0, INVENTORY_BAR_Y, 1024, 576 - INVENTORY_BAR_Y);
    }

    private void checkCollide(InventoryButton button, PointerEvent event) {
        if (button.object != InventoryButton.NULL_PICKABLE && checkCollide(button.layer, event)) {
            int cx = button.getLayer().getX() + button.layer.getW()/2;
            actionUIManager.openInventoryMenu(ActionUIManager.player, button.object, cx, button.layer.getY());

            //ActionUIManager.defineTarget(ActionUIManager.player, Context.getObject());

            /*if (Context.getInteraction() == Interaction.LOOK_AT) {
                button.object.onLook();
                Context.reset();
            } else if (Context.getInteraction() == Interaction.USE_WITH) {
                // Combine
                Context.changeObject(button.object);
                Context.reachObject(null);
            } else {
                Context.setInteraction(Interaction.USE_WITH);
                Context.changeObject(button.object);
            }*/

            /*if (button.object == magnifyingGlass) {
                Context.interaction = Interaction.LOOK_AT;
                if (Context.hasObject()) {
                    ActionUIManager.defineTarget(ActionUIManager.player, Context.getObject());
                    ActionUIManager.hideMenu();
                }
            } else {
                if (Context.getInteraction() == Interaction.LOOK_AT) {
                    button.object.onLook();
                    Context.reset();
                } else if (Context.getInteraction() == Interaction.USE_WITH) {
                    // Combine
                    Context.changeObject(button.object);
                    Context.reachObject(null);
                } else {
                    Context.setInteraction(Interaction.USE_WITH);
                    Context.changeObject(button.object);
                }
            }*/

            /*if (Context.interaction == Interaction.LOOK_AT) {
                Context.object = button.object;
                Context.reachObject(null);
            } else {
                if (Context.interaction == Interaction.USE && Context.object != Context.NULL_OBJECT) {
                    Context.with = button.object;
                    Context.reachObject(null);
                } else {
                    Context.interaction = Interaction.USE;
                    Context.object = button.object;
                }
            }*/
        }
    }

    private boolean checkCollideArrow(ImageLayer layer, PointerEvent event) {
        int x = layer.getX();
        int y = layer.getY();
        int w = layer.getW();
        int h = layer.getH();

        return (x < event.getX() && x + w > event.getX() &&
                y < event.getY() && y + h > event.getY());
    }

    private boolean checkCollide(ImageLayer layer, PointerEvent event) {
        int x = layer.getX();
        int y = layer.getY() - (ROW_OFFSET * cursor);
        int w = layer.getW();
        int h = layer.getH();

        return (x < event.getX() && x + w > event.getX() &&
                y < event.getY() && y + h > event.getY());
    }
}
