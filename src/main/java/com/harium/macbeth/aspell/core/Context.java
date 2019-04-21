package com.harium.macbeth.aspell.core;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.macbeth.aspell.i18n.LanguageManager;
import com.harium.macbeth.aspell.object.BaseObject;

public class Context {

    public static final BaseObject NULL_OBJECT = new BaseObject("");
    public static final int CONTEXT_SENTENCE = 382;

    public static Interaction interaction = Interaction.WALK;
    public static BaseObject object = NULL_OBJECT;
    public static BaseObject with = NULL_OBJECT;

    public static void reachObject() {
        if (object.disabled) {
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

    public static void draw(Graphics g) {
        if (interaction == Interaction.WALK && object == NULL_OBJECT) {
            return;
        }

        String sentence = asWord(interaction);

        if (object != NULL_OBJECT) {
            sentence += " " + object.name;
            if (interaction == Interaction.USE && object.canUse) {
                sentence += " with";
                if (with != NULL_OBJECT) {
                    sentence += " " + with.name;
                }
            }
        }

        // Draw action
        g.drawStringX(sentence, CONTEXT_SENTENCE);
    }

    private static String asWord(Interaction interaction) {
        return LanguageManager.InteractionAsWord(interaction);
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
