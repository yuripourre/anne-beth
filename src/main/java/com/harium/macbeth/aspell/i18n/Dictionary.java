package com.harium.macbeth.aspell.i18n;

import com.harium.macbeth.aspell.core.Interaction;

public interface Dictionary {
    String LEMON = "lemon";
    String SOAP = "soap";
    String SOFTENER = "softener";
    String STOOL = "stool";

    String asWord(Interaction interaction);
    String objectName(String key);
}
