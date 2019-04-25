package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.object.base.PickupableObject;
import com.harium.annebeth.aspell.ui.DialogManager;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

import static com.harium.annebeth.aspell.ui.SceneManager.ROOM_HEIGHT;
import static com.harium.annebeth.aspell.ui.SceneManager.ROOM_OFFSET;

public class FanSwitch extends PickupableObject {

    public FanSwitch(int x, int y) {
        super(LanguageManager.objectName(Dictionary.FAN_SWITCH), x, y, 20, 28);
        layer = new ImageLayer(x, y, w, h, "objects/fan_switch.png");
        inventoryLayer = new ImageLayer("objects/fan_switch_inv.png");
    }

    @Override
    public void onLook() {
        DialogManager.addDialog("It is just a " + name + " with two spin modes.");
    }
}
