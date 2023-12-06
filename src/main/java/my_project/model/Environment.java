package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.io.File;

public class Environment extends GraphicalObject {
    private boolean isActive;

    /**
     * @param filename Required to load sprite
     * @param pX Required to set position
     * @param pY Required to set position
     */
    public Environment(String filename, double pX, double pY) {
        try{
            File environmentFile = new File("src/main/resources/graphic/" + filename);
            setImage(ImageIO.read(environmentFile));
        } catch (Exception e){
            System.out.println("Creating sprite from pathname went wrong!");
        }
        x = pX;
        y = pY;
        width = getMyImage().getWidth();
        height = getMyImage().getHeight();
        isActive = true;
    }

    public void draw(DrawTool drawTool) {
        drawTool.drawImage(myImage,x - myImage.getWidth() / 2,y - myImage.getHeight() / 2);
    }

    public boolean isActive() {
        return isActive;
    }
}
