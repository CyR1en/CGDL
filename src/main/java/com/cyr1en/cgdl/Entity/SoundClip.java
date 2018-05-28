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
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the volume for the clip
     * @param percent value can only be from 0.0 to 1.0
     */
    public void setVolume(float percent) {
        if(percent < 0.0 || percent > 1.0)
            return;
        float dB = (float) (Math.log(percent) / Math.log(10.0) * 20.0);
        volume.setValue(dB);
    }

    public void start() {
        clip.loop(loop);
    }

    public void stop() {
        clip.stop();
    }
}