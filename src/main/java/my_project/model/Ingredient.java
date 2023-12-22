package my_project.model;

/**
 * Ingredients that are required to create a Dish
 */
public abstract class Ingredient extends Item {

    /**
     * set starting position & sprite of Ingredient
     *
     * @param filename filename of the sprite
     * @param pX       x position at start
     * @param pY       y position at start
     */
    public Ingredient(String filename, double pX, double pY) {
        super(filename, pX, pY);
    }
}
