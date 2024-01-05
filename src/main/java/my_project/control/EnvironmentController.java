package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Dishes.Dish;
import my_project.model.Environment.*;
import my_project.model.Environment.Storages.*;
import my_project.model.Shooter;

/**
 * Controls all Environment objects by creating, storing, drawing and, if required, enabling influence (through methods) on them.
 */
public class EnvironmentController {
    //Referenzen
    private List<Environment> environmentObjects;
    private List<CollidableEnvironment> collidableEnvironmentObjects;
    private List<CollidableEnvironment> interactableEnvironmentObjects;
    private CollidableEnvironment[][] unlockableSets;

    //Attribute
    private int[] kitchenOffset = {(int) (1080 * 0.85) / 2, (int) (1920 * 0.85) - 785};
    private int[] bottomStorageOffset = {kitchenOffset[0] + 32 * 8, kitchenOffset[1] };          // Flour, Egg, strawberry and Icecream
    private int[] leftStorageOffset = {kitchenOffset[0] + 32, kitchenOffset[1] - 32 * 3};                     // chocolate, cheese, apple
    private int[] rightStorageOffset = {kitchenOffset[0] + 32 * 18, kitchenOffset[1] - 32 * 4};                    // CoffeePowder, cream, Bacon and spaghetti

    /**
     * Creates and draws all Environment objects
     *
     * @param viewController Required to draw EnvironmentObjects
     */
    public EnvironmentController(ViewController viewController) {
        environmentObjects = new List<>();
        collidableEnvironmentObjects = new List<>();
        interactableEnvironmentObjects = new List<>();
        unlockableSets = new CollidableEnvironment[7][];

        createObjects(viewController);

        //DEBUG CODE
        // for (int i = 0; i < unlockableSets.length; i++) {
        //     activateNextSetOfCooking(viewController);
        // }
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
                for (int j = 0; j < 18 * 32; j = j + 32)
                    environmentObjects.append(new Environment("floortile", kitchenOffset[0] + 32 + j, kitchenOffset[1] - i));

            // Walls and corners
            for (int i = 0; i < 8 * 32; i = i + 32)
                collidableEnvironmentObjects.append(new CollidableEnvironment("leftwall", kitchenOffset[0] + 20, kitchenOffset[1] - i));
            for (int i = 0; i < 8 * 32; i = i + 32)
                collidableEnvironmentObjects.append(new CollidableEnvironment("rightwall", kitchenOffset[0] - 384 + 31 * 32, kitchenOffset[1] - i));

            for (int i = 0; i < 3 * 32; i = i + 32)
                collidableEnvironmentObjects.append(new CollidableEnvironment("topwall", kitchenOffset[0] - 10 + 42 + i, kitchenOffset[1] + 148 - 12 * 32));
            for (int i = 4 * 32; i < 13 * 32; i = i + 32)
                collidableEnvironmentObjects.append(new CollidableEnvironment("topwall", kitchenOffset[0] - 10 + 42 + i, kitchenOffset[1] + 148 - 12 * 32));
            for (int i = 14 * 32; i < 18 * 32; i = i + 32)
                collidableEnvironmentObjects.append(new CollidableEnvironment("topwall", kitchenOffset[0] - 10 + 42 + i, kitchenOffset[1] + 148 - 12 * 32));

            collidableEnvironmentObjects.append(new CollidableEnvironment("wallturn1", kitchenOffset[0] + 20, kitchenOffset[1] + 148 - 12 * 32));
            collidableEnvironmentObjects.append(new CollidableEnvironment("wallturn2", kitchenOffset[0] - 384 + 31 * 32, kitchenOffset[1] + 148 - 12 * 32));

            // Interactles (CookingStations, storages and bin)
            CollidableEnvironment createdObject = new WaffleIron(kitchenOffset[0] + 32 * 9, kitchenOffset[1] - 32 - 3 * 32);
            collidableEnvironmentObjects.append(createdObject);
            interactableEnvironmentObjects.append(createdObject);

            createdObject = new FlourStorage(bottomStorageOffset[0] + 32 * 0, bottomStorageOffset[1]);
            collidableEnvironmentObjects.append(createdObject);
            interactableEnvironmentObjects.append(createdObject);

            createdObject = new EggStorage(bottomStorageOffset[0] + 32 * 1, bottomStorageOffset[1]);
            collidableEnvironmentObjects.append(createdObject);
            interactableEnvironmentObjects.append(createdObject);

            createdObject = new Bin(rightStorageOffset[0], rightStorageOffset[1] - 32 * 2);
            collidableEnvironmentObjects.append(createdObject);
            interactableEnvironmentObjects.append(createdObject);

            createUnlockables();

            // tabletops
            Table table = new Table("tabletop", kitchenOffset[0] + 32 * 4, kitchenOffset[1] - 7 * 32 - 12);
            collidableEnvironmentObjects.append(table);
            interactableEnvironmentObjects.append((table));

            table = new Table("tabletop2", kitchenOffset[0] + 32 * 14, kitchenOffset[1] - 7 * 32 - 12);
            collidableEnvironmentObjects.append(table);
            interactableEnvironmentObjects.append((table));

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
     * fills the unlockableSets array by creating CookingStations and Storages
     */
    private void createUnlockables(){
        // First set
        unlockableSets[0] = new CollidableEnvironment[]{new StrawberryStorage(bottomStorageOffset[0] + 32 * 2, bottomStorageOffset[1])};

        // Second set
        unlockableSets[1] = new CollidableEnvironment[]{new CoffeeMachine(kitchenOffset[0] + 32 * 10, kitchenOffset[1] - 32 - 32 * 3),
                                                        new CoffeePowderStorage(rightStorageOffset[0], rightStorageOffset[1] + 32 * 0)};

        // etc..
        unlockableSets[2] = new CollidableEnvironment[]{new IceCreamStorage(bottomStorageOffset[0] + 32 * 3, bottomStorageOffset[1])};

        unlockableSets[3] = new CollidableEnvironment[]{new Oven(kitchenOffset[0] + 32 * 11, kitchenOffset[1] - 32 - 32 * 3),
                                                        new AppleStorage(leftStorageOffset[0], leftStorageOffset[1])};

        unlockableSets[4] = new CollidableEnvironment[]{new ChocolateStorage(leftStorageOffset[0], leftStorageOffset[1] + 32)};
        unlockableSets[5] = new CollidableEnvironment[]{new CheeseStorage(leftStorageOffset[0], leftStorageOffset[1] + 32 * 2)};

        unlockableSets[6] = new CollidableEnvironment[]{new Stove(kitchenOffset[0] + 32 * 8, kitchenOffset[1] - 32 - 3 * 32),
                                                        new WhippedCreamStorage(rightStorageOffset[0], rightStorageOffset[1] + 32 * 1),
                                                        new BaconStorage(rightStorageOffset[0], rightStorageOffset[1] + 32 * 2),
                                                        new SpaghettiStorage(rightStorageOffset[0], rightStorageOffset[1] + 32 * 3)};
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
    public void addToTable(Dish dish, Table table, ViewController viewController) {
        table.addToTable(dish, viewController);
    }

    /**
     * Draws and appends all objects of the next occupied index of unlockableSets[]
     * @param viewController Required to draw
     */
    public void activateNextSetOfCooking(ViewController viewController){
        for (int i = 0; i < unlockableSets.length; i++) {
            if(unlockableSets[i] == null)
                continue;

            for (int j = 0; j < unlockableSets[i].length; j++) {
                collidableEnvironmentObjects.append(unlockableSets[i][j]);
                interactableEnvironmentObjects.append(unlockableSets[i][j]);
                viewController.draw(unlockableSets[i][j]);
            }
            unlockableSets[i] = null;
            i = unlockableSets.length;
        }
    }

    public List<CollidableEnvironment> getCollidableEnvironmentObjects() {
        return collidableEnvironmentObjects;
    }

    public List<CollidableEnvironment> getInteractableEnvironmentObjects() {
        return interactableEnvironmentObjects;
    }
}