package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Environment extends GraphicalObject {
    private Image img;

    public Environment(String filename, double pX, double pY) {
        try{
            File environmentFile = new File("src/main/resources/graphic/" + filename);
            img = ImageIO.read(environmentFile);
        } catch (Exception e){
            System.out.println("Creating sprite from pathname went wrong!");
        }
        x = pX;
        y = pY;
    }

    public void draw(DrawTool drawTool) {
        // for (int i = 0; i < 4; i++) {
        //     for (int j = 0; j < 3; j++) {
        //         drawTool.drawImage((BufferedImage) grassBg,500*i,500*j);
        //     }
        // }
        drawTool.drawImage((BufferedImage) img,x,y);
    }
}
