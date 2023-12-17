package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.Queue;
import my_project.model.Enemy;

public class WaveController {
    private ProgramController programController;
    private Queue<Enemy[]> enemieWaves;

    public WaveController(ProgramController programController) {
        this.programController = programController;
        enemieWaves = new Queue<>();
        for (int i = 2; i < 5; i++) {
            Enemy[] enemies = new Enemy[i];
            try {
                for (int j = 0; j < enemies.length; j++) {
                    int enemyType = (int) (Math.random() * 4 + 1);
                    enemies[j] = new Enemy(enemyType, 100., 300.);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            enemieWaves.enqueue(enemies);
        }
        addToDraw();
    }

    /**
     * Draws all Enemies from current Wave.
     */
    private void addToDraw(){
        for (int i = 0; i < enemieWaves.front().length; i++) {
            programController.getViewController().draw(enemieWaves.front()[i]);
        }
    }

    /**
     * Checks if the Wave is over and Starts new one.
     */
    public void checkForNewWave(){
        boolean isEmpty = true;
        for (int i = 0; i < enemieWaves.front().length; i++) {
            if (enemieWaves.front()[i] != null)
                isEmpty = false;
        }
        if (isEmpty)
            nextWave();
    }

    /**
     * Deletes old Wave and draws the new one.
     */
    private void nextWave(){
        enemieWaves.dequeue();
        addToDraw();
    }

    public Enemy[] getWave() {
        return enemieWaves.front();
    }
}
