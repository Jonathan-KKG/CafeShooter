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
        checkEnvironmentCollision(entity, pos, entityDir);

    }

    /**
     * Checks collision between Enemy and Dishes. If a Dish hits an Enemy, it gets deleted and if it has the right type the Enemy dies.
     * All Dishes outside the map get deleted.
     *
     * @param pEnemies an Array of all existing Enemies
     */
    public void checkDishCollisions(Enemy[] pEnemies) {
        programController.getDishController().getFlyingDishes().toFirst();
        boolean removed = false;
        while (programController.getDishController().getFlyingDishes().hasAccess()) {
            for (int i = 0; i < pEnemies.length; i++) {
                if (pEnemies[i] != null && programController.getDishController().getFlyingDishes().getContent().collidesWith(pEnemies[i])) {
                    if (pEnemies[i].getRequiredDish().equals(programController.getDishController().getFlyingDishes().getContent().getType())) {
                        programController.removeDrawableFromScene(pEnemies[i]);
                        pEnemies[i] = null;
                        removed = true;
                    }
                    i = pEnemies.length;
                    programController.removeDrawableFromScene(programController.getDishController().getFlyingDishes().getContent());
                    programController.getDishController().getFlyingDishes().remove();
                }
            }
            if (!removed) {
                programController.getDishController().getFlyingDishes().next();
                if (programController.getDishController().getFlyingDishes().hasAccess() && (programController.getDishController().getFlyingDishes().getContent().getY() + programController.getDishController().getFlyingDishes().getContent().getHeight() < 0 ||
                        programController.getDishController().getFlyingDishes().getContent().getY() > 1109 ||
                        programController.getDishController().getFlyingDishes().getContent().getX() + programController.getDishController().getFlyingDishes().getContent().getWidth() < 0 ||
                        programController.getDishController().getFlyingDishes().getContent().getX() > 1920)
                ) {
                    programController.removeDrawableFromScene(programController.getDishController().getFlyingDishes().getContent());
                    programController.getDishController().getFlyingDishes().remove();
                }
            }
        }
    }

    /**
     * Searches for collision with a collidable Environment object & prevents collision by setting position accordingly
     *
     * @param entity    The entity that should be checked for collisions
     * @param entityPos Entity direction that should be adjusted
     */
    private void checkEnvironmentCollision(Entity entity, double[] entityPos, double[] entityDir) {
        List<CollidableEnvironment> envObjs = programController.getEnvironmentController().getCollidableEnvironmentObjects();
        envObjs.toFirst();
        while (envObjs.hasAccess()) {
            if (envObjs.getContent().isColliderActive()) {
                CollidableEnvironment env = envObjs.getContent();
                boolean collided = keepOutOfBounds(
                        env,
                        entity,
                        entityPos, entityDir);
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
     * It works don't touch it (Only god knows why it works)
     * @param collider  object the entity could be colliding with
     * @param entity    entity that should be checked
     * @param futurePos the entitys future position
     * @return whether collision was found or not
     */
    private boolean keepOutOfBounds(GraphicalObject collider, Entity entity, double[] futurePos, double[] entityDir) {
        boolean collided = false;

        boolean entityCompletelyIsUnderCollider = entity.getY() > collider.getY() + collider.getHeight() - 2.5;
        boolean entityCompletelyIsOverCollider = entity.getY() + entity.getHeight() <= collider.getY() + 2.5;
        boolean entityCompletelyIsRightOfCollider = entity.getX() >= collider.getX() + collider.getWidth() - 4;
        boolean entityCompletelyIsLeftOfCollider = entity.getX() + entity.getWidth() <= collider.getX() + 3.5;

        if (entity.collidesWith(collider) && entityDir[0] > 0 && collider.getX() > futurePos[0] && !(entityCompletelyIsOverCollider || entityCompletelyIsUnderCollider)) {
            futurePos[0] = collider.getX() - entity.getWidth();
            collided = true;
        }
        if (entity.collidesWith(collider) && entityDir[0] < 0 && collider.getX() + collider.getWidth() < futurePos[0] + entity.getWidth() && !(entityCompletelyIsOverCollider || entityCompletelyIsUnderCollider)) {
            futurePos[0] = collider.getX() + collider.getWidth();
            collided = true;
        }
        entity.setX(futurePos[0]);

        if (entity.collidesWith(collider) && !(entityCompletelyIsRightOfCollider || entityCompletelyIsLeftOfCollider)) {
            if (collider.getY() > futurePos[1])
                futurePos[1] = collider.getY() - entity.getHeight();
            else if (collider.getY() + collider.getHeight() < futurePos[1] + entity.getHeight())
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