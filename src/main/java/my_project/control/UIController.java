package my_project.control;

import KAGO_framework.control.ViewController;
import my_project.model.Enemy;
import my_project.model.GUI.DishUI;
import my_project.model.GUI.EnemyDishUI;
import my_project.model.GUI.SkillCheckUI;

public class UIController {

    private DishUI dishUI;
    private SkillCheckUI skillCheckUI;
    private EnemyDishUI[] enemyDishUIs;

    /**
     * Creates all GUI elements
     *
     * @param viewController Required to draw GUI elements
     */
    public UIController(ViewController viewController) {
        dishUI = new DishUI(1300, 820);

        viewController.draw(dishUI);
    }

    /**
     * moves green Dish Indicator to next element in ammo count
     *
     * @param element Index of current element
     */
    public void moveAmmoIndicator(int element) {
        if (element != -1)
            dishUI.setX(1317 + 45 * element);
        else
            dishUI.setX(1317);
    }

    /**
     * Creates a new skillcheck
     *
     * @param pos            Position of the cooking station
     * @param type           what type of Dish the cooking station outputs
     * @param viewController Required to draww the new Object
     */
    public void createSkillCheck(double[] pos, String type, ViewController viewController) {
        skillCheckUI = new SkillCheckUI(pos[0], pos[1], type);
        viewController.draw(skillCheckUI);
    }

    /**
     * Progresses the current skillcheck and removes it if it's finished
     *
     * @param programController Required to remove the skillcheck
     * @return whether the skillcheck is finished or not - also false if it's null
     */
    public boolean progressSkillCheck(ProgramController programController) {
        if (skillCheckUI == null)
            return false;

        if (!skillCheckUI.increaseProgress()) {
            programController.getViewController().removeDrawable(skillCheckUI);
            skillCheckUI = null;
            return false;
        }
        return true;
    }

    /**
     * @return what Dish the skillCheckUI is producing
     */
    public String getCurrentSkillCheckType() {
        if (skillCheckUI == null)
            return null;
        return skillCheckUI.getType();
    }

    /**
     * stops drawing the current skillCheckUI and deletes it
     *
     * @param programController Required to stop the drawing
     */
    public void deleteSkillCheckUI(ProgramController programController) {
        programController.getViewController().removeDrawable(skillCheckUI);
        skillCheckUI = null;
    }

    /**
     * moves all enemybubbles of the current wave to their enemy
     *
     * @param enemies
     */
    public void updateEnemyBubbles(Enemy[] enemies) {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] == null)
                i++;
            enemyDishUIs[i].setX(enemies[i].getX());
            enemyDishUIs[i].setY(enemies[i].getY());
        }
    }

    /**
     * creates all bubbles for a new wave
     *
     * @param enemies        The new wave for which bubbles should be created
     * @param viewController Required to draw the UI
     */
    public void createEnemyBubblesOfWave(Enemy[] enemies, ViewController viewController) {
        enemyDishUIs = new EnemyDishUI[enemies.length];
        for (int i = 0; i < enemies.length; i++) {
            enemyDishUIs[i] = new EnemyDishUI(enemies[i].getX(), enemies[i].getY(), enemies[i].getRequiredDish());
            viewController.draw(enemyDishUIs[i]);
        }
    }

    /**
     * @param index          Enemy's position in the wave array
     * @param viewController Required to stop drawing the UI
     */
    public void deleteEnemyUI(int index, ViewController viewController) {
        if (index < enemyDishUIs.length && enemyDishUIs[index] != null) {
            viewController.removeDrawable(enemyDishUIs[index]);
            enemyDishUIs[index] = null;
        }
    }


}
