package my_project.model;

import KAGO_framework.Config;
import KAGO_framework.model.abitur.datenstrukturen.Queue;

public class Shooter extends Player {
    private Dish[] dishes;
    private int currentDish;

    /**
     * @param pX the start x-Position
     * @param pY the stard y-Position
     */
    public Shooter(double pX, double pY) {
        super(pX, pY);
        dishes = new Dish[5];
        for (int i = dishes.length; i > 0; i--) {
            dishes[i-1] = new Dish("floortile.png", 1400 + 35 * i,840);
        }
    }

    /**
     * Returns the current Dish and removes it from the array
     * @return the current Dish
     */
    public Dish getCurrentDish() {
        Dish output = dishes[currentDish];
        dishes[currentDish] = null;
        nextBullet();
        return output;
    }

    public Dish[] getAllDishes(){
        return dishes;
    }

    /**
     * sets the current bullet on the next element in the array.
     * If current bullet is last element, it's set on the first element
     */
    public void nextBullet(){
        if(currentDish == dishes.length-1)
            currentDish = 0;
        else currentDish++;
    }


}
