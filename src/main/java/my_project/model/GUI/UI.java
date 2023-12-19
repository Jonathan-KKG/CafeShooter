package my_project.model.GUI;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public abstract class UI extends GraphicalObject {

    private int[] rgbaBorderValues;
    private int[] rgbaBackgroundValues;
    protected double[] startingPosition;

    /**
     * Initializes UI Model
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     */
    public UI(double pX, double pY) {
        rgbaBackgroundValues = new int[]{40,40,40,160};
        rgbaBorderValues = new int[]{40,40,40,200};
        startingPosition = new double[]{pX, pY};
    }

    /**
     * Graphical implementation of the UI
     * @param drawTool Required to draw the object
     */
    public abstract void draw(DrawTool drawTool);

    /**
     * sets CurrentColor of drawTool to a common RGBA value (the one for a UI background)
     * @param drawTool Required to set the color
     */
    protected void setDrawToolColorToRGBABackgroundVal(DrawTool drawTool){
        drawTool.setCurrentColor(rgbaBackgroundValues[0], rgbaBackgroundValues[1], rgbaBackgroundValues[2], rgbaBackgroundValues[3]);
    }

    /**
     * sets CurrentColor of drawTool to a common RGBA value (the one for a UI Border)
     * @param drawTool Required to draw the object
     */
    protected void setDrawToolColorToRGBABorderVal(DrawTool drawTool){
        drawTool.setCurrentColor(rgbaBorderValues[0], rgbaBorderValues[1], rgbaBorderValues[2], rgbaBorderValues[3]);
    }
}
