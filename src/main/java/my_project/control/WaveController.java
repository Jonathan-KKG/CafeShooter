package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import my_project.model.Enemies.Carlos;
import my_project.model.Enemies.Habib;
import my_project.model.Enemies.Jonathan;
import my_project.model.Enemies.Max;
import my_project.model.Enemy;

/**
 * Responsible for EnemyWaves: Creates, stores and manages Waves
 */
public class WaveController {
    private Queue<Enemy[]> enemyWaves;

    /**
     * Creates a set amount of waves with a set increasing amount of enemies with a random position and type
     *
     * @param programController Required to draw the first wave and its Wants-Bubbles
     */
    public WaveController(ProgramController programController) {
        enemyWaves = new Queue<>();

        // Create x Waves each contaning 2x + i enemies
        for (int i = 0; i < 10; i += 2) {
            Enemy[] enemies = new Enemy[i];

            // Create enemies to fill the waves
            for (int j = 0; j < enemies.length; j++) {
                int enemyType = (int) (Math.random() * 4 + 1);

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

                switch (enemyType){
                    case 1 -> enemies[j] = new Jonathan(x, y);
                    case 2 -> enemies[j] = new Max(x, y);
                    case 3 -> enemies[j] = new Carlos(x, y);
                    case 4 -> enemies[j] = new Habib(x, y);
                }

            }
            enemyWaves.enqueue(enemies);
        }
        nextWave(programController);
    }

    /**
     * Checks if the Wave is over: If so, starts new one.
     *
     * @param programController Required to draw the new wave and its Wants-Bubbles
     */
    public void checkForNewWave(ProgramController programController) {
        boolean isEmpty = true;
        for (int i = 0; i < enemyWaves.front().length; i++) {
            if (enemyWaves.front()[i] != null)
                isEmpty = false;
        }
        if (isEmpty)
            nextWave(programController);
    }

    /**
     * Draws all Enemies from current Wave.
     *
     * @param viewController Required to draw the wave
     */
    private void addToDraw(ViewController viewController) {
        for (int i = 0; i < enemyWaves.front().length; i++) {
            viewController.draw(enemyWaves.front()[i]);
        }
    }

    /**
     * Deletes old Wave and draws the new one.
     *
     * @param programController Required to draw the new Wave and its Wants-Bubbles
     */
    private void nextWave(ProgramController programController) {
        enemyWaves.dequeue();
        addToDraw(programController.getViewController());
        programController.getUiController().createEnemyBubblesOfWave(enemyWaves.front(), programController.getViewController());
    }

    public Enemy[] getWave() {
        return enemyWaves.front();
    }

}
