package my_project.model;

import javax.imageio.ImageIO;
import java.io.File;

public class Enemy extends Entity {
    private String requiredDish;

    /**
     * Creates Enemy object of a specific type
     *
     * @param pX the start x-Position
     * @param pY the start y-Position
     */
    public Enemy(int enemyType, Double pX, Double pY) {
        super(pX, pY);
        String filepath = "src/main/resources/graphic/";
        switch (enemyType) {
            case 1 -> {
                requiredDish = "muffin.png";
                filepath += "Habib.png";
            }
            case 2 -> {
                requiredDish = "spaghet.png";
                filepath += "jonathan.png";
            }
            case 3 -> {
                requiredDish = "mikado.png";
                filepath += "callus.png";
            }
            case 4 -> {
                requiredDish = "cawfee.png";
                filepath += "mksch.png";
            }
            default -> {
                requiredDish = "cawfee.png";
                filepath += "window.png";
                System.out.println("invalid enemyType was provided");
            }
        }

        try {
            File file = new File(filepath);
            myImage = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        speed = 50;
        width = myImage.getWidth();
        height = myImage.getHeight();
    }

    public String getRequiredDish() {
        return requiredDish;
    }
}
