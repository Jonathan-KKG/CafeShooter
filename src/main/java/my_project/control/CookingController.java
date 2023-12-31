package my_project.control;

import my_project.model.Environment.*;
import my_project.model.Cook;

/**
 * Controls Cooking mechanic
 */
public class CookingController {
    private double time;
    private ProgramController programController;
    private String[][][] recipes;
    private CookingStation currentStation;

    /**
     * creates an cookingController object.
     *
     * @param pProgramController necessary to reach all other controllers
     */
    public CookingController(ProgramController pProgramController) {
        programController = pProgramController;
        recipes = new String[][][]{
                {{"Flour", "false"}, {"Egg", "false"}, {"Strawberry", "false"}},
                {{"Flour", "false"}, {"Egg", "false"}, {"IceCream", "false"}},
                {{"Flour", "false"}, {"Egg", "false"}},

                {{"Flour", "false"}, {"Egg", "false"}, {"Cheese", "false"}, {"Chocolate", "false"}},
                {{"Flour", "false"}, {"Egg", "false"}, {"Cheese", "false"}},
                {{"Flour", "false"}, {"Egg", "false"}, {"Chocolate", "false"}},
                {{"Flour", "false"}, {"Egg", "false"}, {"Apple", "false"}},

                {{"CoffeePowder", "false"}},

                {{"Spaghetti", "false"}, {"Cream", "false"}, {"Egg", "false"}, {"Cheese", "false"}, {"Bacon", "false"}}
        };
    }

    /**
     * updates time and removes skillcheck after 3 seconds
     *
     * @param dt Time passed between this and last frame
     */
    public void updateCooking(double dt) {
        if (currentStation == null)
            return;

        if (programController.getEntityController().getCook().isBusy())
            time += dt;

        programController.getUIController().updateSkillCheckUI(time);

        if (time > currentStation.getCookingTime() || !currentStation.isColliderActive()) {
            time = 0;
            programController.getUIController().deleteSkillCheckUI(programController.getViewController());
            abortCooking(programController.getEntityController().getCook());
        }
    }


    /**
     * creates a new dish if there's a cooking station nearby
     */
    public void cook() {
        Cook cook = programController.getEntityController().getCook();
        CollidableEnvironment objectInRange = cook.getClosestObjectInRange();
        if (!(objectInRange instanceof CookingStation) || !objectInRange.isColliderActive() || cook.isBusy())
            return;

        int start = -1;
        int last = -1;
        switch (objectInRange.getClass().getSimpleName()) {
            case "WaffleIron": {
                start = 0;
                last = 3;
                break;
            }
            case "Oven": {
                start = 3;
                last = 7;
            }
            case "CoffeeMachine": {
                start = 7;
                last = 8;
            }
            case "Stove": {
                start = 8;
                last = 9;
            }
        }
        //for (int i = start; i < last; i++) {
        //    if (checkForRightIngredients(i)) {
        cook.setBusy(true);
        time = 0;
        double midTime = Math.random() * 0.2 + 0.4;
        double[] hitTimeWindow = new double[]{midTime - 0.1, midTime + 0.1};
        programController.getUIController().createSkillCheck(new double[]{objectInRange.getX(), objectInRange.getY()}, objectInRange.getClass().getSimpleName(), hitTimeWindow, programController.getViewController());
        currentStation = (CookingStation) objectInRange;
        //        i = last;
        //    }
        //}
    }

    /**
     * adds a click and checks its validity depending on the skillchecktype
     * progresses skillcheck if click is valid
     */
    public void addClick() {
        if (!programController.getEntityController().getCook().isBusy())
            return;

        Cook cook = programController.getEntityController().getCook();
        UIController uiCtrl = programController.getUIController();

        if (currentStation instanceof Stove && !uiCtrl.progressSkillCheck(programController.getViewController())) {
            programController.getDishController().addToHeldItemStack(programController.getDishController().createDish(cook.getX(), cook.getY(), "Coffee"));
            abortCooking(cook);
        } else if (currentStation instanceof CoffeeMachine) {
            for (double i = 1; i < currentStation.getCookingTime(); i ++) {
                if (time > 0.4 + i && time < 0.6 + i) {
                    if(!uiCtrl.progressSkillCheck(programController.getViewController()))
                        abortCooking(programController.getEntityController().getCook());
                    programController.getDishController().addToHeldItemStack(programController.getDishController().createDish(cook.getX(), cook.getY(), "Coffee"));
                    break;
                }
            }
        }
    }

    /**
     * Exits cooking state
     * @param cook Required to switch busy state
     */
    private void abortCooking(Cook cook){
        cook.setBusy(false);
        currentStation = null;
    }

    /**
     * checks whether every ingredient is there for a dish
     *
     * @param type the dish that should be checked
     * @return whether the required ingredients are provided or not
     */
    private boolean checkForRightIngredients(int type) {
        int dish = type;
        DishController dController = programController.getDishController();
        for (int i = 0; i < recipes[dish].length; i++) {
            if (dController.getFirstHeldItem() != null && dController.getFirstHeldItem().getClass().getSimpleName().equals(recipes[dish][i][0])) {
                recipes[dish][i][1] = "true";
                programController.getViewController().removeDrawable(dController.getFirstHeldItem());
                dController.removeFirstHeldItem();
            } else {
                for (int j = 0; j < recipes[dish].length; j++) {
                    if (recipes[dish][j][1].equals("true")) {
                        recipes[dish][j][1] = "false";
                        Cook cook = programController.getEntityController().getCook();
                        dController.addToHeldItemStack(
                                dController.createIngredient(cook.getX(), cook.getY(), recipes[dish][j][0]));
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
        if (!isEverythingThere) {
            for (int j = 0; j < recipes[dish].length; j++) {
                if (recipes[dish][j][1].equals("true")) {
                    recipes[dish][j][1] = "false";
                    Cook cook = programController.getEntityController().getCook();
                    dController.addToHeldItemStack(dController.createIngredient(cook.getX(), cook.getY(), recipes[dish][j][0]));
                }
            }
        }
        return isEverythingThere;
    }
}
