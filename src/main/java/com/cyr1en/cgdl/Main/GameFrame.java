package com.cyr1en.cgdl.Main;

import com.cyr1en.cgdl.GameState.GameStateManager;
import com.cyr1en.cgdl.util.ImageUtil;

import javax.swing.*;
import java.awt.*;

/**
 * com.cyr1en.cgdl.Main Component of the game. Where game components will be added
 *
 * @author  Ethan Bacurio (CyR1en)
 * @version 1.0
 * @since   2016-05-17
 */

public class GameFrame extends JFrame {

    //gets the screen size
    private static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    private boolean showTitleInfo;

    private String title;

    /**
     * @param title sets the title of the game.
     * @param gsm game state manager that will be initialized with the game panel.
     */
    public GameFrame(String title, GameStateManager gsm, int FPS, int tick) {
        super(title);
        GamePanel gamePanel = new GamePanel(gsm, FPS, tick,this);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setContentPane(gamePanel);
        this.setResizable(false);
        this.pack();
        this.setFocusable(true);
        this.setLocation((int) (((dim.getWidth() / 2) - (GamePanel.WIDTH / 2))), (int) ((dim.getHeight() / 2) - (GamePanel.HEIGHT / 2)));
        this.setVisible(true);
        showTitleInfo = false;
        this.title = title;
        this.setIconImage(ImageUtil.loadBufferedImage("/assets/cgdl-frame-logo.png"));
        System.out.println("Frame Initialized...");
    }

    public void setShowTitleInfo(boolean b) {
        showTitleInfo = b;
    }

    public boolean isShowTitleInfo() {
        return showTitleInfo;
    }

    public void updateTitleBar() {
        if(showTitleInfo) {
            int mb = 1000000;
            long max = Runtime.getRuntime().maxMemory() / mb;
            long total = Runtime.getRuntime().totalMemory() / mb;
            long free = Runtime.getRuntime().freeMemory() / mb;
            long used = total - free;
            this.setTitle( title + " | Max: " + max + "mb Used: " + used + "mb" + " | FPS : " + GamePanel.getFPS() + " | TPS: " + GamePanel.getTPS());
        }
    }

    public void clearTitleBar() {
        this.setTitle(title);
    }
}
