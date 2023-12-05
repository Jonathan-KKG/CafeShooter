package my_project.control;

import my_project.model.Enemy;
import my_project.model.Entity;

public class EnemyController {
    private Enemy[] enemies;
    private Entity target;
    public EnemyController(Enemy[] pEnemies, Entity pTarget) {
        enemies = pEnemies;
        target = pTarget;
    }

    /**
     * Updates enemy movement
     * @param dt Benötigt um jeden Frame zu Updaten
     */
    public void updateEnemies(double dt){
        for (int i = 0; i < enemies.length; i++) {
            double xDir = (target.getX()- enemies[i].getX());
            double yDir = (target.getY()- enemies[i].getY());
            double distance = Math.sqrt(xDir * xDir + yDir * yDir);

            enemies[i].move((dt / distance), xDir, yDir);
        }
    }
}
