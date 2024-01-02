package my_project.model.Environment.Storages;
import my_project.model.Ingredients.Chocolate;
import my_project.model.Ingredients.Ingredient;

/**
 * the chocolate storage
 */
public class ChocolateStorage extends Storage {
    /**
     * creates an storage
     * @param pX start x-position
     * @param pY start y-position
     */
    public ChocolateStorage(double pX, double pY) {
        super(pX, pY,"Chocolate");
    }
    /**
     * @return a new ingredient of the type that is created
     */

    @Override
    public Ingredient getIngredient() {
        return new Chocolate(x, y);
    }
}
