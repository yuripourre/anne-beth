package com.harium.annebeth.core.i18n;

import com.harium.annebeth.core.Interaction;
import com.harium.annebeth.core.object.BaseObject;
import com.harium.annebeth.laundry.i18n.languages.English;
import com.harium.annebeth.laundry.i18n.languages.Portuguese;
import com.harium.annebeth.laundry.i18n.languages.Spanish;
import com.harium.annebeth.laundry.i18n.Dictionary;

import java.util.Locale;

import static com.harium.annebeth.laundry.i18n.Dictionary.*;

public class LanguageManager {

    private static Dictionary dictionary;

    public static void init() {
        String lang = Locale.getDefault().toString();

        if (lang.startsWith("es")) {
            dictionary = new Spanish();
        } else if (lang.startsWith("pt")) {
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
            case USE_WITH:
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

    public static void loadCredits() {
        String dummy = LanguageManager.sentence(Dictionary.CREDITS_THANK_YOU);
        if (dummy == null || dummy.isEmpty()) {
            dictionary.loadCredits();
        }
    }
}
