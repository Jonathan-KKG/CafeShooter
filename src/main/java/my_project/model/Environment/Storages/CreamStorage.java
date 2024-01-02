package my_project.model.Environment.Storages;
import my_project.model.Ingredients.Cream;
import my_project.model.Ingredients.Ingredient;
/**
 * the cream storage
 */
public class CreamStorage extends Storage {
    /**
     * creates an storage
     * @param pX start x-position
     * @param pY start y-position
     */
    public CreamStorage(double pX, double pY) {
        super( pX, pY,"Apple");
    }

    /**
     * @return a new ingredient of the type that is stored
     */
    @Override
    public Ingredient getIngredient() {
        return new Cream(x, y);
    }
}
