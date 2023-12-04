package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Environment extends GraphicalObject {
    private BufferedImage sprite;

    public Environment(String pathname, double pX, double pY) throws IOException {
        try{
            File img = new File("src/main/resources/graphic/" + pathname);
            sprite = ImageIO.read(img);
        } catch (Exception e){
            System.out.println("Creating sprite from pathname went wrong!");
        }
        x = pX;
        y = pY;
    }

    public void draw(DrawTool drawTool) {
        drawTool.drawImage(sprite,x,y);
    }
}
