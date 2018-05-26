package com.cyr1en.cgdl.Entity;

import com.cyr1en.cgdl.Main.GamePanel;
import com.cyr1en.cgdl.util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The background for the game
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

    //very straight forward constructor
    public BackGround(String fileName) {
        //used the ImageUtil class from the CGDL(thanks to ethan)
        image = ImageUtil.loadBufferedImage(fileName);
        //initialize the coordinate of the BG to (0,0)
        x = 0;
        y = 0;
    }

    //sets the vector
    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    //updates the background
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

    //draws the background
    public void draw(Graphics2D g, float interpolation) {
        int x = (int)((this.x - lastX) * interpolation + lastX);
        int y = (int)((this.y - lastY) * interpolation + lastY);
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
