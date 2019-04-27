package com.harium.annebeth.aspell.i18n;

import com.harium.annebeth.aspell.core.Interaction;

public interface Dictionary {

    String CACTUS = "cactus";
    String CACTUS_FLOWER = "cactus_flower";
    String CLOTHES_PILE = "pile";
    String LEMON = "lemon";
    String SOAP = "soap";
    String SOFTENER = "softener";
    String STOOL = "stool";
    String SOCK = "sock";
    String SHOYU = "shoyu";
    String WASHER = "washer";
    String REFRIGERATOR = "fridge";
    String FAN = "fan";
    String FAN_SWITCH = "fanswtc";
    String TV = "tv";
    String TRASH = "trash";
    String JIMMY_PLANT = "jimmy";
    String LINO = "lino";


    String asWord(Interaction interaction);
    String objectName(String key);
}
