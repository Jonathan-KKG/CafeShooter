package my_project.model.Enemies;

import my_project.model.Dishes.ChocolateCheeseCake;

/**
 * The Ilias Enemy.
 */
public class Ilias extends Enemy {

    /**
     * Creates Enemy object of a specific type
     *
     * @param pX the start x-Position
     * @param pY the start y-Position
     */
    public Ilias(double pX, double pY) {
        super("Ilias", pX, pY);
        requiredDish = ChocolateCheeseCake.class.getSimpleName();
    }
}
