package com.harium.annebeth.core;

import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.core.object.BaseObject;
import com.harium.annebeth.core.object.PickLevel;
import com.harium.annebeth.core.player.Player;

public class Context {

    public static final BaseObject NULL_OBJECT = new BaseObject("");

    private static Interaction interaction = Interaction.NONE;
    private static BaseObject object = NULL_OBJECT;
    private static BaseObject with = NULL_OBJECT;

    public static void reachObject(Player player) {
        if (object == null) {
            return;
        } else if (object.disabled || object.getName().isEmpty()) {
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
            case USE_WITH:
                return;
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

    public static void changeObject(BaseObject object) {
        if (!hasObject()) {
            Context.object = object;
        } else if (Context.interaction == Interaction.USE || Context.interaction == Interaction.USE_WITH) {
            with = object;
        }
    }

    public static void reset() {
        interaction = Interaction.NONE;
        object = NULL_OBJECT;
        with = NULL_OBJECT;
    }

    public static void setInteraction(Interaction interaction) {
        Context.interaction = interaction;
    }

    public static Interaction getInteraction() {
        return interaction;
    }

    public static boolean hasObject() {
        return object != NULL_OBJECT;
    }

    public static BaseObject getObject() {
        return object;
    }

    public static BaseObject getWith() {
        return with;
    }

    public static void resetObject() {
        object = NULL_OBJECT;
    }
}
