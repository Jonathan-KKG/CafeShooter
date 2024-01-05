package my_project.model.Environment.Storages;
import my_project.model.Ingredients.WhippedCream;
import my_project.model.Ingredients.Ingredient;
/**
 * the cream storage
 */
public class WhippedCreamStorage extends Storage {
    /**
     * creates an storage
     * @param pX start x-position
     * @param pY start y-position
     */
    public WhippedCreamStorage(double pX, double pY) {
        super( pX, pY,WhippedCream.class.getSimpleName());
    }

    /**
     * @return a new ingredient of the type that is stored
     */
    @Override
    public Ingredient getIngredient() {
        return new WhippedCream(x, y);
    }
}
