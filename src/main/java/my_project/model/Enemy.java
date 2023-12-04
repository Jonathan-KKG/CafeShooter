package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Enemy extends Entety {
    private int enemyType;
    private BufferedImage image;
    private double speed;

    public Enemy(Double px, Double py) {
        x = px;
        y = py;
        enemyType =(int) (Math.random()*2+1);
        speed = 1;
        try{
            if (enemyType == 1)
                image = ImageIO.read(new File("src/main/resources/graphic/spaceship.png"));
            else if (enemyType == 2)
                image = ImageIO.read(new File("src/main/resources/graphic/Armor.png"));
        } catch (Exception e){
            System.out.println("Creating sprite from pathname went wrong!");
        }
    }
    public void draw(DrawTool drawTool) {
        drawTool.drawImage((BufferedImage) image,x,y);
    }

    public double getSpeed() {
        return speed;
    }
}
