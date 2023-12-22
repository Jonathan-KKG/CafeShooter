package my_project.model.Environment;

import my_project.model.Dishes.Coffee;

/**
 * A cookingstation that is able to create Coffee
 */
public class CoffeeMachine extends CookingStation{
    /**
     * Creates a new CookingStation
     *
     * @param pX       Starting x pos
     * @param pY       Starting y pos
     */
    public CoffeeMachine(double pX, double pY) {
        super("CoffeeMachine", pX, pY);
        cookableDishes = new Class[]{Coffee.class};
    }
}
