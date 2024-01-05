package my_project.model.Environment;

/**
 * A collidableEnvironment that is able to create Dishes.
 */
public abstract class CookingStation extends CollidableEnvironment {

    protected double cookingTime; // The maximum duration that its skillcheck could go on for
    protected int allowedMistakes;

    /**
     * Creates a new CookingStation
     * @param filename image name of the cooking station
     * @param pX Starting x pos
     * @param pY Starting y pos
     */
    public CookingStation(String filename, double pX, double pY) {
        super(filename, pX, pY);
        allowedMistakes = 0;
    }

    /**
     * checks whether the click is valid or not
     * @param time time passed since initation of cooking in seconds
     * @param isMovingDownwards whether the UI indicator (if existing) is currently moving downwards
     * @param currentHitTimeWindow the time window (if existing) in which the player has to click {earliest, latest}
     * @return whether click was valid or not
     */
    public abstract boolean isClickValid(double time, boolean isMovingDownwards, double[] currentHitTimeWindow, int key, int neededKey);

    public double getCookingTime() {
        return cookingTime;
    }
    public int getAllowedMistakes() {
        return allowedMistakes;
    }
}
