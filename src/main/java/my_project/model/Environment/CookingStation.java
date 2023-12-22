package my_project.model.Environment;

import my_project.model.Dish;

/**
 * A collidableEnvironment that is able to create Dishes.
 */
public abstract class CookingStation extends CollidableEnvironment {

    protected Class<? extends Dish>[] cookableDishes;

    /**
     * Creates a new CookingStation
     * @param filename image name of the cooking station
     * @param pX Starting x pos
     * @param pY Starting y pos
     */
    public CookingStation(String filename, double pX, double pY) {
        super(filename, pX, pY);
    }

    public Class<? extends Dish>[] getCookableObjs() {
        return cookableDishes;
    }
}
