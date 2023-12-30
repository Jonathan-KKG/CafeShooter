package my_project.model.Environment.Racks;
import my_project.model.Ingredients.Chocolate;
import my_project.model.Ingredients.Ingredient;

/**
 * the chocolate storage
 */
public class ChocolateStorage extends Storage {
    /**
     * creates an storage
     * @param filename path of the sprite
     * @param pX start x-position
     * @param pY start y-position
     */
    public ChocolateStorage(String filename, double pX, double pY) {
        super(filename, pX, pY,"Chocolate");
    }
    /**
     * @return a new ingredeent of the type that is stored
     */

    @Override
    public Ingredient getIngredient() {
        return new Chocolate(x, y);
    }
}
