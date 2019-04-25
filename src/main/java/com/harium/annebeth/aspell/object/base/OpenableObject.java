package com.harium.annebeth.aspell.object.base;

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
            layer.simpleDraw(g, x, y);
        } else {
            openLayer.simpleDraw(g, x, y);
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

    public boolean isOpen() {
        return isOpen;
    }

}
