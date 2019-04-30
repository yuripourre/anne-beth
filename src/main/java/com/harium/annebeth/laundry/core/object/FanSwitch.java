package com.harium.annebeth.laundry.core.object;

import com.harium.annebeth.laundry.core.object.base.BaseObject;
import com.harium.annebeth.laundry.core.object.base.PickLevel;
import com.harium.annebeth.laundry.core.object.base.PickupableObject;
import com.harium.annebeth.laundry.core.ui.DialogManager;
import com.harium.annebeth.laundry.core.ui.InventoryManager;
import com.harium.annebeth.laundry.core.ui.SceneManager;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.laundry.i18n.LanguageManager;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class FanSwitch extends PickupableObject {

    private static final int HITBOX_MARGIN = 4;
    boolean picked = false;

    public FanSwitch(int x, int y) {
        super(LanguageManager.objectName(Dictionary.FAN_SWITCH), x - HITBOX_MARGIN, y - HITBOX_MARGIN, 20 + HITBOX_MARGIN * 2, 28 + HITBOX_MARGIN * 2);
        layer = new ImageLayer(x, y, 20, 28, "objects/fan_switch.png");
        inventoryLayer = new ImageLayer(0, 0, 57, 70, "objects/fan_switch_inv.png");
    }

    @Override
    public void draw(Graphics g) {
        if (!visible) {
            return;
        }
        layer.draw(g, HITBOX_MARGIN, HITBOX_MARGIN);
    }

    @Override
    public void onLook() {
        DialogManager.addDialog(LanguageManager.sentence(Dictionary.FAN_SWITCH_LOOK_AT));
    }

    @Override
    public void onUse(BaseObject with) {
        /*if (!picked) {
            DialogManager.addDialog("The fan is broken.");
        } else */
        if (with.name.equals(LanguageManager.objectName(Dictionary.WASHER))) {
            Washer washer = (Washer) with;
            Jukebox.playUse();
            washer.hasSwitch = true;
            visible = false;
            InventoryManager.remove(this.name);
        }
    }

    @Override
    public PickLevel onPickUp() {
        if (SceneManager.normalWorld) {
            negativeDialog();
            return PickLevel.NONE;
        }
        super.onPickUp();
        picked = true;
        return PickLevel.MEDIUM;
    }
}
