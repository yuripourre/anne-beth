package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.object.base.DecorativeObject;
import com.harium.annebeth.aspell.ui.DialogManager;
import com.harium.etyl.layer.ImageLayer;

public class Fan extends DecorativeObject {

    boolean looked = false;

    public Fan(int x, int y) {
        super("fan", x, y, 148, 52);
        layer = new ImageLayer("objects/fan.png");
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

    public void upsideDown() {
        y = 179;
        layer.setScaleY(-1);
    }
}
