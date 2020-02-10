package com.harium.annebeth.laundry.i18n.languages;

import com.harium.annebeth.core.object.BaseObject;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.core.i18n.Gender;

import java.util.HashMap;
import java.util.Map;

public class Spanish implements Dictionary {

    Map<String, String> sentences = new HashMap<>();
    Map<String, Gender> genders = new HashMap<>();

    public Spanish() {
        add(ACTION_OPEN, "Abrir");
        add(ACTION_CLOSE, "Cerrar");
        add(ACTION_USE, "Usar");
        add(ACTION_LOOK_AT, "Examinar");
        add(ACTION_PICK_UP, "Recoger");
        add(ACTION_PULL, "Tirar");
        add(ACTION_WALK, "Caminar");
        add(SKILL_STOOL, "Ahora puedo alcanzar estantes más altos.");

        add(TO, "hasta");
        add(WITH, "con");

        add(CACTUS, "cacto", Gender.MASCULINE);
        add(CACTUS_FLOWER, "flor de cactus", Gender.FEMININE);
        add(JIMMY_PLANT, "Jimmy la Planta", Gender.NEUTER);
        add(LINO, "Lino", Gender.MASCULINE);
        add(CLOTHES_PILE, "pila de ropa", Gender.FEMININE);
        add(LEMON, "limón", Gender.MASCULINE);
        add(DETERGENT, "detergente para lavadora", Gender.MASCULINE);
        add(SOFTENER, "suavizante", Gender.MASCULINE);
        add(STOOL, "escabel", Gender.MASCULINE);
        add(SOCK, "calcetín sucio", Gender.MASCULINE);
        add(SHOYU, "salsa de soja", Gender.FEMININE);
        add(WASHER, "lavadora", Gender.FEMININE);
        add(FAN, "ventilador", Gender.MASCULINE);
        add(FAN_SWITCH, "interruptor", Gender.MASCULINE);
        add(TV, "televisión", Gender.FEMININE);
        add(TABLE, "mesa", Gender.FEMININE);
        add(TRASH, "basurero", Gender.MASCULINE);
        add(REFRIGERATOR, "nevera", Gender.FEMININE);
        add(LAMP, "lámpara", Gender.FEMININE);
        add(BED, "cama", Gender.FEMININE);
        add(CLOCK, "reloj despertador", Gender.MASCULINE);
        add(DRAWER,"mesilla de noche", Gender.FEMININE);
        add(DRAWER_LONG,"cajon", Gender.MASCULINE);
        add(MIRROR, "espejo", Gender.MASCULINE);
        add(PICTURE, "pintura", Gender.FEMININE);
        add(WINDOW, "ventana", Gender.FEMININE);
        add(WARDROBE, "ropero", Gender.MASCULINE);
        // Hall Objects
        add(FRONT_DOOR, "puerta", Gender.FEMININE);
        add(CLOTHES_DOOR, "ropa", Gender.FEMININE);
        add(LAMP_LONG, "lámpara", Gender.FEMININE);
        add(COUCH, "sofá", Gender.MASCULINE);
        add(PHONE, "telefono", Gender.MASCULINE);
        // Kitchen Objects
        add(CABINET, "armario", Gender.MASCULINE);
        add(OVEN, "horno", Gender.MASCULINE);
        add(DISH_WASHER, "lavavajillas", Gender.MASCULINE);
        add(SINK, "fregadero", Gender.MASCULINE);

        add(GOOD_MORNING, "¡Buen día!");
        add(LAUNDRY_DAY, "¡Hoy es dia de lavanderia!");
        add(STANDARD_LOOK_AT, "És solo {undet} {name}.");
        add(CANT_REACH, "No puedo alcanzar.");
        add(CANT_DO_THAT, "No puedo hacer eso.");
        add(DOESNT_FIT_POCKET, "No cabe en mi bolsillo");
        add(WHY_SOULD_I, "¿Por qué debería hacer eso?");
        add(BETTER_NOT, "Hmmm... Mejor no.");
        add(IT_SHOULD_BE_CLOSED, "Preciso fechar primeiro.");
        add(ALMOST_EMPTY, "Está casi vací{suf}.");
        add(EMPTY, "Está vací{suf}.");
        add(FULL_AGAIN, "Está llen{suf} otra vez.");
        add(CACTUS_HAS_FLOWER, "¿Oye, espera, es una flor?");
        add(CACTUS_CANT_PICK, "Esto dolería.");
        add(ENOUGH_FLOWERS, "No creo que necesite más flores.");
        add(JIMMY_PLANT_LOOK_AT, "Jimmy la Planta.");
        add(MIRROR_LOOK_AT, "Oye, esa soy yo.");
        add(BED_LOOK_AT, "Una cama muy cómoda.");
        add(FAN_SWITCH_LOOK_AT, "Un interruptor de ventilador con dos modos de giro.");
        add(FAN_SWITCH_CANT_PICK, "No puedo moverlo. Está atorado.");
        add(LINO_GREETINGS, "¡Hola {name}!");
        add(LINO_SLEEPING, "Adivina qué, él está durmiendo.");
        add(PILE_LOOK_AT, "Es un montón de ropa enorme.");
        add(SOFTENER_LOOK_AT, "Suavizante Magic: ácido, salgado e com um toque floral.");
        add(SOFTENER_ACID, "Bien, es ácido.");
        add(SOFTENER_ACID_SALTY, "Bien, es ácido y salado.");
        add(SOFTENER_ACID_FLOWERS, "Bien, es ácido con un toque de flor.");
        add(SOFTENER_SALTY, "Bien, é salgado.");
        add(SOFTENER_SALTY_FLOWERS, "Bien, é salgado com um toque floral.");
        add(SOFTENER_FLOWERS, "Bien, tiene un toque de flor.");
        add(SOFTENER_NOT_READY, "El suavizante aún no está listo.");
        add(WASHER_OPEN, "La lavadora debe estar abierta primero.");
        add(WASHER_MORE_CLOTHES, "Creo que tengo mas ropa.");
        add(WASHER_EMPTY, "Necesito poner ropa aquí dentro.");
        add(WASHER_DETERGENT, "Necesita un poco de detergente para lavadora.");
        add(WASHER_SOFTENER, "Necesita un poco de suavizante.");
        add(WASHER_DETERGENT_AND_SOFTENER, "Necesita un poco de detergente y suavizante.");
        add(WASHER_REVERSE, "Debo encontrar una manera de REVERSAR esta lavadora.");
        add(WASHER_REVERSED, "Una lavadora invertida.");
        add(WASHER_REVERSED_NAME, "lavadora invertida");
        add(MISSION_COMPLETE, "¡Misión cumplida!");
        sentences.put(SOMETHING_WRONG, "Uh, oh, algo está mal.");
        add(NO_WORDS, "...");
        add(SPIDER_CRAP, "¡Araña santa!");
        add(WASHER_HINT, "Siento que tendré que volver a lavar la ropa.");
        add(HERE_I_GO_AGAIN, "Aquí voy de nuevo.");
        add(HAS_TO_WORK, "Tiene que funcionar.");
    }

    private void add(String id, String sentence, Gender gender) {
        add(id, sentence);
        genders.put(sentence, gender);
    }

    private void add(String id, String sentence) {
        sentences.put(id, sentence);
    }

    public void loadCredits() {
        add(CREDITS_THANK_YOU, "¡Gracias por jugar!");
        add(CREDITS_ART, "Arte");
        add(CREDITS_ORIGINAL_WITCH, "Imágenes originales de la Bruja por doudoulolita");
        add(CREDITS_ORIGINAL_HOUSE, "Imágenes de la Casa por PurpleHeart");
        add(CREDITS_CACTUS_FLOWER, "Flor de Cactus por captainluna");
        add(CREDITS_ORIGINAL_CAT, "Imagen del gato por GarGar");
        add(CREDITS_MAGNIFYING_GLASS, "Imagen da lupa por Angrycheese");
        add(CREDITS_UI, "UI por Kenney");
        add(CREDITS_SPLASH, "Imagen de la abertura por GraphicMama Team");
        add(CREDITS_FONT, "Fonte");
        add(CREDITS_FONT_NAME, "Press Start 2P");
        add(CREDITS_SFX, "Efectos Sonoros");
        add(CREDITS_SFX_CREATOR, "SubspaceAudio");
        add(CREDITS_MUSIC, "Músicas");
        add(CREDITS_MUSIC_HOUSE, "Melody Town Theme por CleytonKauffman");
        add(CREDITS_MUSIC_UPSIDE_DOWN, "Facing it por Komiku");
        add(CREDITS_MUSIC_MENU, "A Simple Trifle por jestar");
        add(CREDITS_TOOLS, "Herramientas");
        add(CREDITS_BETA_TESTERS,"Beta Testers Oficiales");
        add(CREDITS_MADE_BY,"Hecho Por");
        add(CREDITS_SPECIAL_THANKS,"Agradecimientos");
        add(CREDITS_THANKS_DISCORD, "Todos en el canal del Discord de la LibGDX");
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
        String undet = undet(object.getName());
        String det = det(object.getName());
        String result = sentence.replaceAll("\\{name\\}", object.getName());
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
