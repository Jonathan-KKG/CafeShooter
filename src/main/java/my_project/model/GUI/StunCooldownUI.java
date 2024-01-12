package my_project.model.GUI;

import KAGO_framework.view.DrawTool;


/**
 *Ui at shooter position for displaying remaining time for reusing stun ability
 */
public class StunCooldownUI extends UI{
    private double duration;
    private double maxDuration;
    
    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     * @param pMaxDuration maximum duration that the stun lasts for
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
        //static part border
        drawTool.setCurrentColor(171, 128, 19,255);
        drawTool.drawCircle(x,y,13);
        //static part middle
        setDrawToolColorToRGBABackgroundVal(drawTool);
        drawTool.drawFilledCircle(x,y,13);

        //moving part
        drawTool.setCurrentColor((int) (20+ (duration/maxDuration) * 220), (int) (240- (duration/maxDuration) * 220), 20, 220);
        drawTool.drawFilledArc(x-10,y-10,20,90,duration/maxDuration*360,2);
    }

    public void setDuration(double pDuration){
        duration = pDuration;
    }
}
