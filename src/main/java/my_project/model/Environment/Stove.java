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
        cookingTime = 10;
        allowedMistakes = 1;
    }

    /**
     * checks whether the click is valid or not according to SkillCheck
     * @param time time passed since initation of cooking in seconds
     * @param isMovingDownwards whether the UI indicator is currently moving downwards
     * @param currentHitTimeWindow the time window in which the player has to click {earliest, latest}
     * @return whether click was valid or not
     */
    @Override
    public boolean isClickValid(double time, boolean isMovingDownwards, double[] currentHitTimeWindow, int key, int neededKey) {
        for (double i = 0; i < cookingTime; i++) {
            // g: cos(π x)*0.5 (1-2 (1-a))+0.5+x
            double earliestTimeWindow = Math.cos(Math.PI * i)*0.5 * (1-2 *(1-currentHitTimeWindow[0]))+0.5+i;
            double latestTimeWindow =   Math.cos(Math.PI * i)*0.5 * (1-2 *(1-currentHitTimeWindow[1]))+0.5+i;
            double timeDifference = currentHitTimeWindow[1] - currentHitTimeWindow[0];

            if (
                isMovingDownwards &&
                    time < earliestTimeWindow  + (timeDifference) * 0.25  &&
                    time > latestTimeWindow    + (timeDifference) * 0.25  ||
                !isMovingDownwards &&
                    time > earliestTimeWindow &&
                    time < latestTimeWindow
            )
            {
                return true;
            }
        }
        return false;
    }
}
