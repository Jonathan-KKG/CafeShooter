package my_project.model;

public class Dish extends Item {
    private double xVel;
    private double yVel;
    private String type;


    /**
     * set starting position & type of Dish
     * @param filename filename of the sprite, is the type of Dish at the same time
     * @param pX x position at start
     * @param pY y position at start
     */
    public Dish(String filename, double pX, double pY) {
        super(filename,pX,pY);
        type = filename;
    }

    public void setXVel(double xVel) {
        this.xVel = xVel;
    }

    public void setYVel(double yVel) {
        this.yVel = yVel;
    }

    public double setXVel() {
        return xVel;
    }

    public double getYVel() {
        return yVel;
    }

    public String getType() {
        return type;
    }
}
