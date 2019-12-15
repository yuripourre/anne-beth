package com.harium.annebeth.laundry.object;

import com.harium.annebeth.core.object.DecorativeObject;
import com.harium.annebeth.core.object.PickLevel;
import com.harium.annebeth.core.ui.DialogManager;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.layer.ImageLayer;

import static com.harium.annebeth.laundry.i18n.Dictionary.*;

public class Cactus extends DecorativeObject {

    boolean looked = false;

    public Cactus(int x, int y) {
        super(LanguageManager.objectName(Dictionary.CACTUS), x, y, 88, 165);
        layer = new ImageLayer(x, y, w, h, "objects/cactus.png");
    }

    @Override
    public void onLook() {
        super.onLook();
        if (!looked) {
            looked = true;
            DialogManager.addDialog(LanguageManager.sentence(CACTUS_HAS_FLOWER, this));
        }
    }

    @Override
    public PickLevel onPickUp() {
        Jukebox.playCannot();
        DialogManager.addDialog(LanguageManager.objectName(Dictionary.CACTUS_CANT_PICK));
        return PickLevel.NONE;
    }
}
