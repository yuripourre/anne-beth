package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.ui.DialogManager;

public class Fan extends HitBoxObject {

    boolean looked = false;

    public Fan(int x, int y) {
        super("fan", x, y, 64, 120);
    }

    @Override
    public void onLook() {
        if (!looked) {
            looked = true;
            DialogManager.addDialog("Whoa, this fan needs to be cleaned.");
            // Change the name
            name = "dirty fan";
        } else {
            super.onLook();
        }
    }
}
