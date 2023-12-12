package my_project.control;

import KAGO_framework.control.Drawable;
import KAGO_framework.control.ViewController;
import my_project.model.*;
import my_project.view.InputManager;

public class ProgramController {

    //Referenzen
    private Enemy[] enemies;

    private EntityController entityController;
    private ViewController viewController;
    private EnvironmentController environmentController;
    private InputManager inputManager;
    private DishController dishController;
    private CookingController cookingController;

    /**
     * @param viewController A class that manages the visuals and inputs.
     */
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    /**
     * Creates all controllers and initializes program
     */
    public void startProgram(){
        environmentController = new EnvironmentController(viewController);
        enemies = new Enemy[2];

        try {
            enemies[0] = new Enemy(100.,300.);
            enemies[1] = new Enemy(300.,100.);
        } catch (Exception e){
            e.printStackTrace();
        }

        viewController.draw(enemies[0]);
        viewController.draw(enemies[1]);
        entityController = new EntityController(environmentController, viewController);
        cookingController = new CookingController(environmentController, entityController.getCook());
        dishController = new DishController(this, viewController);
        inputManager = new InputManager(dishController);
        viewController.register(inputManager);
    }

    /**
     * Updates the Program.
     * @param dt the Time passed betwen this and the last call of the method
     */
    public void updateProgram(double dt){
        entityController.updateEnemies(dt, enemies, entityController.getCook());
        inputManager.inputUpdate(dt, cookingController, entityController);
        dishController.dishUpdate(dt);
        dishController.checkCollisions(enemies);
    }

    /**
     * Removes a drawable object from the scene
     * @param drawable Object to be removed
     */
    public void removeDrawableFromScene(Drawable drawable){
        viewController.removeDrawable(drawable);
    }

    public Shooter getShooter(){
        return entityController.getShooter();
    }
}
