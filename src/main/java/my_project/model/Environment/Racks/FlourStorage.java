package my_project.model.Environment.Racks;
import my_project.model.Ingredients.Flour;
import my_project.model.Ingredients.Ingredient;

/**
 * the flour storage
 */
public class FlourStorage extends Storage {
    /**
     * creates an storage
     * @param filename path of the sprite
     * @param pX start x-position
     * @param pY start y-position
     */
    public FlourStorage(String filename, double pX, double pY) {
        super(filename, pX, pY,"Flour");
    }
    /**
     * @return a new ingredeent of the type that is stored
     */
    @Override
    public Ingredient getIngredient() {
        return new Flour(x, y);
    }
}
