package com.harium.macbeth.aspell.i18n;

import com.harium.macbeth.aspell.core.Interaction;

import java.util.HashMap;
import java.util.Map;

public class EnglishWords implements Dictionary {

    Map<String, String> words = new HashMap<String, String>();

    {
        words.put(STOOL, "stool");
        words.put(LEMON, "lemon");
        words.put(SOFTENER, "fabric softener");
        words.put(SOAP, "laundry soap");
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
        return words.get(key);
    }
}
