package com.harium.annebeth.aspell.i18n;

import com.harium.annebeth.aspell.core.Interaction;

public interface Dictionary {
    String LEMON = "lemon";
    String SOAP = "soap";
    String SOFTENER = "softener";
    String STOOL = "stool";

    String asWord(Interaction interaction);
    String objectName(String key);
}
