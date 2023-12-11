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
    private Enemy[] enemies;

    private EntityController entityController;
    private ViewController viewController;
    private EnvironmentController environmentController;
    private InputManager inputManager;
    private DishController dishController;
    private CookingController cookingController;

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
        cook = new Cook(800,800);
        enemies = new Enemy[2];

        try {
            enemies[0] = new Enemy(100.,300.);
            enemies[1] = new Enemy(300.,100.);
        } catch (Exception e){
            e.printStackTrace();
        }
        for (int i = 0; i < shooter.getAllDishes().length; i++)
            viewController.draw(shooter.getAllDishes()[i]);

        viewController.draw(shooter);
        viewController.draw(cook);
        viewController.draw(enemies[0]);
        viewController.draw(enemies[1]);
        cookingController = new CookingController(environmentController, cook);
        dishController = new DishController(shooter, viewController);
        entityController = new EntityController(enemies, cook, shooter, environmentController);
        inputManager = new InputManager(dishController, entityController, shooter, cookingController);
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
        dishController.checkCollisions(enemies);
    }
}
