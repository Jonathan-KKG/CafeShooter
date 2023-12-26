package my_project.view;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.InteractiveGraphicalObject;
import my_project.control.ProgramController;


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
     * @param dt               Time passed since last frame
     */
    public void inputUpdate(double dt) {
        exePlayerMovement(dt);
        updateSkillCheckInputs();
    }

    private void updateSkillCheckInputs(){
        if(ViewController.isKeyDown(KeyEvent.VK_SPACE) && !isSkillCheckButtonPressed){
            isSkillCheckButtonPressed = true;
            programController.getCookingController().addClick();
        }
        if(!ViewController.isKeyDown(KeyEvent.VK_SPACE)) isSkillCheckButtonPressed = false;
    }

    /**
     * Checks in which direction each player is moving and calls updatePlayer method accordingly
     *
     * @param dt               Time passed since last frame
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

        if (key == KeyEvent.VK_Q)
            programController.getCookingController().cook();
        if (key == KeyEvent.VK_O)
            programController.getEntityController().getShooter().setBusy(true);
    }

}
