package my_project.model.Environment.Racks;
import my_project.model.Ingredients.Ingredient;
import my_project.model.Ingredients.Strawberry;
/**
 * the straberry storage
 */
public class StraberryStorage extends Storage {
    /**
     * creates an storage
     * @param filename path of the sprite
     * @param pX start x-position
     * @param pY start y-position
     */
    public StraberryStorage(String filename, double pX, double pY) {
        super(filename, pX, pY,"Strawberry");
    }
    /**
     * @return a new ingredeent of the type that is stored
     */
    @Override
    public Ingredient getIngredient() {
        return new Strawberry(x, y);
    }
}
