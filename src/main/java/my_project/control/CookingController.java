package my_project.control;

import my_project.model.CollidableEnvironment;
import my_project.model.Cook;
import my_project.model.CookingStation;

public class CookingController {
    private boolean isCooking;
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
        isCooking = false;
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
        if (isCooking)
            time += dt;
        if (time > 3) {
            isCooking = false;
            time = 0;
            programController.getUiController().deleteSkillCheckUI(programController.getViewController());
            programController.getEntityController().getCook().setCooking(false);
        }
    }


    /**
     * creates a new dish if there's a cooking station nearby
     */
    public void cook() {
        CollidableEnvironment objectInRange = programController.getEntityController().getCook().getClosestObjectInRange();
        if (objectInRange != null && objectInRange.getClass() == CookingStation.class && !isCooking) {
            //if (checkForRightIngrediens(((CookingStation) objectInRange).getCookableObjs())) {
                isCooking = true;
                programController.getEntityController().getCook().setCooking(true);
                time = 0;
                programController.getUiController().createSkillCheck(new double[]{objectInRange.getX(), objectInRange.getY()}, ((CookingStation) objectInRange).getCookableObjs(), programController.getViewController());
            //}
        }
    }

    /**
     * if there is an active check addes an click
     */
    public void addClick() {
        if (isCooking) {
            String dishType = programController.getUiController().getCurrentSkillCheckType();
            if (!programController.getUiController().progressSkillCheck(programController.getViewController())) {
                Cook cook = programController.getEntityController().getCook();
                programController.getDishController().addToHeldItemStack(
                        programController.getDishController().createDish(cook.getX(), cook.getY(), dishType));
                programController.getEntityController().getCook().setCooking(false);
                isCooking = false;
            }
        }
    }

    private boolean checkForRightIngredients(String type) {
        int dish;
        switch (type) {
            case "spaghetti" -> dish = 0;
            case "Coffee" -> dish = 1;
            case "Muffin" -> dish = 2;
            case "Mikado" -> dish = 3;
            default -> dish = -1;
        }
        for (int i = 0; i < recipes[dish].length; i++) {
            if (programController.getDishController().getFirstHeldItem().getType().equals(recipes[dish][i][0])) {
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
