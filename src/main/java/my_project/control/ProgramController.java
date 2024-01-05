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
    private ItemController itemController;
    private CookingController cookingController;
    private WaveController waveController;
    private UIController uiController;

    private boolean firstGame;
    private boolean firstWave;
    private boolean isRunning;

    /**
     * @param viewController A class that manages the visuals and inputs.
     */
    public ProgramController(ViewController viewController) {
        this.viewController = viewController;
        firstGame = true;
    }

    /**
     * Creates all controllers and initializes program
     */
    public void startProgram() {
        environmentController = new EnvironmentController(viewController);
        entityController = new EntityController(this, viewController);
        cookingController = new CookingController(this);
        inputManager = new InputManager(this);
        uiController = new UIController(viewController, firstGame);
        waveController = new WaveController(viewController, uiController,environmentController,this);
        itemController = new ItemController(this);
        isRunning = true;
        firstWave = true;

        viewController.register(inputManager);
    }

    /**
     * Updates the Program.
     *
     * @param dt the Time passed betwen this and the last call of the method
     */
    public void updateProgram(double dt) {

        boolean newWaveInitiated = waveController.checkForNewWave(viewController, uiController, environmentController,this);
        if (!isRunning) return;
        if(newWaveInitiated && firstWave) {
            uiController.deleteStartScreenUI(viewController);
            firstWave = false;
        }

        entityController.updateEnemies(dt, waveController.getWave(), entityController.getCook());
        inputManager.inputUpdate(dt);
        itemController.dishUpdate(dt);
        entityController.dishCollisionUpdate();
        cookingController.updateCooking(dt);
        uiController.updateEnemyBubblesOfWave(waveController.getWave());
        environmentController.updateEnvironments(entityController.getShooter(), dt, viewController, uiController);

    }

    /**
     * prevents updateProgram from executing & draws the finishing frame
     * @param won whether players won.
     */
    public void endGame(boolean won) {
        isRunning = false;
        firstGame = false;
        uiController.drawEndGameScreen(viewController, won);
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

    public ItemController getDishController() {
        return itemController;
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
