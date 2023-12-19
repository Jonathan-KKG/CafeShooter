package my_project.control;

import my_project.model.CollidableEnvironment;
import my_project.model.Cook;
import my_project.model.CookingStation;

public class CookingController {
    private boolean isCooking;
    private double time;
    private ProgramController programController;

    /**
     * creates an cookingController object.
     *
     * @param pProgramController necessary to reach all other controllers
     */
    public CookingController(ProgramController pProgramController) {
        programController = pProgramController;
        isCooking = false;
    }

    /**
     * updates time and removes skillcheck after 3 seconds
     *
     * @param dt Time passed between this and last frame
     */
    public void updateCooking(double dt) {
        if (isCooking)
            time += dt;
        if (time > 3) {
            isCooking = false;
            time = 0;
            programController.getUiController().deleteSkillCheckUI(programController);
        }
    }


    /**
     * creates a new dish if there's a cooking station nearby
     */
    public void cook() {
        CollidableEnvironment objectInRange = programController.getEntityController().getCook().getClosestObjectInRange();
        if (objectInRange != null && objectInRange.getClass() == CookingStation.class && !isCooking) {
            isCooking = true;
            time = 0;
            programController.getUiController().createSkillCheck(new double[]{objectInRange.getX(), objectInRange.getY()}, ((CookingStation) objectInRange).getCookableObjs(), programController.getViewController());
        }
    }

    /**
     * if there is an active check addes an click
     */
    public void addClick() {
        if (isCooking) {
            String dishType = programController.getUiController().getCurrentSkillCheckType();
            if (!programController.getUiController().progressSkillCheck(programController)) {
                Cook cook = programController.getEntityController().getCook();
                programController.getDishController().addToHeldDishStack(
                        programController.getDishController().createDish(cook.getX(), cook.getY(), dishType));
                isCooking = false;
            }
        }
    }
}
