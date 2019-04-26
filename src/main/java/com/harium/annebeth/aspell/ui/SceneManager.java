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
import com.harium.etyl.layer.ImageLayer;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {

    public static final int ROOM_HEIGHT = 310;
    public static final int ROOM_OFFSET = 60;

    public static boolean DEBUG_MODE = true;

    private static final Color background = new Color(0xC7, 0xB0, 0x8B);
    private static final Color upsideDownBG = new Color(0x00, 0xB0, 0x8B);
    public int x = 0;
    public int w = 0;

    //private int offset = 0;

    DummyObject floor;
    Washer washer;
    boolean normalWorld = true;

    List<BaseObject> objectList = new ArrayList<BaseObject>();
    List<BaseObject> foreground = new ArrayList<BaseObject>();

    Layer flash;

    ImageLayer room;

    public SceneManager(int w, int h) {
        this.w = w;
        floor = new DummyObject(0, 0);
        room = new ImageLayer(0, 0, 632, 310, "rooms/room.png");

        foreground.add(new Fan(40, 0));
        foreground.add(new Television(90, 302));

        roomObjects();

        Refrigerator refrigerator = new Refrigerator(730, 108);
        objectList.add(refrigerator);

        Lemon lemon = new Lemon(290, 90);
        refrigerator.add(lemon);
        objectList.add(lemon);

        objectList.add(new FanSwitch(490, 110));

        /*objectList.add(new HitBoxObject("carpet", 80, 340, 104, 50));
        objectList.add(new Lemon(880, 240));
        objectList.add(new Stool(500, 370));
        objectList.add(new Detergent(630, 90));
        objectList.add(new Softener(690, 90));

        objectList.add(new Sock(200, 354));
        objectList.add(new Pile(600, 330));*/

        washer = new Washer(850, refrigerator.y + 71);
        objectList.add(washer);

        /*InventoryManager.pickup(new Sock(0, 0));
        InventoryManager.pickup(new Lemon(0, 0, refrigerator));
        InventoryManager.pickup(new Stool(0, 0));
        InventoryManager.pickup(new Softener(0, 0));
        InventoryManager.pickup(new Detergent(0, 0));*/

        setupEffects(w, h);
    }

    private void roomObjects() {
        objectList.add(new HitBoxObject("mirror", "Hey, it's me.", 384, 100, 76, 98));
        objectList.add(new HitBoxObject("bed", "A very comfortable bed.", 105, 205, 190, 98));
    }

    private void setupEffects(int w, int h) {
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

        room.draw(g);

        for (BaseObject object : objectList) {
            drawObject(g, object);
            if (DEBUG_MODE) {
                drawDebug(g, object);
            }
        }

        drawFlashFx(g);
        //if (DEBUG_MODE) {
        g.drawString("X: " + x, 20, 60);
        //}
    }

    private void drawDebug(Graphics g, BaseObject object) {
        if (!object.visible) {
            g.setColor(Color.GRAY);
        } else {
            g.setColor(Color.BLUE);
        }
        g.drawRect(object.x, object.y, object.w, object.h);
        g.drawString(object.name, object.x, object.y, object.w, object.h);
    }

    private void drawObject(Graphics g, BaseObject object) {
        object.draw(g);
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
            drawObject(g, object);
            if (DEBUG_MODE) {
                drawDebug(g, object);
            }
        }
    }

    public void updateMouse(PointerEvent event, Player player) {
        boolean found = false;

        for (int i = objectList.size() - 1; i >= 0; i--) {
            BaseObject object = objectList.get(i);

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

        for (BaseObject object : foreground) {
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
            floor.setPosition(event.getX(), event.getY());

            player.setTarget(floor);
            Context.setObject(floor);
        }
    }

    public void update(long now) {
        washer.update(now);
        if (normalWorld && washer.explosion) {
            flashEffect();
            Jukebox.playUpsideDownMusic();
            turnWorldUpsideDown();
        }
    }

    public void turnWorldUpsideDown() {
        normalWorld = false;
        room.setY(ROOM_OFFSET);
        room.setScaleY(-1);

        for (BaseObject object : foreground) {
            object.turnUpsideDown();
        }

        for (BaseObject object : objectList) {
            object.turnUpsideDown();
        }
    }

    public void turnWorldNormal() {
        normalWorld = true;
        room.setScaleY(1);
        room.setY(0);

        for (BaseObject object : foreground) {
            object.turnNormal();
        }

        for (BaseObject object : objectList) {
            object.turnNormal();
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

    public void offset(int offset) {
        this.x += offset;
        for (BaseObject object : foreground) {
            object.offset(offset, 0);
        }
        for (BaseObject object : objectList) {
            object.offset(offset, 0);
        }
        room.offset(offset, 0);
        floor.offset(offset, 0);
    }
}
