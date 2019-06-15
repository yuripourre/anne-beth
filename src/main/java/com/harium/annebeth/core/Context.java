package com.harium.annebeth.core;

import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.core.object.BaseObject;
import com.harium.annebeth.core.object.PickLevel;
import com.harium.annebeth.core.player.Player;
import com.harium.annebeth.core.ui.ActionUIManager;

public class Context {

    public static final BaseObject NULL_OBJECT = new BaseObject("");
    public static final int CONTEXT_SENTENCE = 382;

    public static Interaction interaction = Interaction.NONE;
    public static BaseObject object = NULL_OBJECT;
    public static BaseObject with = NULL_OBJECT;

    public static void reachObject(Player player) {
        if (object == null) {
            return;
        } else if (object.disabled || object.name.isEmpty()) {
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
                PickLevel level = object.onPickUp();
                switch (level) {
                    case DOWN:
                        player.pickDown();
                        break;
                    case MEDIUM:
                        player.pickMedium();
                        break;
                    case HIGH:
                        player.pickHigh();
                        break;
                }
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
        interaction = Interaction.NONE;
        object = NULL_OBJECT;
        with = NULL_OBJECT;
    }

}
