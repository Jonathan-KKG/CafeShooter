package my_project.model;

import KAGO_framework.view.DrawTool;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public abstract class Player extends Entity {

    public Player(double pX, double pY){
        super(pX, pY);
        try {
            image = ImageIO.read(new File("src/main/resources/graphic/img.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void draw(DrawTool drawTool) {
        drawTool.drawImage(image,x - image.getWidth()/2,y - image.getHeight()/2);
    }

    public BufferedImage getImage() {
        return image;
    }
}
