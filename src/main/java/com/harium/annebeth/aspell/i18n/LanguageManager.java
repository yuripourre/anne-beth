package com.harium.annebeth.aspell.i18n;

import com.harium.annebeth.aspell.core.Interaction;

public class LanguageManager {

    static Dictionary dictionary = new EnglishWords();

    public static String InteractionAsWord(Interaction interaction) {
        return dictionary.asWord(interaction);
    }

    public static String objectName(String key) {
        return dictionary.objectName(key);
    }
}
