package com.harium.annebeth.laundry;

import com.harium.etyl.commons.context.load.DefaultLoadApplication;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.ImageLayer;

public class LoadingScreen extends DefaultLoadApplication {

    private static final Color color = Color.WHITE;
    private ImageLayer background;
    private static final int SIZE = 12;
    private static final int BAR_SIZE = 400;
    private float percent = 0;

    private int x;

    public LoadingScreen(int w, int h) {
        super(w, h);
    }

    @Override
    public void onChangeText(String s) {

    }

    @Override
    public void onChangeLoad(float value) {
        percent = value / 100;
    }

    @Override
    public void load() {
        background = new ImageLayer("screen/loading.png");
        x = w / 2 - BAR_SIZE / 2;
    }

    @Override
    public void draw(Graphics g) {
        background.simpleDraw(g);
        g.setColor(color);
        g.drawRect(x, 500, BAR_SIZE, SIZE);
        g.fillRect(x + 2, 500 + 2, (BAR_SIZE-2) * percent, SIZE - 3);

    }
}
