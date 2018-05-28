package com.cyr1en.cgdl.GameState;

import com.cyr1en.cgdl.Main.GamePanel;

import java.awt.*;

/**
 * Handles the game states of the game
 *
 * @author Ethan Bacurio (CyR1en)
 * @version 1.0
 * @since 2016-05-17
 */
public abstract class GameStateManager {

    protected static final int INTRO_STATE = -1;

    protected GameState gameState;
    protected int currentState;

    protected GameStateManager(int initialState) {
        currentState = INTRO_STATE;
        gameState = new IntroState(this, initialState);
    }

    /**
     * to be implemented in the subclass. this class should
     * contain and handle the loading of states. needs to be
     * implemented correctly for maximum efficiency
     *
     * @param state Specific state that you want to loadBufferedImage.
     */
    public abstract void loadState(int state);

    public abstract void loadState(GameState state);

    public abstract GameState getState(int state);

    public abstract int getState(GameState state);


    /**
     * change the current game state of the game
     *
     * @param state Specific state that you want to set the current state.
     */
    public void setState(int state) {
        currentState = state;
        loadState(currentState);
    }

    public void setState(GameState state) {
        currentState = getState(state);
        loadState(state);
    }

    /**
     * updateBool method takes care of the code and variable changes in each game state.
     */
    public void update() {
        if (gameState != null)
            gameState.update();
    }

    /**
     * draw method takes care of graphics of each game state
     *
     * @param g graphics component that's needed to paint something onto the screen
     */
    public void draw(Graphics2D g, float interp) {
        if (gameState != null) {
            gameState.setInterpolation(interp);
            gameState.draw(g);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        }
    }

}
