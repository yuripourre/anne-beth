package com.harium.annebeth.core.object;

import com.harium.annebeth.core.ui.DialogManager;

public class HitBoxObject extends BaseObject {

    String lookAtSentence = "";
    String useSentence = "";

    public HitBoxObject(String name, int x, int y, int w, int h) {
        super(name, x, y, w, h);
        canLook = true;
    }

    public HitBoxObject(String mirror, String lookAtSentence, int x, int y, int w, int h) {
        super(mirror, x, y, w, h);
        this.lookAtSentence = lookAtSentence;
    }

    public HitBoxObject(String mirror, String lookAtSentence, String useSentence, int x, int y, int w, int h) {
        super(mirror, x, y, w, h);
        this.lookAtSentence = lookAtSentence;
        this.useSentence = useSentence;
    }

    @Override
    public void onLook() {
        if (lookAtSentence.isEmpty()) {
            super.onLook();
        } else {
            DialogManager.addDialog(lookAtSentence);
        }
    }


    public void onUse(BaseObject with) {
        if (useSentence.isEmpty()) {
            super.onLook();
        } else {
            DialogManager.addDialog(useSentence);
        }
    }
}
