package my_project.model;

/**
 * a Dish Item that is able to satisfy enemies
 */
public abstract class Dish extends Item {
    private double[] direction;


    /**
     * set starting position & sprite of Dish
     *
     * @param filename filename of the sprite, is the type of Dish at the same time
     * @param pX       x position at start
     * @param pY       y position at start
     */
    public Dish(String filename, double pX, double pY) {
        super("Dishes/" + filename, pX, pY);
        speed = 500;
    }

    public void setDirection(double[] dir) {
        direction = dir;
    }

    public double[] getDirection() {
        return direction;
    }
}
