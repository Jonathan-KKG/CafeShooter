package my_project.model.Dishes;

import my_project.model.Dish;

/**
 * The CheeseCake Dish.
 */
public class CheeseCake extends Dish {

    /**
     * set starting position of Dish
     *
     * @param pX       x position at start
     * @param pY       y position at start
     */
    public CheeseCake(double pX, double pY) {
        super("CheeseCake", pX, pY);
    }
}
