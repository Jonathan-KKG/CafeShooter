package my_project.view;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.InteractiveGraphicalObject;
import my_project.control.DishController;
import my_project.control.EntityController;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * Realisiert ein Objekt, dass alle Eingaben empfängt und dann danach passende Methoden
 * im ProgramController aufruft
 */
public class InputManager extends InteractiveGraphicalObject {

    private DishController dishController;
    private EntityController entityController;
    private ViewController viewController;

    /**
     * @param pdishController Required for call of shoot-method
     * @param pEntityController Required for call of move-method
     */
    public InputManager(DishController pdishController, EntityController pEntityController, ViewController pViewController){
        dishController = pdishController;
        entityController = pEntityController;
    }

    /**Aufruf mit jeder Frame
     * @param dt Zeit seit letzter Frame
     */
    public void inputUpdate(double dt){
        exePlayerMovement(dt);
    }

    /**
     * Prüft in welche Richtung der Spieler (Cook und Shooter) bewegt werden soll und bewegt diese anschließend
     * @param dt Benötigt um jeden Frame zu Updaten
     */
    private void exePlayerMovement(double dt){
        int xDirCook = 0;
        int yDirCook = 0;
        int xDirShooter = 0;
        int yDirShooter = 0;

        if(viewController.isKeyDown(KeyEvent.VK_A))
            xDirCook = -1;
        if(viewController.isKeyDown(KeyEvent.VK_D))
            xDirCook = 1;
        if(viewController.isKeyDown(KeyEvent.VK_W))
            yDirCook = -1;
        if(viewController.isKeyDown(KeyEvent.VK_S))
            yDirCook = 1;
        if(viewController.isKeyDown(KeyEvent.VK_LEFT))
            xDirShooter = -1;
        if(viewController.isKeyDown(KeyEvent.VK_RIGHT))
            xDirShooter = 1;
        if(viewController.isKeyDown(KeyEvent.VK_UP))
            yDirShooter = -1;
        if(viewController.isKeyDown(KeyEvent.VK_DOWN))
            yDirShooter = 1;

        entityController.updatePlayers(dt, new double[]{xDirCook, yDirCook}, new double[]{xDirShooter, yDirShooter});
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1){
            dishController.shoot(e.getX(),e.getY());
        }
    }
}
