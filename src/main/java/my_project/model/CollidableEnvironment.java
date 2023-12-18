package my_project.model;

import javax.imageio.ImageIO;
import java.io.File;

public class CollidableEnvironment extends Environment {
    private boolean isColliderActive;
    private int hp;
    private String type;

    /**
     * Additionally activates the collider
     */
    public CollidableEnvironment(String filename, double pX, double pY) {
        super(filename, pX, pY);
        type = filename;
        isColliderActive = true;
        hp = 100;
    }

    /**
     * reduces hp of the Barriers. If hp is <=0 it stops colliding & switches img
     */
    public void reduceHP() {
        hp--;
        if (hp <= 0) {
            isColliderActive = false;
            try {
                myImage = ImageIO.read(new File("src/main/resources/graphic/Environment/window.png"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public boolean isColliderActive() {
        return isColliderActive;
    }

    public String getType() {
        return type;
    }
}
