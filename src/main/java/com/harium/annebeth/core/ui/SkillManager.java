package com.harium.annebeth.core.ui;

import com.harium.annebeth.core.Context;
import com.harium.annebeth.core.object.PickupableObject;
import com.harium.annebeth.core.ui.skill.SkillSlot;
import com.harium.etyl.core.graphics.Graphics;

public class SkillManager {

    static int usedSlots = 0;

    static SkillSlot[] slots;

    public SkillManager(int w, int h) {
        slots = new SkillSlot[1];
        //slots[0] = new SkillSlot(w / 2 - 96 / 2, 16);
        slots[0] = new SkillSlot(16, 16);
    }

    public static void pickup(PickupableObject object) {
        object.visible = false;
        object.inInventory = true;
        slots[usedSlots].setObject(object);
        usedSlots++;
        // Reset Object
        Context.resetObject();
    }

    public static boolean has(String item) {
        for (SkillSlot slot : slots) {
            if (slot.object != null) {
                if (slot.object.name.equals(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void draw(Graphics g) {
        int count = 0;
        for (SkillSlot button : slots) {
            button.draw(g, 0, 0);
            if (count < usedSlots) {
                // Draw slot content
                button.drawObject(g, 0, 0);
            }
            count++;
        }
    }

}
