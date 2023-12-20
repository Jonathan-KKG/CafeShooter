package my_project.model.Enemies;

import my_project.model.Enemy;

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
    public Max(Double pX, Double pY) {
        super("Max", pX, pY);
        requiredDish = "Coffee";
    }
}
