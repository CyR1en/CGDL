package com.cyr1en.cgdl.Handlers;

import com.cyr1en.cgdl.GameState.GameState;
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

    protected GameState gameState;
    protected int currentState;

    /**
     * to be implemented in the subclass. this class should
     * contain and handle the loading of states. needs to be
     * implemented correctly for maximum efficiency
     *
     * @param state Specific state that you want to load.
     */
    public abstract void loadState(int state);

    /**
     *change the current game state of the game
     *
     * @param state Specific state that you want to set the current state.
     */
    public void setState(int state) {
        currentState = state;
        loadState(currentState);


    }

    /**
     * update method takes care of the code and variable changes in each game state.
     */
    public void update() {
        if(gameState != null)
            gameState.update();
    }

    /**
     * draw method takes care of graphics of each game state
     * @param g graphics component that's needed to paint something onto the screen
     */
    public void draw(Graphics2D g) {
        if(gameState != null)
            gameState.draw(g);
        else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        }
    }

}
