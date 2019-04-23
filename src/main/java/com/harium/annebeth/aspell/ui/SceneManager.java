package com.harium.annebeth.aspell.ui;

import com.harium.annebeth.aspell.InGame;
import com.harium.annebeth.aspell.core.Interaction;
import com.harium.annebeth.aspell.object.*;
import com.harium.annebeth.aspell.object.base.BaseObject;
import com.harium.annebeth.aspell.object.base.DummyObject;
import com.harium.annebeth.aspell.object.base.HitBoxObject;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.annebeth.aspell.core.Context;
import com.harium.annebeth.aspell.player.Player;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {

    Washer washer;
    Color background = new Color(0xC7, 0xB0, 0x8B);

    List<BaseObject> objectList = new ArrayList<BaseObject>();
    List<BaseObject> foreground = new ArrayList<BaseObject>();

    public SceneManager() {
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
    }

    public void draw(Graphics g) {
        g.setColor(background);
        g.fillRect(0, 0, g.getWidth(), InGame.BOTTOM_BAR);

        for (BaseObject object : objectList) {
            object.draw(g);
        }
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
        if (washer.explosion) {
            // Effect
            // Flash Effect
        }
    }
}
