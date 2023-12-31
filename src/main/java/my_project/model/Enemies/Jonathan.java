package my_project.model.Enemies;

import my_project.model.Dishes.SpaghettiCarbonara;

/**
 * The Jonathan Enemy.
 */
public class Jonathan extends Enemy {
    /**
     * Creates Enemy object of a specific type
     *
     * @param pX        the start x-Position
     * @param pY        the start y-Position
     */
    public Jonathan(double pX, double pY) {
        super("Jonathan", pX, pY);
        requiredDish = SpaghettiCarbonara.class.getSimpleName();
        speed -= 2;
    }
}
