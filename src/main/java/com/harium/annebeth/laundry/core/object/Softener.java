package com.harium.annebeth.laundry.core.object;

import com.harium.annebeth.laundry.core.object.base.BaseObject;
import com.harium.annebeth.laundry.core.object.base.HighObject;
import com.harium.annebeth.laundry.core.ui.DialogManager;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.laundry.i18n.LanguageManager;
import com.harium.annebeth.laundry.sound.Jukebox;
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
        DialogManager.addDialog(LanguageManager.sentence(Dictionary.SOFTENER_LOOK_AT));
        if (step == UNUSED) {
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.ALMOST_EMPTY));
        } else if (step == USED) {
            if (hasAcid) {
                if (hasSalty) {
                    if (hasFlower) {
                        DialogManager.addDialog(LanguageManager.sentence(Dictionary.FULL_AGAIN));
                    } else {
                        DialogManager.addDialog(LanguageManager.sentence(Dictionary.SOFTENER_ACID_SALTY));
                    }
                } else {
                    if (hasFlower) {
                        DialogManager.addDialog(LanguageManager.sentence(Dictionary.SOFTENER_ACID_FLOWERS));
                    } else {
                        DialogManager.addDialog(LanguageManager.sentence(Dictionary.SOFTENER_ACID));
                    }
                }
            } else {
                if (hasSalty) {
                    if (!hasFlower) {
                        DialogManager.addDialog(LanguageManager.sentence(Dictionary.SOFTENER_SALTY));
                    } else {
                        DialogManager.addDialog(LanguageManager.sentence(Dictionary.SOFTENER_SALTY_FLOWERS));
                    }
                } else if (hasFlower) {
                    DialogManager.addDialog(LanguageManager.sentence(Dictionary.SOFTENER_FLOWERS));
                } else {
                    DialogManager.addDialog(LanguageManager.sentence(Dictionary.EMPTY));
                }
            }
        }
    }

    public void onUse(BaseObject with) {
        if (with.name.equals(LanguageManager.objectName(Dictionary.LEMON))) {
            Lemon lemon = (Lemon) with;
            lemon.combine(this);
        } else if (with.name.equals(LanguageManager.objectName(Dictionary.SHOYU))) {
            Shoyu shoyu = (Shoyu) with;
            shoyu.combine(this);
        } else if (with.name.equals(LanguageManager.objectName(Dictionary.CACTUS_FLOWER))) {
            CactusFlower flower = (CactusFlower) with;
            flower.combine(this);
        } else if (with.name.equals(LanguageManager.objectName(Dictionary.WASHER))) {
            if (step == USED) {
                if (!hasAcid && !hasSalty && !hasFlower) {
                    DialogManager.addDialog(LanguageManager.sentence(Dictionary.EMPTY));
                    return;
                }
            }

            Washer washer = (Washer) with;
            //if (washer.isOpen()) {
            if (step == UNUSED && !washer.hasSoftener) {
                step = USED;
                hasAcid = false;
                hasFlower = false;
                hasSalty = false;

            }
            washer.hasSoftener = true;
            Jukebox.playUse();
            //} else {
            //    DialogManager.addDialog("The washer should be open first.");
            //}
        } else {
            negativeDialog();
        }
    }
}
