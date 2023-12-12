package my_project.view;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.InteractiveGraphicalObject;
import my_project.control.EntityController;
import my_project.control.ProgramController;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Realisiert ein Objekt, dass alle Eingaben empfängt und dann danach passende Methoden
 * im ProgramController aufruft
 */
public class InputManager extends InteractiveGraphicalObject {

    private ProgramController programController;

    /**
     * Initializes inputManager
     * @param pProgramController Needed to get Shooter & Cook objects as well as other controllers
     */
    public InputManager(ProgramController pProgramController) {
        programController = pProgramController;
    }

    /**
     * Is called every frame
     *
     * @param dt               Time passed since last frame
     * @param entityController Required to forward movement inputs
     */
    public void inputUpdate(double dt, EntityController entityController) {
        exePlayerMovement(dt, entityController);
    }

    /**
     * Checks in which direction each player is moving and calls updatePlayer method accordingly
     *
     * @param dt               Time passed since last frame
     * @param entityController Required to forward movement inputs
     */
    private void exePlayerMovement(double dt, EntityController entityController) {
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
        if (ViewController.isKeyDown(KeyEvent.VK_LEFT))
            xDirShooter = -1;
        if (ViewController.isKeyDown(KeyEvent.VK_RIGHT))
            xDirShooter = 1;
        if (ViewController.isKeyDown(KeyEvent.VK_UP))
            yDirShooter = -1;
        if (ViewController.isKeyDown(KeyEvent.VK_DOWN))
            yDirShooter = 1;

        entityController.updatePlayers(dt,
                new double[][]{{xDirCook, yDirCook}, {xDirShooter, yDirShooter}});
    }

    /**
     * Checks if mouse was clicked
     *
     * @param e Object of class MouseEvent which contains all information about the event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1) {
            programController.getDishController().shoot(e.getX(), e.getY());
            programController.getGUIManager().moveAmmoIndicator(programController.getDishController().getCurrentDishIndex());
        }
        if (e.getButton() == 3) {
            programController.getDishController().nextBullet();
            programController.getGUIManager().moveAmmoIndicator(programController.getDishController().getCurrentDishIndex());
        }
    }

    /**
     * Checks if keys were pressed and forwards inputs accordingly
     * @param key Enthält den Zahlencode für die Taste. Kann direkt aus der Klasse KeyEvent geladen werden, z.B. KeyEvent_VK_3
     */
    public void keyPressed(int key) {
        if (key == KeyEvent.VK_Q)
            programController.getCookingController().interact("mikado.png", programController.getCook());
    }
}
