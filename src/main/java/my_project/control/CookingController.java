package my_project.control;

import my_project.model.Environment.CollidableEnvironment;
import my_project.model.Cook;
import my_project.model.Environment.CookingStation;

/**
 *  Controls Cooking mechanic
 */
public class CookingController {
    private double time;
    private ProgramController programController;
    private String[][][] recipes;

    /**
     * creates an cookingController object.
     *
     * @param pProgramController necessary to reach all other controllers
     */
    public CookingController(ProgramController pProgramController) {
        programController = pProgramController;
        recipes = new String[][][]{
                {{"Nudel", "false"}, {"Sahne ", "false"}, {"Speck ", "false"}, {"Kaese ", "false"}},
                {{"Kaffebohnen ", "false"}, {"Milch ", "false"}, {"Zucker ", "false"}},
                {{"Mehl ", "false"}, {"Milch ", "false"}, {"Ei ", "false"}, {"Zucker ", "false"}, {"Schokolade ", "false"}},
                {{"Schokolade ", "false"}, {"Stiks ", "false"}}
        };
    }

    /**
     * updates time and removes skillcheck after 3 seconds
     *
     * @param dt Time passed between this and last frame
     */
    public void updateCooking(double dt) {
        if (programController.getEntityController().getCook().isCooking())
            time += dt;
        if (time > 3) {
            time = 0;
            programController.getUIController().deleteSkillCheckUI(programController.getViewController());
            programController.getEntityController().getCook().setCooking(false);
        }
    }


    /**
     * creates a new dish if there's a cooking station nearby
     */
    public void cook() {
        Cook cook = programController.getEntityController().getCook();
        CollidableEnvironment objectInRange = cook.getClosestObjectInRange();
        if (objectInRange instanceof CookingStation && !cook.isCooking() &&  objectInRange.isColliderActive()) {
            //if (checkForRightIngrediens(((CookingStation) objectInRange).getCookableObjs())) {
                cook.setCooking(true);
                time = 0;
                programController.getUIController().createSkillCheck(new double[]{objectInRange.getX(), objectInRange.getY()}, objectInRange.getClass().getSimpleName(), programController.getViewController());

            //}
        }
    }

    /**
     * if there is an active check addes an click
     */
    public void addClick() {
        if (programController.getEntityController().getCook().isCooking()) {
            if (!programController.getUIController().progressSkillCheck(programController.getViewController())) {
                Cook cook = programController.getEntityController().getCook();
                programController.getDishController().addToHeldItemStack(
                        programController.getDishController().createDish(cook.getX(), cook.getY(), "Coffee"));
                programController.getEntityController().getCook().setCooking(false);
            }
        }
    }

    private boolean checkForRightIngredients(String type) {
        int dish;
        switch (type) {
            case "spaghetti" -> dish = 0;
            case "Coffee" -> dish = 1;
            default -> dish = -1;
        }
        for (int i = 0; i < recipes[dish].length; i++) {
            if (programController.getDishController().getFirstHeldItem().getClass().getSimpleName().equals(recipes[dish][i][0])) {
                recipes[dish][i][1] = "true";
                programController.getDishController().removeFirstHeldItem();
            } else {
                for (int j = 0; j < recipes[dish].length; j++) {
                    if (recipes[dish][i][1].equals("true")) {
                        recipes[dish][i][1] = "true";
                        Cook cook = programController.getEntityController().getCook();
                        programController.getDishController().addToHeldItemStack(
                                programController.getDishController().createDish(cook.getX(), cook.getY(), type));
                        return false;
                    }
                }
            }
        }
        boolean isEverythingThere = true;
        for (int i = 0; i < recipes[dish].length; i++) {
            if (recipes[dish][i][1].equals("false"))
                isEverythingThere = false;
        }
        return isEverythingThere;
    }
}
