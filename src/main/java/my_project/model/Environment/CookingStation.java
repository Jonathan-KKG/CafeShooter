package my_project.model.Environment;

import my_project.model.Dishes.Dish;

/**
 * A collidableEnvironment that is able to create Dishes.
 */
public abstract class CookingStation extends CollidableEnvironment {

    /**
     * Creates a new CookingStation
     * @param filename image name of the cooking station
     * @param pX Starting x pos
     * @param pY Starting y pos
     */
    public CookingStation(String filename, double pX, double pY) {
        super(filename, pX, pY);
    }
}
