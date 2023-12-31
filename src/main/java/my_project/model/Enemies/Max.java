package my_project.model.Enemies;

import my_project.model.Dishes.Coffee;

/**
 * The Max Enemy.
 */
public class Max extends Enemy {
    /**
     * Creates Enemy object of a specific type
     *
     * @param pX        the start x-Position
     * @param pY        the start y-Position
     */
    public Max(double pX, double pY) {
        super("Max", pX, pY);
        requiredDish = Coffee.class.getSimpleName();
        speed -= 4;
    }
}
