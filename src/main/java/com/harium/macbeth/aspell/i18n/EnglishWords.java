package com.harium.macbeth.aspell.i18n;

import com.harium.macbeth.aspell.core.Interaction;

public class EnglishWords implements Dictionary {


    @Override
    public String asWord(Interaction interaction) {
        switch (interaction) {
            case OPEN:
                return "Open";
            case CLOSE:
                return "Close";
            case USE:
                return "Use";
            case LOOK_AT:
                return "Look at";
            case PICK_UP:
                return "Pick up";
            case PULL:
                return "Pull";
            case WALK:
                return "Walk to";
            default:
                return "";
        }
    }
}
