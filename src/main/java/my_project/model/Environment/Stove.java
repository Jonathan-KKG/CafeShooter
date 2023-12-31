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
}
