package com.cyr1en.cgdl.Handlers;

/**
 * Handles mouse inputs
 *
 * @author Ethan Bacurio (CyR1en)
 * @version 1.0
 * @since 2016-05-17
 */
public class Mouse {

    public static int x;
    public static int y;

    private static int action;
    private static int previousAction;

    public static final int RELEASED = 0;
    public static final int PRESSED = 1;

    /**
     * updateBool and check the mouse input
     */
    public static void update() {
        previousAction = action;
    }

    /**
     * updateBool the coordinate of x and y to the current position
     * the mouse in the component
     *
     * @param i1 X coordinate of the mouse
     * @param i2 Y coordinate of the mouse
     */
    public static void setPosition(int i1, int i2) {
        x = i1;
        y = i2;
    }

    /**
     * sets and updateBool the value of action
     *
     * @param i new value for the instance action
     */
    public static void setAction(int i) {
        action = i;
    }

    /**
     * checks if the mouse have been pressed
     *
     * @return true if the mouse is pressed
     */
    public static boolean isPressed() {
        return action == PRESSED && previousAction == RELEASED;
    }

    /**
     * checks if the mouse have been pressed down
     *
     * @return true if the mouse is down
     */
    public static boolean isDown() {
        return action == PRESSED;
    }

}
