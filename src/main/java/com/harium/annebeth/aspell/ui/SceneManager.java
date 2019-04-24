package com.harium.annebeth.aspell.ui;

import com.harium.annebeth.aspell.InGame;
import com.harium.annebeth.aspell.core.Context;
import com.harium.annebeth.aspell.core.Interaction;
import com.harium.annebeth.aspell.object.*;
import com.harium.annebeth.aspell.object.base.BaseObject;
import com.harium.annebeth.aspell.object.base.DummyObject;
import com.harium.annebeth.aspell.object.base.HitBoxObject;
import com.harium.annebeth.aspell.player.Player;
import com.harium.annebeth.aspell.sound.Jukebox;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.animation.Animation;
import com.harium.etyl.core.animation.OnCompleteListener;
import com.harium.etyl.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {

    private static final Color background = new Color(0xC7, 0xB0, 0x8B);
    private static final Color upsideDownBG = new Color(0x00, 0xB0, 0x8B);

    Washer washer;
    boolean normalWorld = true;

    List<BaseObject> objectList = new ArrayList<BaseObject>();
    List<BaseObject> foreground = new ArrayList<BaseObject>();

    Layer flash;

    public SceneManager(int w, int h) {
        objectList.add(new Fan(40, 40));
        objectList.add(new HitBoxObject("carpet", 180, 240, 104, 128));
        objectList.add(new Lemon(480, 240));
        objectList.add(new Stool(300, 240));
        objectList.add(new Detergent(500, 140));
        objectList.add(new Softener(560, 140));

        objectList.add(new Sock(200, 140));
        objectList.add(new Pile(600, 140));

        washer = new Washer(650, 240);
        objectList.add(washer);

        flash = new Layer(0, 0, w, h);
        flash.setVisible(false);
        flash.setOpacity(0);
    }

    public void draw(Graphics g) {
        if (normalWorld) {
            g.setColor(background);
        } else {
            g.setColor(upsideDownBG);
        }
        g.fillRect(0, 0, g.getWidth(), InGame.BOTTOM_BAR);

        for (BaseObject object : objectList) {
            object.draw(g);
        }

        drawFlashFx(g);
    }

    private void drawFlashFx(Graphics g) {
        if (!flash.isVisible()) {
            return;
        }

        g.setColor(Color.WHITE);
        g.fillRect(flash);
        g.setOpacity(flash.getOpacity());
    }

    public void drawForeground(Graphics g) {
        for (BaseObject object : foreground) {
            object.draw(g);
        }
    }

    public void updateMouse(PointerEvent event, Player player) {
        boolean found = false;
        for (BaseObject object : objectList) {
            if (!object.visible) {
                continue;
            }
            if (object.x < event.getX() && object.x + object.w > event.getX() &&
                    object.y < event.getY() && object.y + object.h > event.getY()) {

                player.setTarget(object);
                Context.setObject(object);
                found = true;
                break;
            }
        }

        if (!found) {
            Context.interaction = Interaction.WALK;
            // just walk
            BaseObject object = new DummyObject(event.getX(), event.getY());
            player.setTarget(object);
            Context.setObject(object);
        }
    }

    public void update(long now) {
        washer.update(now);
        if (normalWorld && washer.explosion) {
            normalWorld = false;
            flashEffect();
            Jukebox.playUpsideDownMusic();
        }
    }

    private void flashEffect() {
        if (flash.isVisible()) {
            return;
        }
        flash.setVisible(true);
        flash.setOpacity(0);

        Animation.animate(flash).fadeIn().during(500).onFinish(new OnCompleteListener() {
            @Override
            public void onComplete(long l) {
                flash.setVisible(false);
            }
        }).start();
    }
}
