package my_project.model.GUI.GameStates;

import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * shows all dishes and their recipe
 */
public class RecipeTutorialUI extends GameStateUI {
    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     */
    public RecipeTutorialUI(double pX, double pY) {
        super(pX, pY);
    }

    /**
     * tels how the UI should look
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        drawDefaultBorder(drawTool, 5, startingPosition[0], startingPosition[1], 400, 500);
        try {
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Dishes/ApplePie.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3, startingPosition[1] + 500d * 0.125);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Egg.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 + 4, startingPosition[1] + 500d * 0.125 + 20);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Apple.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 + 20, startingPosition[1] + 500d * 0.125 + 20);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Flour.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 - 12, startingPosition[1] + 500d * 0.125 + 20);

            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Dishes/CheeseCake.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3, startingPosition[1] + 500d * 0.25 + 500d * 0.125);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Egg.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 + 4, startingPosition[1] + 500d * 0.25 + 500d * 0.125 + 20);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Cheese.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 + 20, startingPosition[1] + 500d * 0.25 + 500d * 0.125 + 20);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Flour.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 - 12, startingPosition[1] + 500d * 0.25 + 500d * 0.125 + 20);

            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Dishes/ChocolateCake.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3, startingPosition[1] + 500d * 0.25 * 2 + 500d * 0.125);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Egg.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 + 4, startingPosition[1] + 500d * 0.25 * 2 + 500d * 0.125 + 20);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Chocolate.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 + 20, startingPosition[1] + 500d * 0.25 * 2 + 500d * 0.125 + 20);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Flour.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 - 12, startingPosition[1] + 500d * 0.25 * 2 + 500d * 0.125 + 20);

            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Dishes/ChocolateCheeseCake.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3, startingPosition[1] + 500d * 0.25 * 3 + 500d * 0.125);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Cheese.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 + 28, startingPosition[1] + 500d * 0.25 * 3 + 500d * 0.125 + 20);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Chocolate.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 + 12, startingPosition[1] + 500d * 0.25 * 3 + 500d * 0.125 + 20);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Egg.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 - 4, startingPosition[1] + 500d * 0.25 * 3 + 500d * 0.125 + 20);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Flour.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 - 20, startingPosition[1] + 500d * 0.25 * 3 + 500d * 0.125 + 20);


            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Dishes/Coffee.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 * 2, startingPosition[1] + 500d * 0.25);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/CoffeePowder.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 * 2, startingPosition[1] + 500d * 0.25 + 20);

            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Dishes/SpaghettiCarbonara.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 * 2, startingPosition[1] + 500d * 0.25 + 500d * 0.5);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Cheese.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 * 2, startingPosition[1] + 500d * 0.25 + 500d * 0.5 + 25);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Bacon.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 * 2 + 21, startingPosition[1] + 500d * 0.25 + 500d * 0.5 + 25);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Egg.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 * 2 - 16, startingPosition[1] + 500d * 0.25 + 500d * 0.5 + 25);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Spaghetti.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 * 2 - 32, startingPosition[1] + 500d * 0.25 + 500d * 0.5 + 25);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/WhippedCream.png")), startingPosition[0] + 400d * 0.125 + 400d * 0.3 * 2 + 39, startingPosition[1] + 500d * 0.25 + 500d * 0.5 + 25);


            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Dishes/IceCreamWaffles.png")), startingPosition[0] + 400d * 0.125, startingPosition[1] + 500d * 0.3 * 2 + 500d * 0.15);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Flour.png")), startingPosition[0] + 400d * 0.125, startingPosition[1] + 500d * 0.3 * 2 + 500d * 0.15 + 20);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Egg.png")), startingPosition[0] + 400d * 0.125 - 16, startingPosition[1] + 500d * 0.3 * 2 + 500d * 0.15 + 20);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/IceCream.png")), startingPosition[0] + 400d * 0.125 + 20, startingPosition[1] + 500d * 0.3 * 2 + 500d * 0.15 + 20);


            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Dishes/StrawberryWaffles.png")), startingPosition[0] + 400d * 0.125, startingPosition[1] + 500d * 0.3 + 500d * 0.15);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Flour.png")), startingPosition[0] + 400d * 0.125, startingPosition[1] + 500d * 0.3 + 500d * 0.15 + 20);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Egg.png")), startingPosition[0] + 400d * 0.125 - 16, startingPosition[1] + 500d * 0.3 + 500d * 0.15 + 20);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Strawberry.png")), startingPosition[0] + 400d * 0.125 + 16, startingPosition[1] + 500d * 0.3 + 500d * 0.15 + 20);

            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Dishes/Waffles.png")), startingPosition[0] + 400d * 0.125, startingPosition[1] + 500d * 0.15);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Flour.png")), startingPosition[0] + 400d * 0.125 + 12, startingPosition[1] + 500d * 0.15 + 20);
            drawTool.drawImage(ImageIO.read(new File("src/main/resources/graphic/Ingredients/Egg.png")), startingPosition[0] + 400d * 0.125 - 4, startingPosition[1] + 500d * 0.15 + 20);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean isOnRestartButton(double pX, double pY) {
        return false;
    }
}
