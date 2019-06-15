package com.harium.annebeth.core.ui;

import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.core.i18n.LanguageManager;

public class DialogHelper {

    public static String negativeSentence() {
        // TODO Randomize
        return LanguageManager.sentence(Dictionary.WHY_SOULD_I);
    }

}
