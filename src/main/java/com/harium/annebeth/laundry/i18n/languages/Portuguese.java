package com.harium.annebeth.laundry.i18n.languages;

import com.harium.annebeth.laundry.core.object.base.BaseObject;
import com.harium.annebeth.laundry.i18n.Dictionary;
import com.harium.annebeth.laundry.i18n.Gender;

import java.util.HashMap;
import java.util.Map;

public class Portuguese implements Dictionary {

    Map<String, String> sentences = new HashMap<>();
    Map<String, Gender> genders = new HashMap<>();

    public Portuguese() {
        sentences.put(ACTION_OPEN, "Abrir");
        sentences.put(ACTION_CLOSE, "Fechar");
        sentences.put(ACTION_USE, "Usar");
        sentences.put(ACTION_LOOK_AT, "Examinar");
        sentences.put(ACTION_PICK_UP, "Pegar");
        sentences.put(ACTION_PULL, "Puxar");
        sentences.put(ACTION_WALK, "Andar");
        sentences.put(TO, "até");
        sentences.put(WITH, "com");

        sentences.put(CACTUS, "cacto");
        sentences.put(CACTUS_FLOWER, "flor de cacto");
        sentences.put(JIMMY_PLANT, "Jimmy a Planta");
        sentences.put(LINO, "Lino");
        sentences.put(CLOTHES_PILE, "pilha de roupas");
        sentences.put(LEMON, "limão");
        sentences.put(SOAP, "sabão em pó líquido");
        sentences.put(SOFTENER, "amaciante");
        sentences.put(STOOL, "banquinho");
        sentences.put(SOCK, "meia suja");
        sentences.put(SHOYU, "molho shoyu");
        sentences.put(WASHER, "máquina de lavar");
        sentences.put(FAN, "ventilador");
        sentences.put(FAN_SWITCH, "interruptor");
        sentences.put(TV, "televisão");
        sentences.put(TABLE, "mesa");
        sentences.put(TRASH, "lixeira");
        sentences.put(REFRIGERATOR, "geladeira");
        sentences.put(MIRROR, "espelho");
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

        sentences.put(GOOD_MORNING, "Bom dia!");
        sentences.put(LAUNDRY_DAY, "Hoje é dia de lavar roupa!");
        sentences.put(STANDARD_LOOK_AT, "É apenas {undet} {name}.");
        sentences.put(CANT_REACH, "Não consigo alcançar.");
        sentences.put(CANT_DO_THAT, "Não dá pra fazer isso.");
        sentences.put(WHY_SOULD_I, "Por que eu deveria fazer isso?");
        sentences.put(IT_SHOULD_BE_CLOSED, "Preciso fechar primeiro.");
        sentences.put(ALMOST_EMPTY, "Está quase vazi{det}.");
        sentences.put(EMPTY, "Está vazi{det}.");
        sentences.put(FULL_AGAIN, "Está chei{det} novamente.");
        sentences.put(CACTUS_HAS_FLOWER, "Ei, isso é uma flor?");
        sentences.put(ENOUGH_FLOWERS, "Eu acho que não precisa de mais flores.");
        sentences.put(JIMMY_PLANT_LOOK_AT, "Jimmy a Planta.");
        sentences.put(MIRROR_LOOK_AT, "Ih, sou eu.");
        sentences.put(BED_LOOK_AT, "É uma cama bem confortável.");
        sentences.put(FAN_SWITCH_LOOK_AT, "Um interruptor que faz girar nos dois sentidos.");
        sentences.put(LINO_GREETINGS, "Oi {name}!");
        sentences.put(LINO_SLEEPING, "Adivinha, ele está dormindo.");
        sentences.put(PILE_LOOK_AT, "É uma grande pilha de roupas.");
        sentences.put(SOFTENER_LOOK_AT, "Amaciante Mágic: ácido, salgado e com um toque floral.");
        sentences.put(SOFTENER_ACID, "Bem, é ácido.");
        sentences.put(SOFTENER_ACID_SALTY, "Bem, é ácido e salgado.");
        sentences.put(SOFTENER_ACID_FLOWERS, "Bem, é ácido com um toque floral.");
        sentences.put(SOFTENER_SALTY, "Bem, é salgado.");
        sentences.put(SOFTENER_SALTY_FLOWERS, "Bem, é salgado com um toque floral.");
        sentences.put(SOFTENER_FLOWERS, "Bem, tem um toque floral.");
        sentences.put(WASHER_OPEN, "A máquina de lavar precisa estar aberta.");
        sentences.put(WASHER_MORE_CLOTHES, "Eu acho que tenho mais roupas.");
        sentences.put(WASHER_EMPTY, "Preciso colocar roupas aqui dentro.");
        sentences.put(WASHER_DETERGENT, "Isso precisa de sabão em pó.");
        sentences.put(WASHER_SOFTENER, "Isso precisa de amaciante.");
        sentences.put(WASHER_DETERGENT_AND_SOFTENER, "Isso precisa de sabão em pó e amaciante.");
        sentences.put(WASHER_REVERSE, "Preciso encontrar um jeito de REVERTER essa situação.");
        sentences.put(WASHER_REVERSED, "Uma máquina de lavar invertida.");
        sentences.put(MISSION_COMPLETE, "Missão Cumprida!");
        sentences.put(SOMETHING_WRONG, "Uh oh, isso não está certo.");
        sentences.put(NO_WORDS, "...");
        sentences.put(SPIDER_CRAP, "Aranha do céu!");
        sentences.put(HERE_I_GO_AGAIN, "Lá vou eu de novo.");
        sentences.put(HAS_TO_WORK, "Tem que funcionar.");
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
        return result;
    }

    private String det(String name) {
        switch (genders.get(name)) {
            default:
            case NEUTER:
            case MASCULINE:
                return "o";
            case FEMININE:
                return "a";
        }
    }

    private String undet(String name) {
        switch (genders.get(name)) {
            default:
            case NEUTER:
                return "";
            case MASCULINE:
                return "um";
            case FEMININE:
                return "uma";
        }
    }

}
