package my_project.model.Enemies;

import my_project.model.Dishes.ChocolateCake;

/**
 * The Habib Enemy.
 */
public class Habib extends Enemy {
    /**
     * Creates Enemy object of a specific type
     *
     * @param pX        the start x-Position
     * @param pY        the start y-Position
     */
    public Habib(double pX, double pY) {
        super("Habib", pX, pY);
        requiredDish = ChocolateCake.class.getSimpleName();
    }
}
