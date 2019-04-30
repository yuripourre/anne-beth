package com.harium.annebeth.laundry.i18n;

import com.harium.annebeth.laundry.core.Interaction;
import com.harium.annebeth.laundry.core.object.base.BaseObject;

import static com.harium.annebeth.laundry.i18n.Dictionary.*;

public class LanguageManager {

    private static Dictionary dictionary;

    public static void init(String lang) {
        if ("pt_BR".equals(lang)) {
           dictionary = new Portuguese();
        } else {
            dictionary = new English();
        }
    }

    public static String asWord(Interaction interaction) {
        switch (interaction) {
            case OPEN:
                return dictionary.sentence(ACTION_OPEN);
            case CLOSE:
                return dictionary.sentence(ACTION_CLOSE);
            case USE:
                return dictionary.sentence(ACTION_USE);
            case LOOK_AT:
                return dictionary.sentence(ACTION_LOOK_AT);
            case PICK_UP:
                return dictionary.sentence(ACTION_PICK_UP);
            case PULL:
                return dictionary.sentence(ACTION_PULL);
            case WALK:
                return dictionary.sentence(ACTION_WALK);
            default:
                return "";
        }
    }

    public static String objectName(String key) {
        return dictionary.objectName(key);
    }

    public static String sentence(String key) {
        return dictionary.sentence(key);
    }

    public static String sentence(String key, BaseObject object) {
        return dictionary.sentence(key, object);
    }

}
