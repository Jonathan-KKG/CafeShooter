package my_project.control;

import my_project.model.Enemy;
import my_project.model.Entity;

public class EnemyController {
    Enemy[] enemies;
    Entity target;
    public EnemyController(Enemy[] pEnemies, Entity pTarget) {
        enemies = pEnemies;
        target = pTarget;
    }

    public void updateEnemies(double dt){
        for (int i = 0; i < enemies.length; i++) {
            double xDir = (target.getX()- enemies[i].getX());
            double yDir = (target.getY()- enemies[i].getY());
            double distance = Math.sqrt(xDir * xDir + yDir * yDir);
            enemies[i].setX(enemies[i].getX()+ xDir * enemies[i].getSpeed() * dt / distance);
            enemies[i].setY(enemies[i].getY()+ yDir * enemies[i].getSpeed() * dt / distance);
        }
    }
}
