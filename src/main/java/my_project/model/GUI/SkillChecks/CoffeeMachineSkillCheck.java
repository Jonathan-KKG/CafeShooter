package my_project.model.GUI.SkillChecks;

/**
 * the SkillCheck for the CoffeeMachine CookingStation
 */
public class CoffeeMachineSkillCheck extends timeableSkillCheck {

    /**
     * Initializes UI Model
     *
     * @param pX                 Starting location of the Model
     * @param pY                 Starting location of the Model
     * @param dish               SimpleClassName of what dish is being cooked
     * @param validHitTimeWindow the hit time window that is required of the player {earliest, latest}
     */
    public CoffeeMachineSkillCheck(double pX, double pY, String dish, double[] validHitTimeWindow) {
        super(pX, pY, dish, validHitTimeWindow);
        increment = 1;
    }
}
