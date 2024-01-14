package my_project.model.Enemies;

import my_project.model.Dishes.ChocolateCheeseCake;

/**
 * The Kamel Enemy.
 */
public class Kamel extends Enemy {

    /**
     * Creates Enemy object of a specific type
     *
     * @param pX the start x-Position
     * @param pY the start y-Position
     */
    public Kamel(double pX, double pY) {
        super("Kamel", pX, pY);
        requiredDish = ChocolateCheeseCake.class.getSimpleName();
        speed -= 1;
    }
}
