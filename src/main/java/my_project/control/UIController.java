package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.CollidableEnvironment;
import my_project.model.Enemy;
import my_project.model.Cook;
import my_project.model.GUI.*;


public class UIController {

    private DishUI dishUI;
    private SkillCheckUI skillCheckUI;
    private EnemyDishUI[] enemyDishUIs;
    private List<HPBar> hpBars;
    private DishStackUI dishStackUI;

    /**
     * Creates all GUI elements
     *
     * @param viewController Required to draw GUI elements
     */
    public UIController(ViewController viewController) {
        dishUI = new DishUI(1300, 820);
        hpBars = new List<>();
        dishStackUI = new DishStackUI(500, 100);

        viewController.draw(dishUI);
        viewController.draw(dishStackUI);
    }

    /**
     * updates the amount heldDishStack
     * @param increase boolean to calculate amount ofheldDishStack
     */
    public void updateHeldStackAmmount(boolean increase) {
        dishStackUI.setDishStackAmount(increase);
    }

    /**
     * moves green Dish Indicator to next element in ammo count
     *
     * @param element Index of current element
     */
    public void moveAmmoIndicator(int element) {
        if (element > -1)
            dishUI.setX(1317 + 45 * element);
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
     * @param viewController Required to remove the skillcheck
     * @return whether the skillcheck is finished or not - also false if it's null
     */
    public boolean progressSkillCheck(ViewController viewController) {
        if (skillCheckUI == null)
            return false;

        if (!skillCheckUI.increaseProgress()) {
            deleteSkillCheckUI(viewController);
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
     * @param viewController Required to stop the drawing
     */
    public void deleteSkillCheckUI(ViewController viewController) {
        viewController.removeDrawable(skillCheckUI);
        skillCheckUI = null;
    }

    /**
     * moves all enemybubbles of the current wave to their enemy
     *
     * @param enemies Enemies of the current wave
     */
    public void updateEnemyBubbles(Enemy[] enemies) {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                enemyDishUIs[i].setX(enemies[i].getX());
                enemyDishUIs[i].setY(enemies[i].getY());
            }
        }
    }


    /**
     * moves dishStackUi with cook
     * @param cook used to get position
     */
    public void updateDishStackUI(Cook cook){
        dishStackUI.setX(cook.getX());
        dishStackUI.setY(cook.getY());
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
    public void deleteEnemyBubble(int index, ViewController viewController) {
        if (index < enemyDishUIs.length && enemyDishUIs[index] != null) {
            viewController.removeDrawable(enemyDishUIs[index]);
            enemyDishUIs[index] = null;
        }
    }

    /**
     * draws HPBar for a certain CollidableEnvironment
     * @param env The environment object that needs an HPBar
     * @param viewController Required to draw the new UI
     */
    public void drawHPBar(CollidableEnvironment env, ViewController viewController) {
        HPBar newHPBar = new HPBar(env);
        hpBars.append(newHPBar);
        viewController.draw(newHPBar);
    }

    /** TODO: Check if removing an HPBar works
     *  TODO: Check if "resurrecting" an HPBar works
     * updates all HPBars for CollidableEnvironments
     * @param viewController Required to deleted the HPBar in case it's not required anymore
     */
    public void updateHPBars(ViewController viewController){
        hpBars.toFirst();
        while (hpBars.hasAccess()){
            hpBars.getContent().updateHealth();
            if(hpBars.getContent().getHealth() >= 100) {
                viewController.removeDrawable(hpBars.getContent());
                hpBars.remove();
            }
            else
                hpBars.next();
        }
    }

    /**
     * Draws the final frame after the player has failed
     */
    public void drawEndGameScreen(ViewController viewController) {
        viewController.draw(new EndGameUI(250, 1080* 0.85 / 2));
    }

}
