package my_project.model.Enemies;

import my_project.model.Dishes.StrawberryWaffles;

/**
 * The Maksym Enemy.
 */
public class Maksym extends Enemy {

    /**
     * Creates Enemy object of a specific type
     *
     * @param pX the start x-Position
     * @param pY the start y-Position
     */
    public Maksym(double pX, double pY) {
        super("Maksym", pX, pY);
        requiredDish = StrawberryWaffles.class.getSimpleName();
    }
}
