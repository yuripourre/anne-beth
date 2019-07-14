package com.harium.annebeth.laundry.sound;

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
    private static Sound knob;
    private static Music washer;

    private static boolean musicOn = true;
    private static boolean soundOn = true;
    private static boolean loaded = false;

    public static void init() {
        if (loaded) {
            return;
        }
        menu = new Music("music-menu.mp3");
        menu.setLoop(true);
        normal = new Music("music-ingame.mp3");
        normal.setLoop(true);
        upsidedown = new Music("music-upsidedown.mp3");
        upsidedown.setLoop(true);

        cannot = new Sound("cannot.wav");
        explosion = new Sound("explosion.mp3");
        open = new Sound("open.wav");
        pickup = new Sound("pickup.wav");
        use = new Sound("use.wav");
        knob = new Sound("knob.wav");
        washer = new Music("washer.mp3");

        loaded = true;
    }

    public static void turnMusicOn() {
        musicOn = true;
    }

    public static void turnMusicOff() {
        musicOn = false;
    }

    public static void turnSoundOn() {
        soundOn = true;
    }

    public static void turnSoundOff() {
        soundOn = false;
    }

    public static void playNormalMusic() {
        if (!musicOn) {
            return;
        }
        stopMusics();
        normal.play();
    }

    public static void playUpsideDownMusic() {
        if (!musicOn) {
            return;
        }
        stopMusics();
        upsidedown.play();
    }

    public static void playMenuMusic() {
        if (!musicOn) {
            return;
        }
        stopMusics();
        menu.play();
    }

    public static void playCannot() {
        if (!soundOn) {
            return;
        }
        cannot.play();
    }

    public static void playExplosion() {
        if (!soundOn) {
            return;
        }
        explosion.play();
    }

    public static void playOpen() {
        if (!soundOn) {
            return;
        }
        open.play();
    }

    public static void playUse() {
        if (!soundOn) {
            return;
        }
        use.play();
    }

    public static void playKnob() {
        if (!soundOn) {
            return;
        }
        knob.play();
    }

    public static void playPickup() {
        if (!soundOn) {
            return;
        }
        pickup.play();
    }

    public static void playWasher() {
        if (!soundOn) {
            return;
        }
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

    public static void playGameOver() {

    }

}
