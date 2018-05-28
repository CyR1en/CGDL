package apcs.tamagochi.handler;

import apcs.tamagochi.states.MenuState;
import com.cyr1en.cgdl.GameState.GameStateManager;

/**
 * This handles all the switching of the game state
 */
public class StateManager extends GameStateManager {

    //constants that we can just call through out the code
    public static final int MENU_STATE = 0;
    public static final int PLAYING_STATE = 1;
    //constructor for the state manager class
    public StateManager() {
        //pass in the initial state of the game, which is state 0
        super(MENU_STATE);
    }

    //loads specific states, stated in the parameter
    public void loadState(int state) {
        if(state == MENU_STATE)
            gameState = new MenuState(this);
    }
}
