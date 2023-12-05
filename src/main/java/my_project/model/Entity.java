package my_project.model;

import KAGO_framework.model.GraphicalObject;

public class Entity extends GraphicalObject {
    protected double speed;
    protected int hp;
    public Entity(double pX, double pY) {
        speed = 90;
        x = pX;
        y = pY;
    }

    public double getSpeed() {
        return speed;
    }
    public int getHP() {
        return hp;
    }
}
