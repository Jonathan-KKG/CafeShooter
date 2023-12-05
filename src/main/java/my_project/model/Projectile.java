package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Projectile extends GraphicalObject {
    private BufferedImage image;
    private double xDir;
    private double yDir;

    public Projectile(String filename, double pX, double pY, double pXDir, double pYDir) {
        try{
            File file = new File("src/main/resources/graphic/" + filename);
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        x = pX;
        y = pY;
        xDir = pXDir;
        yDir = pYDir;


    }

    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(image,x - image.getWidth()/2,y - image.getHeight()/2);

    }
}
