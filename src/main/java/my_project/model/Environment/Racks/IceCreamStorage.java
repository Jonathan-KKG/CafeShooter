package my_project.model.Environment.Racks;
import my_project.model.Ingredients.IceCream;
import my_project.model.Ingredients.Ingredient;
/**
 * the ice cream storage
 */
public class IceCreamStorage extends Storage {
    /**
     * creates an storage
     * @param pX start x-position
     * @param pY start y-position
     */
    public IceCreamStorage(double pX, double pY) {
        super(pX, pY,"IceCream");
    }
    /**
     * @return a new ingredient of the type that is created
     */
    @Override
    public Ingredient getIngredient() {
        return new IceCream(x, y);
    }
}
