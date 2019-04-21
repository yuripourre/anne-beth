package com.harium.macbeth.aspell.i18n;

import com.harium.macbeth.aspell.core.Interaction;

public class LanguageManager {

    static Dictionary dictionary = new EnglishWords();

    public static String InteractionAsWord(Interaction interaction) {
        return dictionary.asWord(interaction);
    }


}
