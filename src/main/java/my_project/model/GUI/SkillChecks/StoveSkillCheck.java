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
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        drawUsualBorder(drawTool, 2, startingPosition[0] - width * 0.5 + 16, startingPosition[1] - height * 0.9, width, height);

        // "Progress Bar"
        drawTool.setCurrentColor(120, 117, 117, 255);
        drawTool.drawRectangle(startingPosition[0] - width * 0.45 + 16 + width / 4, startingPosition[1] - height * 0.8, width * 0.4, height * 0.8);
        drawTool.setCurrentColor(120, 117, 117, 80);
        drawTool.drawFilledRectangle(startingPosition[0] - width * 0.45 + 16 + width / 4, startingPosition[1] - height * 0.8, width * 0.4, height * 0.8);

        // moveable part of the "Progress Bar"
        System.out.println(y - height * 0.8 * progress + 2 );
        drawTool.setCurrentColor(255, 0, 0, 255);
        drawTool.drawFilledRectangle(startingPosition[0] - width * 0.45 + 16 + width / 4 + 2, y - height * 0.8 * progress + 2,width * 0.4 - 4, height * 0.8 * progress - 4);
    }
}
