package my_project.model;

import KAGO_framework.model.GraphicalObject;

import java.awt.image.BufferedImage;

public class Entity extends GraphicalObject {
    protected double speed;
    protected int hp;
    public Entity(double pX, double pY) {
        speed = 200;
        x = pX;
        y = pY;
    }

    /**
     * Moves the Entety.
     * @param dt the Time passed betwen this and the last call of the method
     * @param xDir the x-Direction
     * @param yDir
     */
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
