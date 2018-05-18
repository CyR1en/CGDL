package com.cyr1en.cgdl.Entity;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * Handles the game states of the game
 *
 * @author Ethan Bacurio (CyR1en)
 * @version 1.0
 * @since 2016-05-17
 */
public class Particle extends GameObject {

    protected Color color;

    protected int tick;
    protected int tickDelay;

    protected boolean pDy;

    /**
     * zero argument constructor
     */
    public Particle() {
    }

    /**
     * multiple argument constructor for the Particle. Includes iAlpha, pDy, and td
     * @param x X coordinate of the Particle
     * @param y Y coordinate of the particle
     * @param dx Horizontal vertex
     * @param dy Vertical vertex
     * @param w width of the particle
     * @param h height of the particle
     * @param c color of the particle
     * @param iAlpha the initial opacity of the particle
     * @param pDy one sided vertical vertex
     * @param td tick delay
     */
    public Particle(double x, double y, double dx, double dy, int w, int h, Color c, int iAlpha, boolean pDy, int td) {
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.color = new Color(c.getRed(), c.getBlue(), c.getGreen(), iAlpha);
        this.width = w;
        this.height = h;
        this.pDy = pDy;
        tick = 0;
        tickDelay = td;
    }

    /**
     * multiple argument constructor for the Particle.
     * @param x X coordinate of the Particle
     * @param y Y coordinate of the particle
     * @param dx Horizontal vector
     * @param dy Vertical vector
     * @param w width of the particle
     * @param h height of the particle
     * @param c color of the particle
     */
    public Particle(double x, double y, double dx, double dy, int w, int h, Color c) {
        this(x, y, dx, dy, w, h, c, 255, false, 90);
    }

    /**
     * update the variables and properties of the Particle
     * @return true if the tick is greater than the tick delay
     */
    public boolean update() {
        x += dx + Math.random() * 3 - 1.5;
        if(pDy)
            y += dy + Math.random() * 3;
        else
            y += dy + Math.random() * 3 - 1.5;
        tick++;
        color = new Color(color.getRed(), color.getGreen(), color.getBlue(), (255 - tick * 255 / tickDelay));
        return tick >= tickDelay;
    }

    /**
     * Graphics for the Particle
     *
     * @param g Graphics2D component to paint or render the graphics
     *          for this class
     */
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.drawOval((int)x -width / 2, (int) y - height / 2, width, height);

    }

}
