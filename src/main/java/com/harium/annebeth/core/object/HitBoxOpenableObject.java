package com.harium.annebeth.core.object;

import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.core.ui.DialogManager;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.laundry.sound.Jukebox;

public class HitBoxOpenableObject extends BaseObject {

    String lookSentence = "";
    String openSentence = "";
    String useSentence = "";

    public HitBoxOpenableObject(String name, int x, int y, int w, int h) {
        this(name, "", "", "", x, y, w, h);
    }

    public HitBoxOpenableObject(String name, String lookSentence, String openSentence, String useSentence,
                                int x, int y, int w, int h) {
        super(name, x, y, w, h);
        this.lookSentence = lookSentence;
        this.openSentence = openSentence;
        this.useSentence = useSentence;
        canOpen = true;
    }

    public HitBoxOpenableObject look(String lookSentence) {
        this.lookSentence = LanguageManager.sentence(lookSentence);
        return this;
    }

    public HitBoxOpenableObject use(String useSentence) {
        this.useSentence = LanguageManager.sentence(useSentence);
        return this;
    }

    public HitBoxOpenableObject open(String openSentence) {
        this.openSentence = LanguageManager.sentence(openSentence);
        return this;
    }

    @Override
    public void onLook() {
        if (lookSentence.isEmpty()) {
            super.onLook();
        } else {
            DialogManager.addDialog(lookSentence);
        }
    }

    @Override
    public void onOpen() {
        Jukebox.playCannot();
        if (openSentence.isEmpty()) {
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.CANT_DO_THAT));
        } else {
            DialogManager.addDialog(openSentence);
        }
    }

    public void onUse(BaseObject with) {
        Jukebox.playCannot();
        if (useSentence.isEmpty()) {
            negativeDialog();
        } else {
            DialogManager.addDialog(useSentence);
        }
    }
}
