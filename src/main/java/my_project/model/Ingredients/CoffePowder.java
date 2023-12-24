package my_project.model.Ingredients;

import my_project.model.Ingredient;

/**
 * The CoffePowder Ingredient
 */
public class CoffePowder extends Ingredient {
    /**
     * set starting position & sprite of Ingredient
     *
     * @param filename filename of the sprite
     * @param pX       x position at start
     * @param pY       y position at start
     */
    public CoffePowder(String filename, double pX, double pY) {
        super(filename, pX, pY);
    }
}
