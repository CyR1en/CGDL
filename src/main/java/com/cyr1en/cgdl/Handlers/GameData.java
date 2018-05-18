package com.cyr1en.cgdl.Handlers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Handles the game data of the game
 *
 * @author Ethan Bacurio (CyR1en)
 * @version 1.0
 * @since 2016-05-17
 */
public class GameData {

    // things I need to save
    private static int[] saveInfo;

    public static void init() {
        saveInfo = new int[2];
    }

    /**
     * saves a data to the array
     * @param i index of array where you wanted to store the date
     * @param score data that you want to save in the index stated above
     */
    public static void setSaveInfo(int i, int score) {
        if (higherScore(score))
            saveInfo[i] = score;
    }

    /**
     * gets a data from a specific index of the array
     * @param index Index of data that you want to access
     * @return the data value on the index stated above
     */
    public static int getDataAt(int index) {
        GameData.load();
        return saveInfo[index];
    }

    /**
     * compares the last score to the parameter variable that
     * acts as the new up-coming score
     *
     * @param score the new score
     * @return returns true if the last score was lower than the
     *         current score
     */
    public static boolean higherScore(int score) {
        GameData.load();
        return saveInfo[0] < score;
    }

    /**
     * Writes that date on a save file called "save.dat". If
     * this file does not exist in the current directory where
     * the game is, then the game will generate a new file
     * called "save.dat" and save all the date inside that data
     * file
     */
    public static void save() {
        try {
            DataOutputStream out = new DataOutputStream(new FileOutputStream("save.dat"));
            for (int g : saveInfo) {
                out.writeInt(g);
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * this reads the data on the "save.dat" file.
     * This method also generates a new save data if the
     * "save.dat" file does not exist in the directory
     * where the game is
     */
    public static void load() {
        try {
            File f = new File("save.dat");
            if (!f.exists()) {
                defaultInit();
                return;
            }
            DataInputStream in = new DataInputStream(new FileInputStream(f));
            for (int i = 0; i < saveInfo.length; i++) {
                saveInfo[i] = in.readInt();
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
            defaultInit();
        }
    }

    /**
     * default initialization of the game data handler
     */
    private static void defaultInit() {
        for (int i = 0; i < saveInfo.length; i++) {
            saveInfo[i] = 0;
        }
        save();
    }

}
