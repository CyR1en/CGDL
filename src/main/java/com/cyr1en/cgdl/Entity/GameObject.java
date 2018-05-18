package com.cyr1en.cgdl.Entity;

import java.awt.*;

public class GameObject {

    // position
    protected double x;
    protected double y;

    // vector
    protected double dx;
    protected double dy;

    // dimensions
    protected int width;
    protected int height;

    // color
    protected Color color;
    protected Color colorBorder;

    protected GameObject() {
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setPosition(GameObject o) {
        this.x = o.getX();
        this.y = o.getY();
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void setDimensions(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getdx() {
        return dx;
    }

    public double getdy() {
        return dy;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void flipHorizontal() {
        dx = -dx;
    }

    public void flipVertical() {
        dy = -dy;
    }

    public boolean intersects(GameObject o) {
        return getRectangle().intersects(o.getRectangle());
    }

    public boolean intersects(Rectangle rect) {
        return getRectangle().intersects(rect);
    }

    public boolean intersectsCircle(GameObject o) {
        double dx = x - o.getX();
        double dy = y - o.getY();
        double dist2 = dx * dx + dy * dy;
        return dist2 < ((width + o.getWidth()) / 2) * ((width + o.getHeight()) / 2);
    }

    public boolean contains(GameObject o) {
        return getRectangle().contains(o.getRectangle());
    }

    public boolean intersectsCircle(Rectangle rectangle, int x, int y) {
        double dx = this.x - rectangle.getX();
        double dy = this.y - rectangle.getY();
        double dist2 = dx * dx + dy * dy;
        return dist2 < ((width + rectangle.getWidth()) / 2) * ((width + rectangle.getWidth()) / 2);
    }

    public boolean containsCircle(GameObject o) {
        double dx = x - o.getX();
        double dy = y - o.getY();
        double dist = Math.sqrt(dx * dx + dy * dy);
        return dist + o.getWidth() / 2 < width / 2;
    }

    public Rectangle getRectangle() {
        return new Rectangle((int) x - width, (int) y - height, width, height);
    }

    public Rectangle getRectangle(int offset) {
        return new Rectangle((int) x - width, (int) y - height, width, height + offset);
    }

    public void drawCircle(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) x - width / 2, (int) y - height / 2, width, height);
        g.setColor(colorBorder);
        g.drawOval((int) x - width / 2, (int) y - height / 2, width, height);
    }

    public void drawCircleNoBorder(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) x - width / 2, (int) y - height / 2, width, height);
    }

}
