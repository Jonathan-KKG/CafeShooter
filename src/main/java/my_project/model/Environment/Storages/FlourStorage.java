package my_project.model.Environment.Storages;
import my_project.model.Ingredients.Flour;
import my_project.model.Ingredients.Ingredient;

/**
 * the flour storage
 */
public class FlourStorage extends Storage {
    /**
     * creates an storage
     * @param pX start x-position
     * @param pY start y-position
     */
    public FlourStorage(double pX, double pY) {
        super(pX, pY,"Flour");
    }
    /**
     * @return a new ingredient of the type that is created
     */
    @Override
    public Ingredient getIngredient() {
        return new Flour(x, y);
    }
}
