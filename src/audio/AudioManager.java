package audio;

import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;

public class AudioManager {

    public static int LEVEL_1 = 0;
    public static int LEVEL_2 = 1;
    public static int LEVEL_3 = 2;
    public static int LEVEL_4 = 3;
    public static int STAGE_CLEAR = 4;
    public static int STAR = 5;
    public static int DIE = 6;

    public static int JUMP = 0;
    public static int FIREBALL = 1;

    private Clip[] songs, effects;
    private int currentSongId = -1;
    private float volume = 0.5f;
    private boolean songMute, effectMute;

    public AudioManager() {
        loadSongs();
        loadEffects();
    }

    private void loadSongs() {
        String[] songNames = { "level1.wav", "level2.wav", "level3.wav", "level4.wav", "stage_clear.wav", "star.wav",
                "die.wav" };
        songs = new Clip[songNames.length];
        for (int i = 0; i < songNames.length; i++) {
            songs[i] = getGClip(songNames[i]);
        }
    }

    private void loadEffects() {
        String[] effectNames = { "jump.wav", "fireball.wav" };
        effects = new Clip[effectNames.length];
        for (int i = 0; i < effectNames.length; i++) {
            effects[i] = getGClip(effectNames[i]);
        }
    }

    private Clip getGClip(String name) {
        InputStream is = AudioManager.class.getResourceAsStream("sounds/" + name);

        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(is);
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void toggleSongMute() {
        songMute = !songMute;
        for (Clip c : songs) {
            BooleanControl muteControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
            muteControl.setValue(songMute);
        }
    }

    public void toggleEffectMute() {
        effectMute = !effectMute;
        for (Clip c : effects) {
            BooleanControl muteControl = (BooleanControl) c.getControl(BooleanControl.Type.MUTE);
            muteControl.setValue(effectMute);
        }
    }

    public void playEffect(int id) {
        effects[id].setMicrosecondPosition(0);
        effects[id].start();

    }

    public void playSong(int id) {
        if (currentSongId == id)
            return;
        if (currentSongId != -1)
            stopSong();

        songMute = false;

        currentSongId = id;
        songs[currentSongId].setMicrosecondPosition(0);
        songs[currentSongId].loop(Clip.LOOP_CONTINUOUSLY);

    }

    public boolean isSongMute() {
        return songMute;
    }

    public float getVolume() {
        return volume;
    }

    public void stopSong() {
        songMute = true;
        songs[currentSongId].stop();
    }

    public void stopEffect(int id) {
        effects[id].stop();
    }

    public void stopAllEffects() {
        for (Clip c : effects) {
            c.stop();
        }
    }

    public void stopAll() {
        stopSong();
        stopAllEffects();
    }

    public void setLevelSong(int lvlIndex) {
        switch (lvlIndex) {
            case 0:
                playSong(LEVEL_1);
                break;
            case 1:
                playSong(LEVEL_2);
                break;
            case 2:
                playSong(LEVEL_3);
                break;
            case 3:
                playSong(LEVEL_4);
                break;
        }
    }

}