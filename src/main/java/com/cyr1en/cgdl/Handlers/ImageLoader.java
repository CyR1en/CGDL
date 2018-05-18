package com.cyr1en.cgdl.Handlers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * Handles image imports
 *
 * @author Ethan Bacurio (Cyrus)
 * @version 1.0
 * @since 2016-05-17
 */
public class ImageLoader {

    /**
     * loads a picture
     *
     * @param s path for the image file
     * @return  returns a buffered image in the given path, and can be stored in to a variable.
     */
    public static BufferedImage load(String s) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(ImageLoader.class.getResourceAsStream(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
