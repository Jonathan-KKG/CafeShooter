package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Dish extends Item {
    protected double xDir;
    protected double yDir;

    public Dish(String filename, double pX, double pY, double pXDir, double pYDir) {
        super(filename,pX,pY);
        xDir = pXDir;
        yDir = pYDir;

    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(image,x - image.getWidth()/2,y - image.getHeight()/2);
    }


}
