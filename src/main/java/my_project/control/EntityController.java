package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.*;

import java.io.File;

public class EntityController {
    private Shooter shooter;
    private Cook cook;
    private ProgramController programController;
    private String[] enemyTypes;

    /**
     * @param pProgramController Required to get other controllers
     * @param viewController     Required to draw entities
     */
    public EntityController(ProgramController pProgramController, ViewController viewController) {
        programController = pProgramController;

        shooter = new Shooter("img", 150, 150);
        cook = new Cook("img", 800, 800);

        viewController.draw(shooter);
        viewController.draw(cook);

        File[] enemyFiles = new File("src/main/java/my_project/model/Enemies").listFiles();
        enemyTypes = new String[enemyFiles.length];
        for (int i = 0; i < enemyFiles.length; i++) {
            enemyTypes[i] = enemyFiles[i].toString().replaceAll("src\\\\main\\\\java\\\\my_project\\\\model\\\\Enemies\\\\", "").replaceAll(".java", "");
        }
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
            if (enemies[i] != null) {
                double[] dir = {target.getX() - enemies[i].getX() - enemies[i].getWidth() / 2 + target.getWidth() / 2, target.getY() - enemies[i].getY() - enemies[i].getHeight() / 2 + target.getHeight() / 2};
                double distance = Math.sqrt(dir[0] * dir[0] + dir[1] * dir[1]);
                dir[0] /= distance;
                dir[1] /= distance;

                if (distance != 0)
                    checkForScreenAndEnvironCollisions(dt, enemies[i], dir);

                if (enemies[i].collidesWith(cook))
                    programController.endGame();
            }
        }
    }

    /**
     * updates player movement
     *
     * @param dt        Time between last frame and this
     * @param playerDir bentöigt, um Richtung der Bewegungsänderung weiterzugeben : { Cook{x,y}, Shooter{x,y} }
     */
    public void updatePlayers(double dt, double[][] playerDir) {
        if (cook.isCooking()) {
            playerDir[0][0] = 0;
            playerDir[0][1] = 0;
        }
        if (shooter.isRepairing()) {
            playerDir[1][0] = 0;
            playerDir[1][1] = 0;
        }

        checkForScreenAndEnvironCollisions(dt, cook, playerDir[0]);
        updateClosestObjectInRange(programController.getEnvironmentController().getInteractableEnvironmentObjects(), cook);

        checkForScreenAndEnvironCollisions(dt, shooter, playerDir[1]);
        updateClosestObjectInRange(programController.getEnvironmentController().getCollidableEnvironmentObjects(), shooter);
    }

    /**
     * Checks collision between Enemy and Dishes. If a Dish hits an Enemy, it gets deleted and if it has the right type the Enemy dies.
     * All Dishes outside the map get deleted.
     * Moves dishes.
     */
    public void dishCollisionUpdate() {
        List<Dish> dishList = programController.getDishController().getFlyingDishes();
        Enemy[] enemies = programController.getWaveController().getWave();

        dishList.toFirst();
        boolean removed = false;
        while (dishList.hasAccess()) {
            // Check Dish-Enemy collision
            for (int i = 0; i < enemies.length; i++) {
                Dish currentDish = dishList.getContent();
                if (enemies[i] != null && currentDish.collidesWith(enemies[i])) {
                    if (enemies[i].getRequiredDish().equals(currentDish.getType())) {
                        programController.getUiController().deleteEnemyBubble(i, programController.getViewController());
                        programController.getViewController().removeDrawable(enemies[i]);
                        enemies[i] = null;
                        removed = true;
                    }
                    i = enemies.length;
                    programController.getViewController().removeDrawable(currentDish);
                    dishList.remove();
                }
            }
            // Check Dish-Screen collision
            if (!removed) {
                dishList.next();
                Dish currentDish = dishList.getContent();
                if (currentDish == null) break;
                double[] futurePos = new double[]{currentDish.getX(), currentDish.getY()};

                if (keepInsideScreen(currentDish.getDirection(), futurePos, currentDish)) {
                    programController.getViewController().removeDrawable(currentDish);
                    dishList.remove();
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
        boolean damaged = false;

        envObjs.toFirst();
        while (envObjs.hasAccess()) {
            if (envObjs.getContent().isColliderActive()) {
                CollidableEnvironment env = envObjs.getContent();
                if (keepOutOfBounds(env, entity, entityPos, entityDir)) {
                    for (int i = 0; i < enemyTypes.length; i++) {
                        if (entity.getClass().getName().replaceAll("my_project.model.Enemies.", "").equals(enemyTypes[i])) {
                            if (env.getHp() == 100)
                                programController.getUiController().drawHPBar(env, programController.getViewController());
                            env.reduceHP(dt);
                            i = enemyTypes.length;
                            damaged = true;
                        }
                    }
                }
            }
            envObjs.next();
        }
        if (damaged)
            programController.getUiController().updateHPBars(programController.getViewController());
    }

    /**
     * sets the nearest interactable Environment in a set range (+-lowestDistance) of a player
     */
    private void updateClosestObjectInRange(List<CollidableEnvironment> interactables, Player player) {
        CollidableEnvironment closestObj = null;
        double lowestDistance = 70;
        double playerMiddleX = player.getX() + player.getWidth() / 2;
        double playerMiddleY = player.getY() + player.getHeight() / 2;

        interactables.toFirst();
        while (interactables.hasAccess()) {
            if (interactables.getContent().isColliderActive()) {
                CollidableEnvironment currentObject = interactables.getContent();
                if (currentObject.getDistanceTo(player) < lowestDistance && (playerMiddleX < currentObject.getX() + currentObject.getWidth() + 20 || playerMiddleX > currentObject.getX() + currentObject.getWidth() - 20
                        || playerMiddleY < currentObject.getHeight() + 20 || playerMiddleY > currentObject.getHeight() - 20)) {
                    if (closestObj != null) {
                        if (currentObject.getDistanceTo(player) < closestObj.getDistanceTo(player))
                            closestObj = currentObject;
                    } else closestObj = currentObject;
                }
            }
            interactables.next();
        }
        player.setClosestObjectInRange(closestObj);
    }

    /**
     * Checks if entity is colliding with a GraphicalObject and sets new position of entity appropiately to prevent clipping
     * <br> It works don't touch it (Only god knows why it works)
     * <br> This method is the only instance of actually setting positions of Players and Enemies
     *
     * @param collider  object the entity could be colliding with
     * @param entity    entity that should be checked
     * @param futurePos the entitys future position
     * @return whether collision was found or not
     */
    private boolean keepOutOfBounds(GraphicalObject collider, Entity entity, double[] futurePos, double[] entityDir) {
        boolean collided = false;
        keepInsideScreen(entityDir, futurePos, entity);

        if (keepOutOfX(collider, entity, futurePos)) collided = true;
        entity.setX(futurePos[0]);

        if (keepOutOfY(collider, entity, futurePos)) collided = true;
        entity.setY(futurePos[1]);

        return collided;
    }

    /**
     * Checks if entity is horizontally colliding with collider and adjusts futurePos accordingly to prevent clipping
     *
     * @param collider  object the entity could be colliding with
     * @param entity    entity that should be checked
     * @param futurePos the entitys future position that will be adjusted
     * @return whether collision was found or not
     */
    private boolean keepOutOfX(GraphicalObject collider, Entity entity, double[] futurePos) {
        boolean collided = false;
        boolean entityCompletelyIsUnderCollider = entity.getY() > collider.getY() + collider.getHeight() - 3.9; // I don't know about the offsets don't ask me
        boolean entityCompletelyIsOverCollider = entity.getY() + entity.getHeight() < collider.getY() + 3.9;

        if (entity.collidesWith(collider) && collider.getX() > futurePos[0] && !(entityCompletelyIsOverCollider || entityCompletelyIsUnderCollider)) {
            futurePos[0] = collider.getX() - entity.getWidth();
            collided = true;
        }
        if (entity.collidesWith(collider) && collider.getX() + collider.getWidth() < futurePos[0] + entity.getWidth() && !(entityCompletelyIsOverCollider || entityCompletelyIsUnderCollider)) {
            futurePos[0] = collider.getX() + collider.getWidth();
            collided = true;
        }
        return collided;
    }

    /**
     * Checks if entity is vertically colliding with collider and adjusts futurePos accordingly to prevent clipping
     *
     * @param collider  object the entity could be colliding with
     * @param entity    entity that should be checked
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
                futurePos[1] = collider.getY() + collider.getHeight();
            collided = true;
        }

        return collided;
    }

    /**
     * Checks whether entity will be out of the screen and adjusts futurePos accordingly to prevent that
     *
     * @param entityDir direction the entity is moving with
     * @param futurePos the entitys future position that will be adjusted
     * @param entity    entity that should be checked
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