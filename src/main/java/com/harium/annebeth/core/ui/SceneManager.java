package com.harium.annebeth.core.ui;

import com.harium.annebeth.core.Context;
import com.harium.annebeth.core.Interaction;
import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.core.object.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.harium.annebeth.laundry.i18n.Dictionary.*;

// TODO Split this class between generic SceneManager and Laundry SceneManager
public class SceneManager {

    public static boolean DEBUG_MODE = false;

    public static final int ROOM_HEIGHT = 310;
    public static final int ROOM_OFFSET_X = 0;
    public static final int ROOM_OFFSET_Y = 50;
    public static final int HALL_OFFSET_X = 816;
    public static final int KITCHEN_OFFSET_X = HALL_OFFSET_X + 720;
    public static final int DINNER_OFFSET_X = KITCHEN_OFFSET_X + 816;

    private static final Color background = new Color(0xC7, 0xB0, 0x8B);
    //private static final Color upsideDownBG = new Color(0x00, 0xB0, 0x8B);
    public int x = 0;
    public int w = 0;

    DummyObject floor;
    // Openable objects
    public Washer washer;
    public Refrigerator refrigerator;

    public static boolean normalWorld = true;
    public static boolean gameOver = false;

    List<Room> rooms = new ArrayList<Room>();
    List<BaseObject> objectList = new ArrayList<BaseObject>();

    Map<String, PickupableObject> pickupObject = new HashMap<>();
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
        int ox = DINNER_OFFSET_X;
        int oy = ROOM_OFFSET_Y;
        addObjectList(new CactusJimmy(210 + ox, 121 + oy));
        //foreground.add(new Table(-110 + ox, 220 + oy));
    }

    private void addObjectList(BaseObject baseObject) {
        objectList.add(baseObject);
        if (baseObject instanceof PickupableObject) {
            pickupObject.put(baseObject.getName(), (PickupableObject) baseObject);
        }
    }

    private void addForegroundObject(BaseObject baseObject) {
        foreground.add(baseObject);
    }

    public PickupableObject getPickupObject(String name) {
        return pickupObject.get(name);
    }

    private void initKitchen() {
        int ox = KITCHEN_OFFSET_X;
        int oy = 50;

        addForegroundObject(new Table( 840 + ox, 252 + oy));
        addObjectList(new HitBoxOpenableObject(LanguageManager.objectName(WINDOW), 250 + ox, 64 + oy, 124, 86));
        addObjectList(new HitBoxOpenableObject(LanguageManager.objectName(CABINET), 154 + ox, 64 + oy, 100, 78).open(BETTER_NOT));
        addObjectList(new HitBoxOpenableObject(LanguageManager.objectName(CABINET), 374 + ox, 64 + oy, 100, 78).open(BETTER_NOT));
        addObjectList(new HitBoxOpenableObject(LanguageManager.objectName(CABINET), 216 + ox, 188 + oy, 194, 84).open(BETTER_NOT));
        addObjectList(new HitBoxOpenableObject(LanguageManager.objectName(OVEN), 128 + ox, 186 + oy, 90, 88).open(BETTER_NOT));
        addObjectList(new HitBoxOpenableObject(LanguageManager.objectName(DISH_WASHER), 410 + ox, 186 + oy, 66, 88).open(BETTER_NOT));
        addObjectList(new HitBoxObject(LanguageManager.objectName(SINK), 278 + ox, 150 + oy, 76, 36).use(BETTER_NOT));

        addObjectList(new Detergent(505 + ox, 85 + oy));
        addObjectList(new Softener(538 + ox, 89 + oy));
        addObjectList(new Shoyu(505 + ox, 174 + oy));

        refrigerator = new Refrigerator(ox + 10, 106 + oy);
        addObjectList(refrigerator);

        Lemon lemon = new Lemon(290, 90 + oy);
        refrigerator.add(lemon);
        addObjectList(lemon);

        washer = new Washer(ox + 590, 176 + oy);
        addObjectList(washer);

        //foreground.add(new Trash(355 + ox, 294 + oy));
    }

    private void initHall() {
        int ox = 816;
        int oy = 50;

        addObjectList(new HitBoxOpenableObject(LanguageManager.objectName(FRONT_DOOR), 616 + ox, 46 + oy, 104, 206));
        addObjectList(new HitBoxOpenableObject(LanguageManager.objectName(WINDOW), 155 + ox, 14 + oy, 326, 132));
        addObjectList(new HitBoxObject(LanguageManager.objectName(CLOTHES_DOOR), 22 + ox, 60 + oy, 78, 100));
        addObjectList(new HitBoxObject(LanguageManager.objectName(LAMP_LONG), 120 + ox, 116 + oy, 54, 160));
        addObjectList(new HitBoxObject(LanguageManager.objectName(COUCH), 232 + ox, 158 + oy, 190, 125));
        addObjectList(new HitBoxObject(LanguageManager.objectName(PHONE), 456 + ox, 176 + oy, 50, 38));

        Cactus cactus = new Cactus(525 + ox, 102 + oy);
        addObjectList(cactus);
        addObjectList(new CactusFlower(cactus));
        addObjectList(new Stool(290 + ox, 278 + oy));
        addObjectList(new Sock(390 + ox, 278 + oy));
        addObjectList(new Pile(-70 + ox, 270 + oy));
    }

    private void initBedRoom() {
        int ox = ROOM_OFFSET_X;
        int oy = ROOM_OFFSET_Y;
        addForegroundObject(new Fan(358 + ox, -50 + oy));
        addForegroundObject(new Television(350 + ox, 252 + oy));
        addObjectList(new HitBoxOpenableObject(LanguageManager.objectName(WINDOW), 102 + ox, 12 + oy, 96, 136));
        addObjectList(new HitBoxOpenableObject(LanguageManager.objectName(WINDOW), 686 + ox, 12 + oy, 96, 136));
        addObjectList(new HitBoxOpenableObject(LanguageManager.objectName(WARDROBE), ox, 122+ oy , 80, 145).use(BETTER_NOT));
        addObjectList(new HitBoxOpenableObject(LanguageManager.objectName(DRAWER), 235 + ox, 196 + oy, 68, 68).open(BETTER_NOT));
        addObjectList(new HitBoxOpenableObject(LanguageManager.objectName(DRAWER_LONG), 550 + ox, 196 + oy, 120, 70).open(BETTER_NOT));
        addObjectList(new HitBoxObject(LanguageManager.objectName(LAMP), 262 + ox, 130 + oy, 48, 70));
        addObjectList(new HitBoxObject(LanguageManager.objectName(CLOCK), 226 + ox, 150 + oy, 48, 48));
        addObjectList(new HitBoxObject(LanguageManager.objectName(PICTURE), 358 + ox, 60 + oy, 128, 92));
        addObjectList(new HitBoxObject(LanguageManager.objectName(MIRROR), 576 + ox, 96 + oy, 76, 98).look(MIRROR_LOOK_AT).use(MIRROR_LOOK_AT));
        addObjectList(new HitBoxObject(LanguageManager.objectName(BED), 326 + ox, 200 + oy, 190, 98).look(BED_LOOK_AT));
        addObjectList(new FanSwitch(528 + ox, 131 + oy));
        addObjectList(new Lino(133 + ox, 215 + oy));
    }

    private void roomObjects() {
        rooms.add(new BedRoom(ROOM_OFFSET_X, ROOM_OFFSET_Y));
        rooms.add(new Hall(HALL_OFFSET_X, ROOM_OFFSET_Y));
        rooms.add(new Kitchen(KITCHEN_OFFSET_X, ROOM_OFFSET_Y));
        rooms.add(new Dinner(DINNER_OFFSET_X, ROOM_OFFSET_Y));
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
        g.drawString(object.getName(), object.x, object.y, object.w, object.h);
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

        for (int i = foreground.size() - 1; i >= 0; i--) {
            BaseObject object = foreground.get(i);
            if (!object.visible) {
                continue;
            }
            if (object.collide(event.getX(), event.getY())) {
                found = handleObjectInteraction(player, found, object);
                break;
            }
        }

        if (!found) {
            for (int i = objectList.size() - 1; i >= 0; i--) {
                BaseObject object = objectList.get(i);

                if (!object.visible) {
                    continue;
                }
                if (object.collide(event.getX(), event.getY())) {
                    found = handleObjectInteraction(player, found, object);
                    break;
                }
            }
        }

        if (!found) {
            // No object selected or Use with
            if (Context.getInteraction() == Interaction.MENU) {
                Context.reset();
                return;
            }
            if (!Context.hasObject() /*|| Context.getInteraction() == Interaction.USE_WITH*/ || Context.getInteraction() == Interaction.NONE) {
                Context.reset();
                Context.setInteraction(Interaction.WALK);
                // just walk
                floor.setPosition(event.getX(), event.getY());

                player.setTarget(floor);
                Context.changeObject(floor);
            }
        }
    }

    private boolean handleObjectInteraction(Player player, boolean found, BaseObject object) {
        if (Context.getInteraction() == Interaction.LOOK_AT) {
            ActionUIManager.defineTarget(player, object);
        } else if (Context.getInteraction() == Interaction.USE) {
            ActionUIManager.defineTarget(player, object);
            found = true;
        } else if (Context.getInteraction() == Interaction.USE_WITH) {
            Context.setInteraction(Interaction.USE);
            ActionUIManager.defineTarget(player, object);
            found = true;
        } else if (Context.getInteraction() == Interaction.WALK || Context.getInteraction() == Interaction.NONE) {
            openMenu(player, object);
            found = true;
        }
        return found;
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
