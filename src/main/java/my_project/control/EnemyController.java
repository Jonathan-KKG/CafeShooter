package my_project.control;

import my_project.model.Enemy;
import my_project.model.Entity;

public class EnemyController {
    private Enemy[] enemies;
    private Entity target;

    /**
     * @param pEnemies A Array of all existing Enemies
     * @param pTarget the Cook to with the Enemies move.
     */
    public EnemyController(Enemy[] pEnemies, Entity pTarget) {
        enemies = pEnemies;
        target = pTarget;
    }

    /**
     * Moves every Enemy to the Cook.
     * @param dt the Time passed betwen this and the last call of the method
     */
    public void updateEnemies(double dt){
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] != null) {
                double xDir = (target.getX() - enemies[i].getX());
                double yDir = (target.getY() - enemies[i].getY());
                double distance = Math.sqrt(xDir * xDir + yDir * yDir);

                enemies[i].move((dt / distance), xDir, yDir);
            }
        }
    }
}
