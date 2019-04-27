package com.harium.annebeth.aspell.core.object;

import com.harium.annebeth.aspell.core.object.base.BaseObject;
import com.harium.annebeth.aspell.core.object.base.OpenableObject;
import com.harium.annebeth.aspell.core.ui.DialogManager;
import com.harium.annebeth.aspell.i18n.Dictionary;
import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.sound.Jukebox;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.layer.AnimatedLayer;
import com.harium.etyl.layer.ImageLayer;

public class Washer extends OpenableObject {

    public boolean hasSock = false;
    public boolean hasPile = false;
    public boolean hasDetergent = false;
    public boolean hasSoftener = false;
    public boolean hasSwitch = false;
    public boolean turnedOn = false;
    public boolean explosion = false;
    public boolean reversed = false;
    public boolean exploded = false;

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
        layer = new ImageLayer(x, y, w, h, "objects/washer.png");
        openLayer = new ImageLayer(x, y, 96, h, "objects/washer_open.png");
        sock = new ImageLayer(x, y, w, h, "objects/washer_sock.png");
        pile = new ImageLayer(x, y, w, h, "objects/washer_pile.png");
        inside = new AnimatedLayer(x + 10, y + 24, 60, 60, "objects/washer_anim.png");
        inside.setFrames(4);
        inside.setSpeed(600);

        glow = new AnimatedLayer(x, y + 16, 80, 80, "fx/glowpx.png");
        glow.setFrames(6);
        glow.setSpeed(100);

        //hasDetergent = true;
        //hasSoftener = true;
        //hasSock = true;
        //hasPile = true;

        //explode();
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
        g.resetOpacity();
    }

    public void update(long now) {
        if (!turnedOn) {
            return;
        } else if (!explosion) {
            if (lastFrame != 3 && inside.getCurrentFrame() == 3) {
                count++;
                if (count >= 3) {
                    explode();
                }
            }
            lastFrame = inside.getCurrentFrame();
        }
        inside.animate(now);
        glow.animate(now);
    }

    public void explode() {
        if (hasSwitch) {
            reversed = true;
        }
        exploded = true;
        explosion = true;
        turnedOn = false;
        hasSoftener = false;
        hasDetergent = false;
        onOpen();
        Jukebox.stopWasher();
        Jukebox.playExplosion();
    }

    @Override
    public void onUse(BaseObject with) {
        if (!hasSock || !hasPile) {
            if (hasSock || hasPile) {
                DialogManager.addDialog("I think I have more clothes.");
            } else {
                DialogManager.addDialog("It needs some clothes.");
            }
        } else if (!hasDetergent || !hasSoftener) {
            if (hasDetergent) {
                DialogManager.addDialog("It needs some softener.");
            } else if (hasSoftener) {
                DialogManager.addDialog("It needs some detergent.");
            } else {
                DialogManager.addDialog("It needs detergent and softener.");
            }
        } else {
            if (isOpen()) {
                DialogManager.addDialog("It should be closed first.");
            } else {
                if (exploded && !hasSwitch) {
                    DialogManager.addDialog("I must figure a way to REVERSE this situation.");
                } else {
                    turnOn();
                }
            }
        }
    }

    @Override
    public void onLook() {
        if (!hasSwitch) {
            super.onLook();
        } else {
            DialogManager.addDialog("A reversed " + name + ".");
        }
    }

    private void turnOn() {
        turnedOn = true;
        Jukebox.stopMusics();
        Jukebox.playWasher();
        if (!hasSwitch) {
            DialogManager.addDialog("Mission Accomplished!");
            DialogManager.addDialog("Uh oh, something is wrong.");
            DialogManager.addDialog("...");
            DialogManager.addDialog("Spider Crap!");
        } else {
            DialogManager.addDialog("Here I go again.");
            DialogManager.addDialog("It has to work.");
            DialogManager.addDialog("...");
            DialogManager.addDialog("It worked!");
        }
    }

    @Override
    public void turnUpsideDown() {
        //Special case
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        glow.setLocation(x, y + 16);
        inside.setLocation(x + 10, y + 24);
    }

}
