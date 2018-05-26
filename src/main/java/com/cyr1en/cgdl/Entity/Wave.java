package com.cyr1en.cgdl.Entity;

import java.awt.*;

/**
 * A 2D wave entity that can be spawned into the game
 *
 * @author Ethan Bacurio (CyR1en)
 * @version 1.0
 * @since 2016-05-17
 */
public class Wave extends Particle {

    /**
     * Wave constructor
     *
     * @param x initial X coordinate of the particle
     * @param y initial Y coordinate of the particle
     * @param w maximum width of the wave
     * @param c color of the wave
     */
    public Wave(double x, double y, int w, Color c) {
        this.x = x;
        this.y = y;
        this.width = this.height = w;
        this.color = c;
        tick = 0;
        tickDelay = 90;
    }

    /**
     * delay of the wave
     *
     * @param i number of tick delay
     */
    public void setTickDelay(int i) {
        tickDelay = i;
    }

    /**
     * update the wave's variables and properties
     *
     * @return return if the particle tick is equal to the tickDelay
     */
    public boolean update() {
        width++;
        height++;
        tick++;
        color = new Color(color.getRed(), color.getGreen(), color.getBlue(), (255 - tick * 255 / tickDelay));
        return tick == tickDelay;
    }

    /**
     * draws the wave
     *
     * @param g Graphics2D component to paint the wave
     */
    public void draw(Graphics2D g, float interpolation) {
        g.setColor(color);
        g.setStroke(new BasicStroke(2));
        g.drawOval((int) x - width / 2, (int) y - height / 2, width, height);
    }
}
