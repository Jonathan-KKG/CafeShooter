package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.CollidableEnvironment;
import my_project.model.Cook;
import my_project.model.Player;

public class CookingController {
    private EnvironmentController environmentController;
    private List<CollidableEnvironment> cookingStations = environmentController.getCookingStations();

    private Cook cook;




    public CookingController(){

    }

    /**
     *
     * @param cookingStations List with all interactable objects
     * @return  returns true if the players midpoint is within objectWidth +-40 and objectHeight +-40
     */
    private boolean isObjectInRange(List<CollidableEnvironment> cookingStations){

        cookingStations.toFirst();
        while(cookingStations.hasAccess()){
            CollidableEnvironment currentObject = cookingStations.getContent();
            if(cook.getX()- cook.getWidth()/2 < currentObject.getX() + currentObject.getWidth()+40 && cook.getX()- cook.getWidth()/2> currentObject.getX() + currentObject.getWidth()-40
            && cook.getY()- cook.getHeight()/2<currentObject.getHeight()+40 && cook.getY()- cook.getHeight()/2>currentObject.getHeight()-40){
                return true;
            }



        }
        return false;
    }

}
