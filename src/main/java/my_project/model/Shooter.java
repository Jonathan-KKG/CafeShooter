package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.Queue;

public class Shooter extends Player {
    private Dish[] dishes;

    /**
     * @param pX the start x-Position
     * @param pY the stard y-Position
     */
    public Shooter(double pX, double pY) {
        super(pX, pY);
        dishes = new Dish[8];
        for (int i = 0; i <dishes.length; i++) {
            dishes[i] = new Dish("Houerglass.png", x, y);
        }
    }

    public Dish[] getDishes() {
        return dishes;
    }
}
