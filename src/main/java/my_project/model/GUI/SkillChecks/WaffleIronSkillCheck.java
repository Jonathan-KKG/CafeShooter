package my_project.model.GUI.SkillChecks;

import KAGO_framework.view.DrawTool;

/**
 * the SkillCheck for the WaffleIron CookingStation
 */
public class WaffleIronSkillCheck extends SkillCheckUI{

    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     * @param dish SimpleClassName of what dish is being cooked
     */
    public WaffleIronSkillCheck(double pX, double pY, String dish) {
        super(pX, pY, dish);
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
        // TODO SC2: WaffleIronSkillCheck implementation ?????
    }

    /**
     * Required for some skillchecks
     * I.e. constantly moving parts
     *
     * @param time Time passed since creation of the skillcheck (in seconds)
     */
    @Override
    public void updateSkillCheck(double time) {

    }
}
