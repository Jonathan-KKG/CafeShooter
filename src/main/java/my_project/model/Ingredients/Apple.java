package my_project.model.Ingredients;

import my_project.model.Ingredient;

/**
 * The Apple Ingredient.
 */
public class Apple extends Ingredient {

    /**
     * set starting position & sprite of Ingredient
     *
     * @param pX       x position at start
     * @param pY       y position at start
     */
    public Apple(double pX, double pY) {
        super("Apple", pX, pY);
    }
}
