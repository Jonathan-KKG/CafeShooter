package my_project.model.Environment.Racks;
import my_project.model.Ingredients.Cheese;
import my_project.model.Ingredients.Ingredient;

/**
 * the cheese storage
 */
public class CheeseStorage extends Storage {
    /**
     * creates an storage
     * @param filename path of the sprite
     * @param pX start x-position
     * @param pY start y-position
     */
    public CheeseStorage(String filename, double pX, double pY) {
        super(filename, pX, pY,"Cheese");
    }
    /**
     * @return a new ingredeent of the type that is stored
     */

    @Override
    public Ingredient getIngredient() {
        return new Cheese(x, y);
    }
}
