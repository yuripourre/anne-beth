package com.harium.annebeth.laundry.core.object;

import com.harium.annebeth.core.object.PickLevel;
import com.harium.annebeth.core.object.PickupableObject;
import com.harium.annebeth.core.ui.DialogManager;
import com.harium.annebeth.core.ui.SkillManager;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.layer.ImageLayer;

public class Stool extends PickupableObject {

    public Stool(int x, int y) {
        super(LanguageManager.objectName(Dictionary.STOOL), x, y, 60, 32);
        layer = new ImageLayer(x, y, w, h, "objects/stool.png");
        inventoryLayer = new ImageLayer(x, y, 60, 32, "objects/stool.png");
    }

    @Override
    public PickLevel onPickUp() {
        visible = false;
        SkillManager.pickup(this);
        DialogManager.addDialog(LanguageManager.sentence(Dictionary.SKILL_STOOL));
        Jukebox.playPickup();

        return PickLevel.DOWN;
    }
}
