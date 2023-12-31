package my_project.model.GUI.SkillChecks;

import KAGO_framework.view.DrawTool;

/**
 * the SkillCheck for the CoffeeMachine CookingStation
 */
public class CoffeeMachineSkillCheck extends SkillCheckUI{

    private double hitzoneYPosition;
    private double hitzoneHeight;

    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     * @param validHitTimeWindow the hit time window that is required of the player {earliest, latest}
     */
    public CoffeeMachineSkillCheck(double pX, double pY, double[] validHitTimeWindow) {
        super(pX, pY);
        increment = 1;
        y = startingPosition[1];
        hitzoneYPosition = startingPosition[1] - height * 0.8 + 1 + 154 * (1 - validHitTimeWindow[1]);
        hitzoneHeight = (startingPosition[1] - height * 0.8 + 1 + 154 - hitzoneYPosition) * (validHitTimeWindow[1] - validHitTimeWindow[0]);
    }

    /**
     * Moves the current position indicator up and down
     * @param time Time passed since creation of the skillcheck (in seconds)
     */
    @Override
    public void updateSkillCheck(double time) {
        y = startingPosition[1] - height * 0.8 + 1 + 154 * (Math.cos(Math.PI * time) * 0.5 + 0.5); // last multiplier in brackets is a value phasing between 0 and 1
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        drawDefaultSCBorder(drawTool);

        // Valid Hitzone indicator
        drawTool.setCurrentColor(255, 0, 0, 160);
        drawTool.drawFilledRectangle(startingPosition[0] - width * 0.45 + 16 + width / 4 + 1.5, hitzoneYPosition, width * 0.4 - 3, hitzoneHeight);

        // Current position indicator
        drawTool.setCurrentColor(255, 0, 0, 235);
        drawTool.drawFilledRectangle(startingPosition[0] - width * 0.45 + 16 + width / 4 + 1.5, y, width * 0.4 - 3, 3);
        drawTool.setLineWidth(1);
        drawTool.setCurrentColor(0,0,0,255);
        drawTool.drawRectangle(startingPosition[0] - width * 0.45 + 16 + width / 4 + 1.5, y, width * 0.4 - 3, 3);
    }
}
