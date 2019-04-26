package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.object.base.OpenableObject;
import com.harium.annebeth.aspell.object.base.PickupableObject;
import com.harium.annebeth.aspell.ui.SceneManager;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class Refrigerator extends OpenableObject {

    private ImageLayer apple;
    private ImageLayer watermelon;
    private ImageLayer banana;
    private PickupableObject lemon = null;

    public Refrigerator(int x, int y) {
        super(LanguageManager.objectName(Dictionary.REFRIGERATOR), x, y, 108, 168);
        layer = new ImageLayer(x, y, w, h, "objects/fridge.png");
        openLayer = new ImageLayer(x, y, w, h, "objects/fridge_open.png");

        apple = new ImageLayer(x + 49, y + 69, w, h, "objects/apple.png");
        banana = new ImageLayer(x + 16, y + 102, w, h, "objects/banana.png");
        watermelon = new ImageLayer(x + 43, y + 106, w, h, "objects/watermelon.png");
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
            apple.draw(g);
            banana.draw(g);
            watermelon.draw(g);
        }
    }

    public void add(PickupableObject lemon) {
        this.lemon = lemon;
        lemon.visible = false;
        lemon.setPosition(x - 10, y + 150);
    }

    @Override
    public void onOpen() {
        super.onOpen();
        if (lemon != null) {
            lemon.visible = true;
        }
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);

        // NormalWorld
        apple.setLocation(x + 49, y + 69);
        banana.setLocation(x + 16, y + 102);
        watermelon.setLocation(x + 43, y + 106);
    }
}
