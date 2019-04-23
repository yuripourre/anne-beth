package com.harium.annebeth.aspell.object;

import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.object.base.BaseObject;
import com.harium.annebeth.aspell.object.base.OpenableObject;
import com.harium.annebeth.aspell.sound.Jukebox;
import com.harium.annebeth.aspell.ui.DialogManager;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.AnimatedLayer;
import com.harium.etyl.layer.ImageLayer;

public class Washer extends OpenableObject {

    boolean hasSock = false;
    boolean hasPile = false;
    boolean hasDetergent = false;
    boolean hasSoftener = false;
    boolean turnedOn = false;
    public boolean explosion = false;

    private static final Color BACKGROUND = new Color(0x32, 0x2b, 0x28);

    private AnimatedLayer inside;
    private AnimatedLayer glow;

    private ImageLayer sock;
    private ImageLayer pile;

    // Animation Count
    int lastFrame = 0;
    int count = 0;

    public Washer(int x, int y) {
        super(LanguageManager.objectName(Dictionary.WASHER), x, y, 80, 96);
        layer = new ImageLayer("objects/washer.png");
        openLayer = new ImageLayer("objects/washer_open.png");
        sock = new ImageLayer("objects/washer_sock.png");
        pile = new ImageLayer("objects/washer_pile.png");
        inside = new AnimatedLayer(x + 10, y + 24, 60, 60, "objects/washer_anim.png");
        inside.setFrames(4);
        inside.setSpeed(600);

        glow = new AnimatedLayer(x, y + 16, 80, 80, "fx/glowpx.png");
        glow.setFrames(6);
        glow.setSpeed(100);

        hasDetergent = true;
        hasSoftener = true;
        hasSock = true;
        hasPile = true;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(BACKGROUND);
        g.fillRect(x, y, w, h);

        if (!turnedOn) {
            if (hasPile) {
                pile.simpleDraw(g, x + 24, y + 44);
            }
            if (hasSock) {
                sock.simpleDraw(g, x + 14, y + 54);
            }
        } else {
            inside.draw(g, x, y);
        }

        super.draw(g);

        if (turnedOn) {
            glow.draw(g, x, y);
        }
    }

    public void update(long now) {
        if (!turnedOn) {
            return;
        } else if (!explosion) {
            if (lastFrame != 3 && inside.getCurrentFrame() == 3) {
                count++;
                if (count == 3) {
                    explode();
                }
            }
            lastFrame = inside.getCurrentFrame();
        }
        inside.animate(now);
        glow.animate(now);
    }

    private void explode() {
        explosion = true;
        turnedOn = false;
        Jukebox.stopWasher();
        Jukebox.playExplosion();
    }

    @Override
    public void onUse(BaseObject with) {
        if (!hasSock || !hasPile || !hasSoftener || !hasDetergent) {
            DialogManager.addDialog("It's not ready yet.");
        } else {
            if (isOpen()) {
                DialogManager.addDialog("It should be closed first.");
            } else {
                turnOn();
            }
        }
    }

    private void turnOn() {
        turnedOn = true;
        Jukebox.stopMusics();
        Jukebox.playWasher();
        // TODO Add Special Effects
        DialogManager.addDialog("Mission Accomplished!");
        DialogManager.addDialog("Uh oh, something is wrong.");
        DialogManager.addDialog("...");
        DialogManager.addDialog("Spider Crap!");
    }
}
