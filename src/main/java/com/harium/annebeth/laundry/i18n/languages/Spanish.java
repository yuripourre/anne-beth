package com.harium.annebeth.laundry.i18n.languages;

import com.harium.annebeth.laundry.core.object.base.BaseObject;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.laundry.i18n.Gender;

import java.util.HashMap;
import java.util.Map;

public class Spanish implements Dictionary {

    Map<String, String> sentences = new HashMap<>();
    Map<String, Gender> genders = new HashMap<>();

    public Spanish() {
        sentences.put(ACTION_OPEN, "Abrir");
        sentences.put(ACTION_CLOSE, "Cerrar");
        sentences.put(ACTION_USE, "Usar");
        sentences.put(ACTION_LOOK_AT, "Examinar");
        sentences.put(ACTION_PICK_UP, "Recoger");
        sentences.put(ACTION_PULL, "Tirar");
        sentences.put(ACTION_WALK, "Caminar");
        sentences.put(TO, "hasta");
        sentences.put(WITH, "con");

        sentences.put(CACTUS, "cacto");
        sentences.put(CACTUS_FLOWER, "flor de cactus");
        sentences.put(JIMMY_PLANT, "Jimmy la Planta");
        sentences.put(LINO, "Lino");
        sentences.put(CLOTHES_PILE, "pila de ropa");
        sentences.put(LEMON, "limón");
        sentences.put(SOAP, "detergente para lavadora");
        sentences.put(SOFTENER, "suavizante");
        sentences.put(STOOL, "escabel");
        sentences.put(SOCK, "calcetín sucio");
        sentences.put(SHOYU, "salsa de soja");
        sentences.put(WASHER, "lavadora");
        sentences.put(FAN, "ventilador");
        sentences.put(FAN_SWITCH, "interruptor");
        sentences.put(TV, "televisión");
        sentences.put(TABLE, "mesa");
        sentences.put(TRASH, "bote de basura");
        sentences.put(REFRIGERATOR, "refrigerador");
        sentences.put(MIRROR, "espejo");
        sentences.put(BED, "cama");

        genders.put(sentences.get(CACTUS), Gender.MASCULINE);
        genders.put(sentences.get(CACTUS_FLOWER), Gender.FEMININE);
        genders.put(sentences.get(JIMMY_PLANT), Gender.NEUTER);
        genders.put(sentences.get(LINO), Gender.MASCULINE);
        genders.put(sentences.get(CLOTHES_PILE), Gender.FEMININE);
        genders.put(sentences.get(LEMON), Gender.MASCULINE);
        genders.put(sentences.get(SOAP), Gender.MASCULINE);
        genders.put(sentences.get(SOFTENER), Gender.MASCULINE);
        genders.put(sentences.get(STOOL), Gender.MASCULINE);
        genders.put(sentences.get(SOCK), Gender.FEMININE);
        genders.put(sentences.get(SHOYU), Gender.MASCULINE);
        genders.put(sentences.get(WASHER), Gender.FEMININE);
        genders.put(sentences.get(FAN), Gender.MASCULINE);
        genders.put(sentences.get(FAN_SWITCH), Gender.MASCULINE);
        genders.put(sentences.get(TV), Gender.FEMININE);
        genders.put(sentences.get(TABLE), Gender.FEMININE);
        genders.put(sentences.get(TRASH), Gender.FEMININE);
        genders.put(sentences.get(REFRIGERATOR), Gender.FEMININE);
        genders.put(sentences.get(MIRROR), Gender.MASCULINE);
        genders.put(sentences.get(BED), Gender.FEMININE);

        sentences.put(GOOD_MORNING, "¡Buen día!");
        sentences.put(LAUNDRY_DAY, "¡Hoy es dia de lavanderia!");
        sentences.put(STANDARD_LOOK_AT, "És solo {undet} {name}.");
        sentences.put(CANT_REACH, "No puedo alcanzar.");
        sentences.put(CANT_DO_THAT, "No puedo hacer eso.");
        sentences.put(WHY_SOULD_I, "¿Por qué debería hacer eso?");
        sentences.put(IT_SHOULD_BE_CLOSED, "Preciso fechar primeiro.");
        sentences.put(ALMOST_EMPTY, "Está casi vací{suf}.");
        sentences.put(EMPTY, "Está vací{suf}.");
        sentences.put(FULL_AGAIN, "Está llen{suf} otra vez.");
        sentences.put(CACTUS_HAS_FLOWER, "¿Oye, espera, es una flor?");
        sentences.put(ENOUGH_FLOWERS, "No creo que necesite más flores.");
        sentences.put(JIMMY_PLANT_LOOK_AT, "Jimmy la Planta.");
        sentences.put(MIRROR_LOOK_AT, "Oye, esa soy yo.");
        sentences.put(BED_LOOK_AT, "Una cama muy cómoda.");
        sentences.put(FAN_SWITCH_LOOK_AT, "Un interruptor de ventilador con dos modos de giro.");
        sentences.put(LINO_GREETINGS, "¡Hola {name}!");
        sentences.put(LINO_SLEEPING, "Adivina qué, él está durmiendo.");
        sentences.put(PILE_LOOK_AT, "Es un montón de ropa enorme.");
        sentences.put(SOFTENER_LOOK_AT, "Suavizante Magic: ácido, salgado e com um toque floral.");
        sentences.put(SOFTENER_ACID, "Bien, es ácido.");
        sentences.put(SOFTENER_ACID_SALTY, "Bien, es ácido y salado.");
        sentences.put(SOFTENER_ACID_FLOWERS, "Bien, es ácido con un toque de flor.");
        sentences.put(SOFTENER_SALTY, "Bien, é salgado.");
        sentences.put(SOFTENER_SALTY_FLOWERS, "Bien, é salgado com um toque floral.");
        sentences.put(SOFTENER_FLOWERS, "Bien, tiene un toque de flor.");
        sentences.put(WASHER_OPEN, "La lavadora debe estar abierta primero.");
        sentences.put(WASHER_MORE_CLOTHES, "Creo que tengo mas ropa.");
        sentences.put(WASHER_EMPTY, "Necesito poner ropa aquí dentro.");
        sentences.put(WASHER_DETERGENT, "Necesita un poco de detergente para lavadora.");
        sentences.put(WASHER_SOFTENER, "Necesita un poco de suavizante.");
        sentences.put(WASHER_DETERGENT_AND_SOFTENER, "Necesita un poco de detergente y suavizante.");
        sentences.put(WASHER_REVERSE, "Debo encontrar una manera de REVERSAR esta situación.");
        sentences.put(WASHER_REVERSED, "Una arandela invertida.");
        sentences.put(MISSION_COMPLETE, "¡Misión cumplida!");
        sentences.put(SOMETHING_WRONG, "Uh oh, isso não está certo.");
        sentences.put(NO_WORDS, "...");
        sentences.put(SPIDER_CRAP, "¡Araña santa!");
        sentences.put(HERE_I_GO_AGAIN, "Aquí voy de nuevo.");
        sentences.put(HAS_TO_WORK, "Tiene que funcionar.");
    }

    public void loadCredits() {
        sentences.put(CREDITS_THANK_YOU, "¡Gracias por jugar!");
        sentences.put(CREDITS_ART, "Arte");
        sentences.put(CREDITS_ORIGINAL_WITCH, "Imágenes originales de la Bruja por doudoulolita");
        sentences.put(CREDITS_ORIGINAL_HOUSE, "Imágenes de la Casa por PurpleHeart");
        sentences.put(CREDITS_CACTUS_FLOWER, "Flor de Cactus por captainluna");
        sentences.put(CREDITS_ORIGINAL_CAT, "Imagen del gato por GarGar");
        sentences.put(CREDITS_UI, "UI por Kenney");
        sentences.put(CREDITS_SPLASH, "Imagen de la abertura por GraphicMama Team");
        sentences.put(CREDITS_FONT, "Fonte");
        sentences.put(CREDITS_FONT_NAME, "Press Start 2P");
        sentences.put(CREDITS_SFX, "Efectos Sonoros");
        sentences.put(CREDITS_SFX_CREATOR, "SubspaceAudio");
        sentences.put(CREDITS_MUSIC, "Músicas");
        sentences.put(CREDITS_MUSIC_HOUSE, "Melody Town Theme por CleytonKauffman");
        sentences.put(CREDITS_MUSIC_UPSIDE_DOWN, "Facing it por Komiku");
        sentences.put(CREDITS_MUSIC_MENU, "A Simple Trifle por jestar");
        sentences.put(CREDITS_TOOLS, "Herramientas");
        sentences.put(CREDITS_BETA_TESTERS,"Beta Testers Oficiales");
        sentences.put(CREDITS_MADE_BY,"Hecho Por");
        sentences.put(CREDITS_SPECIAL_THANKS,"Agradecimientos");
        sentences.put(CREDITS_THANKS_DISCORD, "Todos en el canal del Discord de la LibGDX");
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
        String sentence = sentence(key);
        sentence = interpolate(sentence, object);
        return sentence;
    }

    private String interpolate(String sentence, BaseObject object) {
        String undet = undet(object.name);
        String det = det(object.name);
        String result = sentence.replaceAll("\\{name\\}", object.name);
        result = result.replaceAll("\\{undet\\}", undet);
        result = result.replaceAll("\\{det\\}", det);
        result = result.replaceAll("\\{suf\\}", det);
        return result;
    }

    private String suffix(String name) {
        switch (genders.get(name)) {
            default:
            case NEUTER:
            case MASCULINE:
                return "o";
            case FEMININE:
                return "a";
        }
    }

    private String det(String name) {
        switch (genders.get(name)) {
            default:
            case NEUTER:
            case MASCULINE:
                return "el";
            case FEMININE:
                return "la";
        }
    }

    private String undet(String name) {
        switch (genders.get(name)) {
            default:
            case NEUTER:
                return "";
            case MASCULINE:
                return "un";
            case FEMININE:
                return "una";
        }
    }

}
