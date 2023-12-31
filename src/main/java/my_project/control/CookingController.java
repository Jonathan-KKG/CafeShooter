package my_project.control;

import my_project.model.Environment.CollidableEnvironment;
import my_project.model.Cook;
import my_project.model.Environment.CookingStation;

/**
 * Controls Cooking mechanic
 */
public class CookingController {
    private double time;
    private ProgramController programController;
    private String[][][] recipes;
    private CookingStation currentStation;
    private String currentCookingDish;

    /**
     * creates an cookingController object.
     *
     * @param pProgramController necessary to reach all other controllers
     */
    public CookingController(ProgramController pProgramController) {
        programController = pProgramController;
        recipes = new String[][][]{
                {{"StrawberryWaffles"},{"Flour", "false"}, {"Egg", "false"}, {"Strawberry", "false"}},
                {{"IceCreamWaffles"},{"Flour", "false"}, {"Egg", "false"}, {"IceCream", "false"}},
                {{"Waffles"},{"Flour", "false"}, {"Egg", "false"}},

                {{"ChocolateCheeseCake"},{"Flour", "false"}, {"Egg", "false"}, {"Cheese", "false"}, {"Chocolate", "false"}},
                {{"CheeseCake"},{"Flour", "false"}, {"Egg", "false"}, {"Cheese", "false"}},
                {{"ChocolateCake"},{"Flour", "false"}, {"Egg", "false"}, {"Chocolate", "false"}},
                {{"ApplePie"},{"Flour", "false"}, {"Egg", "false"}, {"Apple", "false"}},

                {{"Coffee"},{"CoffeePowder", "false"}},

                {{"SpaghettiCarbonara"},{"Spaghetti", "false"}, {"Cream", "false"}, {"Egg", "false"}, {"Cheese", "false"}, {"Bacon", "false"}}
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

        if (time > 3 || !currentStation.isColliderActive()) {
            time = 0;
            programController.getUIController().deleteSkillCheckUI(programController.getViewController());
            currentStation = null;
            programController.getEntityController().getCook().setBusy(false);
        }
    }


    /**
     * creates a new dish if there's a cooking station nearby
     */
    public void cook() {
        Cook cook = programController.getEntityController().getCook();
        CollidableEnvironment objectInRange = cook.getClosestObjectInRange();
        if (objectInRange instanceof CookingStation && objectInRange.isColliderActive() && !cook.isBusy()) {
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
            //checks weather there are alle ingredients for one of the cookable recipes.
            //if than crating a skill-check for it
            for (int i = start; i < last; i++) {
                boolean help = checkForRightIngredients(i);
                System.out.println(help);
                if (help) {
                    System.out.println("cooking");
                    cook.setBusy(true);
                    time = 0;
                    programController.getUIController().createSkillCheck(new double[]{objectInRange.getX(), objectInRange.getY()}, objectInRange.getClass().getSimpleName(), programController.getViewController());
                    currentStation = (CookingStation) objectInRange;
                    break;
                }
            }
        }
    }

    /**
     * if there is an active check addes an click
     */
    public void addClick() {
        if (programController.getEntityController().getCook().isBusy()) {
            if (!programController.getUIController().progressSkillCheck(programController.getViewController())) {
                Cook cook = programController.getEntityController().getCook();
                programController.getDishController().addToHeldItemStack(
                        programController.getDishController().createDish(cook.getX(), cook.getY(), currentCookingDish));
                programController.getEntityController().getCook().setBusy(false);
                currentStation = null;
            }
        }
    }

    /**
     * checks whether every ingredient is there for a dish
     * @param type the dish that should be checked
     * @return whether the required ingredients are provided or not
     */
    private boolean checkForRightIngredients(int type) {
        int dish = type;
        //going through all ingredients required for this recipe for the amount of ingredients needed
        for (int i = 1; i < recipes[dish].length; i++) {
            for (int j = 1; j < recipes[dish].length; j++) {
                // checks weather it is one of the needed ingredients
                if (programController.getDishController().getFirstHeldItem() != null && programController.getDishController().getFirstHeldItem().getClass().getSimpleName().equals(recipes[dish][j][0])) {
                    recipes[dish][j][1] = "true";
                    programController.getViewController().removeDrawable(programController.getDishController().getFirstHeldItem());
                    programController.getDishController().removeFirstHeldItem();
                }
            }
        }
        // checks weather all the needed ingredients are there
        boolean isEverythingThere = true;
        for (int i = 1; i < recipes[dish].length; i++) {
            if (recipes[dish][i][1].equals("false"))
                isEverythingThere = false;
            System.out.println(isEverythingThere);
        }
        // if there are not all needed ingredients gives the removed back
        // else sets the cooking dish
        if (!isEverythingThere){
            for (int j = 1; j < recipes[dish].length; j++) {
                if (recipes[dish][j][1].equals("true")) {
                    recipes[dish][j][1] = "false";
                    Cook cook = programController.getEntityController().getCook();
                    programController.getDishController().addToHeldItemStack(
                            programController.getDishController().createIngredient(cook.getX(), cook.getY(), recipes[dish][j][0]));
                }
            }
        }else
            currentCookingDish = recipes[dish][0][0];
        return isEverythingThere;
    }
}
