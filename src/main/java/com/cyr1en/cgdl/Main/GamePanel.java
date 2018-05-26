package com.cyr1en.cgdl.Main;

import com.cyr1en.cgdl.GameState.GameStateManager;
import com.cyr1en.cgdl.Handlers.Keys;
import com.cyr1en.cgdl.Handlers.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

/**
 * com.cyr1en.cgdl.Main Component of the game. All game processes are passed to this class.
 *
 * @author Ethan Bacurio (CyR1en)
 * @version 1.0
 * @since 2016-05-17
 */

public class GamePanel extends JPanel implements Runnable, KeyListener, MouseMotionListener, MouseListener {

    //game state
    private static GameStateManager gameStateManager;

    //dimension
    public static int WIDTH;
    public static int HEIGHT;

    //threads
    private Thread thread;
    private boolean running;
    private int frameCount = 0;
    private static int fps;
    private double TARGET_FPS;
    private static int GAME_TICK;

    //image
    private BufferedImage image;
    private Graphics2D g;

    private GameFrame frame;
    private boolean paused;

    /**
     * GamePanel constructor
     *
     * @param gameStateManager sets the game state manager that we need
     */
    public GamePanel(GameStateManager gameStateManager, int FPS, int tick, GameFrame frame) {
        super();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        GamePanel.gameStateManager = gameStateManager;
        fps = FPS;
        TARGET_FPS = fps;
        this.frame = frame;
        GAME_TICK = tick;
        paused = false;
        System.out.println("GamePanel Initialized...");
    }

    /**
     * Sets the dimension of the Game Panel
     *
     * @param width  width of the Panel
     * @param height height of the Panel
     */
    public static void initPanel(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
        System.out.println("GamePanel Set to: " + WIDTH + "x" + HEIGHT);
    }

    /**
     * Notifies this component that it now has a parent component.
     * When this method is invoked, the chain of parent components is
     * set up with <code>KeyboardAction</code> event listeners.
     * This method is called by the toolkit internally and should
     * not be called directly by programs.
     *
     * @see #registerKeyboardAction
     */
    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            addKeyListener(this);
            addMouseListener(this);
            addMouseMotionListener(this);
            thread = new Thread(this);
            thread.start();
        }
    }

    /**
     * initialization of multiple instance
     */
    private void init() {
        requestFocus();
        running = true;
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) image.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    }

    @Override
    public void run() {
        init();
        Thread loop = new Thread(this::gameLoop);
        loop.start();
    }

    public static int getFPS() {
        return fps;
    }
    public static int getTPS() {
        return GAME_TICK;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     * <p>
     * Contains the game loop.
     */
    private void gameLoop() {
        final double GAME_HERTZ = GAME_TICK;
        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
        final int MAX_UPDATES_BEFORE_RENDER = 5;
        final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

        double lastUpdateTime = System.nanoTime();
        double lastRenderTime;

        int lastSecondTime = (int) (lastUpdateTime / 1000000000);

        int tps = 0;

        while (running) {
            double now = System.nanoTime();
            int updateCount = 0;

            if (!paused) {
                while (now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER) {
                    gameUpdate();
                    lastUpdateTime += TIME_BETWEEN_UPDATES;
                    updateCount++;
                    tps++;
                }
                if (now - lastUpdateTime > TIME_BETWEEN_UPDATES) {
                    lastUpdateTime = now - TIME_BETWEEN_UPDATES;
                }

                float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES));
                gameDraw(interpolation);
                lastRenderTime = now;
                frameCount++;

                int thisSecond = (int) (lastUpdateTime / 1000000000);
                if (thisSecond > lastSecondTime) {
                    GAME_TICK = tps;
                    fps = frameCount;
                    frameCount = 0;
                    tps = 0;
                    lastSecondTime = thisSecond;
                }

                while (now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES) {
                    Thread.yield();
                    try {
                        Thread.sleep(1);
                    } catch (Exception ignored) {
                    }
                    now = System.nanoTime();
                }
            }
        }
    }

    /**
     * Game updates are passed down to this method
     */
    private void gameUpdate() {
        gameStateManager.update();
        Keys.update();
        Mouse.update();
        frame.updateTitleBar();
    }

    /**
     * Game renders are passed down to this method
     * <p>
     * rendering technique is double buffer
     */
    private void gameRender(float interp) {
        gameStateManager.draw(g, interp);
    }

    /**
     * Draw the rendered buffered image to the panel
     */
    private void gameDraw(float interp) {
        gameRender(interp);
        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }


    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param key Key event that needs to be analyzed.
     */
    @Override
    public void keyPressed(KeyEvent key) {
        Keys.setKey(key.getKeyCode(), true);
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     */
    @Override
    public void keyReleased(KeyEvent key) {
        Keys.setKey(key.getKeyCode(), false);
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     */
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     */
    @Override
    public void mousePressed(MouseEvent me) {
        Mouse.setAction(Mouse.PRESSED);
    }

    /**
     * Invoked when a mouse button has been released on a component.
     */
    @Override
    public void mouseReleased(MouseEvent me) {
        Mouse.setAction(Mouse.RELEASED);
    }

    /**
     * Invoked when the mouse enters a component.
     */
    @Override
    public void mouseEntered(MouseEvent e) {

    }

    /**
     * Invoked when the mouse exits a component.
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  <code>MOUSE_DRAGGED</code> events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * <code>MOUSE_DRAGGED</code> events may not be delivered during a native
     * Drag&amp;Drop operation.
     */
    @Override
    public void mouseDragged(MouseEvent me) {
        Mouse.setAction(Mouse.PRESSED);
        Mouse.setPosition(me.getX(), me.getY());
    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     */
    @Override
    public void mouseMoved(MouseEvent me) {
        Mouse.setPosition(me.getX(), me.getY());
    }

}
