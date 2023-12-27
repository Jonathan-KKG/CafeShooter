package my_project.model.GUI.SkillChecks;

import KAGO_framework.view.DrawTool;

/**
 * the SkillCheck for the CoffeeMachine CookingStation
 */
public class CoffeeMachineSkillCheck extends SkillCheckUI{

    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     */
    public CoffeeMachineSkillCheck(double pX, double pY) {
        super(pX, pY);
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        // TODO SC0: CoffeeMachineSkillCheck implementation
        /*
        idea: hold space bar and release at a correct time? or just press spacebar at the correct time
         */
    }
}
