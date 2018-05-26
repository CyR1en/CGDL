package com.cyr1en.cgdl.Entity;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
public class SoundClip {

    private Clip clip;
    private FloatControl volume;
    private int loop;

    // Constructor
    public SoundClip(String fileName, int loop) {
        this.loop = loop;
        try {
            // Open an audio input stream.
            URL url = this.getClass().getResource(fileName);
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
            // Get a sound clip resource.
            clip = AudioSystem.getClip();

            // Open audio clip and loadBufferedImage samples from the audio input stream.
            clip.open(audioIn);
            volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            volume.setValue(-30.f);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public void start() {
        clip.loop(loop);
    }

    public boolean isRunning() {
        return clip.isRunning();
    }

    public void stop() {
        clip.stop();
    }
}