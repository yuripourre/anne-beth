package com.harium.annebeth.aspell.core;

import com.harium.annebeth.aspell.i18n.LanguageManager;
import com.harium.annebeth.aspell.core.object.base.BaseObject;

public class Context {

    public static final BaseObject NULL_OBJECT = new BaseObject("");
    public static final int CONTEXT_SENTENCE = 382;

    public static Interaction interaction = Interaction.WALK;
    public static BaseObject object = NULL_OBJECT;
    public static BaseObject with = NULL_OBJECT;

    public static void reachObject() {
        if (object.disabled || object.name.isEmpty()) {
            reset();
            return;
        }
        switch (interaction) {
            case OPEN:
                object.onOpen();
                break;
            case CLOSE:
                object.onClose();
                break;
            case USE:
                object.onUse(with);
                break;
            case LOOK_AT:
                object.onLook();
                break;
            case PICK_UP:
                object.onPickUp();
                break;
            case PULL:
                object.onPull();
                break;
            case WALK:
            default:
                break;
        }

        reset();
    }

    public static String asWord(Interaction interaction) {
        return LanguageManager.asWord(interaction);
    }

    public static void setObject(BaseObject object) {
        if (Context.object == NULL_OBJECT) {
            Context.object = object;
        } else if (Context.interaction == Interaction.USE) {
            with = object;
        }
    }

    private static void reset() {
        interaction = Interaction.WALK;
        object = NULL_OBJECT;
        with = NULL_OBJECT;
    }

}
