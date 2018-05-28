package apcs.tamagochi.states;

import apcs.tamagochi.handler.StateManager;
import com.cyr1en.cgdl.Entity.*;
import com.cyr1en.cgdl.Entity.Button.GameButton;
import com.cyr1en.cgdl.GameState.GameState;
import com.cyr1en.cgdl.GameState.GameStateManager;
import com.cyr1en.cgdl.GameState.Transition;
import com.cyr1en.cgdl.Handlers.Mouse;
import com.cyr1en.cgdl.Handlers.ParticleFactory;
import com.cyr1en.cgdl.Main.GamePanel;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.util.ArrayList;

public class MenuState extends GameState {

    // entities/objects
    private BackGround bg;
    private Title title;
    private SoundClip backgroundMusic;
    private Transition transition;

    // game button variables
    private GameButton<MenuState> newGameButton;
    private GameButton<MenuState> loadGameButton;

    // particles
    private ArrayList<Particle> particles;

    // constructor
    public MenuState(GameStateManager gsm) {
        super(gsm);
        init();
    }

    // initialize all the instance variables
    public void init() {
        //initialize the background music
        backgroundMusic = new SoundClip("/sounds/bg-music2.wav", Clip.LOOP_CONTINUOUSLY);
        backgroundMusic.setVolume(0.15f);

        //initialize the title.
        title = new Title("T O M O G U C C I", new Font("Cute Cartoon", Font.BOLD, 70));
        //setting the color of the title
        title.setColor(new Color(40, 50, 55, 245));

        //initializing the background of the state
        bg = new BackGround("/assets/bg2.png");
        //setting the vector to (-1,0) so it moves 1 pixels to the left.
        bg.setVector(-1, 0);

        //========= Button one =========
        newGameButton = new GameButton<>(GamePanel.WIDTH / 2, (int) (GamePanel.HEIGHT * 0.6));
        newGameButton.setText("New Game ", new Font("KG Already Home", Font.PLAIN, 30));
        newGameButton.setHoverSound(new SoundEffect("/sounds/sfx/button-hover.wav"));
        newGameButton.setClickSound(new SoundEffect("/sounds/sfx/button-click.wav"));
        newGameButton.setColor(GameButton.DEFAULT_COLOR);
        newGameButton.setType(GameButton.CENTER);
        newGameButton.setObjType(this);
        newGameButton.setOnClick(state -> {
            state.getBackgroundMusic().stop();
            transition.nextState(StateManager.MENU_STATE);
        });

        //========= Button two =========
        loadGameButton = new GameButton<>(GamePanel.WIDTH / 2, (int) (GamePanel.HEIGHT * 0.6) + 50);
        loadGameButton.setText("Load Game", new Font("KG Already Home", Font.PLAIN, 30));
        loadGameButton.setHoverSound(new SoundEffect("/sounds/sfx/button-hover.wav"));
        loadGameButton.setClickSound(new SoundEffect("/sounds/sfx/button-click.wav"));
        loadGameButton.setColor(GameButton.DEFAULT_COLOR);
        loadGameButton.setType(GameButton.CENTER);
        loadGameButton.setObjType(this);
        loadGameButton.setOnClick(state -> {
            state.getBackgroundMusic().stop();
            transition.nextState(StateManager.MENU_STATE);
        });

        transition = new Transition(gsm ,0, 30, -1, 30);
        particles = new ArrayList<>();
        ParticleFactory.init(particles);
        backgroundMusic.start();
    }

    //updateBool all the variables that's used in this class and other components of it
    public void update() {
        handleInput(); // handle the inputs
        bg.update(); // updateBool the background
        title.update(); // updateBool the title
        // particles updateBool
        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).updateBool()) {
                particles.remove(i);
                i--;
            }
        }
        // buttons
        newGameButton.update();
        loadGameButton.update();
        transition.update();
    }


    // draws the menu state
    public void draw(Graphics2D g) {
        bg.draw(g, getInterpolation()); //draw the background

        //draw each gameButton
        newGameButton.draw(g, getInterpolation());
        loadGameButton.draw(g, getInterpolation());

        //draw the title
        title.draw(g, getInterpolation());

        //draws all the particles
        for (Particle p : particles)
            p.draw(g, getInterpolation());

        transition.draw(g, getInterpolation());
    }

    public void handleInput() {
        if (Mouse.isPressed()) {
            ParticleFactory.createExplosion(Mouse.x, Mouse.y, new Color(40, 40, 40));
            ParticleFactory.createSmallWave(Mouse.x, Mouse.y, 10, new Color(40, 40, 40));
            new SoundEffect("/sounds/sfx/pop.wav").play();
        }
    }

    public SoundClip getBackgroundMusic() {
        return backgroundMusic;
    }

    @Override
    public void setInterpolation(float interpolation) {
        this.interpolation = interpolation;
    }

    @Override
    public float getInterpolation() {
        return interpolation;
    }
}
