package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.object.base.OpenableObject;
import com.harium.annebeth.aspell.object.base.PickupableObject;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class Refrigerator extends OpenableObject {

    private ImageLayer apple;
    private ImageLayer watermelon;
    private ImageLayer banana;

    public Refrigerator(int x, int y) {
        super(LanguageManager.objectName(Dictionary.REFRIGERATOR), x, y, 48, 40);
        layer = new ImageLayer(x, y, w, h, "objects/fridge.png");
        openLayer = new ImageLayer(x, y, w, h, "objects/fridge_open.png");

        apple = new ImageLayer(x, y, w, h, "objects/apple.png");
        banana = new ImageLayer(x, y, w, h, "objects/banana.png");
        watermelon = new ImageLayer(x, y, w, h, "objects/watermelon.png");
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
            apple.draw(g, x, y);
            banana.draw(g, x, y);
            watermelon.draw(g, x+10, y+10);
        }
    }
}
