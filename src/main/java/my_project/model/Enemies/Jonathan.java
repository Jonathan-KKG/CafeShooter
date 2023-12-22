package my_project.model.Enemies;

import my_project.model.Enemy;

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
    public Jonathan(Double pX, Double pY) {
        super("Jonathan", pX, pY);
        requiredDish = "SpaghettiCarbonara";
    }
}
