package com.harium.annebeth.laundry.object;

import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.core.object.DecorativeObject;
import com.harium.annebeth.core.object.PickLevel;
import com.harium.annebeth.core.ui.DialogManager;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.layer.ImageLayer;

public class Table extends DecorativeObject {

    public Table(int x, int y) {
        super(LanguageManager.objectName(Dictionary.TABLE), x, y, 262, 108);
        this.originalY = y;
        layer = new ImageLayer(x, y, w, h, "objects/table.png");
    }

    @Override
    public PickLevel onPickUp() {
        Jukebox.playCannot();
        dialog(LanguageManager.sentence(Dictionary.DOESNT_FIT_POCKET));

        return PickLevel.NONE;
    }
}
