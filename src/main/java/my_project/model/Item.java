package my_project.model;

import KAGO_framework.model.GraphicalObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Item extends GraphicalObject {
    protected BufferedImage image;
    public Item(String filename, double pX, double pY) {
        try{
            File file = new File("src/main/resources/graphic/" + filename);
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        x = pX;
        y = pY;
    }
}
