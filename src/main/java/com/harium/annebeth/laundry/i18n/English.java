package com.harium.annebeth.laundry.i18n;

import com.harium.annebeth.laundry.core.Interaction;
import com.harium.annebeth.laundry.core.object.base.BaseObject;

import java.util.HashMap;
import java.util.Map;

public class English implements Dictionary {

    Map<String, String> sentences = new HashMap<>();

    public English() {
        sentences.put(ACTION_OPEN, "Open");
        sentences.put(ACTION_CLOSE, "Close");
        sentences.put(ACTION_USE, "Use");
        sentences.put(ACTION_LOOK_AT, "Look at");
        sentences.put(ACTION_PICK_UP, "Pick up");
        sentences.put(ACTION_PULL, "Pull");
        sentences.put(ACTION_WALK, "Walk");
        sentences.put(TO, "to");
        sentences.put(WITH, "with");

        sentences.put(CACTUS, "cactus");
        sentences.put(CACTUS_FLOWER, "cactus flower");
        sentences.put(JIMMY_PLANT, "Jimmy the Plant");
        sentences.put(LINO, "Lino");
        sentences.put(CLOTHES_PILE, "pile of clothes");
        sentences.put(LEMON, "lemon");
        sentences.put(SOAP, "laundry detergent");
        sentences.put(SOFTENER, "fabric softener");
        sentences.put(STOOL, "stool");
        sentences.put(SOCK, "dirty sock");
        sentences.put(SHOYU, "soy sauce");
        sentences.put(WASHER, "washer");
        sentences.put(FAN, "fan");
        sentences.put(FAN_SWITCH, "fan switch");
        sentences.put(TV, "television");
        sentences.put(TABLE, "table");
        sentences.put(TRASH, "trash");
        sentences.put(REFRIGERATOR, "refrigerator");
        sentences.put(MIRROR, "mirror");
        sentences.put(BED, "bed");

        sentences.put(GOOD_MORNING, "Good morning!");
        sentences.put(LAUNDRY_DAY, "Today is Laundry Day!");
        sentences.put(STANDARD_LOOK_AT, "It is just a {name}.");
        sentences.put(CANT_REACH, "I can't reach it.");
        sentences.put(WHY_SOULD_I, "Why should I do that?");
        sentences.put(IT_SHOULD_BE_CLOSED, "It should be closed first.");
        sentences.put(ALMOST_EMPTY, "It is almost empty.");
        sentences.put(EMPTY, "It's empty.");
        sentences.put(FULL_AGAIN, "It's full again.");
        sentences.put(CACTUS_HAS_FLOWER, "Oh, wait, is it a flower?");
        sentences.put(JIMMY_PLANT_LOOK_AT, "Jimmy the Plant.");
        sentences.put(MIRROR_LOOK_AT, "Hey, it's me.");
        sentences.put(BED_LOOK_AT, "A very comfortable bed.");
        sentences.put(FAN_SWITCH_LOOK_AT, "A {name} with two spin modes.");
        sentences.put(LINO_GREETINGS, "Hi {name}!");
        sentences.put(LINO_SLEEPING, "Guess what, he is sleeping.");
        sentences.put(PILE_LOOK_AT, "It is a huge pile of clothes.");
        sentences.put(SOFTENER_LOOK_AT, "Magi Softener: acid, salty with a touch of flowers.");
        sentences.put(SOFTENER_ACID, "Well, it's acid.");
        sentences.put(SOFTENER_ACID_SALTY, "Well, it's acid and salty.");
        sentences.put(SOFTENER_ACID_FLOWERS, "Well, it's acid with a touch of flowers.");
        sentences.put(SOFTENER_SALTY, "Well, it's salty.");
        sentences.put(SOFTENER_SALTY_FLOWERS, "Well, it's salty with a touch of flowers.");
        sentences.put(SOFTENER_FLOWERS, "Well, it has a touch of flowers.");
        sentences.put(WASHER_OPEN, "The washer should be open first.");
        sentences.put(WASHER_MORE_CLOTHES, "I think I have more clothes.");
        sentences.put(WASHER_EMPTY, "It needs some clothes.");
        sentences.put(WASHER_DETERGENT, "It needs some detergent.");
        sentences.put(WASHER_SOFTENER, "It needs some softener.");
        sentences.put(WASHER_DETERGENT_AND_SOFTENER, "It needs detergent and softener.");
        sentences.put(WASHER_REVERSE, "I must figure a way to REVERSE this situation.");
        sentences.put(WASHER_REVERSED, "A reversed washer.");
        sentences.put(MISSION_COMPLETE, "Mission Accomplished!");
        sentences.put(SOMETHING_WRONG, "Uh oh, something is wrong.");
        sentences.put(NO_WORDS, "...");
        sentences.put(HERE_I_GO_AGAIN, "Here I go again.");
        sentences.put(HAS_TO_WORK, "It has to work.");
    }

    @Override
    public String asWord(Interaction interaction) {
        switch (interaction) {
            case OPEN:
                return sentences.get(ACTION_OPEN);
            case CLOSE:
                return sentences.get(ACTION_CLOSE);
            case USE:
                return sentences.get(ACTION_USE);
            case LOOK_AT:
                return sentences.get(ACTION_LOOK_AT);
            case PICK_UP:
                return sentences.get(ACTION_PICK_UP);
            case PULL:
                return sentences.get(ACTION_PULL);
            case WALK:
                return sentences.get(ACTION_WALK);
            default:
                return "";
        }
    }

    @Override
    public String objectName(String key) {
        // TODO handle grammatical gender
        return sentence(key);
    }

    @Override
    public String sentence(String key) {
        if (!sentences.containsKey(key)) {
            System.out.println(key + ": not implemented.");
            return "";
        }
        String sentence = sentences.get(key);
        return sentence;
    }

    @Override
    public String sentence(String key, BaseObject object) {
        String text = sentence(key);
        text = interpolate(text, object);

        return text;
    }

    private String interpolate(String sentence, BaseObject object) {
        String result = sentence.replaceAll("\\{name\\}", object.name);
        return result;
    }
}
