package my_project.model.GUI;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public abstract class UI extends GraphicalObject {

    private int[] RGBABorderValues;
    private int[] RGBABackgroundValues;
    protected double[] absolutePosition;

    /**
     * Initializes UI Model
     *
     * @param pX Absolute location of the Model
     * @param pY Absolute location of the Model
     */
    public UI(double pX, double pY) {
        RGBABackgroundValues = new int[]{40, 40, 40, 200};
        RGBABorderValues = new int[]{40, 40, 40, 180};
        absolutePosition = new double[]{pX, pY};
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    public abstract void draw(DrawTool drawTool);

    /**
     * sets CurrentColor of drawTool to a common RGBA value (the one for a UI background)
     *
     * @param drawTool Required to set the color
     */
    protected void setDrawToolColorToRGBABackgroundVal(DrawTool drawTool) {
        drawTool.setCurrentColor(RGBABackgroundValues[0], RGBABackgroundValues[1], RGBABackgroundValues[2], RGBABackgroundValues[3]);
    }

    /**
     * sets CurrentColor of drawTool to a common RGBA value (the one for a UI Border)
     *
     * @param drawTool Required to draw the object
     */
    protected void setDrawToolColorToRGBABorderVal(DrawTool drawTool) {
        drawTool.setCurrentColor(RGBABorderValues[0], RGBABorderValues[1], RGBABorderValues[2], RGBABorderValues[3]);
    }
}
