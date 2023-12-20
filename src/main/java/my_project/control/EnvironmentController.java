package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.*;

/**
 * Controls all Environment objects by creating, storing, drawing and, if required, enabling influence (through methods) on them.
 */
public class EnvironmentController {
    //Referenzen
    private List<Environment> environmentObjects = new List<>();
    private List<CollidableEnvironment> collidableEnvironmentObjects = new List<>();
    private List<CollidableEnvironment> interactableEnvironmentObjects = new List<>();
    private List<Table> tableObjects = new List<>();

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
     * creates all Environment objects and adds them to environmentObjects list
     *
     * @param viewController Required to draw Objects
     */
    private void createObjects(ViewController viewController) {
        try {
            // grasss
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    environmentObjects.append(new Environment("grass", i * 500, j * 500));

            // cafe floor
            for (int i = 0; i < 8 * 32; i = i + 32)
                for (int j = 0; j < 20 * 32; j = j + 32)
                    environmentObjects.append(new Environment("floortile", kitchenOffset[0] + 32 + j, kitchenOffset[1] - i));
            // Collideables:
            for (int i = 0; i < 8 * 32; i = i + 32)
                collidableEnvironmentObjects.append(new CollidableEnvironment("leftwall", kitchenOffset[0] + 20, kitchenOffset[1] - i));

            // Calculating coordinates [#####-50%-/////]
            for (int i = 0; i < 20 * 32; i = i + 32)
                collidableEnvironmentObjects.append(new CollidableEnvironment("topwall", kitchenOffset[0] - 10 + 42 + i, kitchenOffset[1] + 148 - 12 * 32));
            collidableEnvironmentObjects.append(new CollidableEnvironment("wallturn1", kitchenOffset[0] + 20, kitchenOffset[1] + 148 - 12 * 32));
            collidableEnvironmentObjects.append(new CollidableEnvironment("wallturn2", kitchenOffset[0] - 384 + 33 * 32, kitchenOffset[1] + 148 - 12 * 32));
            for (int i = 0; i < 8 * 32; i = i + 32)
                collidableEnvironmentObjects.append(new CollidableEnvironment("rightwall", kitchenOffset[0] - 384 + 33 * 32, kitchenOffset[1] - i));

            // stove
            CollidableEnvironment stove = new CookingStation("stovetop", kitchenOffset[0] + 32, kitchenOffset[1] - 32 - 3 * 32, 1);
            collidableEnvironmentObjects.append(stove);
            interactableEnvironmentObjects.append(stove);
            stove = new CookingStation("stovetop", kitchenOffset[0] + 400, kitchenOffset[1] - 32 - 3 * 32, 2);
            collidableEnvironmentObjects.append(stove);
            interactableEnvironmentObjects.append(stove);

            // tabletop
            for (int i = 0; i < 6 * 64; i = i + 64) {
                Table table = new Table("tabletop", kitchenOffset[0] + 32 + i, kitchenOffset[1] - 5 * 32);
                collidableEnvironmentObjects.append(table);
                tableObjects.append(table);
                interactableEnvironmentObjects.append((table));
            }
            for (int i = 32; i < 6 * 64; i = i + 64) {
                Table table = new Table("tabletop2", kitchenOffset[0] + 32 + i, kitchenOffset[1] - 5 * 32);
                collidableEnvironmentObjects.append(table);
                tableObjects.append(table);
                interactableEnvironmentObjects.append((table));
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
            System.out.println("Creating Environment object went wrong!");
        }
    }

    /**
     * Calls necessary methods to repair an object
     * @param envObj the object to be repaired
     * @param uiController Required to update UI
     * @param viewController Required to update UI
     */
    public void repair(CollidableEnvironment envObj, UIController uiController, ViewController viewController){
        if(envObj != null) {
            envObj.increaseHP();
            uiController.updateHPBars(viewController);
        }
    }

    public List<CollidableEnvironment> getCollidableEnvironmentObjects() {
        return collidableEnvironmentObjects;
    }

    public List<CollidableEnvironment> getInteractableEnvironmentObjects() {
        return interactableEnvironmentObjects;
    }
}