package com.cyr1en.cgdl.Handlers;

import com.cyr1en.cgdl.Entity.Particle;
import com.cyr1en.cgdl.Entity.Wave;

import java.awt.*;
import java.util.ArrayList;

/**
 * Class that will allow you to create particle effects
 * in your game
 *
 * @author  Ethan Bacurio (CyR1en)
 * @version 1.0
 * @since   2016-05-17
 */

public class ParticleFactory {

    private static ArrayList<Particle> particles;

    private static final int MAX_PARTICLES = 64;

    /**
     * initialize all the instance of the class
     *
     * @param p will be used to initialized the instance p
     */
    public static void init(ArrayList<Particle> p) {
        particles = p;
    }

    /**
     * create an explosion with particle
     * @see Particle
     *
     * @param x X coordinate of the explosion origin
     * @param y Y coordinate of the explosion origin
     */
    public static void createExplosion(double x, double y) {
        createExplosion(x, y, Color.WHITE);
    }

    /**
     * create an explosion with particle
     * @see Particle
     *
     * @param x X coordinate of the explosion origin
     * @param y Y coordinate of the explosion origin
     * @param c Color of the explosion
    */
    public static void createExplosion(double x, double y, Color c) {
        for(int i = 0; i < 10; i++) {
            double dx = Math.random() * 4 - 2;
            double dy = Math.random() * 4 - 2;
            particles.add(new Particle(x, y, dx, dy, 2, 2, c));
        }
        checkLimit();
    }

    /**
     * create a spark with particle
     * @see Particle
     *
     * @param x X coordinate of the spark origin
     * @param y Y coordinate of the spark origin
     * @param c Color of the smoke
     */
    public static void createSpark(double x, double y, Color c) {
        for(int i = 0; i < 10; i++) {
            double dx = Math.random() * 2 - 1;
            int dy =(int) (Math.random() * 5);
            particles.add(new Particle(x, y, dx, dy, 1, 1, c, 255, true, 40));
        }
        checkLimit();
    }

    /**
     * create a smoke with particle
     * @see Particle
     *
     * @param x X coordinate of the smoke origin
     * @param y Y coordinate of the smoke origin
     * @param c Color of the  smoke
     */
    public static void createSmoke(double x, double y, Color c) {
        for(int i = 0; i < 10; i++) {
            double dx = Math.random() * -5;
            double dy = Math.random() * 3;
            particles.add(new Particle(x, y, dx, dy, 2, 2, c, 10, false, 80));
        }
        checkLimit();
    }

    /**
     * create a wave with wave object
     * @see Wave
     *
     * @param x X coordinate of the wave's origin
     * @param y Y coordinate of the wave's origin
     * @param width diameter of the wave
     */
    public static void createWave(double x, double y, int width) {
        createWave(x, y, width, Color.WHITE);
    }

    /**
     * create a wave with wave object
     * @see Wave
     *
     * @param x X coordinate of the wave's origin
     * @param y Y coordinate of the wave's origin
     * @param width diameter of the wave
     * @param c Color of the wave
     */
    public static void createWave(double x, double y, int width, Color c) {
        particles.add(new Wave(x, y, width, c));
        checkLimit();
    }

    /**
     * create a small wave with wave object
     * @see Wave
     *
     * @param x X coordinate of the wave's origin
     * @param y Y coordinate of the wave's origin
     * @param width diameter of the wave
     */
    public static void createSmallWave(double x , double y, int width) {
        createSmallWave(x, y, width, Color.WHITE);
    }

    /**
     * create a small wave with wave object
     * @see Wave
     *
     * @param x X coordinate of the wave's origin
     * @param y Y coordinate of the wave's origin
     * @param width diameter of the wave
     * @param c Color of the wave
     */
    public static void createSmallWave(double x, double y, int width, Color c) {
        Wave w = new Wave(x, y, width, c);
        w.setTickDelay(30);
        particles.add(w);
        checkLimit();
    }

    /**
     * Limits the number of particles. Bounded by the particle limit in the instance.
     */
    private static void checkLimit() {
        int extra = particles.size() - MAX_PARTICLES;
        if(extra <= 0)
            return;
        for(int i = 0; i < extra; i++) {
            particles.remove(0);
        }
    }
}
