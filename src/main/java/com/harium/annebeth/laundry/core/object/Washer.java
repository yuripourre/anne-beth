package com.harium.annebeth.laundry.core.object;

import com.harium.annebeth.laundry.core.object.base.BaseObject;
import com.harium.annebeth.laundry.core.object.base.OpenableObject;
import com.harium.annebeth.laundry.core.ui.DialogManager;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.laundry.i18n.LanguageManager;
import com.harium.annebeth.laundry.sound.Jukebox;
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
        sock = new ImageLayer(x, y, 50, 25, "objects/washer_sock.png");
        pile = new ImageLayer(x, y, 51, 34, "objects/washer_pile.png");
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
                pile.draw(g);
            }
            if (hasSock) {
                sock.draw(g);
            }
        } else {
            inside.draw(g);
        }

        super.draw(g);

        if (turnedOn) {
            glow.draw(g);
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
                DialogManager.addDialog(LanguageManager.sentence(Dictionary.WASHER_MORE_CLOTHES));
            } else {
                DialogManager.addDialog(LanguageManager.sentence(Dictionary.WASHER_EMPTY));
            }
        } else if (!hasDetergent || !hasSoftener) {
            if (hasDetergent) {
                DialogManager.addDialog(LanguageManager.sentence(Dictionary.SOFTENER));
            } else if (hasSoftener) {
                DialogManager.addDialog(LanguageManager.sentence(Dictionary.WASHER_DETERGENT));
            } else {
                DialogManager.addDialog(LanguageManager.sentence(Dictionary.WASHER_DETERGENT_AND_SOFTENER));
            }
        } else {
            if (isOpen()) {
                DialogManager.addDialog(LanguageManager.sentence(Dictionary.IT_SHOULD_BE_CLOSED));
            } else {
                if (exploded && !hasSwitch) {
                    DialogManager.addDialog(LanguageManager.sentence(Dictionary.WASHER_REVERSE));
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
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.WASHER_REVERSED));
        }
    }

    private void turnOn() {
        turnedOn = true;
        Jukebox.stopMusics();
        Jukebox.playWasher();
        if (!hasSwitch) {
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.MISSION_COMPLETE));
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.SOMETHING_WRONG));
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.NO_WORDS));
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.SPIDER_CRAP));
        } else {
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.HERE_I_GO_AGAIN));
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.HAS_TO_WORK));
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.NO_WORDS));
            DialogManager.addDialog(LanguageManager.sentence(Dictionary.NO_WORDS));
        }
    }

    @Override
    public void turnUpsideDown() {
        // Special case
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);

        glow.setLocation(x, y + 16);
        inside.setLocation(x + 10, y + 24);
        pile.setLocation(x + 24, y + 44);
        sock.setLocation(x + 14, y + 54);
    }

}
