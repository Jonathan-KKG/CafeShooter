package my_project.model.GUI.SkillChecks;

import KAGO_framework.view.DrawTool;

/**
 * the SkillCheck for the Stove CookingStation
 */
public class StoveSkillCheck extends SkillCheckUI{

    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     */
    public StoveSkillCheck(double pX, double pY) {
        super(pX, pY);
        y = startingPosition[1];
        increment = 0.055;
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        super.draw(drawTool);

        // moveable part of the "Progress Bar"
        drawTool.setCurrentColor(255, 0, 0, 255);
        drawTool.drawFilledRectangle(startingPosition[0] - width * 0.45 + 16 + width / 4 + 1.5, startingPosition[1] - height * 0.8 * progress + 2,width * 0.4 - 3, height * 0.8 * progress - 4);
    }

    /** not required
     * xd
     */
    @Override
    public void updateSkillCheck(double time) {

    }
}
