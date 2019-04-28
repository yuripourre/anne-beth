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

public class Shoyu extends PickupableObject {

    private static final int HITBOX_MARGIN = 4;

    public Shoyu(int x, int y) {
        super(LanguageManager.objectName(Dictionary.SHOYU), x - HITBOX_MARGIN, y - HITBOX_MARGIN, 11 + HITBOX_MARGIN * 2, 23 + HITBOX_MARGIN * 2);
        layer = new ImageLayer(x, y, 11, 23, "objects/shoyu.png");
        inventoryLayer = new ImageLayer(x, y, 23, 48, "objects/shoyu_inv.png");
    }

    @Override
    public PickLevel onPickUp() {
        if (SceneManager.isUpsideDown() && !InventoryManager.has("stool")) {
            DialogManager.addDialog("I can't reach it.");
            return PickLevel.NONE;
        } else {
            super.onPickUp();
            if (SceneManager.isUpsideDown()) {
                return PickLevel.HIGH;
            } else {
                return PickLevel.MEDIUM;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        if (!visible) {
            return;
        }
        layer.draw(g, HITBOX_MARGIN, HITBOX_MARGIN);
    }

    @Override
    public void onUse(BaseObject with) {
        if (!inInventory) {
            negativeDialog();
            return;
        }
        if (with.name.equals(LanguageManager.objectName(Dictionary.SOFTENER))) {
            combine((Softener) with);
        } else {
            negativeDialog();
        }
    }

    public void combine(Softener softener) {
        if (softener.hasSalty) {
            DialogManager.addDialog("I think it is salty enough.");
            return;
        }
        Jukebox.playUse();
        softener.hasSalty = true;
        visible = false;
        removeFromInventory();
    }

}
