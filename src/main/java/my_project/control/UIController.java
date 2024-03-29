package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Environment.CollidableEnvironment;
import my_project.model.Enemies.Enemy;
import my_project.model.Cook;
import my_project.model.GUI.*;
import my_project.model.GUI.GameStates.*;
import my_project.model.GUI.SkillChecks.*;

/**
 * Responsible for drawing and managing every GUI on the screen
 */
public class UIController {

    private RecipeTutorialUI recipeTutorialUI;
    private DishUI dishUI;
    private SkillCheckUI skillCheckUI;
    private EnemyDishUI[] enemyDishUIs;
    private List<HPBar> hpBars;
    private DishStackUI dishStackUI;
    private GameStateUI gameStateUI;
    private StunCooldownUI stunCooldownUI;

    /**
     * Creates all GUI elements
     *
     * @param viewController Required to draw GUI elements
     * @param firstGame      !(whether the user restarted the game at least once already or not=
     */
    public UIController(ViewController viewController, boolean firstGame) {
        recipeTutorialUI = new RecipeTutorialUI(1100, 50);
        dishUI = new DishUI(1300, 820);
        hpBars = new List<>();
        dishStackUI = new DishStackUI(816, 797);

        viewController.draw(dishUI);
        viewController.draw(dishStackUI);

        if (firstGame) {
            gameStateUI = new StartScreenUI(0, 0);
            viewController.draw(gameStateUI);
        }
    }

    /**
     * Deletes the startscreen UI
     *
     * @param viewController Required to stop drawing it
     */
    public void deleteStartScreenUI(ViewController viewController) {
        if (!(gameStateUI instanceof StartScreenUI))
            return;

        viewController.removeDrawable(gameStateUI);
        gameStateUI = null;
    }

    /**
     * updates the amount heldDishStack
     *
     * @param increase boolean to calculate amount ofheldDishStack
     */
    public void updateHeldItemsAmount(boolean increase) {
        dishStackUI.setDishStackAmount(increase);
    }

    /**
     * moves green Dish Indicator to next element in ammo count
     *
     * @param element Index of current element
     */
    public void moveAmmoIndicator(int element) {
        if (element > -1)
            dishUI.setX(1317 + 55 * element);
    }

    /**
     * Creates a new skillcheck
     *
     * @param pos            Position of the cooking station
     * @param type           what type of Dish the cooking station outputs
     * @param viewController Required to draw the new Object
     * @param dishName       SimpleClassName of what dish is being cooked
     */
    public void createSkillCheck(double[] pos, String type, double[] hitTimeWindow, ViewController viewController, String dishName) {
        switch (type) {
            case "Oven" -> skillCheckUI = new OvenSkillCheck(pos[0], pos[1], dishName);
            case "Stove" -> skillCheckUI = new StoveSkillCheck(pos[0], pos[1], dishName, hitTimeWindow);
            case "CoffeeMachine" -> skillCheckUI = new CoffeeMachineSkillCheck(pos[0], pos[1], dishName, hitTimeWindow);
            case "WaffleIron" -> skillCheckUI = new WaffleIronSkillCheck(pos[0], pos[1], dishName);
            default -> System.out.println("Wrong SkillCheckType was provided on call of 'UIController.createSkillCheck(...)'!");
        }
        viewController.draw(skillCheckUI);
    }

    /**
     * changes the time interval in which the player has to interact to progress cooking
     *
     * @param hitTimeWindow the new time window in seconds {earliest, latest}
     */
    public void changeSkillCheckHitzone(double[] hitTimeWindow) {
        if (!(skillCheckUI instanceof timeableSkillCheck))
            return;

        ((timeableSkillCheck) skillCheckUI).setNewHitzone(hitTimeWindow);
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
     * updates the current skillCheckUI if required, e.g. moves parts that have to be moved
     *
     * @param time time passed since creation of the skillcheck
     */
    public void updateSkillCheckUI(double time) {
        if (skillCheckUI == null)
            return;

        skillCheckUI.updateSkillCheck(time);
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
     * creates UI for stun cooldown
     * @param viewController required for drawing
     * @param pos position of shooter
     * @param maxDuration the whole duration of the stun cooldown
     */
    public void createStunCooldown(ViewController viewController, double[] pos, double maxDuration){
        stunCooldownUI = new StunCooldownUI(pos[0], pos[1], maxDuration);
        viewController.draw(stunCooldownUI);
    }

    /**
     * updates UI for stun cooldown and deletes it once it has spoken its final words
     * @param viewController required for drawing
     * @param duration remaining cooldown
     * @param pos position of shooter
     */
    public void updateStunCooldownUI(ViewController viewController, double duration, double[] pos) {
        if (stunCooldownUI == null)
            return;

        stunCooldownUI.setX(pos[0]);
        stunCooldownUI.setY(pos[1]);

        stunCooldownUI.setDuration(duration);
        if(duration <= 0)
            deleteStunCooldownUI(viewController);
    }

    /**
     * deletes stun cooldown ui
     * @param viewController required for not reverse anti-undrawing*-1
     */
    private void deleteStunCooldownUI(ViewController viewController) {
        viewController.removeDrawable(stunCooldownUI);
        stunCooldownUI = null;
    }

    /**
     * moves all enemy bubbles of the current wave to their enemy
     *
     * @param enemies Enemies of the current wave
     */
    public void updateEnemyBubblesOfWave(Enemy[] enemies) {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null && enemies[i].isActive()) {
                enemyDishUIs[i].setX(enemies[i].getX());
                enemyDishUIs[i].setY(enemies[i].getY());
            }
        }
    }

    /**
     * moves dishStackUi with cook
     *
     * @param cook used to get position
     */
    public void updateDishStackUI(Cook cook) {
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
     *
     * @param env            The environment object that needs an HPBar
     * @param viewController Required to draw the new UI
     */
    public void drawHPBar(CollidableEnvironment env, ViewController viewController) {
        HPBar newHPBar = new HPBar(env);
        hpBars.append(newHPBar);
        viewController.draw(newHPBar);
    }

    /**
     * updates all HPBars for CollidableEnvironments
     *
     * @param viewController Required to deleted the HPBar in case it's not required anymore
     */
    public void updateHPBars(ViewController viewController) {
        hpBars.toFirst();
        while (hpBars.hasAccess()) {
            hpBars.getContent().updateHealth();
            if (hpBars.getContent().getHealth() >= 100) {
                viewController.removeDrawable(hpBars.getContent());
                hpBars.remove();
            } else
                hpBars.next();
        }
    }

    /**
     * togels weather recipeUi is visibil
     *
     * @param viewController Required to deleted and draw the UI
     */
    public void toggleRecipeUI(ViewController viewController) {
        if (recipeTutorialUI.isDrawn()) {
            viewController.removeDrawable(recipeTutorialUI);
            recipeTutorialUI.setDrawn(false);
        } else {
            viewController.draw(recipeTutorialUI);
            recipeTutorialUI.setDrawn(true);
        }
    }

    /**
     * Draws the final frame after the player has failed
     *
     * @param viewController Required to draw
     * @param won            whether the players lost or won the game
     */
    public void drawEndGameScreen(ViewController viewController, boolean won) {
        if (won)
            gameStateUI = new WonGameUI(250, 1080 * 0.85 / 4);
        else
            gameStateUI = new LostGameUI(250, 1080 * 0.85 / 4);
        viewController.draw(gameStateUI);
    }

    /**
     * Restarts game if mouse is on the Restart button.
     *
     * @param pX                x position of mouse
     * @param pY                y position of mouse
     * @param programController Required to restart the game if needed
     */
    public void restartGame(double pX, double pY, ProgramController programController) {
        if ((gameStateUI instanceof LostGameUI || gameStateUI instanceof WonGameUI) && gameStateUI.isOnRestartButton(pX, pY))
            programController.restartGame();
    }

    public boolean isMovingDownwards() {
        if (skillCheckUI instanceof timeableSkillCheck)
            return ((timeableSkillCheck) skillCheckUI).isMovingDownwards();

        return false;
    }

    public double getIndicatorSpeed() {
        if (skillCheckUI instanceof timeableSkillCheck)
            return ((timeableSkillCheck) skillCheckUI).getSpeed();
        return 1;
    }

    public int getNeededKey(){
        return skillCheckUI.getRequiredKey();
    }

    public void setNewNeededKey(){
        if (skillCheckUI instanceof WaffleIronSkillCheck)
            ((WaffleIronSkillCheck) skillCheckUI).setNeededKey();
    }
}
