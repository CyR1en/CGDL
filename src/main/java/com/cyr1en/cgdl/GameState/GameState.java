package com.cyr1en.cgdl.GameState;

import com.cyr1en.cgdl.Handlers.GameStateManager;

import java.awt.*;

/**
 * Super class of all the states of the game
 *
 * @author Ethan Bacurio (CyR1en)
 * @version 1.0
 * @since 2016-05-17
 */
public abstract class GameState {

    protected GameStateManager gsm;

    /**
     * constructor for the GameState
     *
     * @param gsm the game state manager that's going to be used to
     *            handle the game states
     */
    public GameState(GameStateManager gsm) {
        this.gsm = gsm;
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

}
