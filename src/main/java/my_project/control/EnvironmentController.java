package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Dishes.Dish;
import my_project.model.Environment.*;
import my_project.model.Environment.Storages.*;
import my_project.model.Shooter;

import java.lang.reflect.InvocationTargetException;

/**
 * Controls all Environment objects by creating, storing, drawing and, if required, enabling influence (through methods) on them.
 */
public class EnvironmentController {
    //Referenzen
    private List<Environment> environmentObjects = new List<>();
    private List<CollidableEnvironment> collidableEnvironmentObjects = new List<>();
    private List<CollidableEnvironment> interactableEnvironmentObjects = new List<>();

    //Attribute
    private int[] kitchenOffset = {(int) (1080 * 0.85) - 480, (int) (1920 * 0.85) - 785};

    /**
     * Creates and draws all Environment objects
     *
     * @param viewController Required to draw EnvironmentObjects
     */
    public EnvironmentController(ViewController viewController) {
        createObjects(viewController);
    }

    /**
     * Calls necessary methods to repair an object
     *
     * @param shooter        Required to get the objects that should be repaired & set busy state
     * @param uiController   Required to update UI
     * @param viewController Required to update UI
     */
    public void updateEnvironments(Shooter shooter, double dt, ViewController viewController, UIController uiController) {
        if (!shooter.isBusy())
            return;
        boolean allObjsRepaired = true;
        List<CollidableEnvironment> objsInRange = shooter.getObjectsInRange();

        objsInRange.toFirst();
        while (objsInRange.hasAccess()) {
            CollidableEnvironment env = objsInRange.getContent();

            if (env.getHp() < 100) {
                env.increaseHP(dt * 100);
                allObjsRepaired = false;
            }
            if (!env.isColliderActive() && env.getHp() >= 100) {
                env.setColliderActive(true);
                viewController.draw(env);
            }
            objsInRange.next();
        }
        if (allObjsRepaired)
            shooter.setBusy(false);
        uiController.updateHPBars(viewController);
    }

    /**
     * creates all Environment objects, adds them to their respective lists and draws them
     *
     * @param viewController Required to draw Objects
     */
    private void createObjects(ViewController viewController) {
        try {
            // grass
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    environmentObjects.append(new Environment("grass", i * 500, j * 500));

            // cafe floor
            for (int i = 0; i < 8 * 32; i = i + 32)
                for (int j = 0; j < 20 * 32; j = j + 32)
                    environmentObjects.append(new Environment("floortile", kitchenOffset[0] + 32 + j, kitchenOffset[1] - i));

            // Walls and corners
            for (int i = 0; i < 8 * 32; i = i + 32)
                collidableEnvironmentObjects.append(new CollidableEnvironment("leftwall", kitchenOffset[0] + 20, kitchenOffset[1] - i));
            for (int i = 0; i < 8 * 32; i = i + 32)
                collidableEnvironmentObjects.append(new CollidableEnvironment("rightwall", kitchenOffset[0] - 384 + 33 * 32, kitchenOffset[1] - i));
            for (int i = 0; i < 20 * 32; i = i + 32)
                collidableEnvironmentObjects.append(new CollidableEnvironment("topwall", kitchenOffset[0] - 10 + 42 + i, kitchenOffset[1] + 148 - 12 * 32));
            collidableEnvironmentObjects.append(new CollidableEnvironment("wallturn1", kitchenOffset[0] + 20, kitchenOffset[1] + 148 - 12 * 32));
            collidableEnvironmentObjects.append(new CollidableEnvironment("wallturn2", kitchenOffset[0] - 384 + 33 * 32, kitchenOffset[1] + 148 - 12 * 32));

            // CookingStations
            CollidableEnvironment cookingStation = new Stove(kitchenOffset[0] + 32, kitchenOffset[1] - 32 - 3 * 32);
            collidableEnvironmentObjects.append(cookingStation);
            interactableEnvironmentObjects.append(cookingStation);
            cookingStation = new WaffleIron(kitchenOffset[0] + 400, kitchenOffset[1] - 32 - 3 * 32);
            collidableEnvironmentObjects.append(cookingStation);
            interactableEnvironmentObjects.append(cookingStation);
            cookingStation = new CoffeeMachine(kitchenOffset[0] + 368, kitchenOffset[1] - 32 - 3 * 32);
            collidableEnvironmentObjects.append(cookingStation);
            interactableEnvironmentObjects.append(cookingStation);


            // tabletops
            for (int i = 0; i < 6 * 64; i = i + 64) {
                Table table = new Table("tabletop", kitchenOffset[0] + 32 + i, kitchenOffset[1] - 5 * 32-64);
                collidableEnvironmentObjects.append(table);
                interactableEnvironmentObjects.append((table));
            }
            for (int i = 32; i < 6 * 64; i = i + 64) {
                Table table = new Table("tabletop2", kitchenOffset[0] + 32 + i, kitchenOffset[1] - 5 * 32-64);
                collidableEnvironmentObjects.append(table);
                interactableEnvironmentObjects.append((table));
            }

            Class[] storageTypes = new Class[]{AppleStorage.class, BaconStorage.class, CheeseStorage.class, ChocolateStorage.class, CoffeePowderStorage.class, CookieStorage.class, CreamStorage.class, EggStorage.class, FlourStorage.class, IceCreamStorage.class, SpaghettiStorage.class, StraberryStorage.class};

            // Storages
            Storage storage;
            for (int i = 0; i < storageTypes.length; i++) {
                try {
                    storage = (Storage) storageTypes[i].getDeclaredConstructor(double.class, double.class).newInstance(kitchenOffset[0] + 32 + 32 * i, kitchenOffset[1]);
                    collidableEnvironmentObjects.append(storage);
                    interactableEnvironmentObjects.append((storage));
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
                    e.printStackTrace();
                }
            }

            // draws created objects
            environmentObjects.toFirst();
            while (environmentObjects.hasAccess()) {
                viewController.draw(environmentObjects.getContent());
                environmentObjects.next();
            }
            collidableEnvironmentObjects.toFirst();
            while (collidableEnvironmentObjects.hasAccess()) {
                viewController.draw(collidableEnvironmentObjects.getContent());
                collidableEnvironmentObjects.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * reduces hp of an CollidableEnvironment object and deactivates it if hp is zero or lower
     *
     * @param env            The Object that should be damaged
     * @param dt             Time between this and last frame to reduce hp equally across all frames
     * @param viewController Required to stop drawing the object if and when it deactivates
     */
    public void damage(CollidableEnvironment env, double dt, ViewController viewController, UIController uiController) {
        env.increaseHP(-dt * 1);
        uiController.updateHPBars(viewController);

        if (env.getHp() > 0)
            return;
        env.setColliderActive(false);
        viewController.removeDrawable(env);
        if (env instanceof Table)
            ((Table) env).emptyQueue(viewController);
    }

    /**
     * Enqueues a dish to a table and adjusts its position
     *
     * @param dish  The dish that should be added
     * @param table the table that should be added to
     */
    public void addToTable(Dish dish, Table table) {
        table.addToTable(dish);
        dish.setX(table.getX());
        dish.setY(table.getY());
    }

    public List<CollidableEnvironment> getCollidableEnvironmentObjects() {
        return collidableEnvironmentObjects;
    }

    public List<CollidableEnvironment> getInteractableEnvironmentObjects() {
        return interactableEnvironmentObjects;
    }
}