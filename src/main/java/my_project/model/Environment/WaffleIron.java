package my_project.model.Environment;

import my_project.model.Dishes.IceCreamWaffles;
import my_project.model.Dishes.StrawberryWaffles;
import my_project.model.Dishes.Waffles;

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
        cookableDishes = new Class[]{Waffles.class, StrawberryWaffles.class, IceCreamWaffles.class};
    }
}
