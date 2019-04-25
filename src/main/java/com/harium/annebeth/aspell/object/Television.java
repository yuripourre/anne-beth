package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.object.base.DecorativeObject;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class Television extends DecorativeObject {

    public Television(int x, int y) {
        super(LanguageManager.objectName(Dictionary.TV), x, y, 124, 108);
        this.originalY = y;
        layer = new ImageLayer(x, y, w, h, "objects/television.png");
    }

}
