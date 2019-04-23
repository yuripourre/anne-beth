package com.harium.annebeth.aspell.sound;

import com.harium.etyl.sound.model.Music;
import com.harium.etyl.sound.model.Sound;

public class Jukebox {

    private static Music menu;
    private static Music normal;
    private static Music upsidedown;

    private static Sound cannot;
    private static Sound explosion;
    private static Sound open;
    private static Sound pickup;
    private static Sound use;
    private static Music washer;

    public static void init() {
        menu = new Music("music-menu.mp3");
        menu.setLoop(true);
        normal = new Music("music-ingame.mp3");
        normal.setLoop(true);
        upsidedown = new Music("music-upsidedown.mp3");
        upsidedown.setLoop(true);

        cannot = new Sound("cannot.wav");
        explosion = new Music("explosion.mp3");
        open = new Sound("open.wav");
        pickup = new Sound("pickup.wav");
        use = new Sound("use.wav");
        washer = new Music("washer.mp3");
    }

    public static void playNormalMusic() {
        stopMusics();
        normal.play();
    }

    public static void playUpsideDownMusic() {
        stopMusics();
        upsidedown.play();
    }

    public static void playMenuMusic() {
        stopMusics();
        menu.play();
    }

    public static void playCannot() {
        cannot.play();
    }

    public static void playExplosion() {
        explosion.play();
    }

    public static void playOpen() {
        open.play();
    }

    public static void playUse() {
        use.play();
    }

    public static void playPickup() {
        pickup.play();
    }

    public static void playWasher() {
        washer.play();
    }

    public static void stopMusics() {
        stopMusic(menu);
        stopMusic(normal);
        stopMusic(upsidedown);
    }

    public static void stopMusic(Music music) {
        //if (music.isPlaying()) {
            music.stop();
        //}
    }

    public static void stopWasher() {
        washer.stop();
    }
}
