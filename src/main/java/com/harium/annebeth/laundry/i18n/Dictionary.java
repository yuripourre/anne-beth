package com.harium.annebeth.laundry.i18n;

import com.harium.annebeth.core.i18n.LanguageManager;
import com.harium.annebeth.core.object.BaseObject;
import com.harium.annebeth.core.object.HitBoxObject;
import com.harium.annebeth.core.object.HitBoxOpenableObject;

public interface Dictionary {

    String ACTION_OPEN = "open";
    String ACTION_CLOSE = "close";
    String ACTION_USE = "use";
    String ACTION_LOOK_AT = "look_at";
    String ACTION_PICK_UP = "pick";
    String ACTION_PULL = "pull";
    String ACTION_WALK = "walk";

    String GOOD_MORNING = "good_morning";
    String LAUNDRY_DAY = "laundry day";
    String SKILL_STOOL = "skill_stool";

    String CACTUS = "cactus";
    String CACTUS_FLOWER = "cactus_flower";
    String CLOTHES_PILE = "pile";
    String LEMON = "lemon";
    String DETERGENT = "soap";
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
    String TABLE = "table";
    // Bedroom Objects
    String BED = "bed";
    String LAMP = "lamp";
    String DRAWER = "drawer";
    String DRAWER_LONG = "drawer_l";
    String CLOCK = "clock";
    String MIRROR = "mirror";
    String PICTURE = "picture";
    String WINDOW = "window";
    String WARDROBE = "wardrobe";
    // Hall Objects
    String FRONT_DOOR = "door";
    String CLOTHES_DOOR = "clothesd";
    String LAMP_LONG = "lampl";
    String COUCH = "couch";
    String PHONE = "phone";

    String STANDARD_LOOK_AT = "std_look";
    String CANT_REACH = "cant_reach";
    String CANT_DO_THAT = "cant_do";
    String WHY_SOULD_I = "why_should_i";
    String BETTER_NOT = "better_not";
    String IT_SHOULD_BE_CLOSED = "it_close";
    String ALMOST_EMPTY = "almost_empty";
    String EMPTY = "empty";
    String FULL_AGAIN = "full";
    String CACTUS_HAS_FLOWER = "cactus_has_flower";
    String CACTUS_CANT_PICK = "cactus_hurts";
    String ENOUGH_FLOWERS = "enough_flowers";
    String JIMMY_PLANT_LOOK_AT = "jimmy_look";
    String FAN_SWITCH_LOOK_AT = "fanswitch_look";
    String FAN_SWITCH_CANT_PICK = "fanswitch_cntpick";
    String BED_LOOK_AT = "bed_look";
    String MIRROR_LOOK_AT = "mirror_look";
    String LINO_GREETINGS = "linohi";
    String LINO_SLEEPING = "linosleepy";
    String PILE_LOOK_AT = "pile_look";
    String SOFTENER_LOOK_AT = "soft_look";
    String SOFTENER_ACID = "soft_acid";
    String SOFTENER_ACID_SALTY = "soft_acid_salt";
    String SOFTENER_ACID_FLOWERS = "soft_acid_flower";
    String SOFTENER_SALTY = "soft_salt";
    String SOFTENER_SALTY_FLOWERS = "soft_salt_flower";
    String SOFTENER_FLOWERS = "soft_flower";
    String SOFTENER_NOT_READY = "soft_nready";
    String WASHER_OPEN = "washer_open";
    String WASHER_MORE_CLOTHES = "washer_clothes";
    String WASHER_EMPTY = "washer_empty";
    String WASHER_DETERGENT = "washer_detergent";
    String WASHER_SOFTENER = "washer_softener";
    String WASHER_DETERGENT_AND_SOFTENER = "washer_detandsoft";
    String WASHER_REVERSE = "washer_reverse";
    String WASHER_REVERSED = "washer_reversed";
    String WASHER_REVERSED_NAME = "washer_reversed_name";

    String MISSION_COMPLETE = "mission_complete";
    String SOMETHING_WRONG = "something_wrong";
    String NO_WORDS = "...";
    String SPIDER_CRAP = "scrap";
    String WASHER_HINT = "wash_hint";
    String HERE_I_GO_AGAIN = "here_again";
    String HAS_TO_WORK = "has_work";
    String TO = "to";
    String WITH = "with";

    String CREDITS_THANK_YOU = "thank_you";
    String CREDITS_ART = "art";
    String CREDITS_ORIGINAL_WITCH = "witchimg";
    String CREDITS_ORIGINAL_HOUSE = "houseimg";
    String CREDITS_CACTUS_FLOWER = "flowerimg";
    String CREDITS_ORIGINAL_CAT = "catimg";
    String CREDITS_MAGNIFYING_GLASS = "magimg";
    String CREDITS_UI = "ui";
    String CREDITS_SPLASH = "splash";
    String CREDITS_FONT = "font";
    String CREDITS_FONT_NAME = "font_name";
    String CREDITS_SFX = "sfx";
    String CREDITS_SFX_CREATOR = "sfx_guy";
    String CREDITS_MUSIC = "music";
    String CREDITS_MUSIC_MENU = "m_menu";
    String CREDITS_MUSIC_HOUSE = "m_house";
    String CREDITS_MUSIC_UPSIDE_DOWN = "m_upside";
    String CREDITS_TOOLS = "tools";
    String CREDITS_BETA_TESTERS = "beta";
    String CREDITS_MADE_BY = "made";
    String CREDITS_SPECIAL_THANKS = "thanks";
    String CREDITS_THANKS_DISCORD = "tdiscord";

    void loadCredits();

    String objectName(String key);

    String sentence(String key);

    String sentence(String key, BaseObject object);
}
