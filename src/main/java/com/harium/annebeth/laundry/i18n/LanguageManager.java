package com.harium.annebeth.laundry.i18n;

import com.harium.annebeth.laundry.core.Interaction;
import com.harium.annebeth.laundry.core.object.base.BaseObject;

public class LanguageManager {

    static Dictionary dictionary = new English();

    public static String asWord(Interaction interaction) {
        return dictionary.asWord(interaction);
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
