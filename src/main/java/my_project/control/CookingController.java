package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.CollidableEnvironment;
import my_project.model.Cook;
import my_project.model.Dish;
import my_project.model.Player;

public class CookingController {
    private EnvironmentController environmentController;
    private List<CollidableEnvironment> cookingStations = environmentController.getCookingStations();

    private Cook cook;

    public CookingController(){

    }

    /**
     * @param cookingStations List with all interactable objects
     * @return  returns true if the players midpoint is within objectWidth +-40 and objectHeight +-40
     */
    private CollidableEnvironment objectInRange(List<CollidableEnvironment> cookingStations) {

        cookingStations.toFirst();
        double lowestDistance = 100;
        CollidableEnvironment output = null;
        while (cookingStations.hasAccess()) {
            CollidableEnvironment currentObject = cookingStations.getContent();
            if (cook.getX() - cook.getWidth() / 2 < currentObject.getX() + currentObject.getWidth() + 40 && cook.getX() - cook.getWidth() / 2 > currentObject.getX() + currentObject.getWidth() - 40
                    && cook.getY() - cook.getHeight() / 2 < currentObject.getHeight() + 40 && cook.getY() - cook.getHeight() / 2 > currentObject.getHeight() - 40) {
                if (currentObject.getDistanceTo(cook) < lowestDistance) {
                    output = currentObject;
                }
            }
        }
        return output;
    }

  //TODO: swap param image for actual image of the cooked dish
    public void interact(String image){
        cookingStations.toFirst();
        while(cookingStations.hasAccess()){
            if(objectInRange(cookingStations)!= null){
                Dish dish = new Dish(image, cookingStations.getContent().getX(), cookingStations.getContent().getY());
            }
        }

    }

}
