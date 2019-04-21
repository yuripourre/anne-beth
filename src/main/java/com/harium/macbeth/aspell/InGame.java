package com.harium.macbeth.aspell;

import com.harium.etyl.commons.context.Application;
import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.macbeth.aspell.core.Context;
import com.harium.macbeth.aspell.object.*;
import com.harium.macbeth.aspell.player.Player;
import com.harium.macbeth.aspell.ui.DialogManager;
import com.harium.macbeth.aspell.ui.InventoryManager;
import com.harium.macbeth.aspell.ui.UIManager;

import java.util.ArrayList;
import java.util.List;

public class InGame extends Application {

    Player player;
    DialogManager dialogManager;
    UIManager uiManager;
    InventoryManager inventoryManager;

    List<BaseObject> objectList = new ArrayList<BaseObject>();

    public InGame(int w, int h) {
        super(w, h);
    }

    public void load() {
        dialogManager = new DialogManager();
        player = new Player(0, 80);

        objectList.add(new Fan(40, 40));
        objectList.add(new HitBoxObject("carpet", 180, 240, 104, 128));
        objectList.add(new Lemon(400, 240));

        uiManager = new UIManager();
        inventoryManager = new InventoryManager();
    }

    @Override
    public void update(long now) {
        player.update(now);
        dialogManager.update(now);
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(this);

        for (BaseObject object : objectList) {
            object.draw(g);
        }

        player.draw(g);

        uiManager.draw(g);
        inventoryManager.draw(g);
        dialogManager.draw(g);
        Context.draw(g);
    }

    @Override
    public void updateMouse(PointerEvent event) {
        super.updateMouse(event);
        if (event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
            if (event.getY() < 410) {
                boolean found = false;
                for (BaseObject object : objectList) {
                    if (object.disabled) {
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
                    // just walk
                    BaseObject object = new DummyObject(event.getX(), event.getY());
                    player.setTarget(object);
                    Context.setObject(object);
                }
            } else {
                uiManager.updateMouse(event);
                inventoryManager.updateMouse(event);
            }
        }
    }
}
