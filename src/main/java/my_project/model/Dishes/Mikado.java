package my_project.model.Dishes;

import my_project.model.Dish;

/**
 * The Mikado Dish.
 */
public class Mikado extends Dish {
    /**
     * set starting position & type of Dish
     *
     * @param pX       x position at start
     * @param pY       y position at start
     */
    public Mikado(double pX, double pY) {
        super("Mikado", pX, pY);
    }
}
