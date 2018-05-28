package com.cyr1en.cgdl.Entity;

import com.cyr1en.cgdl.Main.GamePanel;
import com.cyr1en.cgdl.util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Background object for states
 */
public class BackGround {

    //the background image
    private BufferedImage image;

    //Coordinate
    private double x;
    private double y;
    private double lastX;
    private double lastY;

    //vector
    private double dx;
    private double dy;

    /**
     * Background constructor
     *
     * @param fileName image to use for the background
     */
    public BackGround(String fileName) {
        image = ImageUtil.loadBufferedImage(fileName);
        x = 0;
        y = 0;
    }

    /**
     * set the vector for the background
     *
     * @param dx horizontal velocity
     * @param dy vertical velocity
     */
    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Update the back ground
     * <p>
     * Call this on the update function of your {@link com.cyr1en.cgdl.GameState.GameState} GameState
     * </p>
     */
    public void update() {
        lastX = x;
        lastY = y;
        x += dx;
        y += dy;
        if (x + image.getWidth() <= 0 || x >= GamePanel.WIDTH) {
            lastX = 0;
            x = 0;
        }
        if (y + image.getWidth() <= 0 || y >= GamePanel.HEIGHT) {
            lastY = 0;
            y = 0;
        }
    }

    /**
     * Render function for the background object
     *
     * <p>
     * Call this on the draw function of your {@link com.cyr1en.cgdl.GameState.GameState}.
     * </p>
     * @param g Graphics2D object that was passed down to the {@link com.cyr1en.cgdl.GameState.GameState} draw function.
     * @param interpolation interpolation value that was passed down to the {@link com.cyr1en.cgdl.GameState.GameState} draw function.
     */
    public void draw(Graphics2D g, float interpolation) {
        int x = (int) ((this.x - lastX) * interpolation + lastX);
        int y = (int) ((this.y - lastY) * interpolation + lastY);
        //center
        g.drawImage(image, x, y, null);
        //right
        g.drawImage(image, x + image.getWidth(), y, null);
        //left
        //g.drawImage(image, x - image.getWidth(), y, null);
        //top
        //g.drawImage(image, x, y - image.getHeight(), null);
        //bottom
        //g.drawImage(image, x, y + image.getHeight(), null);
        //top-left
        //g.drawImage(image, x - image.getWidth(), y - image.getHeight(), null);
        //top-right
        // g.drawImage(image, x + image.getWidth(), y - image.getHeight(), null);
        //bottom-left
        // g.drawImage(image, x - image.getWidth(), y + image.getHeight(), null);
        //bottom-right
        //g.drawImage(image, x + image.getWidth(), y + image.getHeight(), null);
    }


}
