package my_project.model;

import KAGO_framework.model.GraphicalObject;

public class Entity extends GraphicalObject {
    protected double speed;
    protected int hp;
    public Entity() {
    }

    public double getSpeed() {
        return speed;
    }
    public int getHP() {
        return hp;
    }
}
