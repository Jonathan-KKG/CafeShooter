package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.*;

public class EntityController {
    private Shooter shooter;
    private Cook cook;
    private ProgramController programController;

    /**
     * @param pProgramController Required to get other controllers
     * @param viewController     Required to draw entities
     */
    public EntityController(ProgramController pProgramController, ViewController viewController) {
        programController = pProgramController;

        shooter = new Shooter(150, 150);
        cook = new Cook(800, 800);

        viewController.draw(shooter);
        viewController.draw(cook);

    }

    /**
     * Moves every Enemy towards an assigned target
     *
     * @param dt      Time passed between this and last frame
     * @param enemies all enemies that should be moved
     * @param target  the target the enemies should move to
     */
    public void updateEnemies(double dt, Enemy[] enemies, Entity target) {
        for (int i = 0; i < enemies.length; i++) {
            if (enemies[i] == null)
                return;

            double[] dir = {target.getX() - enemies[i].getX(), target.getY() - enemies[i].getY()};
            double distance = Math.sqrt(dir[0] * dir[0] + dir[1] * dir[1]);
            dir[0] /= distance;
            dir[1] /= distance;
            if (distance != 0)
                checkForCollisions(dt, enemies[i], dir);
        }
    }

    /**
     * updates player movement
     *
     * @param dt        Time between last frame and this
     * @param playerDir bentöigt, um Richtung der Bewegungsänderung weiterzugeben : { Cook{x,y}, Shooter{x,y} }
     */
    public void updatePlayers(double dt, double[][] playerDir) {
        checkForCollisions(dt, cook, playerDir[0]);
        checkForCollisions(dt, shooter, playerDir[1]);
    }

    /**
     * Checks for any collisions & adjusts Dir accordingly
     *
     * @param dt        time passed between this frame and last one
     * @param entity    entity that should be checked
     * @param entityDir direction the entity is moving
     */
    private void checkForCollisions(double dt, Entity entity, double[] entityDir) {
        // Check collision w/ screen borders
        keepWithinScreen(
                new double[][]{
                        {0, 1920 * 0.85 - 19},
                        {1080 * 0.85 - 40, 0}
                },
                entity,
                entityDir
        );

        double[] pos = {entity.getX() + entity.getSpeed() * dt * entityDir[0], entity.getY() + entity.getSpeed() * dt * entityDir[1]};
        // Check collision w/ collidable Environment
        checkEnvironmentCollision(entity, pos);

    }

    /**
     * Searches for collision with a collidable Environment object & prevents collision by setting position accordingly
     *
     * @param entity    The entity that should be checked for collisions
     * @param entityPos Entity direction that should be adjusted
     */
    private void checkEnvironmentCollision(Entity entity, double[] entityPos) {
        List<CollidableEnvironment> envObjs = programController.getEnvironmentController().getCollidableEnvironmentObjects();
        envObjs.toFirst();
        while (envObjs.hasAccess()) {
            if (envObjs.getContent().isColliderActive()) {
                CollidableEnvironment env = envObjs.getContent();
                boolean collided = keepOutOfBounds(
                        env,
                        entity,
                        entityPos);
                if (collided && entity.getClass().toString().equals("class my_project.model.Enemy"))
                    env.reduceHP();
            }
            envObjs.next();
        }
    }

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
     * Checks if entity is colliding with a GraphicalObject and sets new position of entity appropiately to prevent clipping
     *
     * @param collider  object the entity could be colliding with
     * @param entity    entity that should be checked
     * @param futurePos the entitys future position
     * @return whether collision was found or not
     */
    private boolean keepOutOfBounds(GraphicalObject collider, Entity entity, double[] futurePos) {
        boolean collided = false;
        if (entity.collidesWith(collider)) {
            if (collider.getX() > entity.getX())
                futurePos[0] = collider.getX() - entity.getWidth();
            else if (collider.getX() + collider.getWidth() < entity.getX() + entity.getWidth())
                futurePos[0] = collider.getX() + collider.getWidth();
            collided = true;
        }
        entity.setX(futurePos[0]);

        if (entity.collidesWith(collider)) {
            if (collider.getY() > entity.getY())
                futurePos[1] = collider.getY() - entity.getHeight();
            else if (collider.getY() + collider.getHeight() < entity.getY() + entity.getHeight())
                futurePos[1] = collider.getY() + collider.getHeight() + 1;
            collided = true;
        }
        entity.setY(futurePos[1]);
        return collided;
    }

    public Cook getCook() {
        return cook;
    }

    public Shooter getShooter() {
        return shooter;
    }
}