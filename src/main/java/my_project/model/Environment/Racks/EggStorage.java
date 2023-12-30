package my_project.model.Environment.Racks;
import my_project.model.Ingredients.Egg;
import my_project.model.Ingredients.Ingredient;

/**
 * the egg storage
 */
public class EggStorage extends Storage {
    /**
     * creates an storage
     * @param pX start x-position
     * @param pY start y-position
     */
    public EggStorage(double pX, double pY) {
        super(pX, pY,"Egg");
    }
    /**
     * @return a new ingredient of the type that is created
     */
    @Override
    public Ingredient getIngredient() {
        return new Egg(x, y);
    }
}
