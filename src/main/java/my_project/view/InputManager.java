package my_project.view;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.InteractiveGraphicalObject;
import my_project.model.Cook;
import my_project.model.Shooter;

import java.awt.event.KeyEvent;

/**
 * Realisiert ein Objekt, dass alle Eingaben empfängt und dann danach passende Methoden
 * im ProgramController aufruft
 */
public class InputManager extends InteractiveGraphicalObject {


    private ViewController viewController;
    private Cook cook;
    private Shooter shooter;

    /**
     * Objekterzeugung
     * @param pViewController Nötig, um den Aufruf der Interface-Methoden sicherzustellen
     * @param pCook Nötig, damit beliebiges Cook Objekt genommen werden kann
     * @param pShooter Nötig, damit beliebiges Shooter Objekt genommen werden kann
     */
    public InputManager(ViewController pViewController, Cook pCook, Shooter pShooter){
        viewController = pViewController;
        cook = pCook;
        shooter = pShooter;

    }

    public void inputUpdate(double dt){
        exePlayerMovement(dt);
    }

    /**
     * Prüft in welche Richtung der Spieler (Cook und Shooter) bewegt werden soll und bewegt diese anschließend
     * @param dt Benötigt um jeden Frame zu Updaten
     *
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

        cook.setX(cook.getX() + xDirCook * cook.getSpeed() * dt);
        cook.setY(cook.getY() + yDirCook * cook.getSpeed() * dt);
        shooter.setX(shooter.getX() + xDirShooter * shooter.getSpeed() * dt);
        shooter.setY(shooter.getY() + yDirShooter * shooter.getSpeed() * dt);
    }

    private void exePlayerShoot(){

    }

}
