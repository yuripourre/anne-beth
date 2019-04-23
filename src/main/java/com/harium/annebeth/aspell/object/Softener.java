package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.object.base.BaseObject;
import com.harium.annebeth.aspell.object.base.HighObject;
import com.harium.annebeth.aspell.ui.DialogManager;
import com.harium.etyl.layer.ImageLayer;

public class Softener extends HighObject {

    private static final int UNUSED = 0;
    private static final int USED = 1;

    boolean hasAcid = false;
    boolean hasSalty = false;
    boolean hasFlower = false;

    int step = UNUSED;

    public Softener(int x, int y) {
        super(LanguageManager.objectName(Dictionary.SOFTENER), x, y, 50, 72);
        layer = new ImageLayer("objects/softener.png");
        inventoryLayer = new ImageLayer("objects/softener.png");
    }

    @Override
    public void onLook() {
        DialogManager.addDialog("Magi Softener: acid, salty with a touch of flowers.");
        if (step == UNUSED) {
            DialogManager.addDialog("It is almost empty.");
        } else if (step == USED) {

            if (!hasAcid && !hasSalty && !hasFlower) {
                DialogManager.addDialog("It's empty.");
            } else {
                String sentence = "It's ";
                if (hasAcid) {
                    sentence += "acid";
                    if (hasSalty && !hasFlower) {
                        sentence += "and salty";
                    } else if (!hasSalty && hasFlower) {
                        sentence += "with a touch of flowers";
                    } else {
                        sentence += "full again.";
                    }
                } else {
                    if (hasSalty && !hasFlower) {
                        sentence += "salty";
                    } else if (!hasSalty && hasFlower) {
                        sentence += "a touch of flowers";
                    } else {
                        sentence += "salty with a touch of flowers";
                    }
                }

                sentence += ".";
                DialogManager.addDialog(sentence);
            }
        }
    }

    public void onUse(BaseObject with) {
        if (with.name.equals(LanguageManager.objectName(Dictionary.WASHER))) {
            Washer washer = (Washer) with;
            if (washer.isOpen()) {
                step = USED;
                washer.hasSoftener = true;
            } else {
                DialogManager.addDialog("The washer should be open first.");
            }
        }
    }
}
