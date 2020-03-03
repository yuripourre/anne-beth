package com.harium.annebeth.laundry.i18n.languages;

import com.harium.annebeth.core.i18n.Gender;
import com.harium.annebeth.core.object.BaseObject;
import com.harium.annebeth.laundry.i18n.Dictionary;

import java.util.HashMap;
import java.util.Map;

public class Portuguese implements Dictionary {

    Map<String, String> sentences = new HashMap<>();
    Map<String, Gender> genders = new HashMap<>();

    public Portuguese() {
        add(ACTION_OPEN, "Abrir");
        add(ACTION_CLOSE, "Fechar");
        add(ACTION_USE, "Usar");
        add(ACTION_LOOK_AT, "Examinar");
        add(ACTION_PICK_UP, "Pegar");
        add(ACTION_PULL, "Puxar");
        add(ACTION_WALK, "Andar");
        add(SKILL_STOOL, "Agora eu alcanço prateleiras mais altas.");
        add(TO, "até");
        add(WITH, "com");

        add(CACTUS, "cacto", Gender.MASCULINE);

        add(CACTUS_FLOWER, "flor de cacto", Gender.FEMININE);
        add(JIMMY_PLANT, "Jimmy a Planta", Gender.MASCULINE);
        add(LINO, "Lino", Gender.MASCULINE);
        add(CLOTHES_PILE, "pilha de roupas", Gender.FEMININE);
        add(LEMON, "limão", Gender.MASCULINE);
        add(DETERGENT, "sabão em pó líquido", Gender.MASCULINE);
        add(SOFTENER, "amaciante", Gender.MASCULINE);
        add(STOOL, "banquinho", Gender.MASCULINE);
        add(SOCK, "meia suja", Gender.FEMININE);
        add(SHOYU, "molho shoyu", Gender.MASCULINE);
        add(WASHER, "máquina de lavar", Gender.FEMININE);
        add(FAN, "ventilador", Gender.MASCULINE);
        add(FAN_SWITCH, "interruptor", Gender.MASCULINE);
        add(TV, "televisão", Gender.FEMININE);
        add(TABLE, "mesa", Gender.FEMININE);
        add(TRASH, "lixeira", Gender.FEMININE);
        add(REFRIGERATOR, "geladeira", Gender.FEMININE);
        add(LAMP, "luminária", Gender.FEMININE);
        // Bedroom Objects
        add(BED, "cama", Gender.FEMININE);
        add(CLOCK, "despertador", Gender.MASCULINE);
        add(DRAWER, "mesa de cabeceira", Gender.FEMININE);
        add(DRAWER_LONG, "gaveteiro", Gender.MASCULINE);
        add(PICTURE, "quadro", Gender.MASCULINE);
        add(MIRROR, "espelho", Gender.MASCULINE);
        add(WINDOW, "janela", Gender.FEMININE);
        add(WARDROBE, "armário", Gender.MASCULINE);

        // Hall Objects
        add(FRONT_DOOR, "porta", Gender.FEMININE);
        add(CLOTHES_DOOR, "roupas", Gender.FEMININE);
        add(LAMP_LONG, "abajour", Gender.MASCULINE);
        add(COUCH, "sofá", Gender.MASCULINE);
        add(PHONE, "telefone", Gender.MASCULINE);
        // Kitchen Objects
        add(CABINET, "armário", Gender.MASCULINE);
        add(OVEN, "fogão", Gender.MASCULINE);
        add(DISH_WASHER, "lava-louças", Gender.FEMININE);
        add(SINK, "pia", Gender.FEMININE);

        add(GOOD_MORNING, "Bom dia!");
        add(LAUNDRY_DAY, "Hoje é dia de lavar roupa!");
        add(STANDARD_LOOK_AT, "É apenas {undet} {name}.");
        add(CANT_REACH, "Não consigo alcançar.");
        add(CANT_DO_THAT, "Não dá pra fazer isso.");
        add(DOESNT_FIT_POCKET, "Não cabe no meu bolso.");
        add(WHY_SOULD_I, "Por que eu deveria fazer isso?");
        add(BETTER_NOT, "Hummm... Melhor não.");
        add(IT_SHOULD_BE_CLOSED, "Preciso fechar primeiro.");
        add(ALMOST_EMPTY, "Está quase vazi{det}.");
        add(EMPTY, "Está vazi{det}.");
        add(FULL_AGAIN, "Está chei{det} novamente.");
        add(CACTUS_HAS_FLOWER, "Ei, isso é uma flor?");
        add(CACTUS_CANT_PICK, "Isso ia doer.");
        add(ENOUGH_FLOWERS, "Eu acho que não precisa de mais flores.");
        add(JIMMY_PLANT_LOOK_AT, "Jimmy a Planta.");
        add(MIRROR_LOOK_AT, "Ih, sou eu.");
        add(BED_LOOK_AT, "É uma cama bem confortável.");
        add(FAN_SWITCH_LOOK_AT, "Um interruptor que faz girar nos dois sentidos.");
        add(FAN_SWITCH_CANT_PICK, "Não consigo tirar, está emperrado.");
        add(LINO_GREETINGS, "Oi {name}!");
        add(LINO_SLEEPING, "Adivinha, ele está dormindo.");
        add(PILE_LOOK_AT, "É uma grande pilha de roupas.");
        add(SOFTENER_LOOK_AT, "Amaciante Mágic: ácido, salgado e com um toque floral.");
        add(SOFTENER_ACID, "Bem, é ácido.");
        add(SOFTENER_ACID_SALTY, "Bem, é ácido e salgado.");
        add(SOFTENER_ACID_FLOWERS, "Bem, é ácido com um toque floral.");
        add(SOFTENER_SALTY, "Bem, é salgado.");
        add(SOFTENER_SALTY_FLOWERS, "Bem, é salgado com um toque floral.");
        add(SOFTENER_FLOWERS, "Bem, tem um toque floral.");
        add(SOFTENER_NOT_READY, "O amaciante ainda não está pronto.");
        add(WASHER_OPEN, "A máquina de lavar precisa estar aberta.");
        add(WASHER_MORE_CLOTHES, "Eu acho que tenho mais roupas.");
        add(WASHER_EMPTY, "Preciso colocar roupas aqui dentro.");
        add(WASHER_DETERGENT, "Isso precisa de sabão em pó.");
        add(WASHER_SOFTENER, "Isso precisa de amaciante.");
        add(WASHER_DETERGENT_AND_SOFTENER, "Isso precisa de sabão em pó e amaciante.");
        add(WASHER_REVERSE, "Preciso encontrar um jeito de REVERTER essa máquina de lavar.");
        add(WASHER_REVERSED, "Uma máquina de lavar invertida.");
        add(WASHER_REVERSED_NAME, "máquina de lavar invertida");
        add(MISSION_COMPLETE, "Missão Cumprida!");
        add(SOMETHING_WRONG, "Uh oh, isso não está certo.");
        add(NO_WORDS, "...");
        add(SPIDER_CRAP, "Aranha do céu!");
        add(WASHER_HINT, "Eu sinto que vou ter que lavar as roupas de novo.");
        add(HERE_I_GO_AGAIN, "Lá vou eu de novo.");
        add(HAS_TO_WORK, "Tem que funcionar.");
    }

    private void add(String id, String sentence, Gender gender) {
        add(id, sentence);
        genders.put(sentence, gender);
    }

    private void add(String id, String sentence) {
        sentences.put(id, sentence);
    }

    public void loadCredits() {
        add(CREDITS_THANK_YOU, "Obrigado por jogar!");
        add(CREDITS_ART, "Arte");
        add(CREDITS_ORIGINAL_WITCH, "Imagens Originais da Bruxinha por doudoulolita");
        add(CREDITS_ORIGINAL_HOUSE, "Imagens da Casa por PurpleHeart");
        add(CREDITS_CACTUS_FLOWER, "Flor de Cacto por captainluna");
        add(CREDITS_ORIGINAL_CAT, "Imagem do gato por GarGar");
        add(CREDITS_MAGNIFYING_GLASS, "Imagem da lupa por Angrycheese");
        add(CREDITS_UI, "UI por Kenney");
        add(CREDITS_SPLASH, "Imagem de abertura por GraphicMama Team");
        add(CREDITS_FONT, "Fonte");
        add(CREDITS_FONT_NAME, "Press Start 2P");
        add(CREDITS_SFX, "Efeitos Sonoros");
        add(CREDITS_SFX_CREATOR, "SubspaceAudio");
        add(CREDITS_MUSIC, "Músicas");
        add(CREDITS_MUSIC_HOUSE, "Magic in the Garden por Rafael Krux");
        add(CREDITS_MUSIC_UPSIDE_DOWN, "Facing it por Komiku");
        add(CREDITS_MUSIC_MENU, "A Simple Trifle por jestar");
        add(CREDITS_TOOLS, "Ferramentas");
        add(CREDITS_BETA_TESTERS, "Beta Testers Oficiais");
        add(CREDITS_MADE_BY, "Feito Por");
        add(CREDITS_SPECIAL_THANKS, "Agradecimentos");
        add(CREDITS_THANKS_DISCORD, "Todos no canal do Discord da LibGDX");
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
        return result;
    }

    private String det(String name) {
        Gender gender;

        if (!genders.containsKey(name)) {
            gender = Gender.NEUTER;
            System.err.println("Not defined gender: "+name);
        } else {
            gender = genders.get(name);
        }

        switch (gender) {
            default:
            case NEUTER:
            case MASCULINE:
                return "o";
            case FEMININE:
                return "a";
        }
    }

    private String undet(String name) {
        Gender gender;

        if (!genders.containsKey(name)) {
            gender = Gender.NEUTER;
        } else {
            gender = genders.get(name);
        }

        switch (gender) {
            default:
            case NEUTER:
            case MASCULINE:
                return "um";
            case FEMININE:
                return "uma";
        }
    }

}
