package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.io.File;

public class Environment extends GraphicalObject {
    /**
     * @param filename Required to load sprite
     * @param pX       Required to set position
     * @param pY       Required to set position
     */
    public Environment(String filename, double pX, double pY) {
        try {
            myImage = ImageIO.read(new File("src/main/resources/graphic/" + filename));
        } catch (Exception e) {
            System.out.println("Creating sprite from pathname went wrong!");
        }
        x = pX;
        y = pY;
        width = myImage.getWidth();
        height = myImage.getHeight();
    }

    /** draws the image of (this)
     * @param drawTool
     */
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(myImage, x, y);
        drawTool.drawRectangle(x,y,width, height);
    }
}
