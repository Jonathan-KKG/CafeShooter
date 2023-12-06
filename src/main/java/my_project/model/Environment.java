package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.io.File;

public class Environment extends GraphicalObject {
    private boolean isActive;

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
        drawTool.drawImage(getMyImage(),x,y);
    }

    public boolean isActive() {
        return isActive;
    }
}
