package my_project.model.Ingredients;

/**
 * The Cheese Ingredient
 */
public class Cheese extends Ingredient {
    /**
     * set starting position & sprite of Ingredient
     *
     * @param pX       x position at start
     * @param pY       y position at start
     */
    public Cheese( double pX, double pY) {
        super("Cheese", pX, pY);
    }
}
