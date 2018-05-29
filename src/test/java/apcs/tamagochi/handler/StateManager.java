package apcs.tamagochi.handler;

import apcs.tamagochi.entity.Pet;
import apcs.tamagochi.states.LoadState;
import apcs.tamagochi.states.MenuState;
import apcs.tamagochi.states.PlayingState;
import com.cyr1en.cgdl.GameState.GameState;
import com.cyr1en.cgdl.GameState.GameStateManager;

/**
 * This handles all the switching of the game state
 */
public class StateManager extends GameStateManager {

    //constants that we can just call through out the code
    public static final int MENU_STATE = 0;
    public static final int PLAYING_STATE = 1;
    public static final int LOAD_STATE = 2;

    //constructor for the state manager class
    public StateManager() {
        //pass in the initial state of the game, which is state 0
        super(MENU_STATE);
    }

    public void setState(GameState state) {
        gameState = state;
    }

    //loads specific states, stated in the parameter
    public void loadState(int state) {
        this.gameState = getState(state);
    }

    @Override
    public void loadState(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public GameState getState(int i) {
        if (i == MENU_STATE)
            return new MenuState(this);
        else if (i == PLAYING_STATE) {
            return new PlayingState(this, new Pet(1));
        } else if (i == LOAD_STATE) {
            return new LoadState(this, null);
        }
        return null;
    }

    @Override
    public int getState(GameState gameState) {
        if (gameState instanceof MenuState)
            return MENU_STATE;
        if (gameState instanceof PlayingState)
            return PLAYING_STATE;
        if (gameState instanceof LoadState)
            return LOAD_STATE;
        return -1;
    }
}
