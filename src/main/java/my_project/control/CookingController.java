package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.CollidableEnvironment;
import my_project.model.Cook;
import my_project.model.Dish;

public class CookingController {
    private List<CollidableEnvironment> cookingStations;
    private boolean isCooking;
    private double time;
    private int timesClicked;
    private ProgramController programController;


    public CookingController(EnvironmentController environmentController, ProgramController pProgramController) {
        cookingStations = environmentController.getCookingStations();
        programController = pProgramController;
    }

    public void updateCooking(double dt){
        if (isCooking)
            time += dt;
        if (time > 3)
            checkForSucses();
    }

    /**
     * @param cook Required to calculate position of cook
     * @return returns object that is nearest to the player, if no object is within +-40 of from the player returns null
     */
    private CollidableEnvironment objectInRange(Cook cook) {

        cookingStations.toFirst();
        double lowestDistance = 100;
        CollidableEnvironment output = null;
        double cookMiddleX = cook.getX() + cook.getWidth() / 2;
        double cookMiddleY = cook.getY() + cook.getHeight() / 2;
        while (cookingStations.hasAccess()) {
            CollidableEnvironment currentObject = cookingStations.getContent();
            if (cookMiddleX < currentObject.getX() + currentObject.getWidth() + 40 || cookMiddleX > currentObject.getX() + currentObject.getWidth() - 40
                    || cookMiddleY < currentObject.getHeight() + 40 || cookMiddleY > currentObject.getHeight() - 40) {
                if (currentObject.getDistanceTo(cook) < lowestDistance) {
                    output = currentObject;
                }
            }
            cookingStations.next();
        }
        return output;
    }

    //TODO: swap param image for actual image of the cooked dish

    /**
     * creates a new dish in the middle of the nearest object 
     * @param dishType type of dish from 1-4
     * @param cook cook player to check nearest object
     * @param dishController used to create dish
     */
    public void cook(int dishType, Cook cook, DishController dishController, ViewController viewController) {
            Dish dish = dishController.createDish(cook.getX(), cook.getY(), dishType);
            dishController.addToHeldDishStack(dish);
            viewController.draw(dish);
    }

    public void checkForNerestObject(Cook cook){
        if (!isCooking) {
            CollidableEnvironment objectInRange = objectInRange(cook);
            if (objectInRange != null) {
                isCooking = true;
                time = 0;
            }
        }
    }

    private void checkForSucses(){
        if (timesClicked >= 40){
            cook(1, programController.getCook(), programController.getDishController(), programController.getViewController());
        }
        isCooking = false;
        time = 0;
    }

    public void addClick(){
        if (isCooking){
            timesClicked++;
        }
    }
}
