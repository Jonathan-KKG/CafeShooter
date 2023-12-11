package my_project.model;

public class Dish extends Item {
    protected double xVel;
    protected double yVel;

    public Dish(String filename, double pX, double pY) {
        super(filename,pX,pY);
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
}
