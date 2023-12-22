package my_project.model.Environment;

import my_project.model.Dishes.ApplePie;
import my_project.model.Dishes.CheeseCake;
import my_project.model.Dishes.ChocolateCake;
import my_project.model.Dishes.ChocolateCheeseCake;

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
        cookableDishes = new Class[]{ChocolateCake.class, CheeseCake.class, ChocolateCheeseCake.class, ApplePie.class};
    }
}
