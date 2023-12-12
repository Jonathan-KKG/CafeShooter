package my_project.control;

import KAGO_framework.control.Drawable;
import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import my_project.model.*;
import my_project.view.InputManager;

public class ProgramController {

    //Referenzen
    private Queue<Enemy[]> enemieWaves;

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
        for (int i = 2; i < 5; i++) {
            Enemy[] enemies = new Enemy[i];
            try {
                for (int j = 0; j < enemies.length; j++) {
                    enemies[j] = new Enemy(100., 300.);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            enemieWaves.enqueue(enemies);
        }

        viewController.draw(enemieWaves.front()[0]);
        viewController.draw(enemieWaves.front()[1]);
        entityController = new EntityController(environmentController, viewController);
        cookingController = new CookingController(environmentController);
        dishController = new DishController(this, viewController);
        inputManager = new InputManager(dishController, this);
        viewController.register(inputManager);
    }

    /**
     * Updates the Program.
     * @param dt the Time passed betwen this and the last call of the method
     */
    public void updateProgram(double dt){
        entityController.updateEnemies(dt, enemieWaves.front(), entityController.getCook());
        inputManager.inputUpdate(dt, entityController);
        dishController.dishUpdate(dt);
        dishController.checkCollisions(enemieWaves.front());
        checkForNewWave();
    }

    public void checkForNewWave(){
        boolean isEmpty = true;
        for (int i = 0; i < enemieWaves.front().length; i++) {
            if (enemieWaves.front()[i] != null)
                isEmpty = false;
        }
        if (isEmpty)
            nextWave();
    }

    private void nextWave(){
        enemieWaves.dequeue();
        for (int i = 0; i < enemieWaves.front().length; i++) {
            viewController.draw(enemieWaves.front()[i]);
        }
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

    public Cook getCook(){
        return entityController.getCook();
    }

    public CookingController getCookingController(){
        return cookingController;
    }
}
