package my_project.model.GUI;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

/**
 * Contains values and methods to ensure uniform colors and a draw method for UIs
 */
public abstract class UI extends GraphicalObject {

    private int[] rgbaBorderValues;
    private int[] rgbaBackgroundValues;
    protected double[] startingPosition;

    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     */
    public UI(double pX, double pY) {
        rgbaBackgroundValues = new int[]{40, 40, 40, 160};
        rgbaBorderValues = new int[]{40, 40, 40, 200};
        startingPosition = new double[]{pX, pY};
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
        drawTool.setCurrentColor(rgbaBackgroundValues[0], rgbaBackgroundValues[1], rgbaBackgroundValues[2], rgbaBackgroundValues[3]);
    }

    /**
     * sets CurrentColor of drawTool to a common RGBA value (the one for a UI Border)
     *
     * @param drawTool Required to draw the object
     */
    protected void setDrawToolColorToRGBABorderVal(DrawTool drawTool) {
        drawTool.setCurrentColor(rgbaBorderValues[0], rgbaBorderValues[1], rgbaBorderValues[2], rgbaBorderValues[3]);
    }

    /**
     * Draws a rectangular border with background that is uniform across all GUIs that utilize it
     * @param drawTool DrawTool that should be drawn with
     * @param lineWidth desired linewidth; -1 for default
     * @param pX leftmost x pos
     * @param pY uppermost y pos
     * @param width width of the border & background
     * @param height height of the border & background
     */
    protected void drawDefaultBorder(DrawTool drawTool, int lineWidth, double pX, double pY, double width, double height){
        if(lineWidth <= 0)
            drawTool.setLineWidth(5);
        else
            drawTool.setLineWidth(lineWidth);

        setDrawToolColorToRGBABorderVal(drawTool);
        drawTool.drawRectangle(pX,pY,width,height);

        setDrawToolColorToRGBABackgroundVal(drawTool);
        drawTool.drawFilledRectangle(pX,pY,width,height);
    }
}
