package com.lnw.acceeeeeppt.system;

import java.io.File;
import javax.sound.sampled.*;

public class SoundManager {
    private static Clip bgmClip;

    private static int currentMasterVol = 50;
    private static int currentMusicVol = 50;
    private static int currentSfxVol = 50;

    // If the sound didn't play you can delete this path and Shft + Alt + C at
    // "sounds" folder and then paste it in here
    private static final String SOUND_FOLDER = "C:\\Users\\Windows\\Desktop\\acceeeeeppt\\resources\\sounds";

    private static File findAudioFile(String fileName) {
        File folder = new File(SOUND_FOLDER);
        if (folder.exists() && folder.listFiles() != null) {
            String searchName = fileName.toLowerCase().replace(".wav", "").trim();
            for (File file : folder.listFiles()) {
                if (file.getName().toLowerCase().contains(searchName)) {
                    return file;
                }
            }
        }
        return null;
    }

    public static void playBGM(String fileName) {
        try {
            File musicFile = findAudioFile(fileName);

            if (musicFile != null) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicFile);
                bgmClip = AudioSystem.getClip();
                bgmClip.open(audioInput);

                updateBGMVolume();
                bgmClip.loop(Clip.LOOP_CONTINUOUSLY);
                bgmClip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void playSFX(String fileName) {
        try {
            File sfxFile = findAudioFile(fileName);

            if (sfxFile != null) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(sfxFile);
                Clip sfxClip = AudioSystem.getClip();
                sfxClip.open(audioInput);

                FloatControl gainControl = (FloatControl) sfxClip.getControl(FloatControl.Type.MASTER_GAIN);
                float finalVol = (currentSfxVol * currentMasterVol) / 100.0f;
                float db = (finalVol <= 0) ? -80.0f : (float) (Math.log10(finalVol / 100.0) * 20.0);
                gainControl.setValue(db);

                sfxClip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setVolumeSettings(int master, int sfx, int music) {
        currentMasterVol = master;
        currentSfxVol = sfx;
        currentMusicVol = music;
        updateBGMVolume();
    }

    private static void updateBGMVolume() {
        if (bgmClip != null && bgmClip.isOpen()) {
            FloatControl gainControl = (FloatControl) bgmClip.getControl(FloatControl.Type.MASTER_GAIN);
            float finalVol = (currentMusicVol * currentMasterVol) / 100.0f;
            float db = (finalVol <= 0) ? -80.0f : (float) (Math.log10(finalVol / 100.0) * 20.0);
            gainControl.setValue(db);
        }
    }
}