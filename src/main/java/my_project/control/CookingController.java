package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.CollidableEnvironment;
import my_project.model.Cook;
import my_project.model.CookingStation;

public class CookingController {
    private List<CollidableEnvironment> cookingStations;
    private boolean isCooking;
    private double time;
    private ProgramController programController;


    public CookingController(ProgramController pProgramController) {
        programController = pProgramController;
        cookingStations = programController.getEnvironmentController().getCookingStations();
        isCooking = false;
    }

    public void updateCooking(double dt) {
        if (isCooking)
            time += dt;
        if (time > 3){
            isCooking = false;
            time = 0;
            programController.getUiController().deleteSkillCheckUI(programController);
        }
    }

    /**
     * returns the nearest cooking station in a set range (+-40) of a cook
     *
     * @return returns object that is nearest to the player, if no object is within +-40 of from the player returns null
     */
    private CollidableEnvironment objectInRange() {
        CollidableEnvironment output = null;
        Cook cook = programController.getCook();
        double lowestDistance = 100;
        double cookMiddleX = cook.getX() + cook.getWidth() / 2;
        double cookMiddleY = cook.getY() + cook.getHeight() / 2;

        cookingStations.toFirst();
        while (cookingStations.hasAccess()) {
            if (cookingStations.getContent().isColliderActive()) {
                CollidableEnvironment currentObject = cookingStations.getContent();
                if (cookMiddleX < currentObject.getX() + currentObject.getWidth() + 40 || cookMiddleX > currentObject.getX() + currentObject.getWidth() - 40
                        || cookMiddleY < currentObject.getHeight() + 40 || cookMiddleY > currentObject.getHeight() - 40) {
                    if (currentObject.getDistanceTo(cook) < lowestDistance) {
                        output = currentObject;
                    }
                }
            }
            cookingStations.next();
        }
        return output;
    }

    /**
     * creates a new dish if there's a cooking station nearby
     */
    public void cook() {
        CollidableEnvironment objectInRange = objectInRange();

        if (objectInRange != null && objectInRange.getClass() == CookingStation.class && !isCooking) {
            //TODO: stop drawing last held object, when putting new one on stack
            isCooking = true;
            time = 0;
            programController.getUiController().createSkillCheck(new double[]{objectInRange.getX(), objectInRange.getY()}, ((CookingStation) objectInRange).getCookableObjs(), programController.getViewController());
        }
    }

    public void addClick() {
        if (isCooking) {
            String dishType = programController.getUiController().getCurrentSkillCheckType();
            if(!programController.getUiController().progressSkillCheck(programController)){
                Cook cook = programController.getCook();
                programController.getDishController().addToHeldDishStack(
                        programController.getDishController().createDish(cook.getX(), cook.getY(), dishType));
                isCooking = false;
            }
        }
    }
}
