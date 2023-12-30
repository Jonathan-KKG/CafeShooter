package my_project.model.Environment.Storages;

import my_project.model.Ingredients.CoffeePowder;
import my_project.model.Ingredients.Ingredient;

/**
 * the cawfee powder storage
 */
public class CoffeePowderStorage extends Storage {
    /**
     * creates an storage
     * @param pX start x-position
     * @param pY start y-position
     */
    public CoffeePowderStorage(double pX, double pY ) {
        super(pX, pY,"CoffeePowder");
    }
    /**
     * @return a new ingredient of the type that is created
     */
    @Override
    public Ingredient getIngredient() {
        return new CoffeePowder(x, y);
    }
}
