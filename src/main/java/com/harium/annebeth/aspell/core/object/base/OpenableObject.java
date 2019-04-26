package com.harium.annebeth.aspell.core.object.base;

import com.harium.annebeth.aspell.sound.Jukebox;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class OpenableObject extends BaseObject {

    protected boolean isOpen = false;
    public ImageLayer layer;
    public ImageLayer openLayer;

    public OpenableObject(String name, int x, int y, int w, int h) {
        super(name, x, y, w, h);
        canOpen = true;
    }

    @Override
    public void draw(Graphics g) {
        if (!visible) {
            return;
        }
        if (!isOpen) {
            layer.draw(g);
        } else {
            openLayer.draw(g);
        }
    }

    @Override
    public void onOpen() {
        isOpen = true;
        Jukebox.playOpen();
    }

    @Override
    public void onClose() {
        isOpen = false;
        Jukebox.playOpen();
    }

    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        layer.setLocation(x, y);
        openLayer.setLocation(x, y);
    }

    public boolean isOpen() {
        return isOpen;
    }

}
