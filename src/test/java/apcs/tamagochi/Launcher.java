package apcs.tamagochi;

import apcs.tamagochi.handler.StateManager;
import com.cyr1en.cgdl.Main.GameFrame;
import com.cyr1en.cgdl.Main.GamePanel;
import com.cyr1en.cgdl.util.FontUtil;

public class Launcher {
    public static void main(String[] args) {
        //set the game window to 500 x 700
        GamePanel.initPanel(500, 700);
        /*
        initialize game frame, set the title to "Tomogucci", passed a new
        instance of StateManager, set the target frames to 200 frames per
        second, and set the target game refresh rate to 60 ticks per second.
         */
        GameFrame gameFrame = new GameFrame("Tomogucci", new StateManager(), 200, 60);
        //show debug information on the title bar
        gameFrame.setShowTitleInfo(true);
        //registering all fonts in the fonts directory
        FontUtil.registerAllFonts("/fonts/", Launcher.class);
    }
}
