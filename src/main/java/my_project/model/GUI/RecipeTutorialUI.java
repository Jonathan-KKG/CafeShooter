package my_project.model.GUI;

import KAGO_framework.view.DrawTool;

/**
 * shows all dishes and their recipe
 */
public class RecipeTutorialUI extends UI {
    
    private int borderOffset;
    
    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     */
    public RecipeTutorialUI(double pX, double pY) {
        super(pX, pY);
        setNewImage("src/main/resources/graphic/Recipes.png");
        borderOffset = 25;
    }

    /**
     * tels how the UI should look
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        drawDefaultBorder(drawTool, 5, startingPosition[0], startingPosition[1], myImage.getWidth() + borderOffset * 2, myImage.getHeight() + borderOffset * 2);
        drawTool.drawImage(myImage,startingPosition[0] + borderOffset,startingPosition[1] + borderOffset);
    }
}
