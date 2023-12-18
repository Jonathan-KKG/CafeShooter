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

    /** returns the nearest cooking station in a set range (+-40) of a cook
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
            if(cookingStations.getContent().isColliderActive()) {
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
     * @param dishType type of dish from 1-4
     * @param cook cook player to check nearest object
     * @param dishController used to create dish
     */
    public void cook(int dishType, Cook cook, DishController dishController, ProgramController programController) {
        CollidableEnvironment objectInRange = objectInRange(cook);

        if (objectInRange != null) {
            //TODO: stop drawing last held object, when putting new one on stack
            //dishController.removeFirstHeldDish();
            programController.getGUIManager().createSkillCheck(new double[]{objectInRange.getX(), objectInRange.getY()}, objectInRange.getType(), programController.getViewController());
            Dish dish = dishController.createDish(cook.getX(), cook.getY(), dishType);
            dishController.addToHeldDishStack(dish);
            programController.getViewController().draw(dish);
        }
    }



    
    




}
