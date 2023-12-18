package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.CollidableEnvironment;
import my_project.model.Cook;
import my_project.model.CookingStation;

public class CookingController {
    private List<CollidableEnvironment> cookingStations;
    private boolean isCooking;
    private double time;
    private int timesClicked;
    private ProgramController programController;


    public CookingController(ProgramController pProgramController) {
        programController = pProgramController;
        cookingStations = programController.getEnvironmentController().getCookingStations();
        isCooking = false;
    }

    public void updateCooking(double dt) {
        if (isCooking)
            time += dt;
        if (time > 3)
            // idk?
        checkForSuccess();
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
     * creates a new dish in the middle of the nearest object
     */
    public void cook() {
        Cook cook = programController.getCook();
        CollidableEnvironment objectInRange = objectInRange();

        if (objectInRange.getClass() == CookingStation.class && !isCooking) {
            //TODO: stop drawing last held object, when putting new one on stack
            //dishController.removeFirstHeldDish();
            programController.getGUIManager().createSkillCheck(new double[]{objectInRange.getX(), objectInRange.getY()}, ((CookingStation) objectInRange).getCookableObjs(), programController.getViewController());
            isCooking = true;
        }
    }

    public void checkForNearestObject() {
        if (!isCooking) {
            CollidableEnvironment objectInRange = objectInRange();
            if (objectInRange != null) {
                isCooking = true;
                time = 0;
            }
        }
    }

    private void checkForSuccess() {
        time = 0;
    }

    public void addClick() {
        if (isCooking) {
            String dishType = programController.getGUIManager().getCurrentSkillCheckType();
            timesClicked++;
            if(!programController.getGUIManager().progressSkillCheck(programController)){
                Cook cook = programController.getCook();
                programController.getDishController().addToHeldDishStack(
                        programController.getDishController().createDish(cook.getX(), cook.getY(), dishType));
                isCooking = false;
            }
        }
    }
}
