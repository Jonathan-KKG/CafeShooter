package my_project.model.GUI;

import KAGO_framework.view.DrawTool;

// TODO: javad
public class StunCooldownUI extends UI{
    
    private double duration;
    private double maxDuration;
    
    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     */
    public StunCooldownUI(double pX, double pY, double pMaxDuration) {
        super(pX, pY);
        maxDuration = pMaxDuration;
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    public void draw(DrawTool drawTool) {
        drawDefaultBorder(drawTool, 3, x,y,18,6);

        drawTool.setCurrentColor((int) (240 - (duration/maxDuration) * 220), (int) (20 + (duration/maxDuration) * 220), 20, 220);
        drawTool.drawFilledRectangle(x+1.5,y+1, duration * 16 - 0.5,4);

    }

    public void setDuration(double pDuration){
        duration = pDuration;
    }
}
