package my_project.model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class CollidableEnvironment extends Environment{
    private boolean isColliderActive;
    private int hp;

    /**
     * Additionally activates the collider
     */
    public CollidableEnvironment(String filename, double pX, double pY) {
        super(filename, pX, pY);
        isColliderActive = true;
        hp = 100;
    }

    /**
     * Second constructor to create invisible environmentalObjects
     */
    public CollidableEnvironment(double pX, double pY, double pWidth, double pHeight) {
        super( pX, pY ,pWidth,pHeight);
        isColliderActive = true;
        hp = 5;
    }

    public boolean isColliderActive() {
        return isColliderActive;
    }

    /**
     * reduces hp of the Bariers. If ig goes to 0 they brace and don`t stop entities anymore.
     */
    public void reduceHP(){
        hp--;
        if (hp <= 0) {
            isColliderActive = false;
            try {
                myImage =  ImageIO.read(new File("src/main/resources/graphic/window.png"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
