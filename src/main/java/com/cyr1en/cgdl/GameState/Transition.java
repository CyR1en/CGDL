package com.cyr1en.cgdl.GameState;

import com.cyr1en.cgdl.Entity.GameObject;
import com.cyr1en.cgdl.Main.GamePanel;

import java.awt.*;

public class Transition extends GameObject {

    public GameStateManager gsm;

    // fade variables
    private int fadeInTimer;
    private int fadeInDelay;
    private int fadeOutTimer;
    private int fadeOutDelay;
    private int alpha;

    private int nextState;

    private boolean next;

    public Transition(GameStateManager gsm, int fadeInTimer, int fadeInDelay, int fadeOutTimer, int fadeOutDelay) {
        this.gsm = gsm;
        this.fadeInTimer = fadeInTimer;
        this.fadeInDelay = fadeInDelay;
        this.fadeOutDelay = fadeOutDelay;
        this.fadeOutTimer = fadeOutTimer;
        nextState = -1;
    }

    public void nextState(int state) {
        nextState = state;
        next = true;
    }

    @Override
    public void update() {
        if (fadeInTimer >= 0) {
            fadeInTimer++;
            alpha = (int) (255.0 * fadeInTimer / fadeInDelay);
            if (fadeInTimer == fadeInDelay) {
                fadeInTimer = -1;
            }
        }

        if (next) {
            fadeOutTimer++;
            alpha = (int) (255.0 * fadeOutTimer / fadeOutDelay);
            if (fadeOutTimer == fadeOutDelay) {
                gsm.setState(nextState);
                next = false;
            }
        }
        if (alpha < 0)
            alpha = 0;
        if (alpha > 255)
            alpha = 255;
    }

    @Override
    public void draw(Graphics2D g, float interpolation) {
        if (fadeInTimer >= 0) {
            g.setColor(new Color(255, 255, 255, 255 - alpha));
            g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        }
        if (fadeOutTimer >= 0) {
            g.setColor(new Color(255, 255, 255, alpha));
            g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        }
    }
}