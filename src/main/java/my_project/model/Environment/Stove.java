package my_project.model.Environment;

/**
 * A cookingstation that is able to create Spaghetti
 */
public class Stove extends CookingStation{

    /**
     * Creates a new CookingStation
     *
     * @param pX       Starting x pos
     * @param pY       Starting y pos
     */
    public Stove(double pX, double pY) {
        super("Stove", pX, pY);
        cookingTime = 3;
    }

    /**
     * checks whether the click is valid or not according to SkillCheck
     * @param time time passed since initation of cooking in seconds
     * @param isMovingDownwards whether the UI indicator is currently moving downwards
     * @param currentHitTimeWindow the time window in which the player has to click {earliest, latest}
     * @return whether click was valid or not
     */
    @Override
    public boolean isClickValid(double time, boolean isMovingDownwards, double[] currentHitTimeWindow) {
        return true;
    }
}
