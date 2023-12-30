package my_project.model.Environment.Racks;
import my_project.model.Ingredients.Apple;
import my_project.model.Ingredients.Ingredient;
/**
 * the apple storage
 */
public class AppleStorage extends Storage {
    /**
     * creates an storage
     * @param filename path of the sprite
     * @param pX start x-position
     * @param pY start y-position
     */
    public AppleStorage(String filename, double pX, double pY) {
        super(filename, pX, pY,"Apple");
    }

    /**
     * @return a new ingredeent of the type that is stored
     */
    @Override
    public Ingredient getIngredient() {
        return new Apple(x, y);
    }
}
