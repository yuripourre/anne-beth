package com.harium.annebeth.aspell.core.object;

import com.harium.annebeth.aspell.core.ui.SceneManager;
import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.core.object.base.BaseObject;
import com.harium.annebeth.aspell.core.object.base.PickupableObject;
import com.harium.annebeth.aspell.sound.Jukebox;
import com.harium.annebeth.aspell.core.ui.DialogManager;
import com.harium.annebeth.aspell.core.ui.InventoryManager;
import com.harium.etyl.core.context.Scene;
import com.harium.etyl.layer.ImageLayer;

public class FanSwitch extends PickupableObject {

    boolean picked = false;

    public FanSwitch(int x, int y) {
        super(LanguageManager.objectName(Dictionary.FAN_SWITCH), x, y, 20, 28);
        layer = new ImageLayer(x, y, w, h, "objects/fan_switch.png");
        inventoryLayer = new ImageLayer("objects/fan_switch_inv.png");
    }

    @Override
    public void onLook() {
        DialogManager.addDialog("A " + name + " with two spin modes.");
    }

    @Override
    public void onUse(BaseObject with) {
        /*if (!picked) {
            DialogManager.addDialog("The fan is broken.");
        } else */if (with.name.equals(LanguageManager.objectName(Dictionary.WASHER))) {
            Washer washer = (Washer) with;
            Jukebox.playUse();
            washer.hasSwitch = true;
            visible = false;
            InventoryManager.remove(this.name);
        }
    }

    @Override
    public void onPickUp() {
        if (SceneManager.normalWorld) {
            negativeDialog();
            return;
        }
        super.onPickUp();
        picked = true;
    }
}
