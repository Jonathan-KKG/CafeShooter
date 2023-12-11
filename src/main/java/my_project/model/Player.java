package my_project.model;

import KAGO_framework.view.DrawTool;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public abstract class Player extends Entity {

    /**
     * @param pX the start x-Position
     * @param pY the stard y-Position
     */
    public Player(double pX, double pY){
        super(pX, pY);
        try {
            myImage = ImageIO.read(new File("src/main/resources/graphic/img.png"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        width = myImage.getWidth();
        height = myImage.getHeight();
    }

    /**
     *Drawes the Enemy
     * @param drawTool the tool used to draw things
     */
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(myImage,x,y);
    }

    public BufferedImage getImage() {
        return myImage;
    }
}
