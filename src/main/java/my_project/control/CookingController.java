package my_project.control;

import KAGO_framework.control.ViewController;
import my_project.model.Environment.*;
import my_project.model.Cook;

/**
 * Controls Cooking mechanic, including as skillchecks
 */
public class CookingController {
    private double time;
    private ProgramController programController;
    private String[][][] recipes;
    private CookingStation currentStation;
    private String currentCookingDish;
    private double[] currentHitTimeWindow;

    /**
     * creates an cookingController object.
     *
     * @param pProgramController necessary to reach all other controllers
     */
    public CookingController(ProgramController pProgramController) {
        programController = pProgramController;
        recipes = new String[][][]{
                {{"StrawberryWaffles"}, {"Flour", "false"}, {"Egg", "false"}, {"Strawberry", "false"}},
                {{"IceCreamWaffles"}, {"Flour", "false"}, {"Egg", "false"}, {"IceCream", "false"}},
                {{"Waffles"}, {"Flour", "false"}, {"Egg", "false"}},

                {{"ChocolateCheeseCake"}, {"Flour", "false"}, {"Egg", "false"}, {"Cheese", "false"}, {"Chocolate", "false"}},
                {{"CheeseCake"}, {"Flour", "false"}, {"Egg", "false"}, {"Cheese", "false"}},
                {{"ChocolateCake"}, {"Flour", "false"}, {"Egg", "false"}, {"Chocolate", "false"}},
                {{"ApplePie"}, {"Flour", "false"}, {"Egg", "false"}, {"Apple", "false"}},

                {{"Coffee"}, {"CoffeePowder", "false"}},

                {{"SpaghettiCarbonara"}, {"Spaghetti", "false"}, {"Cream", "false"}, {"Egg", "false"}, {"Cheese", "false"}, {"Bacon", "false"}}
        };
        currentHitTimeWindow = null;
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
            exitCooking(programController.getEntityController().getCook());
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

        //selects cookable recipes
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
                break;
            }
            case "CoffeeMachine": {
                start = 7;
                last = 8;
                break;
            }
            case "Stove": {
                start = 8;
                last = 9;
                break;
            }
        }

        //checks whether there are all ingredients for one of the cookable recipes.
        //if than crating a skill-check for it
        for (int i = start; i < last; i++) {
            if (!checkForRightIngredients(i))
                continue;

            cook.setBusy(true);
            time = 0;
            newHitTimeWindow();
            programController.getUIController().createSkillCheck(new double[]{objectInRange.getX(), objectInRange.getY()}, objectInRange.getClass().getSimpleName(), currentHitTimeWindow, programController.getViewController());
            currentStation = (CookingStation) objectInRange;
            i = last;
        }
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
        ItemController dCtrl = programController.getDishController();
        ViewController vCtrl = programController.getViewController();

        if (currentStation.isClickValid(time, uiCtrl.isMovingDownwards(), currentHitTimeWindow)) {
            if (uiCtrl.progressSkillCheck(vCtrl)) {
                newHitTimeWindow();
                uiCtrl.changeSkillCheckHitzone(currentHitTimeWindow);
                return;
            }

            dCtrl.addToHeldItemStack(dCtrl.createDish(cook.getX(), cook.getY(), currentCookingDish));
            exitCooking(cook);
        } else {
            exitCooking(cook);
            uiCtrl.deleteSkillCheckUI(vCtrl);
        }

    }

    /**
     * Changes the HitTimeWindow to a value between 0.2 and 0.7
     */
    private void newHitTimeWindow() {
        double midTime = Math.random() * 0.3 + 0.3;
        currentHitTimeWindow = new double[]{midTime - 0.05, midTime + 0.05};
    }

    /**
     * Exits cooking state
     *
     * @param cook Required to switch busy state
     */
    private void exitCooking(Cook cook) {
        cook.setBusy(false);
        currentStation = null;
    }

    /**
     * checks whether every ingredient is there for a dish
     *
     * @param dish the index of the dish's recipe in the recipes array that should be checked
     * @return whether the required ingredients are provided or not
     */
    private boolean checkForRightIngredients(int dish) {
        // going through all ingredients required for this recipe for the amount of ingredients needed
        for (int i = 1; i < recipes[dish].length; i++) {
            for (int j = 1; j < recipes[dish].length; j++) {
                // checks whether it is one of the needed ingredients
                if (programController.getDishController().getFirstHeldItem() != null && programController.getDishController().getFirstHeldItem().getClass().getSimpleName().equals(recipes[dish][j][0])) {
                    recipes[dish][j][1] = "true";
                    programController.getViewController().removeDrawable(programController.getDishController().getFirstHeldItem());
                    programController.getDishController().removeFirstHeldItem();
                }
            }
        }
        // checks whether all the needed ingredients are there
        boolean isEverythingThere = true;
        for (int i = 1; i < recipes[dish].length; i++) {
            if (recipes[dish][i][1].equals("false"))
                isEverythingThere = false;
        }
        // if there are not all needed ingredients gives the removed back
        // else sets the cooking dish
        if (!isEverythingThere) {
            for (int j = 1; j < recipes[dish].length; j++) {
                if (recipes[dish][j][1].equals("true")) {
                    recipes[dish][j][1] = "false";
                    Cook cook = programController.getEntityController().getCook();
                    programController.getDishController().addToHeldItemStack(
                            programController.getDishController().createIngredient(cook.getX(), cook.getY(), recipes[dish][j][0]));
                }
            }
        } else {
            currentCookingDish = recipes[dish][0][0];
            for (int i = 1; i < recipes[dish].length; i++) {
                recipes[dish][i][1] = "false";
            }
        }

        return isEverythingThere;
    }
}
