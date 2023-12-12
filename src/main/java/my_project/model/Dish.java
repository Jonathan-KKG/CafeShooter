package my_project.model;

public class Dish extends Item {
    private double xVel;
    private double yVel;
    private String type;


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
