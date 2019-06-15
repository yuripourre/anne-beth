package com.harium.annebeth.laundry.object;

import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.core.object.DecorativeObject;
import com.harium.etyl.layer.ImageLayer;

public class Trash extends DecorativeObject {

    public Trash(int x, int y) {
        super(LanguageManager.objectName(Dictionary.TRASH), x, y, 52, 64);
        this.originalY = y;
        layer = new ImageLayer(x, y, w, h, "objects/trash.png");
    }

}
