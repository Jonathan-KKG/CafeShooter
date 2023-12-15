package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
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

        shooter = new Shooter("img.png", 150, 150);
        cook = new Cook("img.png", 800, 800);

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
            if (enemies[i] == null) return;

            double[] dir = {target.getX() - enemies[i].getX(), target.getY() - enemies[i].getY()};
            double distance = Math.sqrt(dir[0] * dir[0] + dir[1] * dir[1]);
            dir[0] /= distance;
            dir[1] /= distance;
            if (distance != 0)
                checkForScreenAndEnvironCollisions(dt, enemies[i], dir);
        }
    }

    /**
     * updates player movement
     *
     * @param dt        Time between last frame and this
     * @param playerDir bentöigt, um Richtung der Bewegungsänderung weiterzugeben : { Cook{x,y}, Shooter{x,y} }
     */
    public void updatePlayers(double dt, double[][] playerDir) {
        checkForScreenAndEnvironCollisions(dt, cook, playerDir[0]);
        checkForScreenAndEnvironCollisions(dt, shooter, playerDir[1]);
    }

    /**
     * Checks collision between Enemy and Dishes. If a Dish hits an Enemy, it gets deleted and if it has the right type the Enemy dies.
     * All Dishes outside the map get deleted.
     *
     * @param pEnemies an Array of all existing Enemies
     */
    public void checkDishCollisions(Enemy[] pEnemies) {
        List<Dish> dishList = programController.getDishController().getFlyingDishes();
        dishList.toFirst();
        boolean removed = false;
        while (dishList.hasAccess()) {
            for (int i = 0; i < pEnemies.length; i++) {
                Dish currentDish = dishList.getContent();
                if (pEnemies[i] != null && currentDish.collidesWith(pEnemies[i])) {
                    if (pEnemies[i].getRequiredDish().equals(currentDish.getType())) {
                        programController.removeDrawableFromScene(pEnemies[i]);
                        pEnemies[i] = null;
                        removed = true;
                    }
                    i = pEnemies.length;
                    programController.removeDrawableFromScene(currentDish);
                    dishList.remove();
                }
            }
            if (!removed) {
                dishList.next();
                Dish currentDish = dishList.getContent();
                if (currentDish == null) break;
                double[] futurePos = new double[]{currentDish.getX(), currentDish.getY()};

                if (keepInsideScreen(currentDish.getDirection(), futurePos, currentDish)) {
                    programController.removeDrawableFromScene(currentDish);
                    dishList.remove();
                }

            }
        }
    }

    public void checkForDishCollisions() {
        boolean removed = false;
        programController.getDishController().getFlyingDishes().toFirst();
        while (programController.getDishController().getFlyingDishes().hasAccess()) {
            for (int i = 0; i < programController.getWaveController().getWave().length; i++) {
                if (programController.getWaveController().getWave()[i] != null && programController.getDishController().getFlyingDishes().getContent().collidesWith(programController.getWaveController().getWave()[i])) {
                    if (programController.getWaveController().getWave()[i].getRequiredDish().equals(programController.getDishController().getFlyingDishes().getContent().getType())) {
                        programController.removeDrawableFromScene(programController.getWaveController().getWave()[i]);
                        removed = true;
                    }
                    programController.removeDrawableFromScene(programController.getDishController().getFlyingDishes().getContent());
                    programController.getDishController().getFlyingDishes().remove();
                    if (removed)
                        programController.getWaveController().getWave()[i] = null;
                    else if (programController.getDishController().getFlyingDishes().hasAccess() && (programController.getDishController().getFlyingDishes().getContent().getY() + programController.getDishController().getFlyingDishes().getContent().getHeight() < 0 ||
                            programController.getDishController().getFlyingDishes().getContent().getY() > 1109 ||
                            programController.getDishController().getFlyingDishes().getContent().getX() + programController.getDishController().getFlyingDishes().getContent().getWidth() < 0 ||
                            programController.getDishController().getFlyingDishes().getContent().getX() > 1920)
                    ) {
                        programController.removeDrawableFromScene(programController.getDishController().getFlyingDishes().getContent());
                        programController.getDishController().getFlyingDishes().remove();
                    } else
                        programController.getDishController().getFlyingDishes().next();
                }
            }
        }
    }

    /**
     * Searches for collisions with collidable Environment object
     * <br> or screen border & prevents collision by adjusting
     * <br> and setting position accordingly
     *
     * @param dt        Time passed between this and last frame
     * @param entity    The entity that should be checked for collisions
     * @param entityDir direction the enemy is moving with
     */
    private void checkForScreenAndEnvironCollisions(double dt, Entity entity, double[] entityDir) {
        double[] entityPos = {entity.getX() + entity.getSpeed() * dt * entityDir[0], entity.getY() + entity.getSpeed() * dt * entityDir[1]};
        List<CollidableEnvironment> envObjs = programController.getEnvironmentController().getCollidableEnvironmentObjects();

        envObjs.toFirst();
        while (envObjs.hasAccess()) {
            if (envObjs.getContent().isColliderActive()) {
                CollidableEnvironment env = envObjs.getContent();
                boolean collided = keepOutOfBounds(env, entity, entityPos, entityDir);
                if (collided && entity.getClass().toString().equals("class my_project.model.Enemy"))
                    env.reduceHP();
            }
            envObjs.next();
        }
    }

    /**
     * Checks if entity is colliding with a GraphicalObject and sets new position of entity appropiately to prevent clipping
     * <br> It works don't touch it (Only god knows why it works)
     * <br> This method is the only instance of actually setting positions of Players and Enemies
     * @param collider  object the entity could be colliding with
     * @param entity    entity that should be checked
     * @param futurePos the entitys future position
     * @return whether collision was found or not
     */
    private boolean keepOutOfBounds(GraphicalObject collider, Entity entity, double[] futurePos, double[] entityDir) {
        boolean collided = false;
        keepInsideScreen(entityDir, futurePos, entity);

        if (keepOutOfX(collider, entity, futurePos, entityDir)) collided = true;
        entity.setX(futurePos[0]);

        if (keepOutOfY(collider, entity, futurePos)) collided = true;
        entity.setY(futurePos[1]);

        return collided;
    }

    /** Checks if entity is horizontally colliding with collider and adjusts futurePos accordingly to prevent clipping
     * @param collider  object the entity could be colliding with
     * @param entity entity that should be checked
     * @param futurePos the entitys future position that will be adjusted
     * @param entityDir direction the entity is moving with
     * @return whether collision was found or not
     */
    private boolean keepOutOfX(GraphicalObject collider, Entity entity, double[] futurePos, double[] entityDir) {
        boolean collided = false;
        boolean entityCompletelyIsUnderCollider = entity.getY() > collider.getY() + collider.getHeight() - 2.5; // I don't know about the offsets don't ask me
        boolean entityCompletelyIsOverCollider = entity.getY() + entity.getHeight() < collider.getY() + 2.5;

        if (entity.collidesWith(collider) && entityDir[0] > 0 && collider.getX() > futurePos[0] && !(entityCompletelyIsOverCollider || entityCompletelyIsUnderCollider)) {
            futurePos[0] = collider.getX() - entity.getWidth();
            collided = true;
        }
        if (entity.collidesWith(collider) && entityDir[0] < 0 && collider.getX() + collider.getWidth() < futurePos[0] + entity.getWidth() && !(entityCompletelyIsOverCollider || entityCompletelyIsUnderCollider)) {
            futurePos[0] = collider.getX() + collider.getWidth();
            collided = true;
        }
        return collided;
    }

    /** Checks if entity is vertically colliding with collider and adjusts futurePos accordingly to prevent clipping
     * @param collider  object the entity could be colliding with
     * @param entity entity that should be checked
     * @param futurePos the entitys future position that will be adjusted
     * @return whether collision was found or not
     */
    private boolean keepOutOfY(GraphicalObject collider, Entity entity, double[] futurePos) {
        boolean collided = false;
        boolean entityCompletelyIsRightOfCollider = entity.getX() > collider.getX() + collider.getWidth() - 4;
        boolean entityCompletelyIsLeftOfCollider = entity.getX() + entity.getWidth() < collider.getX() + 3.5;

        if (entity.collidesWith(collider) && !(entityCompletelyIsRightOfCollider || entityCompletelyIsLeftOfCollider)) {
            if (collider.getY() > futurePos[1]) futurePos[1] = collider.getY() - entity.getHeight();
            else if (collider.getY() + collider.getHeight() < futurePos[1] + entity.getHeight())
                futurePos[1] = collider.getY() + collider.getHeight() + 1;
            collided = true;
        }

        return collided;
    }

    /**Checks whether entity will be out of the screen and adjusts futurePos accordingly to prevent that
     * @param entityDir direction the entity is moving with
     * @param futurePos the entitys future position that will be adjusted
     * @param entity entity that should be checked
     * @return whether collision was found or not
     */
    private boolean keepInsideScreen(double[] entityDir, double[] futurePos, Entity entity) {
        if (entityDir[0] < 0 && 1 > futurePos[0]) {
            futurePos[0] = 1;
            return true;
        } else if (entityDir[0] > 0 && 1920 * 0.85 - 16 < futurePos[0] + entity.getWidth()) {
            futurePos[0] = 1920 * 0.85 - 16 - entity.getWidth();
            return true;
        }
        if (entityDir[1] > 0 && 1080 * 0.85 - 39 < futurePos[1] + entity.getHeight()) {
            futurePos[1] = 1080 * 0.85 - 39 - entity.getWidth();
            return true;
        } else if (entityDir[1] < 0 && 0 > futurePos[1]) {
            futurePos[1] = 1;
            return true;
        }
        return false;
    }

    public Cook getCook() {
        return cook;
    }

    public Shooter getShooter() {
        return shooter;
    }
}