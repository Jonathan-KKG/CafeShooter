package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.*;

public class EntityController {
    private Enemy[] enemies;
    private Cook cook;
    private Shooter shooter;
    private List<Environment> environmentObjects;
    private ViewController viewController;

    public EntityController(Enemy[] pEnemies, Cook pCook, Shooter pShooter, EnvironmentController envController, ViewController pViewController) {
        enemies = pEnemies;
        cook = pCook;
        shooter = pShooter;
        environmentObjects = envController.getCollidableEnvironmentObjects();
        viewController = pViewController;
    }

    /**
     * Updates enemy movement
     * @param dt Benötigt um jeden Frame zu Updaten
     */
    public void updateEnemies(double dt) {
        for (int i = 0; i < enemies.length; i++) {
            double xDir = (cook.getX() - enemies[i].getX());
            double yDir = (cook.getY() - enemies[i].getY());
            double distance = Math.sqrt(xDir * xDir + yDir * yDir);

            enemies[i].move((dt / distance), xDir, yDir);
        }
    }

    /**
     * updates player movement
     *
     * @param dt Benötigt um jeden Frame zu Updaten
     * @param pXDirPlayer,pYDirPlayer bentöigt, um Richtung der Bewegungsänderung weiterzugeben
     */
    public void updatePlayers(double dt, double pXDirPlayer, double pYDirPlayer) {
        environmentObjects.toFirst();
        while (environmentObjects.hasAccess()) {
            // player should not be able to move into a collidable object
            if(isMovingIntoEnvObject(environmentObjects.getContent(), cook, pXDirPlayer, pYDirPlayer))
                return;
            environmentObjects.next();
        }
        cook.move(dt, pXDirPlayer, pYDirPlayer);
    }

    /**
     * @param env environment object that should be checked
     * @param player player that should be checked
     * @param pXDirPlayer,yDirPlayer direction the player is moving
     * @return whether player is colliding & moving in the direction of an active environmentObject
     */
    private boolean isMovingIntoEnvObject(Environment env, Player player, double pXDirPlayer, double pYDirPlayer) {
        if (env.collidesWith(player) && env.isActive()) {
            return pXDirPlayer == 1 && env.getX() > player.getX() ||
                    pXDirPlayer == -1 && env.getX() < player.getX() ||
                    pYDirPlayer == 1 && env.getY() < player.getY() ||
                    pYDirPlayer == -1 && env.getY() > player.getY();
        }
        return false;
    }
}
