package my_project.view;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.InteractiveGraphicalObject;
import my_project.control.ProgramController;
import my_project.control.UIController;
import my_project.model.Cook;
import my_project.model.Dishes.Dish;
import my_project.model.Environment.Bin;
import my_project.model.Environment.CollidableEnvironment;
import my_project.model.Environment.CookingStation;
import my_project.model.Environment.Storages.Storage;
import my_project.model.Environment.Table;
import my_project.model.Item;
import my_project.model.Shooter;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Realisiert ein Objekt, dass alle Eingaben empfängt und dann danach passende Methoden
 * in Controllern aufruft
 */
public class InputManager extends InteractiveGraphicalObject {

    private ProgramController programController;
    private boolean isSkillCheckButtonPressed;

    /**
     * Initializes inputManager
     *
     * @param pProgramController Needed to get Shooter & Cook objects as well as other controllers
     */
    public InputManager(ProgramController pProgramController) {
        programController = pProgramController;
        isSkillCheckButtonPressed = false;
    }

    /**
     * Is called every frame
     *
     * @param dt Time passed since last frame
     */
    public void inputUpdate(double dt) {
        exePlayerMovement(dt);
        updateSkillCheckInputs();
    }

    /**
     * Updates skillcheck button input
     * Won't forward an input if button is held
     */
    private void updateSkillCheckInputs() {
        if (ViewController.isKeyDown(KeyEvent.VK_SPACE) && !isSkillCheckButtonPressed) {
            isSkillCheckButtonPressed = true;
            programController.getCookingController().addClick(-1);
        }
        if (ViewController.isKeyDown(KeyEvent.VK_A) && !isSkillCheckButtonPressed) {
            isSkillCheckButtonPressed = true;
            programController.getCookingController().addClick(0);
        }
        if (ViewController.isKeyDown(KeyEvent.VK_D) && !isSkillCheckButtonPressed) {
            isSkillCheckButtonPressed = true;
            programController.getCookingController().addClick(1);
        }
        if (ViewController.isKeyDown(KeyEvent.VK_S) && !isSkillCheckButtonPressed) {
            isSkillCheckButtonPressed = true;
            programController.getCookingController().addClick(2);
        }
        if (ViewController.isKeyDown(KeyEvent.VK_W) && !isSkillCheckButtonPressed) {
            isSkillCheckButtonPressed = true;
            programController.getCookingController().addClick(3);
        }
        if (!ViewController.isKeyDown(KeyEvent.VK_SPACE) && !ViewController.isKeyDown(KeyEvent.VK_A) && !ViewController.isKeyDown(KeyEvent.VK_D) &&!ViewController.isKeyDown(KeyEvent.VK_S) &&!ViewController.isKeyDown(KeyEvent.VK_W)) isSkillCheckButtonPressed = false;
    }

    /**
     * Checks in which direction each player is moving and calls updatePlayer method accordingly
     *
     * @param dt Time passed since last frame
     */
    private void exePlayerMovement(double dt) {
        int xDirCook = 0;
        int yDirCook = 0;
        int xDirShooter = 0;
        int yDirShooter = 0;

        if (ViewController.isKeyDown(KeyEvent.VK_A))
            xDirCook = -1;
        if (ViewController.isKeyDown(KeyEvent.VK_D))
            xDirCook = 1;
        if (ViewController.isKeyDown(KeyEvent.VK_W))
            yDirCook = -1;
        if (ViewController.isKeyDown(KeyEvent.VK_S))
            yDirCook = 1;
        if (ViewController.isKeyDown(KeyEvent.VK_J))
            xDirShooter = -1;
        if (ViewController.isKeyDown(KeyEvent.VK_L))
            xDirShooter = 1;
        if (ViewController.isKeyDown(KeyEvent.VK_I))
            yDirShooter = -1;
        if (ViewController.isKeyDown(KeyEvent.VK_K))
            yDirShooter = 1;

        programController.getEntityController().updatePlayers(dt,
                new double[][]{{xDirCook, yDirCook}, {xDirShooter, yDirShooter}});

        // Move heldDishes and the UI along with cook position
        if (!programController.getEntityController().getCook().isBusy()) {
            programController.getDishController().moveHeldItems();
            programController.getUIController().updateDishStackUI(programController.getEntityController().getCook());
        }
    }

    /**
     * Checks if mouse was clicked
     *
     * @param e Object of class MouseEvent which contains all information about the event.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (!programController.isRunning()) {
            if (e.getButton() == 1)
                programController.getUIController().restartGame(e.getX(), e.getY(), programController);
            return;
        }

        if (programController.getEntityController().getShooter().isBusy())
            return;

        if (e.getButton() == 1)
            programController.getDishController().shoot(e.getX(), e.getY());

        if (e.getButton() == 3)
            programController.getDishController().nextBullet();
    }

    /**
     * Checks if keys were pressed and forwards inputs accordingly
     *
     * @param key Contains the Numbercode for the key. Can directly be loaded from the Class KeyEvent e.g. KeyEvent_VK_6
     */
    public void keyPressed(int key) {
        if (!programController.isRunning())
            return;

        Shooter shooter = programController.getEntityController().getShooter();
        Cook cook = programController.getEntityController().getCook();

        CollidableEnvironment closestObjShooter = shooter.getClosestObjectInRange();
        CollidableEnvironment closestObjCook = cook.getClosestObjectInRange();

        Item heldItem = programController.getDishController().getFirstHeldItem();

        if (key == KeyEvent.VK_Q) {
            if (closestObjCook instanceof Table && heldItem instanceof Dish) {
                programController.getDishController().removeFirstHeldItem();
                programController.getEnvironmentController().addToTable((Dish) heldItem, (Table) closestObjCook, programController.getViewController());
            } else if (closestObjCook instanceof CookingStation)
                programController.getCookingController().cook();
            else if (closestObjCook instanceof Storage)
                programController.getDishController().addToHeldItemStack(((Storage) closestObjCook).getIngredient());
            else if (closestObjCook instanceof Bin)
                programController.getDishController().removeFirstHeldItem();
        }

        if (key == KeyEvent.VK_U)
            shooter.setBusy(true);

        if (key == KeyEvent.VK_E)
            programController.getUIController().toggleRecipeUI(programController.getViewController());

        if (key == KeyEvent.VK_O && closestObjShooter instanceof Table)
            programController.getDishController().moveToStoredDishes((Table) closestObjShooter);

        if(key == KeyEvent.VK_H && shooter.getStunCooldown()<= 0){
            programController.getEntityController().stunEnemies();
            programController.getUIController().createStunCooldown(programController.getViewController(), new double[]{shooter.getX(), shooter.getY()}, shooter.getMaxStunCooldown());
        }
    }
}
