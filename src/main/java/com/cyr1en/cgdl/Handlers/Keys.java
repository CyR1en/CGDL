package com.cyr1en.cgdl.Handlers;

import java.awt.event.KeyEvent;

/**
 * Handles mouse inputs
 *
 * @author Ethan Bacurio (CyR1en)
 * @version 1.0
 * @since 2016-05-17
 */

public class Keys {

    public static final int NUM_KEYS = 11;

    public static boolean keyState[] = new boolean[NUM_KEYS];
    public static boolean prevKeyState[] = new boolean[NUM_KEYS];

    public static int A = 0;
    public static int D = 1;
    public static int SPACE = 2;
    public static int ENTER = 3;
    public static int M = 4;
    public static int W = 5;
    public static int S = 6;
    public static int UP = 7;
    public static int DOWN = 8;
    public static int LEFT = 9;
    public static int RIGHT = 10;

    /**
     * sets the value of a certain key to be either true or false
     *
     * @param i index of the array where the value of a key is stored
     * @param b the value of key you're setting to the index specified above
     */
    public static void setKey(int i, boolean b) {
        if (i == KeyEvent.VK_A || i == KeyEvent.VK_LEFT)
            keyState[A] = b;
        if (i == KeyEvent.VK_D || i == KeyEvent.VK_RIGHT)
            keyState[D] = b;
        if (i == KeyEvent.VK_SPACE)
            keyState[SPACE] = b;
        if (i == KeyEvent.VK_ENTER)
            keyState[ENTER] = b;
        if (i == KeyEvent.VK_M)
            keyState[M] = b;
        if (i == KeyEvent.VK_DOWN)
            keyState[DOWN] = b;
        if (i == KeyEvent.VK_UP)
            keyState[UP] = b;
        if (i == KeyEvent.VK_LEFT)
            keyState[LEFT] = b;
        if (i == KeyEvent.VK_RIGHT)
            keyState[RIGHT] = b;
        if (i == KeyEvent.VK_W)
            keyState[W] = b;
        if (i == KeyEvent.VK_S)
            keyState[S] = b;
    }

    /**
     * updates and check key inputs
     */
    public static void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            prevKeyState[i] = keyState[i];
        }
    }

    /**
     * Used to check if a button is pressed
     *
     * @param i the key value, or index of the key you wanted to check
     * @return true if the button specified above is pressed
     */
    public static boolean isPressed(int i) {
        return keyState[i] && !prevKeyState[i];
    }
}

