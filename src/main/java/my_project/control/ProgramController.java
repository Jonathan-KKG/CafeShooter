package my_project.control;

import KAGO_framework.control.ViewController;
import my_project.model.*;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {

    //Attribute
    private Player player;
    private Environment myEnv;
    private Enemy[] enemy;
    private EnemyController enemyController;

    //Referenzen
    private ViewController viewController;
    private EnvironmentController environmentController;

    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    public void startProgram() {
        environmentController = new EnvironmentController(viewController);
        player = new Shooter(150,150, viewController);
        enemy = new Enemy[2];
        try {
            enemy[0] = new Enemy(100.,300.);
            enemy[1] = new Enemy(300.,100.);
        } catch (Exception e){
            System.out.println("oopsies");
        }

        viewController.draw(myEnv);
        viewController.draw(player);
        viewController.draw(enemy[0]);
        viewController.draw(enemy[1]);
        enemyController = new EnemyController(enemy,player);
    }

    /**
     * Aufruf mit jeder Frame
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){
        enemyController.updateEnemies(dt);
    }
}
