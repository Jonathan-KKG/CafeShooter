package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import my_project.model.Dishes.ChocolateCheeseCake;
import my_project.model.Enemies.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Responsible for EnemyWaves: Creates, stores and manages Waves
 */
public class WaveController {
    private Queue<Enemy[]> enemyWaves;
    private Timer spawnTimer;
    private String[] knownDishes;

    /**
     * Saves enemeytypes as strings, creates all waves and initiates the first one
     *
     * @param viewController Required to draw the first wave and its Wants-Bubbles
     * @param uiController   Required to draw the first wave and its Wants-Bubbles
     */
    public WaveController(ViewController viewController, UIController uiController) {
        spawnTimer = new Timer();
        enemyWaves = new Queue<>();
        // Order of enemyTypes[] is important for scaling difficulty
        Class<? extends Enemy>[] enemyTypes = new Class[]{Maxim.class, Maksym.class, Max.class, Alex.class, Carlos.class, Habib.class, Haya.class, Kamel.class, Jonathan.class};
        knownDishes = new String[enemyTypes.length];

        createWaves(enemyTypes);

        // Ready First Wave
        scheduleWaveDrawing(viewController);
        uiController.createEnemyBubblesOfWave(enemyWaves.front(), viewController);

        // DEBUG CODE
        // Add EnvController as parameter
        // (!) repeat condition shouldn't be 9 or higher
        // for (int i = 0; i < 8; i++) {
        //     nextWave(viewController,uiController,envController);
        // }
    }

    /**
     * Creates a set amount of waves with a set increasing amount of enemies with a random position and type
     */
    private void createWaves(Class<? extends Enemy>[] enemyTypes) {
        int increment = 1;
        int maximum = 10;

        for (int i = increment; i < maximum; i += increment) {

            Enemy[] enemies = new Enemy[(int)(i * 1.5)];

            // enemies[0] reserved for newest enemy type
            double[] pos = getRandomEnemyPosition();
            try {
                enemies[0] = enemyTypes[i / increment - 1].getDeclaredConstructor(double.class, double.class).newInstance(pos[0], pos[1]);
            } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
                e.printStackTrace();
            }

            // Create enemies to fill the waves
            for (int j = 1; j < enemies.length; j++) {
                pos = getRandomEnemyPosition();

                try {
                    int randomType = (int) (Math.random() * (i / increment) - 1); // don't touch it it works
                    enemies[j] = enemyTypes[randomType].getDeclaredConstructor(double.class, double.class).newInstance(pos[0], pos[1]);
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
                    e.printStackTrace();
                }
            }

            enemyWaves.enqueue(enemies);
        }
    }

    /**
     * Checks if the Wave is over: If so, starts new one.
     *
     * @param viewController Required to draw the new wave and its Wants-Bubbles
     * @param uiController   Required to draw the new wave and its Wants-Bubbles
     * @param envController  Required to unlock new cooking environment objects
     * @return whether a new wave was initiated or not
     */
    public boolean checkForNewWave(ViewController viewController, UIController uiController, EnvironmentController envController, ProgramController programController) {
        if (enemyWaves.isEmpty()) return false;

        boolean waveIsEmpty = true;
        for (int i = 0; i < enemyWaves.front().length; i++)
            if (enemyWaves.front()[i] != null)
                waveIsEmpty = false;

        if (waveIsEmpty)
            nextWave(viewController, uiController, envController);
        else return false;

        if (!enemyWaves.isEmpty())
            return true;

        programController.endGame(true);
        return false;
    }

    /**
     * Creates a random position over, left or right outside of the screen
     *
     * @return double[]{x,y}
     */
    private double[] getRandomEnemyPosition() {
        double x;
        double y;

        int spawnTopLeftOrRight = (int) (Math.random() * 100);      // Determine spawn position of Enemy in relation to screen borders
        // Following values contain off-by-one errors
        if (spawnTopLeftOrRight < 25) {                               // spawn left of screen
            x = (int) -(Math.random() * 100 + 50);                     // between -150 and -50
            y = (int) (Math.random() * (1080 * 0.85 - 300));           // between 0 and 1080 * 0.85 - 300
        } else if (spawnTopLeftOrRight > 75) {                       // spawn right of screen
            x = (int) (Math.random() * 100 + 1920 * 0.85 + 50);       // between 1920 * 0.85 + 50 and 1920 * 0.85 + 150
            y = (int) (Math.random() * (1080 * 0.85 - 300));           // between 0 and 1080 * 0.85 - 300
        } else {                                                    // spawn top of screen (including area over left and right out-of-screen)
            x = (int) (Math.random() * (1920 * 0.85 + 300) - 150);      // between -150 and 1920 * 0.85 + 150
            y = (int) -(Math.random() * 100 + 50);                      // between -150 and -50
        }
        return new double[]{x, y};
    }

    /**
     * schedules the drawing and activation of an enemy
     *
     * @param enemy          the Enemy that should be scheduled
     * @param viewController Required to draw the Enemy
     */
    private TimerTask addElementToDraw(Enemy enemy, ViewController viewController) {
        return new TimerTask() {
            @Override
            public void run() {
                viewController.draw(enemy);
                enemy.activate();
            }
        };
    }

    /**
     * schedule the drawing and activation of every enemy of the current wave
     *
     * @param viewController Required to draw the enemies
     */
    private void scheduleWaveDrawing(ViewController viewController) {
        for (int i = 0; i < enemyWaves.front().length; i++) {
            long randomDelay = (long) (Math.random() * 2500 * enemyWaves.front().length + i * 5000L);
            spawnTimer.schedule(addElementToDraw(enemyWaves.front()[i], viewController), randomDelay);
        }
    }

    /**
     * Deletes old Wave and schedules drawing of the new one.
     *
     * @param viewController Required to draw the new Wave and its Wants-Bubbles
     * @param uiController   Required to draw the new Wave and its Wants-Bubbles
     */
    private void nextWave(ViewController viewController, UIController uiController, EnvironmentController envController) {
        enemyWaves.dequeue();
        if (enemyWaves.isEmpty())
            return;

        scheduleWaveDrawing(viewController);
        uiController.createEnemyBubblesOfWave(enemyWaves.front(), viewController);

        for (int i = 0; i < knownDishes.length; i++) {
            if (enemyWaves.front()[0].getRequiredDish().equals(knownDishes[i]) || enemyWaves.front()[0].getRequiredDish().equals(ChocolateCheeseCake.class.getSimpleName()))
                break;

            if (knownDishes[i] == null) {
                knownDishes[i] = enemyWaves.front()[0].getRequiredDish();
                envController.activateNextSetOfCooking(viewController);
                i = knownDishes.length;
            }
        }
    }

    public Enemy[] getWave() {
        return enemyWaves.front();
    }

}
