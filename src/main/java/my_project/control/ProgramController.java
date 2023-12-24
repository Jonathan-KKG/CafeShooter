package my_project.control;

import KAGO_framework.control.ViewController;
import my_project.view.InputManager;

/**
 * Creates all Controllers and enables communication between them
 * Calls updaateProgram to update every Controller
 * Deals with ending the game
 */
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

    private boolean isRunning;

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
        entityController = new EntityController(this, viewController);
        cookingController = new CookingController(this);
        inputManager = new InputManager(this);
        uiController = new UIController(viewController);
        waveController = new WaveController(this);
        dishController = new DishController(this);
        isRunning = true;

        viewController.register(inputManager);
    }

    /**
     * Updates the Program.
     *
     * @param dt the Time passed betwen this and the last call of the method
     */
    public void updateProgram(double dt) {
        if (!isRunning) return;

        entityController.updateEnemies(dt, waveController.getWave(), entityController.getCook());
        inputManager.inputUpdate(dt);
        dishController.dishUpdate(dt);
        entityController.dishCollisionUpdate();
        waveController.checkForNewWave(this);
        cookingController.updateCooking(dt);
        uiController.updateEnemyBubblesOfWave(waveController.getWave());
        environmentController.updateEnvironments(entityController.getShooter(), dt, viewController, uiController);
    }

    /**
     * prevents updateProgram from executing & draws the finishing frame
     */
    public void endGame() {
        isRunning = false;
        uiController.drawEndGameScreen(viewController);
        entityController.endGame();
    }

    /**
     * Resets the ViewController and restarts the program
     */
    public void restartGame() {
        viewController.reset();
        startProgram();
    }

    public boolean isRunning() {
        return isRunning;
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

    public UIController getUIController() {
        return uiController;
    }

    public EntityController getEntityController() {
        return entityController;
    }

    public WaveController getWaveController() {
        return waveController;
    }
}
