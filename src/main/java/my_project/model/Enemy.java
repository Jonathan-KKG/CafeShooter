package my_project.model;

import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class Enemy extends Entity {
    private int enemyType;
    private BufferedImage image;

    public Enemy(Double pX, Double pY) {
        super(pX, pY);
        enemyType =(int) (Math.random()*2+1);
        speed = 28;
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
        drawTool.drawImage(image,x - image.getWidth()/2,y - image.getHeight()/2);
    }

}
