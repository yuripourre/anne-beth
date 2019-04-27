package com.harium.annebeth.aspell.core.object;

import com.harium.annebeth.aspell.core.object.base.BaseObject;
import com.harium.annebeth.aspell.core.object.base.PickupableObject;
import com.harium.annebeth.aspell.core.ui.DialogManager;
import com.harium.annebeth.aspell.core.ui.InventoryManager;
import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.sound.Jukebox;
import com.harium.etyl.layer.ImageLayer;

public class Lemon extends PickupableObject {

    public Lemon(int x, int y) {
        super(LanguageManager.objectName(Dictionary.LEMON), x, y, 27, 23);
        layer = new ImageLayer(x, y, w, h, "objects/lemon.png");
        inventoryLayer = new ImageLayer("objects/lemon.png");
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
        if (with.name.equals(LanguageManager.objectName(Dictionary.SOFTENER))) {
            combine((Softener) with);
        } else {
            negativeDialog();
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
    public void turnUpsideDown() {
        //super.turnUpsideDown();
    }

    @Override
    public void turnNormal() {
        //super.turnNormal();
    }
}
