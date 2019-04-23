package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.object.base.BaseObject;
import com.harium.annebeth.aspell.object.base.OpenableObject;
import com.harium.annebeth.aspell.ui.DialogManager;
import com.harium.etyl.layer.ImageLayer;

public class Washer extends OpenableObject {

    boolean hasSock = false;
    boolean hasPile = false;
    boolean hasSoap = false;
    boolean hasSoftener = false;

    public Washer(int x, int y) {
        super(LanguageManager.objectName(Dictionary.WASHER), x, y, 80, 96);
        layer = new ImageLayer("objects/washer.png");
        openLayer = new ImageLayer("objects/washer_open.png");
    }

    @Override
    public void onUse(BaseObject with) {
        if (!hasSock || !hasPile || !hasSoftener || !hasSoap) {
            DialogManager.addDialog("It's not ready yet.");
        } else {
            if (isOpen()) {
                DialogManager.addDialog("It should be closed first.");
            } else {
                // TODO Special Effects
                DialogManager.addDialog("Uh oh, this is strange.");
            }
        }
    }
}
