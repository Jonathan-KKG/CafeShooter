package my_project.model.Ingredients;

/**
 * The Cookie Ingredient
 */
public class Cookie extends Ingredient {
    /**
     * set starting position & sprite of Ingredient
     *
     * @param pX       x position at start
     * @param pY       y position at start
     */
    public Cookie( double pX, double pY) {
        super("Cookies", pX, pY);
    }
}
