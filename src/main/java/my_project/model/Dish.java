package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    public void setxVel(double xVel) {
        this.xVel = xVel;
    }

    public void setyVel(double yVel) {
        this.yVel = yVel;
    }

    public double getxVel() {
        return xVel;
    }

    public double getyVel() {
        return yVel;
    }
}
