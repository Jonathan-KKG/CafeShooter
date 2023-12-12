package my_project.view;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.InteractiveGraphicalObject;
import my_project.control.CookingController;
import my_project.control.DishController;
import my_project.control.EntityController;
import my_project.model.Shooter;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Realisiert ein Objekt, dass alle Eingaben empfängt und dann danach passende Methoden
 * im ProgramController aufruft
 */
public class InputManager extends InteractiveGraphicalObject {

    private DishController dishController;
    private Shooter shooter;

    /**
     * Initializes inputManager
     *
     * @param pDishController Needed for mouse input (to forward ammo and shooting inputs)
     */
    public InputManager(DishController pDishController) {
        dishController = pDishController;
    }

    /**
     * Aufruf mit jeder Frame
     *
     * @param dt                Zeit seit letzter Frame
     * @param cookingController Required to forward cooking inputs
     * @param entityController  Required to forward movement inputs
     */
    public void inputUpdate(double dt, CookingController cookingController, EntityController entityController) {
        exePlayerMovement(dt, entityController);
        exeCooking(cookingController);
    }

    /**
     * Checks in which direction each player is moving and calls updatePlayer method accordingly
     *
     * @param dt      Benötigt um jeden Frame zu Updaten
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

        entityController.updatePlayers(dt, new double[][]{{xDirCook, yDirCook}, {xDirShooter, yDirShooter}});
    }

    /**
     * checks if Q-button was clicked and executes cooking process
     */
    private void exeCooking(CookingController cookingController) {
        if (ViewController.isKeyDown(KeyEvent.VK_Q))
            cookingController.interact("mikado.png");
    }

    /**
     * Checks if mouse was clicked
     *
     * @param e Object of class MouseEvent which contains all information about the event.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1)       // Linksklick
            dishController.shoot(e.getX(), e.getY()); // schießen
        if (e.getButton() == 3)       // Rechtsklick
            dishController.nextBullet();     // Zwischen Munition wechseln

    }
}
