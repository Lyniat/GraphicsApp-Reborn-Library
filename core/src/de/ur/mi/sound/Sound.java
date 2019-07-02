package de.ur.mi.sound;

import com.badlogic.gdx.Gdx;

public class Sound {

    private String filePath;
    private com.badlogic.gdx.audio.Sound audioClip;

    /**
     * Creates a new sound by loading it from a given filepath pointing to a
     * .wav file
     *
     * @param filePath
     *            The name of the sound file which should be placed in the class
     *            folder of the project
     */
    public Sound(String filePath) {
        this.filePath = filePath;
        audioClip = Gdx.audio.newSound(Gdx.files.internal(filePath));
    }

    /**
     * Loops the loaded sound until it is stopped.
     */
    public void loop() {
        try {
            audioClip.loop();
        } catch (Exception e) {
            System.err.println("Error playing sound:");
            System.err.println(e.getMessage());
        }
    }

    /**
     * Plays the loaded sound once.
     */
    public void play() {
        try {
            audioClip.play();
        } catch (Exception e) {
            System.err.println("Error playing sound:");
            System.err.println(e.getMessage());
        }
    }

    /**
     * Stops the loaded sound.
     */
    public void stop() {
        audioClip.stop();
    }

}