package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.*;

public class EntityController {
    private Enemy[] enemies;
    private Cook cook;
    private Shooter shooter;
    private List<Environment> environmentObjects;

    /**
     * @param pEnemies Required for enemy movement
     * @param pCook Required for player movement
     * @param pShooter Required for player movement
     * @param envController Required for player - CollidableEnvironment Collision
     */
    public EntityController(Enemy[] pEnemies, Cook pCook, Shooter pShooter, EnvironmentController envController) {
        enemies = pEnemies;
        cook = pCook;
        shooter = pShooter;
        environmentObjects = envController.getCollidableEnvironmentObjects();
    }

    /**
     * Updates enemy movement
     * @param dt Benötigt um jeden Frame zu Updaten
     */
    public void updateEnemies(double dt) {
        for (int i = 0; i < enemies.length; i++) {
            double[] dir = {cook.getX() - enemies[i].getX(), cook.getY() - enemies[i].getY()};
            dir = checkForCollisions(enemies[i], dir);
            // TODO 1: distance, therefore dir, is NaN because of squareroot of (0) --> leads to unwanted behavior (enemies move to upper left corner)
            double distance = Math.sqrt(dir[0] * dir[0] + dir[1] * dir[1]);

            if(!Double.isNaN(distance))
                enemies[i].move((dt / distance), dir[0], dir[1]);
        }
    }

    /**
     * updates player movement
     * @param dt Benötigt um jeden Frame zu Updaten
     * @param cookDir,pYDirPlayer bentöigt, um Richtung der Bewegungsänderung weiterzugeben
     */
    public void updatePlayers(double dt, double[] cookDir, double[] shooterDir) {
        double[] newDirCook = checkForCollisions(cook, cookDir);
        double[] newDirShooter = checkForCollisions(shooter, shooterDir);
        cook.move(dt, newDirCook[0], newDirCook[1]);
        shooter.move(dt, newDirShooter[0], newDirShooter[1]);
    }

    /**Checks for any collisions & adjusts Dir accordingly
     * @param entity entity that should be checked
     * @param entityDir direction the entity is moving
     * @return double[] adjusted direction with length 2 (sets dir[i] = 0 to restrict movement into collider) Default: returns entered parameters
     */
    private double[] checkForCollisions(Entity entity, double[] entityDir){
        double[] newDir = entityDir;
        newDir = checkEnvironmentCollision(entity, newDir);
        //newDir = checkScreenBorderCollision(entity, newDir);
        return newDir;
    }

    /**Searches for collision with a collidable Environment object & adjusts direction accordingly
     * @param entity The entity that should be checked for collisions
     * @param entityDir Entity direction that should be adjusted
     * @return double[] adjusted direction with length 2 (sets dir[i] = 0 to restrict movement into collider)
     */
    private double[] checkEnvironmentCollision(Entity entity, double[] entityDir) {
        double[] newDir = entityDir;

        // TODO 2: Fix following behavior: Colliding with an object from your below leads to restriction to right movement (same effect with your right collison, upward movement)
        // TODO 3: TEST THIS:: Entering kitchen from left side is possible but not exiting????? --> Similar behavior with exiting upward

        environmentObjects.toFirst();
        while (environmentObjects.hasAccess()) {
            if (environmentObjects.getContent().collidesWith(entity) && environmentObjects.getContent().isActive()) {
                Environment env = environmentObjects.getContent();
                newDir = keepWithinBoundaries(
                new double[][]{
                                {env.getX(), env.getX()}, //TODO 4: add image width to this
                                {env.getY(), env.getX()}
                                },
                        entity,
                        entityDir);
            }

            if(newDir != entityDir)
                return newDir;
            environmentObjects.next();
        }
        return entityDir;
    }

    /**Searches for collision with a screenborder & adjusts direction accordingly
     * @param entity entity that should be checked
     * @param entityDir direction the entity is moving
     * @return double[] adjusted direction with length 2 (sets dir[i] = 0 to restrict movement into collider) Default: returns entered parameters
     */
    private double[] checkScreenBorderCollision(Entity entity, double[] entityDir){
        double[] newDir = entityDir;
        newDir = keepWithinBoundaries(
        new double[][]{
                    {0, 1080+29},
                    {1920, 0}
                },
                entity,
                newDir
        );
        return newDir;
    }

    /**Checks if gO is moving within certain boundaries. If not, prevents moving further out by adjusting direction
     * @param boundaries 2D array: {x{LeftBorder, RightBorder}, y{UpperBorder, BottomBorder}}
     * @param entity entity that should be checked
     * @param entityDir direction the entity is moving
     * @return
     */
    private double[] keepWithinBoundaries(double[][] boundaries, Entity entity, double[] entityDir){
        double[] newDir = entityDir;
        if(
                newDir[0] < 0 && boundaries[0][0] > entity.getX() ||
                newDir[0] > 0 && boundaries[0][1] < entity.getX()
        ) newDir[0] = 0;
        if(
                newDir[1] > 0 && boundaries[1][0] < entity.getY() ||
                newDir[1] < 0 && boundaries[1][1] > entity.getY()
        ) newDir[1] = 0;
        return newDir;
    }

}