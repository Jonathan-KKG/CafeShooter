package my_project.model.Enemies;

import my_project.model.Dishes.ApplePie;

/**
 * The Carlos Enemy.
 */
public class Carlos extends Enemy {
    /**
     * Creates Enemy object of a specific type
     *
     * @param pX        the start x-Position
     * @param pY        the start y-Position
     */
    public Carlos(Double pX, Double pY) {
        super("callus", pX, pY);
        requiredDish = ApplePie.class.getSimpleName();
    }
}
