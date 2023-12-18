package my_project.control;

import KAGO_framework.control.Drawable;
import KAGO_framework.control.ViewController;
import my_project.model.*;
import my_project.view.InputManager;

public class ProgramController {

    //Referenzen
    private EntityController entityController;
    private ViewController viewController;
    private EnvironmentController environmentController;
    private InputManager inputManager;
    private DishController dishController;
    private CookingController cookingController;
    private WaveController waveController;
    private UIController uiController;

    /**
     * @param viewController A class that manages the visuals and inputs.
     */
    public ProgramController(ViewController viewController) {
        this.viewController = viewController;
    }

    /**
     * Creates all controllers and initializes program
     */
    public void startProgram() {
        environmentController = new EnvironmentController(viewController);
        uiController = new UIController(viewController);
        entityController = new EntityController(this, viewController);
        cookingController = new CookingController(this);
        dishController = new DishController(this);
        inputManager = new InputManager(this);
        waveController = new WaveController(viewController);

        viewController.register(inputManager);
    }

    /**
     * Updates the Program.
     *
     * @param dt the Time passed betwen this and the last call of the method
     */
    public void updateProgram(double dt) {
        entityController.updateEnemies(dt, waveController.getWave(), entityController.getCook());
        inputManager.inputUpdate(dt, entityController);
        dishController.dishUpdate(dt);
        entityController.checkDishCollisions(waveController.getWave());
        waveController.checkForNewWave(viewController);
        cookingController.updateCooking(dt);
    }

    /**
     * Removes a drawable object from the scene
     *
     * @param drawable Object to be removed
     */
    public void removeDrawableFromScene(Drawable drawable) {
        viewController.removeDrawable(drawable);
    }

    public CookingController getCookingController() {
        return cookingController;
    }

    public DishController getDishController() {
        return dishController;
    }

    public EnvironmentController getEnvironmentController() {
        return environmentController;
    }

    public ViewController getViewController() {
        return viewController;
    }

    public UIController getUiController() {
        return uiController;
    }

    public EntityController getEntityController() {
        return entityController;
    }
}
