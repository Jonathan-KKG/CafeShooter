package my_project.view;

import KAGO_framework.control.ViewController;
import my_project.model.DishUI;


public class GUIManager {

    DishUI dishUI;

    /**
     * Creates all GUI elements
     * @param viewController Required to draw GUI elements
     */
    public GUIManager(ViewController viewController){
        dishUI = new DishUI(1425,834);
        viewController.draw(dishUI);
    }

    /**
     * moves green Dish Indicator to next element in ammo count
     * @param element Index of current element
     */
    public void moveAmmoIndicator(int element){
        if(element != -1)
            dishUI.setX(dishUI.getPosition()[0] + 10 + 35 * element);
        else
            dishUI.setX(dishUI.getPosition()[0] + 10);
    }

}