package com.harium.annebeth.aspell.core.object;

import com.harium.annebeth.aspell.core.object.base.PickupableObject;
import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class CactusFlower extends PickupableObject {

    Cactus cactus;

    public CactusFlower(Cactus cactus) {
        super(LanguageManager.objectName(Dictionary.CACTUS_FLOWER), cactus.x + 58, cactus.y + 8, 27, 23);
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
}
