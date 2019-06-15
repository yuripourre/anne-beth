package com.harium.annebeth.core.object;

import com.harium.annebeth.core.ui.DialogManager;
import com.harium.annebeth.core.ui.SkillManager;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.core.i18n.LanguageManager;

public class HighObject extends PickupableObject {

    public HighObject(String name, int x, int y, int w, int h) {
        super(name, x, y, w, h);
    }

    @Override
    public PickLevel onPickUp() {
        if (!SkillManager.has(LanguageManager.objectName(Dictionary.STOOL))) {
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.CANT_REACH));
            return PickLevel.NONE;
        } else {
            super.onPickUp();
        }

        return PickLevel.HIGH;
    }
}
