package my_project.model.Environment.Storages;
import my_project.model.Ingredients.Ingredient;
import my_project.model.Ingredients.Strawberry;
/**
 * the straberry storage
 */
public class StraberryStorage extends Storage {
    /**
     * creates an storage
     * @param pX start x-position
     * @param pY start y-position
     */
    public StraberryStorage(double pX, double pY) {
        super(pX, pY,"Strawberry");
    }
    /**
     * @return a new ingredient of the type that is created
     */
    @Override
    public Ingredient getIngredient() {
        return new Strawberry(x, y);
    }
}
