package my_project.model.Dishes;

import my_project.model.Dish;

/**
 * The Spaghetti Dish.
 */
public class Spaghetti extends Dish {
    /**
     * set starting position & type of Dish
     *
     * @param filename filename of the sprite, is the type of Dish at the same time
     * @param pX       x position at start
     * @param pY       y position at start
     */
    public Spaghetti(String filename, double pX, double pY) {
        super(filename, pX, pY);
    }
}
