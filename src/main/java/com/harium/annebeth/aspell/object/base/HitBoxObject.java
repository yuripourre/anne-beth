package com.harium.annebeth.aspell.object.base;

import com.harium.annebeth.aspell.ui.DialogManager;

public class HitBoxObject extends BaseObject {

    String lookAtSentence = "";

    public HitBoxObject(String name, int x, int y, int w, int h) {
        super(name, x, y, w, h);
        canLook = true;
    }

    public HitBoxObject(String mirror, String lookAtSentence, int x, int y, int w, int h) {
        super(mirror, x, y, w, h);
        this.lookAtSentence = lookAtSentence;
    }

    @Override
    public void onLook() {
        if (lookAtSentence.isEmpty()) {
            super.onLook();
        } else {
            DialogManager.addDialog(lookAtSentence);
        }
    }
}
