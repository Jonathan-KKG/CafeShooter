package my_project.model;

public class Shooter extends Player {
    private Dish[] dishes;
    private int currentDish;

    /**
     * @param pX the start x-Position
     * @param pY the stard y-Position
     */
    public Shooter(double pX, double pY) {
        super(pX, pY);
        dishes = new Dish[8];
        for (int i = 0; i <dishes.length; i++) {
            dishes[i] = new Dish("floortile.png", x, y);
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
