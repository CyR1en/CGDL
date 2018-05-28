package com.cyr1en.cgdl.GameState;

import com.cyr1en.cgdl.Entity.GameObject;
import com.cyr1en.cgdl.Main.GamePanel;

import java.awt.*;
import java.util.function.Consumer;

public class Transition extends GameObject {

    public GameStateManager gsm;

    // fade variables
    private int fadeInTimer;
    private int fadeInDelay;
    private int fadeOutTimer;
    private int fadeOutDelay;
    private int alpha;

    private GameState nextState;

    private Consumer<Transition> consumer;

    private boolean next;

    public Transition(GameStateManager gsm, int fadeInTimer, int fadeInDelay, int fadeOutTimer, int fadeOutDelay) {
        this.gsm = gsm;
        this.fadeInTimer = fadeInTimer;
        this.fadeInDelay = fadeInDelay;
        this.fadeOutDelay = fadeOutDelay;
        this.fadeOutTimer = fadeOutTimer;
        nextState = new IntroState(gsm, -1);
    }

    public void nextState(int state) {
        nextState = gsm.getState(state);
        next = true;
    }

    public void nextState(GameState state) {
        System.out.println("called nextState");
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
                if(consumer != null) {
                    consumer.accept(this);
                    System.out.println("1 - transitioned");
                    next = false;
                } else {
                    gsm.setState(nextState);
                    System.out.println("2 - transitioned");
                    next = false;
                }
            }
        }
        if (alpha < 0)
            alpha = 0;
        if (alpha > 255)
            alpha = 255;
    }

    public void after(Consumer<Transition> consumer) {
        next = true;
        this.consumer = consumer;
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
