package com.harium.annebeth.aspell.core.object;

import com.harium.annebeth.aspell.core.object.base.DecorativeObject;
import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.etyl.layer.ImageLayer;

public class Table extends DecorativeObject {

    public Table(int x, int y) {
        super(LanguageManager.objectName(Dictionary.TABLE), x, y, 296, 108);
        this.originalY = y;
        layer = new ImageLayer(x, y, w, h, "objects/table.png");
    }

}
