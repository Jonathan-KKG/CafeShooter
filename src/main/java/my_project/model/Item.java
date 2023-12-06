package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Item extends GraphicalObject {
    /**
     * @param filename the Immage that should be Drawn.
     * @param pX the start x-Position
     * @param pY the stard y-Position
     */
    public Item(String filename, double pX, double pY) {
        try{
            File file = new File("src/main/resources/graphic/" + filename);
            myImage = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        x = pX;
        y = pY;
        width = myImage.getWidth();
        height = myImage.getHeight();
    }

    /**
     *Drawes the Enemy
     * @param drawTool the tool used to draw things
     */
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(myImage,x - myImage.getWidth()/2,y - myImage.getHeight()/2);
    }
}
