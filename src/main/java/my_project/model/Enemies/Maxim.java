package my_project.model.Enemies;

import my_project.model.Dishes.Waffles;

/**
 * The Maxim Enemy.
 */
public class Maxim extends Enemy {

    /**
     * Creates Enemy object of a specific type
     *
     * @param pX the start x-Position
     * @param pY the start y-Position
     */
    public Maxim(double pX, double pY) {
        super("Maxim", pX, pY);
        requiredDish = Waffles.class.getSimpleName();
    }
}
