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
        cookingTime = 10;
    }

    /**
     * checks whether the click is valid or not
     * @param time time passed since initation of cooking in seconds
     * @param isMovingDownwards whether the UI indicator (if existing) is currently moving downwards
     * @param currentHitTimeWindow the time window (if existing) in which the player has to click {earliest, latest}
     * @return whether click was valid or not
     */
    @Override
    public boolean isClickValid(double time, boolean isMovingDownwards, double[] currentHitTimeWindow) {
        for (double i = 0; i < cookingTime; i++) {
            if (
                isMovingDownwards &&
                    time < Math.cos(Math.PI * i)*0.5 * (1-2 *(1-currentHitTimeWindow[0]))+0.5+i + currentHitTimeWindow[1] - currentHitTimeWindow[0]  && // g: cos(π x)*0.5 (1-2 (1-a))+0.5+x
                    time > Math.cos(Math.PI * i)*0.5 * (1-2 *(1-currentHitTimeWindow[1]))+0.5+i + currentHitTimeWindow[1] - currentHitTimeWindow[0] ||
                !isMovingDownwards &&
                    time > Math.cos(Math.PI * i)*0.5 * (1-2 *(1-currentHitTimeWindow[0]))+0.5+i &&
                    time < Math.cos(Math.PI * i)*0.5 * (1-2 *(1-currentHitTimeWindow[1]))+0.5+i
            )
            {
                return true;
            }
        }
        return false;
    }
}
