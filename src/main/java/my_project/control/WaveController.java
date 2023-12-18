package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import my_project.model.Enemy;

public class WaveController {
    private Queue<Enemy[]> enemieWaves;

    /**
     * Creates a set amount of waves with a set increasing amount of enemies with a random position and type
     *
     * @param viewController Required to draw the first wave
     */
    public WaveController(ViewController viewController) {

        enemieWaves = new Queue<>();

        // Create x Waves each contaning 2x + i enemies
        for (int i = 2; i < 10; i += 2) {
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

                enemies[j] = new Enemy(enemyType, x, y);
            }
            enemieWaves.enqueue(enemies);
        }
        addToDraw(viewController);
    }

    /**
     * Checks if the Wave is over: If so, starts new one.
     *
     * @param viewController Required to draw the new wave
     */
    public void checkForNewWave(ViewController viewController) {
        boolean isEmpty = true;
        for (int i = 0; i < enemieWaves.front().length; i++) {
            if (enemieWaves.front()[i] != null)
                isEmpty = false;
        }
        if (isEmpty)
            nextWave(viewController);
    }

    /**
     * Draws all Enemies from current Wave.
     *
     * @param viewController Required to draw the wave
     */
    private void addToDraw(ViewController viewController) {
        for (int i = 0; i < enemieWaves.front().length; i++) {
            viewController.draw(enemieWaves.front()[i]);
        }
    }

    /**
     * Deletes old Wave and draws the new one.
     *
     * @param viewController Required to draw the new Wave
     */
    private void nextWave(ViewController viewController) {
        enemieWaves.dequeue();
        addToDraw(viewController);
    }

    public Enemy[] getWave() {
        return enemieWaves.front();
    }
}
