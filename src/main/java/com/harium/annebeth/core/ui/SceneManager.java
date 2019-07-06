package com.harium.annebeth.core.ui;

import com.harium.annebeth.core.Context;
import com.harium.annebeth.core.Interaction;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.core.object.BaseObject;
import com.harium.annebeth.core.object.DummyObject;
import com.harium.annebeth.core.object.HitBoxObject;
import com.harium.annebeth.core.player.Player;
import com.harium.annebeth.laundry.object.*;
import com.harium.annebeth.laundry.room.*;
import com.harium.annebeth.laundry.sound.Jukebox;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.commons.layer.Layer;
import com.harium.etyl.core.animation.Animation;
import com.harium.etyl.core.animation.OnCompleteListener;
import com.harium.etyl.core.graphics.Graphics;

import java.util.ArrayList;
import java.util.List;

import static com.harium.annebeth.laundry.i18n.Dictionary.*;

// TODO Split this class between generic SceneManager and Laundry SceneManager
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

    private ActionUIManager actionUIManager;

    public SceneManager(int w, int h, ActionUIManager actionUIManager) {
        this.w = w;
        this.actionUIManager = actionUIManager;

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
        //foreground.add(new Table(-110 + ox, 220 + oy));
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

        //foreground.add(new Trash(355 + ox, 294 + oy));
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
        objectList.add(new HitBoxObject(LanguageManager.sentence(MIRROR), LanguageManager.sentence(MIRROR_LOOK_AT), LanguageManager.sentence(MIRROR_LOOK_AT), 576 + ox, 96 + oy, 76, 98));
        objectList.add(new HitBoxObject(LanguageManager.sentence(BED), LanguageManager.sentence(BED_LOOK_AT), 326 + ox, 200 + oy, 190, 98));
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
        g.fillRect(0, 0, g.getWidth(), InventoryManager.INVENTORY_BAR_Y);

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

        for (int i = objectList.size() - 1; i >= 0; i--) {
            BaseObject object = objectList.get(i);

            if (!object.visible) {
                continue;
            }
            if (object.collide(event.getX(), event.getY())) {
                if (Context.interaction == Interaction.LOOK_AT) {
                    ActionUIManager.defineTarget(player, object);
                } else if (Context.interaction == Interaction.USE) {
                    ActionUIManager.defineTarget(player, object);
                    found = true;
                } else if (Context.interaction == Interaction.WALK || Context.interaction == Interaction.NONE) {
                    openMenu(player, object);
                    found = true;
                }
                break;
            }
        }

        if (!found) {
            // No object selected or Use with
            if (!Context.hasObject() || Context.interaction == Interaction.USE || Context.interaction == Interaction.NONE) {
                Context.reset();
                Context.interaction = Interaction.WALK;
                // just walk
                floor.setPosition(event.getX(), event.getY());

                player.setTarget(floor);
                Context.changeObject(floor);
            }
        }
    }

    private void openMenu(Player player, BaseObject object) {
        Context.changeObject(object);
        actionUIManager.openMenu(player, object);
    }

    public void update(long now) {
        washer.update(now);
        if (washer.explosion && !washer.reversed) {
            flashEffect();
            Jukebox.playUpsideDownMusic();
            turnWorldUpsideDown();
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
