package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.CollidableEnvironment;
import my_project.model.Cook;
import my_project.model.Dish;

public class CookingController {
    private List<CollidableEnvironment> cookingStations;

    public CookingController(EnvironmentController environmentController) {
        cookingStations = environmentController.getCookingStations();
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
        CollidableEnvironment objectInRange = objectInRange(cook);
        if (objectInRange != null) {
            Dish dish = dishController.createDish(cook.getX(), cook.getY(), dishType);
            dishController.addToHeldDishStack(dish);
            viewController.draw(dish);
        }

    }



    
    




}
