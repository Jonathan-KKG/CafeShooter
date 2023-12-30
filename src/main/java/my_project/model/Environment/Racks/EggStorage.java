package my_project.model.Environment.Racks;
import my_project.model.Ingredients.Egg;
import my_project.model.Ingredients.Ingredient;

/**
 * the egg storage
 */
public class EggStorage extends Storage {
    /**
     * creates an storage
     * @param filename path of the sprite
     * @param pX start x-position
     * @param pY start y-position
     */
    public EggStorage(String filename, double pX, double pY) {
        super(filename, pX, pY,"Egg");
    }
    /**
     * @return a new ingredeent of the type that is stored
     */
    @Override
    public Ingredient getIngredient() {
        return new Egg(x, y);
    }
}
