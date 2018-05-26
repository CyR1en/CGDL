package com.cyr1en.cgdl.GameState;

import java.awt.*;
import java.io.Serializable;

/**
 * Super class of all the states of the game
 *
 * @author Ethan Bacurio (CyR1en)
 * @version 1.0
 * @since 2016-05-17
 */
public abstract class GameState implements Serializable {

    protected GameStateManager gsm;
    protected float interpolation;

    /**
     * constructor for the com.cyr1en.cgdl.GameState
     *
     * @param gsm the game state manager that's going to be used to
     *            handle the game states
     */
    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
        interpolation = 0;
    }

    /**
     * initialization of all the instance in this state
     */
    public abstract void init();

    /**
     * updates all the objects inside of this state
     */
    public abstract void update();

    /**
     * draws all the objects and the current game state
     * to the screen
     *
     * @param g Graphic2D component that allows us to paint
     *          objects
     */
    public abstract void draw(Graphics2D g);

    /**
     * methods to handle all that inputs in this state
     */
    public abstract void handleInput();

    public abstract void setInterpolation(float interpolation);

    public abstract float getInterpolation();

}
