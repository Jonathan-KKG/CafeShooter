package my_project.model.Enemies;

import my_project.model.Dishes.IceCreamWaffles;

/**
 * The Alex Enemy.
 */
public class Alex extends Enemy {

    /**
     * Creates Enemy object of a specific type
     *
     * @param pX the start x-Position
     * @param pY the start y-Position
     */
    public Alex(double pX, double pY) {
        super("Alex", pX, pY);
        requiredDish = IceCreamWaffles.class.getSimpleName();
        speed += 1;
    }
}
