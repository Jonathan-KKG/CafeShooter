package my_project.view;

import KAGO_framework.control.ViewController;
import my_project.control.ProgramController;
import my_project.model.GUI.DishUI;
import my_project.model.GUI.SkillCheckUI;


public class GUIManager {

    private DishUI dishUI;
    private SkillCheckUI skillCheckUI;

    /**
     * Creates all GUI elements
     * @param viewController Required to draw GUI elements
     */
    public GUIManager(ViewController viewController){
        dishUI = new DishUI(1300,820);

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

    public boolean progressSkillCheck(ProgramController programController){
        if(skillCheckUI == null)
            return false;

        if(!skillCheckUI.increaseProgress()){
            programController.getViewController().removeDrawable(skillCheckUI);
            skillCheckUI = null;
            return false;
        }
        return true;
    }

    public String getCurrentSkillCheckType(){
        if(skillCheckUI == null)
            return null;
        return skillCheckUI.getType();
    }

}
