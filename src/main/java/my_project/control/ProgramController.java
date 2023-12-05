package my_project.control;

import KAGO_framework.control.ViewController;
import my_project.model.*;
import my_project.view.InputManager;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {

    //Referenzen
    private Shooter shooter;
    private Cook cook;
    private Environment myEnv;
    private Enemy[] enemy;

    private EnemyController enemyController;
    private ViewController viewController;
    private EnvironmentController environmentController;
    private InputManager inputManager;

    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    /**
     * Erstellt alle Objekte
     */
    public void startProgram() {
        environmentController = new EnvironmentController(viewController);
        shooter = new Shooter(150,150);
        cook = new Cook(450,150);
        enemy = new Enemy[2];
        try {
            enemy[0] = new Enemy(100.,300.);
            enemy[1] = new Enemy(300.,100.);
        } catch (Exception e){
            System.out.println("oopsies");
        }

        viewController.draw(myEnv);
        viewController.draw(shooter);
        viewController.draw(cook);
        viewController.draw(enemy[0]);
        viewController.draw(enemy[1]);
        enemyController = new EnemyController(enemy,shooter);
        inputManager = new InputManager(viewController,cook,shooter);
    }

    /**
     * Aufruf mit jeder Frame
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){
        enemyController.updateEnemies(dt);
        inputManager.inputUpdate(dt);

    }
}
