package my_project.model.Environment;

/**
 * A cookingstation that is able to create Waffles
 */
public class WaffleIron extends CookingStation {
    /**
     * Creates a new CookingStation
     *
     * @param pX       Starting x pos
     * @param pY       Starting y pos
     */
    public WaffleIron(double pX, double pY) {
        super("WaffleIron", pX, pY);
        cookingTime = 3;
    }
}
