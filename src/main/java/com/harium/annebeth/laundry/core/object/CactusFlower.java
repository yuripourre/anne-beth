package com.harium.annebeth.laundry.core.object;

import com.harium.annebeth.laundry.core.object.base.BaseObject;
import com.harium.annebeth.laundry.core.object.base.PickLevel;
import com.harium.annebeth.laundry.core.object.base.PickupableObject;
import com.harium.annebeth.laundry.core.ui.DialogManager;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.laundry.i18n.LanguageManager;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class CactusFlower extends PickupableObject {

    private Cactus cactus;

    public CactusFlower(Cactus cactus) {
        super(LanguageManager.objectName(Dictionary.CACTUS_FLOWER), cactus.x + 58, cactus.y + 8, 24, 12);
        this.cactus = cactus;
        layer = new ImageLayer(x, y, w, h, "objects/cactus_flower.png");
        inventoryLayer = new ImageLayer("objects/cactus_flower_inv.png");
    }

    @Override
    public void draw(Graphics g) {
        if (!visible || !cactus.looked) {
            return;
        }
        super.draw(g);
    }

    @Override
    public void turnNormal() {
        super.turnNormal();
        layer.setLocation(cactus.x + 58, cactus.y + 8);
    }

    @Override
    public void turnUpsideDown() {
        super.turnUpsideDown();
        layer.setLocation(cactus.x - 58, cactus.y - 8);
    }

    @Override
    public void onUse(BaseObject with) {
        if (!inInventory) {
            negativeDialog();
            return;
        }
        if (with.name.equals(LanguageManager.objectName(Dictionary.SOFTENER))) {
            Softener softener = (Softener) with;
            combine(softener);
        } else {
            negativeDialog();
        }
    }

    public void combine(Softener softener) {
        if (softener.hasFlower) {
            DialogManager.addDialog("I don't think it needs more flowers.");
            return;
        }
        Jukebox.playUse();
        softener.hasFlower = true;
        visible = false;
        removeFromInventory();
    }

    @Override
    public PickLevel onPickUp() {
        super.onPickUp();
        return PickLevel.HIGH;
    }
}
