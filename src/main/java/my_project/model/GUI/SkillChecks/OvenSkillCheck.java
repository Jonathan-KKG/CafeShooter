package my_project.model.GUI.SkillChecks;

import KAGO_framework.view.DrawTool;

/**
 * the SkillCheck for the Oven CookingStation
 */
public class OvenSkillCheck extends timeableSkillCheck{

    /**
     * Initializes UI Model
     *
     * @param pX    Starting location of the Model
     * @param pY    Starting location of the Model
     * @param firstValidHitTimeWindow the starting hit time window that is required of the player {earliest, latest}
     */
    public OvenSkillCheck(double pX, double pY, double[] firstValidHitTimeWindow) {
        super(pX, pY, firstValidHitTimeWindow);
        increment = 0.2;
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        super.draw(drawTool);

        // Progress Bar - moving part
        drawTool.setLineWidth(1);
        drawTool.setCurrentColor(0,255,0,255);
        drawTool.drawFilledRectangle(startingPosition[0] + width * 0.55, startingPosition[1] - height * 0.75 + height * 0.7 * (1  - progress), 5, height * 0.7 * progress);
        // Progress Bar - static part
        drawTool.setCurrentColor(0,0,0,255);
        drawTool.drawRectangle(startingPosition[0] + width * 0.55, startingPosition[1] - height * 0.75, 5, height * 0.7);
    }
}
