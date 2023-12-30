package my_project.model.Environment.Racks;

import KAGO_framework.view.DrawTool;
import my_project.model.Ingredients.Bacon;
import my_project.model.Ingredients.Ingredient;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/**
 * the bacon storage
 */
public class BaconStorage extends Storage {
    /**
     * creates an storage
     * @param filename path of the sprite
     * @param pX start x-position
     * @param pY start y-position
     */
    public BaconStorage(String filename, double pX, double pY ) {
        super(filename, pX, pY,"Bacon");
    }
    /**
     * @return a new ingredeent of the type that is stored
     */
    @Override
    public Ingredient getIngredient() {
        return new Bacon(x, y);
    }
}
