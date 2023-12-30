package my_project.model.Environment.Racks;

import KAGO_framework.view.DrawTool;
import my_project.model.Environment.CollidableEnvironment;
import my_project.model.Ingredients.Ingredient;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * an object of this creates ingredient on interaction.
 */
public abstract class Storage extends CollidableEnvironment {
    private BufferedImage ingredeent;

    /**
     * creates an storage
     * @param filename path of the sprite
     * @param pX start x-position
     * @param pY start y-position
     * @param type the ingredient the Storage Stores
     */
    public Storage(String filename, double pX, double pY, String type) {
        super(filename, pX, pY);
        try {
            ingredeent = ImageIO.read(new File("src/main/resources/graphic/Ingredients/"+ type+".png"));
        } catch (IOException e) {
            System.out.println("immage wrong");
        }
    }

    @Override
    public void draw(DrawTool drawTool) {
        super.draw(drawTool);
        drawTool.drawImage(ingredeent, x + myImage.getWidth() / 2d - ingredeent.getWidth() / 2d, y + myImage.getHeight() / 2d - ingredeent.getHeight() / 2d);
    }

    public abstract Ingredient getIngredient();
}
