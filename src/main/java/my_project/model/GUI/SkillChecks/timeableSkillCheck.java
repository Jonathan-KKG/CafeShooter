package my_project.model.GUI.SkillChecks;

import KAGO_framework.view.DrawTool;

/**
 * A class for all skillchecks that require timing of the player
 */
public abstract class timeableSkillCheck extends SkillCheckUI {

    protected double hitzoneYPosition;
    protected double hitzoneHeight;
    protected double speed;
    protected boolean movingDownwards;

    /**
     * Initializes UI Model
     *
     * @param pX                 Starting location of the Model
     * @param pY                 Starting location of the Model
     * @param dish               SimpleClassName of what dish is being cooked
     * @param validHitTimeWindow the hit time window that is required of the player {earliest, latest}
     */
    public timeableSkillCheck(double pX, double pY, String dish, double[] validHitTimeWindow) {
        super(pX, pY, dish);
        setNewHitzone(validHitTimeWindow);
        speed = 1;
    }

    /**
     * Required for some skillchecks
     * I.e. constantly moving parts
     *
     * @param time Time passed since creation of the skillcheck (in seconds)
     */
    @Override
    public void updateSkillCheck(double time) {
        double futureY = startingPosition[1] - height * 0.8 + 1 + 154 * (Math.cos(Math.PI * time * speed) * 0.5 + 0.5); // last multiplier in brackets is a value phasing between 0 and 1
        movingDownwards = y < futureY;
        y = futureY;
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        super.draw(drawTool);

        // Valid Hitzone indicator
        drawTool.setCurrentColor(255, 0, 0, 160);
        drawTool.drawFilledRectangle(startingPosition[0] - width * 0.45 + 16 + width / 4 + 1.5, hitzoneYPosition, width * 0.4 - 3, hitzoneHeight);
        // Current position indicator
        drawTool.setCurrentColor(255, 0, 0, 235);
        drawTool.drawFilledRectangle(startingPosition[0] - width * 0.45 + 16 + width / 4 + 1.5, y, width * 0.4 - 3, 3);
        drawTool.setLineWidth(1);
        drawTool.setCurrentColor(0, 0, 0, 255);
        drawTool.drawRectangle(startingPosition[0] - width * 0.45 + 16 + width / 4 + 1.5, y, width * 0.4 - 3, 3);
    }

    /**
     * changes the time interval in which the player has to interact in order to progress cooking
     *
     * @param validHitTimeWindow the new time window in seconds {earliest, latest}
     */
    public void setNewHitzone(double[] validHitTimeWindow) {
        hitzoneHeight = (height * 0.8) * (validHitTimeWindow[1] - validHitTimeWindow[0]);
        hitzoneYPosition = startingPosition[1] - height * 0.8 + 1 + height * 0.8 * (1 - validHitTimeWindow[1]);
    }

    public boolean isMovingDownwards() {
        return movingDownwards;
    }

    public double getSpeed() {
        return speed;
    }
}
