package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.GraphicalObject;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.*;
import my_project.model.Dishes.Dish;
import my_project.model.Enemies.Enemy;
import my_project.model.Environment.CollidableEnvironment;


/**
 * Controls all aspects of Enemy and Player objects, which includes movement, collision and update;
 * Additionally handles DishCollisions of "flyingDishes"
 */
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
            if (enemies[i] != null && enemies[i].isActive()) {
                if (enemies[i].getStunDuration() >= 0)
                    enemies[i].setStunDuration(enemies[i].getStunDuration() - dt);
                else {
                    double[] dir = {target.getX() - enemies[i].getX() - enemies[i].getWidth() / 2 + target.getWidth() / 2, target.getY() - enemies[i].getY() - enemies[i].getHeight() / 2 + target.getHeight() / 2};
                    double distance = Math.sqrt(dir[0] * dir[0] + dir[1] * dir[1]);
                    dir[0] /= distance;
                    dir[1] /= distance;

                    if (distance != 0)
                        checkForScreenAndEnvironCollisions(dt, enemies[i], dir);
                }
            }

            if (enemies[i] != null && enemies[i].collidesWith(cook))
                programController.endGame(false);
        }
    }

    /**
     * updates player movement
     *
     * @param dt        Time between last frame and this
     * @param playerDir bentöigt, um Richtung der Bewegungsänderung weiterzugeben : { Cook{x,y}, Shooter{x,y} }
     */
    public void updatePlayers(double dt, double[][] playerDir) {
        if (cook.isBusy()) {
            playerDir[0][0] = 0;
            playerDir[0][1] = 0;
        }
        if (shooter.isBusy()) {
            playerDir[1][0] = 0;
            playerDir[1][1] = 0;
        }
        shooter.setStunCooldown(shooter.getStunCooldown() - dt);

        checkForScreenAndEnvironCollisions(dt, cook, playerDir[0]);
        cook.setClosestObjectInRange(getClosestObjectInRange(programController.getEnvironmentController().getInteractableEnvironmentObjects(), cook));

        checkForScreenAndEnvironCollisions(dt, shooter, playerDir[1]);
        shooter.setClosestObjectInRange(getClosestObjectInRange(programController.getEnvironmentController().getInteractableEnvironmentObjects(), shooter));
        shooter.setObjectsInRange(getObjectsInRange(programController.getEnvironmentController().getCollidableEnvironmentObjects(), shooter));
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
        while (dishList.hasAccess()) {
            boolean removed = false;
            // Check Dish-Enemy collision
            for (int i = 0; i < enemies.length; i++) {
                Dish currentDish = dishList.getContent();
                if (enemies[i] != null && currentDish.collidesWith(enemies[i])) {
                    if (enemies[i].getRequiredDish().equals(currentDish.getClass().getSimpleName())) {
                        programController.getUIController().deleteEnemyBubble(i, programController.getViewController());
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

        envObjs.toFirst();
        while (envObjs.hasAccess()) {
            if (!envObjs.getContent().isColliderActive())
                nextToNextActiveCollider(envObjs);
            if (!envObjs.hasAccess())
                break;

            CollidableEnvironment env = envObjs.getContent();
            if (keepOutOfBounds(env, entity, entityPos, entityDir) && entity instanceof Enemy) {
                if (env.getHp() == 100)
                    programController.getUIController().drawHPBar(env, programController.getViewController());
                programController.getEnvironmentController().damage(env, dt, programController.getViewController(), programController.getUIController());
            }

            envObjs.next();
        }
    }

    /**
     * Recursively nexts to next active collider in a given list
     *
     * @param envObjs list that should be nexted through
     */
    private void nextToNextActiveCollider(List<CollidableEnvironment> envObjs) {
        if (!envObjs.hasAccess() || envObjs.getContent().isColliderActive())
            return;
        envObjs.next();
        nextToNextActiveCollider(envObjs);
    }

    /**
     * sets the nearest interactable Environment in a set range
     *
     * @param interactables list of all objects that shall be checkened
     * @param player        player that shall be checkened😎
     * @return list of all objects in range
     */
    private CollidableEnvironment getClosestObjectInRange(List<CollidableEnvironment> interactables, Player player) {
        CollidableEnvironment closestObj;
        List<CollidableEnvironment> objectsInRange = getObjectsInRange(interactables, player);
        objectsInRange.toFirst();
        closestObj = objectsInRange.getContent();
        while (objectsInRange.hasAccess()) {
            if (objectsInRange.getContent().isColliderActive() && objectsInRange.getContent().getDistanceTo(player) < closestObj.getDistanceTo(player)) {
                closestObj = objectsInRange.getContent();
            }
            objectsInRange.next();
        }

        return closestObj;
    }

    /**
     * finds all objects in range
     *
     * @param interactables list of all objects that shall be checkened
     * @param player        player that shall be checkened😎
     * @return list of all objects in range
     */
    private List<CollidableEnvironment> getObjectsInRange(List<CollidableEnvironment> interactables, Player player) {
        List<CollidableEnvironment> objectsInRange = new List<>();

        interactables.toFirst();
        while (interactables.hasAccess()) {
            CollidableEnvironment currentObject = interactables.getContent();
            if (player.getDistanceTo(currentObject) < 70) {
                objectsInRange.append(currentObject);
            }
            interactables.next();
        }
        return objectsInRange;
    }

    /**
     * Shooter prevents nearby enemies from moving if he is able to stun
     */
    public void stunEnemies() {
        if (shooter.getStunCooldown() >= 0)
            return;
        shooter.setStunCooldown(10);
        Enemy[] wave = programController.getWaveController().getWave();
        for (int i = 0; i < wave.length; i++) {
            if (!wave[i].isActive())
                continue;
            if (shooter.getDistanceTo(wave[i]) < 70)
                wave[i].setStunDuration(3.5);
        }
    }

    /**
     * Changes player sprites to how did you do in pe today? or XD
     */
    public void endGame(boolean won) {
        if (!won) {
            cook.setNewImage("src/main/resources/graphic/PlayerDefeated.png");
            shooter.setNewImage("src/main/resources/graphic/PlayerDefeated.png");
        } else {
            cook.setNewImage("src/main/resources/graphic/PlayerAscended.png");
            shooter.setNewImage("src/main/resources/graphic/PlayerAscended.png");
        }
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