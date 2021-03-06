package com.harium.annebeth.laundry.object;

import com.harium.annebeth.core.object.BaseObject;
import com.harium.annebeth.core.object.PickLevel;
import com.harium.annebeth.core.object.PickupableObject;
import com.harium.annebeth.core.ui.DialogManager;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.layer.ImageLayer;

public class Lemon extends PickupableObject {

    boolean picked = false;

    public Lemon(int x, int y) {
        super(LanguageManager.objectName(Dictionary.LEMON), x, y, 27, 23);
        layer = new ImageLayer(x, y, w, h, "objects/lemon.png");
        inventoryLayer = new ImageLayer(0, 0, 27, 23, "objects/lemon.png");
    }

    public Lemon() {
        this(0, 0);
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
        if (softener.hasAcid) {
            DialogManager.addDialog("I don't think it needs more acid.");
            return;
        }
        Jukebox.playUse();
        softener.hasAcid = true;
        visible = false;
        removeFromInventory();
    }

    @Override
    public PickLevel onPickUp() {
        super.onPickUp();
        picked = true;
        return PickLevel.DOWN;
    }

    @Override
    public void turnUpsideDown() {
        //super.turnUpsideDown();
    }

    @Override
    public void turnNormal() {
        //super.turnNormal();
    }

    public boolean isPicked() {
        return picked;
    }
}
