package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

/**
 * A class for every object on the screen that isn't moving and isn't UI
 */
public class Environment extends GraphicalObject {
    /**
     * @param filename Required to load sprite
     * @param pX       Required to set position
     * @param pY       Required to set position
     */
    public Environment(String filename, double pX, double pY) {
        super("Environment/" + filename, pX, pY);
        x = pX;
        y = pY;
    }

    /**
     * draws the image of (this)
     *
     * @param drawTool Required to draw the object
     */
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(myImage, x, y);
    }
}
