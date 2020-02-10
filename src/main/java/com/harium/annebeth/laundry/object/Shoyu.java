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

public class Shoyu extends PickupableObject {

    public Shoyu(int x, int y) {
        super(LanguageManager.objectName(Dictionary.SHOYU), x, y, 11, 23, 23, 48);
        layer = new ImageLayer(x, y, 11, 23, "objects/shoyu.png");
        inventoryLayer = new ImageLayer(x, y, 23, 48, "objects/shoyu_inv.png");
        border = 12;
    }

    @Override
    public PickLevel onPickUp() {
        if (SceneManager.isUpsideDown()) {
            if (!InventoryManager.has(LanguageManager.objectName(Dictionary.STOOL))) {
                DialogManager.addDialog(LanguageManager.sentence(Dictionary.CANT_REACH));
                return PickLevel.NONE;
            }
        }
        return super.onPickUp();
    }

    @Override
    public void draw(Graphics g) {
        if (!visible) {
            return;
        }
        layer.draw(g);
    }

    @Override
    public void onUse(BaseObject with) {
        if (!inInventory) {
            negativeDialog();
            return;
        }
        if (with.getName().equals(LanguageManager.objectName(Dictionary.SOFTENER))) {
            combine((Softener) with);
        } else {
            cantUse();
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
