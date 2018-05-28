package com.cyr1en.cgdl.Entity.Button;

import com.cyr1en.cgdl.Entity.GameObject;
import com.cyr1en.cgdl.Entity.SoundEffect;
import com.cyr1en.cgdl.Handlers.Mouse;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

/**
 * TButton class to make game buttons
 *
 * @author Ethan Bacurio (CyR1en)
 * @version 1.0
 * @since 2016-05-17
 */
public class GameButton<T> extends GameObject {

    public static final Color DEFAULT_COLOR = new Color(20, 20, 20, 150);

    private int x;
    private int y;
    private int width;
    private int height;

    private Font font;
    private String text;
    private GlyphVector gv;
    private int textWidth;
    private int textHeight;

    private BufferedImage buttonImage;

    private boolean hover;
    private boolean active;

    private int type;
    public static final int CENTER = 0;
    public static final int SHOP_BUTTON = 2;

    private static Color c;

    private SoundEffect hoverSound;
    private boolean playedHover;
    private SoundEffect clickSound;

    private T objType;
    private Consumer<T> consumer;

    public GameButton(int x, int y) {
        this.x = x;
        this.y = y;
        active = true;
        type = CENTER;
        c = DEFAULT_COLOR;
        hoverSound = null;
        clickSound = null;
        playedHover = false;
    }

    public GameButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        active = true;
        type = CENTER;
        c = DEFAULT_COLOR;
        hoverSound = null;
        clickSound = null;
        playedHover = false;
    }

    public void setColor(Color color) {
        c = color;
    }

    public void setActive(boolean b) {
        active = b;
    }

    public boolean isActive() {
        return active;
    }

    public void setType(int i) {
        type = i;
    }

    public void setFont(Font f) {
        font = f;
    }

    public void setText(String s) {
        setText(s, font);
    }

    public void setText(String s, Font f) {
        text = s;
        font = f;
        Rectangle rect = getPixelBounds(font);
        textWidth = rect.width;
        textHeight = rect.height;
        width = textWidth + 20;
        height = textHeight + 10;
    }

    public void setHoverSound(SoundEffect effect) {
        hoverSound = effect;
    }

    public void setClickSound(SoundEffect effect) {
        clickSound = effect;
    }

    private boolean isHovering() {
        int x = Mouse.x;
        int y = Mouse.y;
        if (type == CENTER) {
            return x > this.x - width / 2 && x < this.x + width / 2 && y > this.y - height / 2
                    && y < this.y + height / 2;
        } else if (type == SHOP_BUTTON) {
            return x > this.x - 14 && x < this.x + 14 && y < this.y + 8 && y > this.y - 8;
        }
        return false;
    }

    private void setHover(boolean b) {
        hover = b;
    }

    private void drawShopButton(Graphics2D g) {
        g.setColor(new Color(220, 220, 220, 100));
        g.fillOval(x + 14, y - 13, 5, 5);
        int[] xPos = {x + 15, x - 15, x - 10, x + 10};
        int[] yPos = {y - 8, y - 8, y + 8, y + 8};
        g.fillPolygon(xPos, yPos, xPos.length);
    }

    private void playHoverSound() {
        if(hoverSound == null)
            return;
        if(!playedHover) {
            hoverSound.play();
            playedHover = true;
        }
    }

    private ButtonAction<T> click() {
        if(hoverSound != null)
            clickSound.play();
        return new ButtonAction<>(objType);
    }

    public void setOnClick(Consumer<T> consumer) {
        this.consumer = consumer;
    }

    public void setObjType(T t) {
        objType = t;
    }

    private Rectangle getPixelBounds(Font f) {
        AffineTransform at = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(at, true, true);
        gv = f.createGlyphVector(frc, text);
        return gv.getPixelBounds(null, 0, 0);
    }

    @Override
    public void update() {
        if(isHovering())
            setHover(true);
        else
            setHover(false);

        if(hover && active) {
            playHoverSound();
        } else if (playedHover && !hover) {
            playedHover =false;
        }

        if(hover && Mouse.isPressed()) {
            click().process(consumer);
        }
    }

    public void draw(Graphics2D g, float interpolation) {
        if (active)
            g.setColor(c);
        else
            g.setColor(Color.GRAY);
        if (hover && active) {
            g.setStroke(new BasicStroke(2));
            if (type == CENTER) {
                g.setColor(c.darker());
                Font f = new Font(font.getName(), Font.BOLD, font.getSize() + 2);
                g.setFont(f);
                g.drawString(text, x - getPixelBounds(f).width / 2, y + 10);
            } else if (type == SHOP_BUTTON) {
                g.setColor(new Color(255, 255, 255));
                g.drawLine(x - 15, y + 13, x + 15, y + 13);
            }
        } else {
            g.setFont(font);
            if (type == CENTER) {
                g.drawString(text, x - textWidth / 2, y + 10);
            } else if (type == SHOP_BUTTON) {
                drawShopButton(g);
            }
            g.setFont(null);
        }

        if (active)
            g.setColor(c);
        else
            g.setColor(Color.GRAY);
    }
}