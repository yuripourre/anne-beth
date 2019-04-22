package com.harium.annebeth.aspell.object;

public class DummyObject extends BaseObject {

    public DummyObject(int x, int y) {
        super("", x, y, 0, 0);
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
