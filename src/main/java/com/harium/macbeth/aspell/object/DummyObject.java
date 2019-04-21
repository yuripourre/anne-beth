package com.harium.macbeth.aspell.object;

public class DummyObject extends BaseObject {

    public DummyObject(int x, int y) {
        super("", x, y, 0, 0);
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
