package my_project.model.Environment.Storages;
import my_project.model.Ingredients.Apple;
import my_project.model.Ingredients.Ingredient;
/**
 * the apple storage
 */
public class AppleStorage extends Storage {
    /**
     * creates a storage
     * @param pX start x-position
     * @param pY start y-position
     */
    public AppleStorage(double pX, double pY) {
        super(pX, pY,"Apple");
    }

    /**
     * @return a new ingredient of the type that is created
     */
    @Override
    public Ingredient getIngredient() {
        return new Apple(x, y);
    }
}
