package my_project.model.GUI.SkillChecks;

/**
 * the SkillCheck for the CoffeeMachine CookingStation
 */
public class CoffeeMachineSkillCheck extends timeableSkillCheck{

    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     * @param validHitTimeWindow the hit time window that is required of the player {earliest, latest}
     */
    public CoffeeMachineSkillCheck(double pX, double pY, double[] validHitTimeWindow) {
        super(pX, pY, validHitTimeWindow);
        increment = 1;
    }
}
