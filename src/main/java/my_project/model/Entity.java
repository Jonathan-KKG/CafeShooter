package my_project.model;

import KAGO_framework.model.GraphicalObject;

import java.awt.image.BufferedImage;

public class Entity extends GraphicalObject {
    protected double speed;
    protected int hp;
    protected BufferedImage image;
    public Entity(double pX, double pY) {
        speed = 130;
        x = pX;
        y = pY;
    }

    public void move(double dt, double xDir, double yDir) {
        x += xDir * dt * speed;
        y += yDir * dt * speed;

    }

    public double getSpeed() {
        return speed;
    }
    public int getHP() {
        return hp;
    }
}
