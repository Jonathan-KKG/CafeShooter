package my_project.model;

import KAGO_framework.model.GraphicalObject;

/**
 * Blueprint to create most other drawables in the game
 */
public abstract class Entity extends GraphicalObject {
    protected double speed;

    /**
     * Creates new Entity
     *
     * @param filename The image the entity should have, file being located in the graphics folder
     * @param pX       Starting x position
     * @param pY       Starting y position
     */
    public Entity(String filename, double pX, double pY) {
        super(filename, pX, pY);
        speed = 200;
    }

    public double getSpeed() {
        return speed;
    }


}
