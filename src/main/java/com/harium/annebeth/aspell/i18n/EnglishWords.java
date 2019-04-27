package com.harium.annebeth.aspell.i18n;

import com.harium.annebeth.aspell.core.Interaction;

import java.util.HashMap;
import java.util.Map;

public class EnglishWords implements Dictionary {

    Map<String, String> words = new HashMap<String, String>();

    {
        words.put(CACTUS, "cactus");
        words.put(CACTUS_FLOWER, "cactus flower");
        words.put(JIMMY_PLANT, "Jimmy the Plant");
        words.put(LINO, "Lino");
        words.put(CLOTHES_PILE, "pile of clothes");
        words.put(LEMON, "lemon");
        words.put(SOAP, "laundry detergent");
        words.put(SOFTENER, "fabric softener");
        words.put(STOOL, "stool");
        words.put(SOCK, "dirty sock");
        words.put(SHOYU, "soy sauce");
        words.put(WASHER, "washer");
        words.put(FAN, "fan");
        words.put(FAN_SWITCH, "fan switch");
        words.put(TV, "television");
        words.put(REFRIGERATOR, "refrigerator");
    }

    @Override
    public String asWord(Interaction interaction) {
        switch (interaction) {
            case OPEN:
                return "Open";
            case CLOSE:
                return "Close";
            case USE:
                return "Use";
            case LOOK_AT:
                return "Look at";
            case PICK_UP:
                return "Pick up";
            case PULL:
                return "Pull";
            case WALK:
                return "Walk to";
            default:
                return "";
        }
    }

    @Override
    public String objectName(String key) {
        if (!words.containsKey(key)) {
            System.out.println(key + ": not implemented.");
        }
        return words.get(key);
    }
}
