package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.object.base.BaseObject;
import com.harium.annebeth.aspell.object.base.PickupableObject;
import com.harium.annebeth.aspell.ui.DialogManager;
import com.harium.annebeth.aspell.ui.InventoryManager;
import com.harium.etyl.layer.ImageLayer;

public class Sock extends PickupableObject {

    public Sock(int x, int y) {
        super(LanguageManager.objectName(Dictionary.SOCK), x, y, 80, 40);
        layer = new ImageLayer("objects/redsock.png");
        inventoryLayer = new ImageLayer("objects/redsock.png");
    }

    @Override
    public void onUse(BaseObject with) {
        if (with.name.equals(LanguageManager.objectName(Dictionary.WASHER))) {
            Washer washer = (Washer) with;
            if (washer.isOpen()) {
                washer.hasSock = true;
                visible = false;
                InventoryManager.remove(this.name);
            } else {
                DialogManager.addDialog("The washer should be open first.");
            }
        }
    }
}
