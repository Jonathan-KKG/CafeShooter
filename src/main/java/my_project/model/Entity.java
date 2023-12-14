package my_project.model;

import KAGO_framework.model.GraphicalObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Entity extends GraphicalObject {
    protected double speed;

    /**
     * Creates new Entity
     * @param filename The image the entity should have, file being located in the graphics folder
     * @param pX Starting x position
     * @param pY Starting y position
     */
    public Entity(String filename, double pX, double pY) {
        try{
            File file = new File("src/main/resources/graphic/" + filename);
            myImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        speed = 200;
        x = pX;
        y = pY;
        width = myImage.getWidth();
        height = myImage.getHeight();
    }

    /**
     * default constructor
     * @param pX Starting x position
     * @param pY Starting y position
     */
    public Entity(double pX, double pY){
        speed = 200;
        x = pX;
        y = pY;
    }

    public double getSpeed() {
        return speed;
    }


}
