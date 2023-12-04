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
            double xDir = (target.getX()- enemies[i].getX())/(target.getY() - enemies[i].getY());
            double yDir = (target.getY()- enemies[i].getY())/(target.getX() - enemies[i].getX());
            /*System.out.println(xDir);
            System.out.println(yDir);
            System.out.println(enemies[i].getX());
            System.out.println(enemies[i].getY());*/
            enemies[i].setX(enemies[i].getX()+ xDir * enemies[i].getSpeed() * dt);
            enemies[i].setY(enemies[i].getY()+ yDir * enemies[i].getSpeed() * dt);
            System.out.println(enemies[i].getX());
            //System.out.println(enemies[i].getY());
        }
    }
}
