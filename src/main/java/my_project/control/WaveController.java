package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import my_project.model.Enemies.Carlos;
import my_project.model.Enemies.Habib;
import my_project.model.Enemies.Jonathan;
import my_project.model.Enemies.Max;
import my_project.model.Enemy;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Responsible for EnemyWaves: Creates, stores and manages Waves
 */
public class WaveController {
    private Queue<Enemy[]> enemyWaves;
    private String[] enemyTypes;
    private Timer spawnTimer;

    /**
     * Saves enemeytypes as strings, creates all waves and initiates the first one
     *
     * @param viewController Required to draw the first wave and its Wants-Bubbles
     * @param uiController   Required to draw the first wave and its Wants-Bubbles
     */
    public WaveController(ViewController viewController, UIController uiController) {
        File[] enemyFiles = new File("src/main/java/my_project/model/Enemies").listFiles();
        enemyTypes = new String[enemyFiles.length];
        for (int i = 0; i < enemyFiles.length; i++) {
            enemyTypes[i] = enemyFiles[i].getName().replaceAll(".java", "");
        }

        spawnTimer = new Timer();
        enemyWaves = new Queue<>();

        createWaves();
        nextWave(viewController, uiController);
    }

    /**
     * Creates a set amount of waves with a set increasing amount of enemies with a random position and type
     */
    private void createWaves() {
        // Create x Waves each contaning 2x + i enemies
        for (int i = 0; i < 10; i += 2) {
            Enemy[] enemies = new Enemy[i];

            // Create enemies to fill the waves
            for (int j = 0; j < enemies.length; j++) {
                int enemyType = (int) (Math.random() * 3 + 1);

                double x;
                double y;
                int spawnTopLeftOrRight = (int) (Math.random() * 100);      // Determine spawn position of Enemy in relation to screen borders
                // Following values contain off-by-one errors
                if (spawnTopLeftOrRight < 33) {                               // spawn left of screen
                    x = (int) -(Math.random() * 100 + 50);                     // between -150 and -50
                    y = (int) (Math.random() * (1080 * 0.85 - 300));           // between 0 and 1080 * 0.85 - 300
                } else if (spawnTopLeftOrRight > 66) {                       // spawn right of screen
                    x = (int) (Math.random() * 100 + 1920 * 0.85 + 50);       // between 1920 * 0.85 + 50 and 1920 * 0.85 + 150
                    y = (int) (Math.random() * (1080 * 0.85 - 300));           // between 0 and 1080 * 0.85 - 300
                } else {                                                    // spawn top of screen (including area over left and right out-of-screen)
                    x = (int) (Math.random() * (1920 * 0.85 + 300) - 150);      // between -150 and 1920 * 0.85 + 150
                    y = (int) -(Math.random() * 100 + 50);                      // between -150 and -50
                }

                switch (enemyType) {
                    case 1 -> enemies[j] = new Jonathan(x, y);
                    case 2 -> enemies[j] = new Max(x, y);
                    case 3 -> enemies[j] = new Carlos(x, y);
                    case 4 -> enemies[j] = new Habib(x, y);
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
     */
    public void checkForNewWave(ViewController viewController, UIController uiController) {
        boolean isEmpty = true;
        for (int i = 0; i < enemyWaves.front().length; i++) {
            if (enemyWaves.front()[i] != null)
                isEmpty = false;
        }
        if (isEmpty)
            nextWave(viewController, uiController);
    }

    /**
     * schedules the drawing and activation of an enemy
     * @param enemy the Enemy that should be scheduled
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
     * @param viewController Required to draw the enemies
     */
    private void scheduleWaveDrawing(ViewController viewController) {
        for (int i = 0; i < enemyWaves.front().length; i++) {
            long randomDelay = (long) (Math.random() * 1000 * enemyWaves.front().length + i * 500L);
            spawnTimer.schedule(addElementToDraw(enemyWaves.front()[i], viewController), randomDelay);
        }
    }

    /**
     * Deletes old Wave and schedules drawing of the new one.
     *
     * @param viewController Required to draw the new Wave and its Wants-Bubbles
     * @param uiController   Required to draw the new Wave and its Wants-Bubbles
     */
    private void nextWave(ViewController viewController, UIController uiController) {
        enemyWaves.dequeue();
        scheduleWaveDrawing(viewController);
        uiController.createEnemyBubblesOfWave(enemyWaves.front(), viewController);
    }

    public Enemy[] getWave() {
        return enemyWaves.front();
    }

}
