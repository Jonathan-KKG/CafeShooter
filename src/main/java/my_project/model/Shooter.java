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
        dishes = new Dish[5];
        for (int i = dishes.length; i > 0; i--) {
            int help = (int) (Math.random() * 4 +1);
            if (help == 1)
                dishes[i - 1] = new Dish("Muffin.png", 1400 + 35 * i, 840);
            if (help == 2)
                dishes[i - 1] = new Dish("Spaghet.png", 1400 + 35 * i, 840);
            if (help == 3)
                dishes[i - 1] = new Dish("Mikado.png", 1400 + 35 * i, 840);
            if (help == 4)
                dishes[i - 1] = new Dish("Cawfee.png", 1400 + 35 * i, 840);
        }
    }

    /**
     * Returns the current Dish and removes it from the array
     *
     * @return the current Dish
     */
    public Dish getCurrentDish() {
        if (currentDish == -1)
            return null;

        Dish output = dishes[currentDish];
        dishes[currentDish] = null;
        nextBullet();
        return output;
    }

    public Dish[] getAllDishes() {
        return dishes;
    }

    /**
     * sets the current bullet on the next element in the array.
     * If current bullet is last element, it starts searching from the beginning
     */
    public void nextBullet() {
        currentDish = nextOccupiedIndex();
    }

    /**
     * finds next index in dishes array that is not null
     * @return next occupied index
     */
    private int nextOccupiedIndex() {
        if (currentDish != dishes.length - 1)
            for (int i = currentDish + 1; i < dishes.length; i++)
                if (dishes[i] != null)
                    return i;

        for (int i = 0; i < currentDish; i++) {
            if (dishes[i] != null)
                return i;
        }
        return -1;
    }
}
