package com.cyr1en.cgdl.BasicGraphics;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Class the lets you easily draw shapes to your panel
 *
 * @author  Ethan Bacurio (CyR1en)
 * @version 1.0
 * @since   2016-05-17
 */
public class Draw {

    private static Graphics2D g;

    /**
     * initialize graphics component
     *
     * @param g Graphics 2d object that's going to be initializing the instance g
     */
    public static void init(Graphics2D g) {
        Draw.g = g;
    }

    /**
     * draw a filled rectangle
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Width rectangle
     * @param height Height of rectangle
     * @param color fill color
     */
    public static void rectangle(int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }

    /**
     * draw a rectangle
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Width rectangle
     * @param height Height of rectangle
     * @param color stroke color
     */
    public static void rectangle1(int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.drawRect(x, y, width, height);
    }

    /**
     * draw a filled ellipse
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Width of the ellipse
     * @param height Height of rectangle
     * @param color fill color
     */
    public static void oval(int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.fillOval(x - width / 2,  y - height / 2, width, height);
    }

    /**
     * draw an ellipse
     *
     * @param x X coordinate
     * @param y Y coordinate
     * @param width Width of the ellipse
     * @param height Height of rectangle
     * @param color stroke color
     */
    public static void oval1(int x, int y, int width, int height, Color color) {
        g.setColor(color);
        g.drawOval(x - width / 2,  y - height / 2, width, height);
    }
}
