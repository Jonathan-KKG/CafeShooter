package my_project.model.Environment.Storages;
import my_project.model.Ingredients.Ingredient;
import my_project.model.Ingredients.Spaghetti;
/**
 * the spaghetti storage
 */
public class SpaghettiStorage extends Storage {
    /**
     * creates an storage
     * @param pX start x-position
     * @param pY start y-position
     */
    public SpaghettiStorage(double pX, double pY) {
        super(pX, pY,"Spaghetti");
    }
    /**
     * @return a new ingredient of the type that is created
     */
    @Override
    public Ingredient getIngredient() {
        return new Spaghetti(x, y);
    }
}
