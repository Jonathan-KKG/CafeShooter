package my_project.model.Environment.Racks;

import my_project.model.Ingredients.CoffeePowder;
import my_project.model.Ingredients.Ingredient;

/**
 * the cawfee powder storage
 */
public class CoffeePowderStorage extends Storage {
    /**
     * creates an storage
     * @param filename path of the sprite
     * @param pX start x-position
     * @param pY start y-position
     */
    public CoffeePowderStorage(String filename, double pX, double pY ) {
        super(filename, pX, pY,"CoffeePowder");
    }
    /**
     * @return a new ingredeent of the type that is stored
     */
    @Override
    public Ingredient getIngredient() {
        return new CoffeePowder(x, y);
    }
}
