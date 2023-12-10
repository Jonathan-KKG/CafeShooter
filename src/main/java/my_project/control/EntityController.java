package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.*;

public class EntityController {
    private Enemy[] enemies;
    private Cook cook;
    private Shooter shooter;
    private List<CollidableEnvironment> environmentObjects;

    /**
     * @param pEnemies      Array of all Existing enemies
     * @param pCook         Required for player movement
     * @param pShooter      Required for player movement
     * @param envController Required for player - CollidableEnvironment Collision
     */
    public EntityController(Enemy[] pEnemies, Cook pCook, Shooter pShooter, EnvironmentController envController) {
        enemies = pEnemies;
        cook = pCook;
        shooter = pShooter;
        environmentObjects = envController.getCollidableEnvironmentObjects();
    }

    /**
     * Moves every Enemy towards the Cook
     *
     * @param dt Time passed between this and last frame
     */
    public void updateEnemies(double dt) {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] == null)
                return;

            double[] dir = {cook.getX() - enemies[i].getX(), cook.getY() - enemies[i].getY()};
            double distance = Math.sqrt(dir[0] * dir[0] + dir[1] * dir[1]);
            dir[0] /= distance;
            dir[1] /= distance;
            checkForCollisions(dt, enemies[i], dir);
            // TODO 1: distance, therefore dir, is NaN because of squareroot of (0) --> leads to unwanted behavior (enemies move to upper left corner)


        }
    }

    /**
     * updates player movement
     *
     * @param dt                  Benötigt um jeden Frame zu Updaten
     * @param cookDir,pYDirPlayer bentöigt, um Richtung der Bewegungsänderung weiterzugeben
     */
    public void updatePlayers(double dt, double[] cookDir, double[] shooterDir) {
        checkForCollisions(dt, cook, cookDir);
        checkForCollisions(dt, shooter, shooterDir);
    }

    /**
     * Checks for any collisions & adjusts Dir accordingly
     *
     * @param entity    entity that should be checked
     * @param entityDir direction the entity is moving
     */
    private void checkForCollisions(double dt, Entity entity, double[] entityDir) {
        // Check collision w/ collidable Environment
        checkEnvironmentCollision(dt, entity, entityDir);
        // Check collision w/ screen borders
        keepWithinScreen(
                new double[][]{
                        {0, 1920 * 0.85 - 19},
                        {1080 * 0.85 - 42, 0}
                },
                entity,
                entityDir
        );

    }

    /**
     * Searches for collision with a collidable Environment object & adjusts direction accordingly
     * @param entity    The entity that should be checked for collisions
     * @param entityDir Entity direction that should be adjusted
     */
    private void checkEnvironmentCollision(double dt, Entity entity, double[] entityDir) {
        // TODO 2: Fix following behavior: Colliding with an object from your below leads to restriction to right movement (same effect with your right collison, upward movement)

        double[] newPos = {entity.getX() + entity.getSpeed() * dt * entityDir[0], entity.getY() + entity.getSpeed() * dt * entityDir[1]};
        double[] oldPos = {entity.getX() + entity.getSpeed() * dt * entityDir[0], entity.getY() + entity.getSpeed() * dt * entityDir[1]};
        environmentObjects.toFirst();
        while (environmentObjects.hasAccess()) {
            if (environmentObjects.getContent().isColliderActive()) {
                CollidableEnvironment env = environmentObjects.getContent();
                keepOutOfBounds(
                        new double[][]{
                                {env.getX(), env.getX() + env.getWidth()},
                                {env.getY() + env.getHeight(), env.getY()}
                        },
                        entity,
                        newPos);

            }
            if (oldPos == newPos)
                break;
            environmentObjects.next();
        }
    }

    /*
    private double[] checkEntityCollision(Entity entity, double[] entityDir, Entity collidingEntity){
        double[] newDir = entityDir;
        newDir = keepWithinBoundaries(
        new double[][]{
                        {collidingEntity.getX() - collidingEntity.getWidth() / 2, collidingEntity.getX() + collidingEntity.getWidth() / 2},
                        {collidingEntity.getY() - collidingEntity.getHeight() / 2, collidingEntity.getY() + collidingEntity.getHeight() / 2}
                },
                entity,
                entityDir
        );
        return newDir;
    }*/

    /**
     * Checks if gO is moving within screen. If not, prevents moving further out by adjusting direction
     *
     * @param boundaries 2D array: {x{LeftBorder, RightBorder}, y{BottomBorder, UpperBorder}}
     * @param entity     entity that should be checked
     * @param entityDir  direction the entity is moving
     */
    private void keepWithinScreen(double[][] boundaries, Entity entity, double[] entityDir) {
        if (
                entityDir[0] < 0 && boundaries[0][0] > entity.getX() ||
                        entityDir[0] > 0 && boundaries[0][1] < entity.getX() + entity.getWidth()
        ) entityDir[0] = 0;

        if (
                entityDir[1] > 0 && boundaries[1][0] < entity.getY() + entity.getHeight() ||
                        entityDir[1] < 0 && boundaries[1][1] > entity.getY()
        ) entityDir[1] = 0;
    }

    /**
     * Checks if entity is moving within certain boundaries. If not, prevents moving further out by adjusting direction
     * @param boundaries 2D array: {x{LeftBorder, RightBorder}, y{BottomBorder, UpperBorder}}
     * @param entity     entity that should be checked
     * @param pos        the entitys position that should be adjusted
     */
    private void keepOutOfBounds(double[][] boundaries, Entity entity, double[] pos) {
        if (environmentObjects.getContent().collidesWith(entity)) {
            if (boundaries[0][0] > entity.getX())
                pos[0] = boundaries[0][0] - entity.getWidth();
            else if (boundaries[0][1] < entity.getX() + entity.getWidth())
                pos[0] = boundaries[0][1];
        }
        entity.setX(pos[0]);

        if (environmentObjects.getContent().collidesWith(entity)) {
            if (boundaries[1][0] > entity.getY() && !(boundaries[1][1] > entity.getY()))
                pos[1] = boundaries[1][0] + 1;
            else if (boundaries[1][1] < entity.getY() + entity.getHeight())
                pos[1] = boundaries[1][1] - entity.getHeight();
        }
        entity.setY(pos[1]);
    }

}