package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.object.base.BaseObject;

public class Fridge extends BaseObject {

    boolean isOpen = false;

    public Fridge(int x, int y) {
        super("fridge", x, y, 64, 120);
    }

    @Override
    public void onOpen() {
        isOpen = true;
    }

    @Override
    public void onClose() {
        isOpen = false;
    }
}
