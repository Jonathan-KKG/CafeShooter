package my_project.model.GUI.SkillChecks;

import KAGO_framework.view.DrawTool;

/**
 * the SkillCheck for the Oven CookingStation
 */
public class OvenSkillCheck extends SkillCheckUI{
    /**
     * Initializes UI Model
     *
     * @param pX    Starting location of the Model
     * @param pY    Starting location of the Model
     */
    public OvenSkillCheck(double pX, double pY) {
        super(pX, pY);
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        // TODO SC1: OvenSkillCheck implementation
        /*
        idea: press at a correct time, multiple times, while indicator moves faster?
         */
    }
}
