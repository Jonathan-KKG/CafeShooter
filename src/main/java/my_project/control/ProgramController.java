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
    private Enemy[] enemy;

    private EntityController entityController;
    private ViewController viewController;
    private EnvironmentController environmentController;
    private InputManager inputManager;
    private DishController dishController;

    /**
     * @param viewController A clase that manages the Visuals.
     */
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    /**
     * Creates all objects
     */
    public void startProgram(){
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
        for (int i = 0; i < shooter.getDishes().length; i++) {
            viewController.draw(shooter.getDishes()[i]);
        }
        viewController.draw(shooter);
        viewController.draw(cook);
        viewController.draw(enemy[0]);
        viewController.draw(enemy[1]);
        dishController = new DishController(shooter, viewController);
        entityController = new EntityController(enemy, cook, shooter, environmentController);
        inputManager = new InputManager(dishController, entityController);
        viewController.register(inputManager);
    }

    /**
     * Updates the Program.
     * @param dt the Time passed betwen this and the last call of the method
     */
    public void updateProgram(double dt){
        entityController.updateEnemies(dt);
        inputManager.inputUpdate(dt);
        dishController.dishUpdate(dt);
        dishController.checkCollisions(enemy);

    }
}
