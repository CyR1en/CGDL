package apcs.tamagochi.states;

import apcs.tamagochi.entity.Food;
import apcs.tamagochi.entity.Pet;
import apcs.tamagochi.handler.StateManager;
import com.cyr1en.cgdl.Entity.BackGround;
import com.cyr1en.cgdl.Entity.Button.GameButton;
import com.cyr1en.cgdl.Entity.SoundClip;
import com.cyr1en.cgdl.Entity.SoundEffect;
import com.cyr1en.cgdl.GameState.GameState;
import com.cyr1en.cgdl.GameState.GameStateManager;
import com.cyr1en.cgdl.GameState.Transition;
import com.cyr1en.cgdl.Handlers.Keys;
import com.cyr1en.cgdl.Main.GamePanel;
import com.cyr1en.cgdl.util.SerializationUtil;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class PlayingState extends GameState {

    private BackGround background;
    private SoundClip backgroundMusic;

    private Transition transition;
    private Pet pet;
    private ArrayList<Food> foods;

    private GameButton<PlayingState> feedButton;
    private GameButton<PlayingState> saveButton;
    private GameButton<PlayingState> homeButton;


    public PlayingState(GameStateManager gsm, Pet pet) {
        super(gsm);
        transition = new Transition(gsm, 0, 60, -1, 60);
        this.pet = pet;
        init();
    }

    @Override
    public void init() {
        backgroundMusic = new SoundClip("/sounds/bg-music2.wav", Clip.LOOP_CONTINUOUSLY);
        backgroundMusic.setVolume(0.10f);
        backgroundMusic.start();
        background = new BackGround("/assets/defaultbg.png");

        foods = new ArrayList<>();
        foods.add(new Food(10, "Salad"));
        foods.add(new Food(50, "Sushi"));
        foods.add(new Food(100, "Pizza"));

        feedButton = new GameButton<>((int) (GamePanel.WIDTH * .15), GamePanel.HEIGHT - 70);
        feedButton.setText("\uD83C\uDF7D ", new Font("Segoe UI Emoji", Font.PLAIN, 70));
        feedButton.setHoverSound(new SoundEffect("/sounds/sfx/button-hover.wav"));
        feedButton.setClickSound(new SoundEffect("/sounds/sfx/eat.wav"));
        feedButton.setColor(new Color(224, 1, 107, 255));
        feedButton.setType(GameButton.CENTER);
        feedButton.setObjType(this);
        feedButton.setOnClick(state -> {
            feed();
        });

        saveButton = new GameButton<>(GamePanel.WIDTH - 20, GamePanel.HEIGHT - (GamePanel.HEIGHT - 15));
        saveButton.setText("\uD83D\uDCBE", new Font("Segoe UI Emoji", Font.PLAIN, 20));
        saveButton.setHoverSound(new SoundEffect("/sounds/sfx/button-hover.wav"));
        saveButton.setClickSound(new SoundEffect("/sounds/sfx/button-click.wav"));
        saveButton.setType(GameButton.CENTER);
        saveButton.setObjType(this);
        saveButton.setOnClick(state -> {
            File dir = new File("saves/");
            boolean exist;
            if(!dir.exists()) {
                exist = dir.mkdirs();
            } else {
                exist = true;
            }
            if(exist) {
                SerializationUtil.serialize(pet, "saves/save.dat");
            }
        });

        homeButton = new GameButton<>(15, 15);
        homeButton.setText("\uD83C\uDFE0", new Font("Segoe UI Emoji", Font.PLAIN, 20));
        homeButton.setHoverSound(new SoundEffect("/sounds/sfx/button-hover.wav"));
        homeButton.setClickSound(new SoundEffect("/sounds/sfx/button-click.wav"));
        homeButton.setType(GameButton.CENTER);
        homeButton.setObjType(this);
        homeButton.setOnClick(state -> {
            backgroundMusic.stop();
            transition.nextState(StateManager.MENU_STATE);
        });

        pet.setAnimation(Pet.DEFAULT);
        pet.setPosition(GamePanel.WIDTH / 2, GamePanel.HEIGHT / 2);
    }

    public void feed() {
        pet.feed(foods.get((int)(Math.random() * 3)));
    }

    @Override
    public void update() {
        handleInput();
        background.update();
        pet.update();
        feedButton.update();
        saveButton.update();
        homeButton.update();
        transition.update();
    }

    @Override
    public void draw(Graphics2D g) {
        background.draw(g, getInterpolation());
        feedButton.draw(g, getInterpolation());
        saveButton.draw(g, getInterpolation());
        homeButton.draw(g, getInterpolation());
        pet.draw(g, getInterpolation());
        transition.draw(g, getInterpolation());
    }

    @Override
    public void handleInput() {
        if(Keys.isPressed(Keys.SPACE)) {
            boolean follow = !pet.isFollow();
            pet.setFollow(follow);
        }
    }

    @Override
    public void setInterpolation(float v) {
        this.interpolation = v;
    }

    @Override
    public float getInterpolation() {
        return interpolation;
    }
}
