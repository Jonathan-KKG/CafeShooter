package my_project.model.Enemies;

import my_project.model.Dishes.CheeseCake;

/**
 * The Haya Enemy.
 */
public class Haya extends Enemy {

    /**
     * Creates Enemy object of a specific type
     *
     * @param pX the start x-Position
     * @param pY the start y-Position
     */
    public Haya(double pX, double pY) {
        super("Haya", pX, pY);
        requiredDish = CheeseCake.class.getSimpleName();
    }
}
