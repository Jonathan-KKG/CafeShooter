package my_project.model.Environment.Racks;
import my_project.model.Ingredients.IceCream;
import my_project.model.Ingredients.Ingredient;
/**
 * the ice cream storage
 */
public class IceCreamStorage extends Storage {
    /**
     * creates an storage
     * @param filename path of the sprite
     * @param pX start x-position
     * @param pY start y-position
     */
    public IceCreamStorage(String filename, double pX, double pY) {
        super(filename, pX, pY,"IceCream");
    }
    /**
     * @return a new ingredeent of the type that is stored
     */
    @Override
    public Ingredient getIngredient() {
        return new IceCream(x, y);
    }
}
