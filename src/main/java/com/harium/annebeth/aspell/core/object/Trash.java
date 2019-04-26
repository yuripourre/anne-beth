package com.harium.annebeth.aspell.core.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.core.object.base.DecorativeObject;
import com.harium.etyl.layer.ImageLayer;

public class Trash extends DecorativeObject {

    public Trash(int x, int y) {
        super(LanguageManager.objectName(Dictionary.TRASH), x, y, 148, 52);
        this.originalY = y;
        layer = new ImageLayer(x, y, w, h, "objects/trash.png");
    }

}
