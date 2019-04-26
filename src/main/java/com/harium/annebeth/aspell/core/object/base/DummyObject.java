package com.harium.annebeth.aspell.core.object.base;

public class DummyObject extends BaseObject {

    public DummyObject(int x, int y) {
        super("", x, y, 0, 0);
        visible = false;
        disabled = true;
    }

    public void onOpen() {
    }

    public void onClose() {
    }

    public void onPickUp() {
    }

    public void onUse(BaseObject with) {
    }

    public void onPull() {
    }

    public void onLook() {
    }

    public int centerX() {
        return x;
    }

}
