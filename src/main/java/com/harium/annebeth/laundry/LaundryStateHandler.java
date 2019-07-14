package com.harium.annebeth.laundry;

import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.core.state.GameState;
import com.harium.annebeth.core.ui.InventoryManager;
import com.harium.annebeth.core.ui.SkillManager;
import com.harium.annebeth.laundry.i18n.Dictionary;

import java.util.HashSet;
import java.util.Set;

public class LaundryStateHandler {

    protected static final String TRUE = "true";
    protected static final String FALSE = "false";

    protected static final String SKILL0 = "skill0";
    protected static final String INVENTORY0 = "inv0";
    protected static final String INVENTORY1 = "inv1";
    protected static final String INVENTORY2 = "inv2";
    protected static final String INVENTORY3 = "inv3";
    protected static final String INVENTORY4 = "inv4";
    protected static final String INVENTORY5 = "inv5";
    protected static final String INVENTORY6 = "inv6";
    protected static final String FRIDGE_OPEN = "fridge_open";
    protected static final String FRIDGE_WAS_OPEN = "fridge_was_open";
    protected static final String LEMON_WAS_PICKED = "lemon_was_picked";
    protected static final String WASHER_OPEN = "washer_open";
    protected static final String WASHER_SOCK_INSIDE = "washer_sock";
    protected static final String WASHER_CLOTHES_INSIDE = "washer_clothes";
    protected static final String WASHER_SOFTENER = "washer_soft";
    protected static final String WASHER_DETERGENT = "washer_deter";
    protected static final String WASHER_EXPLODED = "washer_exp";
    protected static final String WASHER_REVERSED = "washer_rev";

    protected static final String OFFSET = "offset";
    protected static final String PLAYER_X = "px";
    protected static final String PLAYER_Y = "py";

    private static final Set<String> pickupItems = new HashSet<>();

    public static void save(InGame ingame, GameState state) {
        saveSkills(state);
        saveInventory(state);
        saveObjects(ingame, state);
        saveScene(ingame, state);
    }

    private static void saveScene(InGame ingame, GameState state) {
        saveState(state, PLAYER_X, ingame.player.getX());
        saveState(state, PLAYER_Y, ingame.player.getY());
        saveState(state, OFFSET, ingame.sceneManager.x);
    }

    private static void saveObjects(InGame ingame, GameState state) {
        // Order matters
        saveState(state, WASHER_EXPLODED, ingame.sceneManager.washer.exploded);
        saveState(state, WASHER_REVERSED, ingame.sceneManager.washer.reversed);
        saveState(state, WASHER_OPEN, ingame.sceneManager.washer.isOpen());
        saveState(state, WASHER_SOCK_INSIDE, ingame.sceneManager.washer.hasSock);
        saveState(state, WASHER_CLOTHES_INSIDE, ingame.sceneManager.washer.hasPile);
        saveState(state, WASHER_SOFTENER, ingame.sceneManager.washer.hasSoftener);
        saveState(state, WASHER_DETERGENT, ingame.sceneManager.washer.hasDetergent);

        saveState(state, FRIDGE_OPEN, ingame.sceneManager.refrigerator.isOpen());
        saveState(state, FRIDGE_WAS_OPEN, ingame.sceneManager.refrigerator.wasOpen());
        saveState(state, LEMON_WAS_PICKED, ingame.sceneManager.refrigerator.lemon.isPicked());

        saveState(state, PLAYER_X, ingame.player.getX());
        saveState(state, PLAYER_Y, ingame.player.getY());
    }

    private static void saveState(GameState state, String key, boolean value) {
        if (value) {
            state.save(key, TRUE);
        } else {
            state.save(key, FALSE);
        }
    }

    private static void saveState(GameState state, String key, int value) {
        state.save(key, Integer.toString(value));
    }

    private static void saveSkills(GameState state) {
        if (SkillManager.has(Dictionary.STOOL)) {
            state.save(SKILL0, Dictionary.STOOL);
        }
    }

    private static void saveInventory(GameState state) {
        saveInventorySlot(INVENTORY0, 0, state);
        saveInventorySlot(INVENTORY1, 1, state);
        saveInventorySlot(INVENTORY2, 2, state);
        saveInventorySlot(INVENTORY3, 3, state);
        saveInventorySlot(INVENTORY4, 4, state);
        saveInventorySlot(INVENTORY5, 5, state);
        saveInventorySlot(INVENTORY6, 6, state);
    }

    private static void saveInventorySlot(String key, int index, GameState state) {
        String name = InventoryManager.get(index);
        if (!InventoryManager.get(index).isEmpty()) {
            state.save(key, name);
        }
    }

    public static void load(InGame ingame, GameState state) {
        init();
        loadSkills(ingame, state);
        loadInventory(ingame, state);
        loadObjects(ingame, state);
        loadScene(ingame, state);
    }

    private static void init() {
        if (pickupItems.isEmpty()) {
            pickupItems.add(Dictionary.SOCK);
            pickupItems.add(Dictionary.CLOTHES_PILE);
            pickupItems.add(Dictionary.SHOYU);
            pickupItems.add(Dictionary.LEMON);
            pickupItems.add(Dictionary.CACTUS_FLOWER);
            pickupItems.add(Dictionary.SOFTENER);
            pickupItems.add(Dictionary.DETERGENT);
            pickupItems.add(Dictionary.FAN_SWITCH);
        }
    }

    private static void loadObjects(InGame ingame, GameState state) {

        // Load washer state
        if (TRUE.equals(state.load(FRIDGE_WAS_OPEN))) {
            ingame.sceneManager.refrigerator.onOpen();
            if (FALSE.equals(state.load(FRIDGE_OPEN))) {
                ingame.sceneManager.refrigerator.onClose();
            }
            if (TRUE.equals(state.load(LEMON_WAS_PICKED))) {
                ingame.sceneManager.refrigerator.hideLemon();
            }
        }

        // Load washer state
        if (TRUE.equals(state.load(WASHER_EXPLODED))) {
            ingame.sceneManager.washer.exploded = true;
            ingame.sceneManager.turnWorldUpsideDown();
        }
        if (TRUE.equals(state.load(WASHER_OPEN))) {
            ingame.sceneManager.washer.reversed = true;
        }
        if (TRUE.equals(state.load(WASHER_REVERSED))) {
            ingame.sceneManager.washer.onOpen();
        }
        if (TRUE.equals(state.load(WASHER_SOCK_INSIDE))) {
            ingame.sceneManager.washer.hasSock = true;
        }
        if (TRUE.equals(state.load(WASHER_CLOTHES_INSIDE))) {
            ingame.sceneManager.washer.hasPile = true;
        }
        if (TRUE.equals(state.load(WASHER_SOFTENER))) {
            ingame.sceneManager.washer.hasSoftener = true;
        }
        if (TRUE.equals(state.load(WASHER_DETERGENT))) {
            ingame.sceneManager.washer.hasDetergent = true;
        }

    }

    private static void loadScene(InGame ingame, GameState state) {
        int px = loadInt(state, PLAYER_X, 442);
        int py = loadInt(state, PLAYER_Y, 200);
        ingame.player.setPosition(px, py);

        int offset = loadInt(state, OFFSET);
        int diff = offset - ingame.sceneManager.x;
        ingame.sceneManager.offset(diff);
    }

    private static int loadInt(GameState state, String key, int defaultValue) {
        String value = state.load(key);
        if (value != null && !value.isEmpty()) {
            return Integer.parseInt(state.load(key));
        }
        return defaultValue;
    }

    private static int loadInt(GameState state, String key) {
        return loadInt(state, key, 0);
    }

    private static void loadInventory(InGame ingame, GameState state) {
        loadInventoryItem(ingame, state.load(INVENTORY0));
        loadInventoryItem(ingame, state.load(INVENTORY1));
        loadInventoryItem(ingame, state.load(INVENTORY2));
        loadInventoryItem(ingame, state.load(INVENTORY3));
        loadInventoryItem(ingame, state.load(INVENTORY4));
        loadInventoryItem(ingame, state.load(INVENTORY5));
        loadInventoryItem(ingame, state.load(INVENTORY6));
    }

    /**
     * Handles all pickupable items
     *
     * @param ingame
     * @param inventory
     */
    private static void loadInventoryItem(InGame ingame, String inventory) {
        if (inventory == null || inventory.isEmpty()) {
            return;
        }

        // TODO Do not use new, use the object itself
        if (pickupItems.contains(inventory)) {
            InventoryManager.pickup(ingame.sceneManager.getPickupObject(LanguageManager.objectName(inventory)));
        }
    }

    private static void loadSkills(InGame ingame, GameState state) {
        String skill = state.load(SKILL0);
        if (skill.equals(Dictionary.STOOL)) {
            SkillManager.pickup(ingame.sceneManager.getPickupObject(LanguageManager.objectName(skill)));
        }
    }

}
