package my_project.model.Ingredients;

/**
 * The Egg Ingredient
 */
public class Egg extends Ingredient {
    /**
     * set starting position & sprite of Ingredient
     *
     * @param filename filename of the sprite
     * @param pX       x position at start
     * @param pY       y position at start
     */
    public Egg(String filename, double pX, double pY) {
        super(filename, pX, pY);
    }
}
