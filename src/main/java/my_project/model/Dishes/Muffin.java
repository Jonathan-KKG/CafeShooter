package my_project.model.Dishes;

import my_project.model.Dish;

/**
 * The Muffin Dish.
 */
public class Muffin extends Dish {
    /**
     * set starting position & type of Dish
     *
     * @param pX       x position at start
     * @param pY       y position at start
     */
    public Muffin(double pX, double pY) {
        super("Muffin", pX, pY);
    }
}
