package my_project.model.Environment;

/**
 * A collidableEnvironment that is able to create Dishes.
 */
public abstract class CookingStation extends CollidableEnvironment {

    protected double cookingTime; // The maximum duration that its skillcheck could go on for

    /**
     * Creates a new CookingStation
     * @param filename image name of the cooking station
     * @param pX Starting x pos
     * @param pY Starting y pos
     */
    public CookingStation(String filename, double pX, double pY) {
        super(filename, pX, pY);
    }

    public double getCookingTime() {
        return cookingTime;
    }
}
