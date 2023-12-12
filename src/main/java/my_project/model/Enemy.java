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
        enemyType =(int) (Math.random()*4+1);
        requiredDish = "class my_project.model.Dish";
        speed = 100;
        try{
            if (enemyType == 1) {
                myImage = ImageIO.read(new File("src/main/resources/graphic/habib.png"));
                requiredDish = "Muffin.png";
            }
            else if (enemyType == 2) {
                myImage = ImageIO.read(new File("src/main/resources/graphic/jonathan.png"));
                requiredDish = "Spaghet.png";
            }
            else if (enemyType == 3) {
                myImage = ImageIO.read(new File("src/main/resources/graphic/callus.png"));
                requiredDish = "Mikado.png";
            }
            else if (enemyType == 4) {
                myImage = ImageIO.read(new File("src/main/resources/graphic/mksch.png"));
                requiredDish = "Cawfee.png";
            }
        } catch (Exception e){
            System.out.println("Creating sprite from pathname went wrong!");
        }

        width = myImage.getWidth();
        height = myImage.getHeight();
    }

    public void draw(DrawTool drawTool) {
        drawTool.drawImage(myImage,x,y);
    }

    public String getRequiredDish() {
        return requiredDish;
    }
}
