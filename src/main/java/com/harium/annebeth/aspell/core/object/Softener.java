package com.harium.annebeth.aspell.core.object;

import com.harium.annebeth.aspell.core.object.base.BaseObject;
import com.harium.annebeth.aspell.core.object.base.HighObject;
import com.harium.annebeth.aspell.core.ui.DialogManager;
import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.sound.Jukebox;
import com.harium.etyl.layer.ImageLayer;

public class Softener extends HighObject {

    private static final int UNUSED = 0;
    private static final int USED = 1;

    boolean hasAcid = true;
    boolean hasSalty = true;
    boolean hasFlower = true;

    int step = UNUSED;

    public Softener(int x, int y) {
        super(LanguageManager.objectName(Dictionary.SOFTENER), x, y, 25, 36);
        layer = new ImageLayer(x, y, w, h, "objects/softener.png");
        inventoryLayer = new ImageLayer("objects/softener_inv.png");
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
                String sentence = "Well, it's ";

                if (hasAcid && hasSalty && hasFlower) {
                    sentence += "full again.";
                } else if (hasAcid) {
                    sentence += "acid";
                    if (hasSalty && !hasFlower) {
                        sentence += " and salty";
                    } else if (!hasSalty && hasFlower) {
                        sentence += " with a touch of flowers";
                    }
                } else {
                    if (hasSalty && !hasFlower) {
                        sentence += "salty";
                    } else if (!hasSalty && hasFlower) {
                        sentence += "smelling like flowers";
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
            if (step == USED) {
                if (!hasAcid && !hasSalty && !hasFlower) {
                    DialogManager.addDialog("It's empty.");
                    return;
                }
            }

            Washer washer = (Washer) with;
            if (washer.isOpen()) {
                if (step == UNUSED && !washer.hasSoftener) {
                    step = USED;
                    washer.hasSoftener = true;
                    hasAcid = false;
                    hasFlower = false;
                    hasSalty = false;
                    Jukebox.playUse();
                }
            } else {
                DialogManager.addDialog("The washer should be open first.");
            }
        } else {
            negativeDialog();
        }
    }
}
