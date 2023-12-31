package my_project.model.Environment;

/**
 * A cookingstation that is able to create Cakes and Pies
 */
public class Oven extends CookingStation{
    /**
     * Creates a new CookingStation
     *
     * @param pX       Starting x pos
     * @param pY       Starting y pos
     */
    public Oven(double pX, double pY) {
        super("Oven", pX, pY);
        cookingTime = 3;
    }

}
