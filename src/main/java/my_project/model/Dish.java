package my_project.model;

import KAGO_framework.view.DrawTool;

public class Dish extends Item {
    protected double xVel;
    protected double yVel;

    public Dish(String filename, double pX, double pY) {
        super(filename,pX,pY);

    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(image,x - image.getWidth()/2,y - image.getHeight()/2);
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
