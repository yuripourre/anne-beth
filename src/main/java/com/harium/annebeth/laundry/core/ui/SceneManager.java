package com.harium.annebeth.laundry.core.ui;

import com.harium.annebeth.laundry.InGame;
import com.harium.annebeth.laundry.core.Context;
import com.harium.annebeth.laundry.core.Interaction;
import com.harium.annebeth.laundry.core.object.*;
import com.harium.annebeth.laundry.core.object.base.BaseObject;
import com.harium.annebeth.laundry.core.object.base.DummyObject;
import com.harium.annebeth.laundry.core.object.base.HitBoxObject;
import com.harium.annebeth.laundry.core.player.Player;
import com.harium.annebeth.laundry.core.room.*;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.animation.Animation;
import com.harium.etyl.core.animation.OnCompleteListener;
import com.harium.etyl.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {

    public static final int ROOM_HEIGHT = 310;
    public static final int ROOM_OFFSET = 50;

    public static boolean DEBUG_MODE = false;

    private static final Color background = new Color(0xC7, 0xB0, 0x8B);
    //private static final Color upsideDownBG = new Color(0x00, 0xB0, 0x8B);
    public int x = 0;
    public int w = 0;

    DummyObject floor;
    Washer washer;
    public static boolean normalWorld = true;
    public static boolean gameOver = false;

    List<Room> rooms = new ArrayList<Room>();
    List<BaseObject> objectList = new ArrayList<BaseObject>();
    List<BaseObject> foreground = new ArrayList<BaseObject>();

    Layer flash;

    public SceneManager(int w, int h) {
        this.w = w;
        floor = new DummyObject(0, 0);

        initBedRoom();
        initHall();
        initKitchen();
        initDinner();

        roomObjects();

        setupEffects(w, h);

        // TODO REMOVE
        //offset(-500);
        //InventoryManager.pickup(new Shoyu(0, 0));
        //InventoryManager.pickup(new Stool(0, 0));
        /*offset(-1500);

        InventoryManager.pickup(new Sock(0, 0));
        InventoryManager.pickup(new Pile(0, 0));
        InventoryManager.pickup(new Lemon(0, 0));
        InventoryManager.pickup(new Shoyu(0, 0));
        InventoryManager.pickup(new CactusFlower(new Cactus(0, 0)));
        InventoryManager.pickup(new Stool(0, 0));
        InventoryManager.pickup(new Softener(0, 0));
        InventoryManager.pickup(new Detergent(0, 0));
        InventoryManager.pickup(new FanSwitch(0, 0));*/

        /*washer.explode();
        washer.hasSock = true;
        washer.hasPile = true;
        washer.reversed = true;
        washer.hasDetergent = true;
        washer.hasSoftener = true;
        washer.hasSwitch = true;*/
    }

    public static boolean isUpsideDown() {
        return !normalWorld;
    }

    private void initDinner() {
        int ox = 816 + 720 + 816;
        int oy = 50;
        objectList.add(new CactusJimmy(235 + ox, 121 + oy));
        foreground.add(new Table(-110 + ox, 220 + oy));
    }

    private void initKitchen() {
        int ox = 816 + 720;
        int oy = 50;

        objectList.add(new Detergent(505 + ox, 85 + oy));
        objectList.add(new Softener(538 + ox, 89 + oy));
        objectList.add(new Shoyu(505 + ox, 174 + oy));

        Refrigerator refrigerator = new Refrigerator(ox + 10, 106 + oy);
        objectList.add(refrigerator);

        Lemon lemon = new Lemon(290, 90 + oy);
        refrigerator.add(lemon);
        objectList.add(lemon);

        washer = new Washer(ox + 590, 176 + oy);
        objectList.add(washer);
    }

    private void initHall() {
        int ox = 816;
        int oy = 50;

        Cactus cactus = new Cactus(525 + ox, 102 + oy);
        objectList.add(cactus);
        objectList.add(new CactusFlower(cactus));
        objectList.add(new Stool(290 + ox, 278 + oy));
        objectList.add(new Sock(390 + ox, 278 + oy));
        objectList.add(new Pile(-70 + ox, 270 + oy));
    }

    private void initBedRoom() {
        int ox = 0;
        int oy = 50;
        foreground.add(new Fan(358 + ox, -50 + oy));
        foreground.add(new Television(350 + ox, 252 + oy));
        objectList.add(new HitBoxObject("mirror", "Hey, it's me.", 576 + ox, 96 + oy, 76, 98));
        objectList.add(new HitBoxObject("bed", "A very comfortable bed.", 326 + ox, 200 + oy, 190, 98));
        objectList.add(new FanSwitch(528 + ox, 131 + oy));
        objectList.add(new Lino(133 + ox, 215 + oy));
    }

    private void roomObjects() {
        rooms.add(new BedRoom(0, ROOM_OFFSET));
        rooms.add(new Hall(816, ROOM_OFFSET));
        rooms.add(new Kitchen(816 + 720, ROOM_OFFSET));
        rooms.add(new Dinner(816 + 720 + 816, ROOM_OFFSET));
    }

    private void setupEffects(int w, int h) {
        flash = new Layer(0, 0, w, h);
        flash.setVisible(false);
        flash.setOpacity(0);
    }

    public void draw(Graphics g) {
        g.setColor(background);
        g.fillRect(0, 0, g.getWidth(), InGame.BOTTOM_BAR);

        for (Room room : rooms) {
            room.draw(g);
        }

        for (BaseObject object : objectList) {
            drawObject(g, object);
            if (DEBUG_MODE) {
                drawDebug(g, object);
            }
        }

        drawFlashFx(g);
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

        for (BaseObject object : foreground) {
            if (!object.visible) {
                continue;
            }
            if (object.x < event.getX() && object.x + object.w > event.getX() &&
                    object.y < event.getY() && object.y + object.h > event.getY()) {

                defineTarget(player, object);
                found = true;
                break;
            }
        }

        if (!found) {
            for (int i = objectList.size() - 1; i >= 0; i--) {
                BaseObject object = objectList.get(i);

                if (!object.visible) {
                    continue;
                }
                if (object.x < event.getX() && object.x + object.w > event.getX() &&
                        object.y < event.getY() && object.y + object.h > event.getY()) {

                    defineTarget(player, object);
                    found = true;
                    break;
                }
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

    private void defineTarget(Player player, BaseObject object) {
        player.setTarget(object);
        Context.setObject(object);
        if (Context.interaction == Interaction.NONE) {
            Context.interaction = Interaction.WALK;
        }
    }

    public void update(long now) {
        washer.update(now);
        if (washer.explosion && !washer.reversed) {
            flashEffect();
            Jukebox.playUpsideDownMusic();
            turnWorldUpsideDown();
            System.out.println("Explosion");
            washer.explosion = false;
        } else if (washer.explosion && washer.reversed) {
            flashEffect();
            //Jukebox.playNormalMusic();
            //Jukebox.playGameOver();

            //turnWorldNormal();
            // TODO GAME OVER
            gameOver = true;
            //System.out.println("Game Over");
        }
    }

    public void turnWorldUpsideDown() {
        normalWorld = false;

        for (Room room : rooms) {
            room.turnUpsideDown();
        }

        for (BaseObject object : foreground) {
            object.turnUpsideDown();
        }

        for (BaseObject object : objectList) {
            object.turnUpsideDown();
        }

        // Fix composite objects
        offset(0);
    }

    public void turnWorldNormal() {
        normalWorld = true;

        for (Room room : rooms) {
            room.turnNormal();
        }

        for (BaseObject object : foreground) {
            object.turnNormal();
        }

        for (BaseObject object : objectList) {
            object.turnNormal();
        }
    }

    public void flashEffect() {
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
        for (Room room : rooms) {
            room.offset(offset, 0);
        }
        floor.offset(offset, 0);
        flash.offset(offset, 0);
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
