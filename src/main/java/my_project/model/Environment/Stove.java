package my_project.model.Environment;

import my_project.model.Dishes.SpaghettiCarbonara;

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

    }
}
