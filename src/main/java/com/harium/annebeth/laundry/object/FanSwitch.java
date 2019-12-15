package com.harium.annebeth.laundry.object;

import com.harium.annebeth.core.object.BaseObject;
import com.harium.annebeth.core.object.PickLevel;
import com.harium.annebeth.core.object.PickupableObject;
import com.harium.annebeth.core.ui.DialogManager;
import com.harium.annebeth.core.ui.InventoryManager;
import com.harium.annebeth.core.ui.SceneManager;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class FanSwitch extends PickupableObject {

    boolean picked = false;
    public ImageLayer upsideLayer;

    public FanSwitch(int x, int y) {
        super(LanguageManager.objectName(Dictionary.FAN_SWITCH), x, y, 20, 28, 57, 70);

        layer = new ImageLayer(x, y, 20, 28, "objects/fan_switch.png");
        inventoryLayer = new ImageLayer(0, 0, 57, 70, "objects/fan_switch_inv.png");
        upsideLayer = new ImageLayer(x, y, 20, 28, "objects/fan_switch.png");

        this.border = 10;
    }

    @Override
    public void draw(Graphics g) {
        if (!visible) {
            return;
        }
        if (SceneManager.normalWorld) {
            layer.draw(g);
        } else {
            upsideLayer.draw(g);
        }
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
        if (Washer.isWasher(with)) {
            Washer washer = (Washer) with;
            Jukebox.playUse();
            washer.hasSwitch = true;
            visible = false;
            InventoryManager.remove(this.getName());
        } else {
            cantUse();
        }
    }

    @Override
    public PickLevel onPickUp() {
        if (SceneManager.normalWorld) {
            Jukebox.playCannot();
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.FAN_SWITCH_CANT_PICK));
            return PickLevel.NONE;
        }
        super.onPickUp();
        picked = true;
        return PickLevel.MEDIUM;
    }
}
