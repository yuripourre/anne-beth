package com.harium.annebeth.laundry.object;

import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.core.object.BaseObject;
import com.harium.annebeth.core.object.HighObject;
import com.harium.annebeth.core.ui.DialogManager;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class CactusFlower extends HighObject {

    private Cactus cactus;

    public CactusFlower() {
        super(LanguageManager.objectName(Dictionary.CACTUS_FLOWER), 0, 0, 40, 20, 65, 80);
        layer = new ImageLayer(x, y, 40, 20, "objects/cactus_flower.png");
        inventoryLayer = new ImageLayer(x, y, 65, 80, "objects/cactus_flower_inv.png");
    }

    public CactusFlower(Cactus cactus) {
        super(LanguageManager.objectName(Dictionary.CACTUS_FLOWER), cactus.x + 50, cactus.y + 4, 40, 20, 65, 80);
        this.cactus = cactus;
        layer = new ImageLayer(x, y, 40, 20, "objects/cactus_flower.png");
        inventoryLayer = new ImageLayer(x, y, 65, 80, "objects/cactus_flower_inv.png");
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
        if (with.getName().equals(LanguageManager.objectName(Dictionary.SOFTENER))) {
            Softener softener = (Softener) with;
            combine(softener);
        } else {
            cantUse();
        }
    }

    public void combine(Softener softener) {
        if (softener.hasFlower) {
            // Should never happen
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.ENOUGH_FLOWERS));
            return;
        }
        Jukebox.playUse();
        softener.hasFlower = true;
        visible = false;
        removeFromInventory();
    }

}
