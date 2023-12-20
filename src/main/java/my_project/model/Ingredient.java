package my_project.model;

/**
 * Ingredients that are required to create a Dish
 */
public abstract class Ingredient extends Item {
    public Ingredient(String filename, double pX, double pY) {
        super(filename, pX, pY);
    }
}
