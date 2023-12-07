package my_project.model;

import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.io.File;

public class Enemy extends Entity {
    private int enemyType;
    private String requiredDish;

    /**
     * @param pX the start x-Position
     * @param pY the start y-Position
     */
    public Enemy(Double pX, Double pY) {
        super(pX, pY);
        enemyType =(int) (Math.random()*2+1);
        requiredDish = "class my_project.model.Dish";
        speed = 100;
        try{
            if (enemyType == 1)
                myImage = ImageIO.read(new File("src/main/resources/graphic/spaceship.png"));
            else if (enemyType == 2)
                myImage = ImageIO.read(new File("src/main/resources/graphic/Armor.png"));
        } catch (Exception e){
            System.out.println("Creating sprite from pathname went wrong!");
        }

        width = myImage.getWidth();
        height = myImage.getHeight();
    }

    public void draw(DrawTool drawTool) {
        drawTool.drawImage(myImage,x - myImage.getWidth()/2,y - myImage.getHeight()/2);
    }

    public String getRequierdDish() {
        return requiredDish;
    }
}
