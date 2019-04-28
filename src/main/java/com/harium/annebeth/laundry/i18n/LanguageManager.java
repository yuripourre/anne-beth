package com.harium.annebeth.laundry.i18n;

import com.harium.annebeth.laundry.core.Interaction;

public class LanguageManager {

    static Dictionary dictionary = new English();

    public static String asWord(Interaction interaction) {
        return dictionary.asWord(interaction);
    }

    public static String objectName(String key) {
        return dictionary.objectName(key);
    }
}
