package my_project.model.Environment.Storages;
import my_project.model.Ingredients.Bacon;
import my_project.model.Ingredients.Ingredient;

/**
 * the bacon storage
 */
public class BaconStorage extends Storage {
    /**
     * creates an storage
     * @param pX start x-position
     * @param pY start y-position
     */
    public BaconStorage(double pX, double pY ) {
        super(pX, pY,"Bacon");
    }
    /**
     * @return a new ingredient of the type that is stored
     */
    @Override
    public Ingredient getIngredient() {
        return new Bacon(x, y);
    }
}
