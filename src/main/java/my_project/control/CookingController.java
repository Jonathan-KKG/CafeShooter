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
                        programController.getDishController().createDish(cook.getX(), cook.getY(), "Coffee"));
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
        //System.out.println("\n\n\n\n\n\n\n\n\n");
        int dish = type;
        for (int i = 0; i < recipes[dish].length; i++) {
            //System.out.println(i);
            for (int j = 0; j < recipes[dish].length; j++) {
                //System.out.println(j);
                //if (programController.getDishController().getFirstHeldItem() != null)
                    //System.out.println(programController.getDishController().getFirstHeldItem().getClass().getSimpleName());
                //System.out.println(recipes[dish][j][0]);
                if (programController.getDishController().getFirstHeldItem() != null && programController.getDishController().getFirstHeldItem().getClass().getSimpleName().equals(recipes[dish][j][0])) {
                    recipes[dish][j][1] = "true";
                    //System.out.println(recipes[dish][j][1]);
                    programController.getViewController().removeDrawable(programController.getDishController().getFirstHeldItem());
                    programController.getDishController().removeFirstHeldItem();
                }
            }
        }
        boolean isEverythingThere = true;
        for (int i = 0; i < recipes[dish].length; i++) {
            if (recipes[dish][i][1].equals("false"))
                isEverythingThere = false;
            System.out.println(isEverythingThere);
        }
        if (!isEverythingThere){
            for (int j = 0; j < recipes[dish].length; j++) {
                if (recipes[dish][j][1].equals("true")) {
                    recipes[dish][j][1] = "false";
                    Cook cook = programController.getEntityController().getCook();
                    programController.getDishController().addToHeldItemStack(
                            programController.getDishController().createIngredient(cook.getX(), cook.getY(), recipes[dish][j][0]));
                }
            }
        }
        return isEverythingThere;
    }
}
