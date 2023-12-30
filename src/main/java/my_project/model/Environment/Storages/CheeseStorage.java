package my_project.model.Environment.Storages;
import my_project.model.Ingredients.Cheese;
import my_project.model.Ingredients.Ingredient;

/**
 * the cheese storage
 */
public class CheeseStorage extends Storage {
    /**
     * creates an storage
     * @param pX start x-position
     * @param pY start y-position
     */
    public CheeseStorage(double pX, double pY) {
        super(pX, pY,"Cheese");
    }
    /**
     * @return a new ingredient of the type that is created
     */

    @Override
    public Ingredient getIngredient() {
        return new Cheese(x, y);
    }
}
