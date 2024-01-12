package my_project.model.Environment.Storages;

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
    private BufferedImage ingredient;

    /**
     * creates an storage
     * @param pX start x-position
     * @param pY start y-position
     * @param type the ingredient the Storage Stores
     */
    public Storage(double pX, double pY, String type) {
        super("storage", pX, pY);
        try {
            ingredient = ImageIO.read(new File("src/main/resources/graphic/Ingredients/"+ type+".png"));
        } catch (IOException e) {
            System.out.println("immage wrong");
        }
    }

    /**
     * Additionally draws the corresponding ingredientImage
     * @param drawTool Required to draw the object
     */
    public void draw(DrawTool drawTool) {
        super.draw(drawTool);
        drawTool.drawImage(ingredient, x + myImage.getWidth() / 2d - ingredient.getWidth() / 2d, y + myImage.getHeight() / 2d - ingredient.getHeight() / 2d);
    }

    public abstract Ingredient getIngredient();
}
