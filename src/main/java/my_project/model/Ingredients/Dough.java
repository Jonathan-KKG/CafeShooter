package my_project.model.Ingredients;

/**
 * The Dough Ingredient
 */
public class Dough extends Ingredient {
    /**
     * set starting position & sprite of Ingredient
     *
     * @param filename filename of the sprite
     * @param pX       x position at start
     * @param pY       y position at start
     */
    public Dough(String filename, double pX, double pY) {
        super(filename, pX, pY);
    }
}
