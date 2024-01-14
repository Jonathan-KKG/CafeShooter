package my_project.model.Environment;

/**
 * A cookingstation that is able to create Cakes and Pies
 */
public class Oven extends CookingStation{
    /**
     * Creates a new CookingStation
     *
     * @param pX       Starting x pos
     * @param pY       Starting y pos
     */
    public Oven(double pX, double pY) {
        super("Oven", pX, pY);
        cookingTime = 3;
    }

    /**
     * checks whether the click is valid or not
     * @param time time passed since initation of cooking in seconds
     * @param isMovingDownwards whether the UI indicator (if existing) is currently moving downwards
     * @param currentHitTimeWindow the time window (if existing) in which the player has to click {earliest, latest}
     * @return whether click was valid or not
     */
    @Override
    public boolean isClickValid(double time, boolean isMovingDownwards, double[] currentHitTimeWindow, int key, int neededKey) {
        return key == neededKey;
    }
}
