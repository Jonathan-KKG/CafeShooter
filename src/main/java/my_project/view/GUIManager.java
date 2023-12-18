package my_project.view;

import KAGO_framework.control.ViewController;
import my_project.model.GUI.DishUI;
import my_project.model.GUI.SkillCheckUI;


public class GUIManager {

    DishUI dishUI;
    SkillCheckUI skillCheckUI;

    /**
     * Creates all GUI elements
     * @param viewController Required to draw GUI elements
     */
    public GUIManager(ViewController viewController){
        dishUI = new DishUI(1317,834);

        viewController.draw(dishUI);
    }

    /**
     * moves green Dish Indicator to next element in ammo count
     * @param element Index of current element
     */
    public void moveAmmoIndicator(int element){
        if(element != -1)
            dishUI.setX(1317 + 45 * element);
        else
            dishUI.setX(1317);
    }

    public void createSkillCheck(double[] pos, String type, ViewController viewController){
        skillCheckUI = new SkillCheckUI(pos[0], pos[1], type);
        viewController.draw(skillCheckUI);
    }

}
