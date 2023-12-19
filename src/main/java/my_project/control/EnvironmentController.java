package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.CollidableEnvironment;
import my_project.model.Environment;
import my_project.model.CookingStation;
import my_project.model.Table;

public class EnvironmentController {
    //Referenzen
    private List<Environment> environmentObjects = new List<>();
    private List<CollidableEnvironment> collidableEnvironmentObjects = new List<>();
    private List<CollidableEnvironment> cookingStations = new List<>();
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
                    environmentObjects.append(new Environment("grass.png", i * 500, j * 500));

            // cafe floor
            for (int i = 0; i < 8 * 32; i = i + 32)
                for (int j = 0; j < 20 * 32; j = j + 32)
                    environmentObjects.append(new Environment("floortile.png", kitchenOffset[0] + 32 + j, kitchenOffset[1] - i));
            // Collideables:
            for (int i = 0; i < 8 * 32; i = i + 32)
                collidableEnvironmentObjects.append(new CollidableEnvironment("leftwall.png", kitchenOffset[0] + 20, kitchenOffset[1] - i));

            // Calculating coordinates [#####-50%-/////]
            for (int i = 0; i < 20 * 32; i = i + 32)
                collidableEnvironmentObjects.append(new CollidableEnvironment("topwall.png", kitchenOffset[0] - 10 + 42 + i, kitchenOffset[1] + 148 - 12 * 32));
            collidableEnvironmentObjects.append(new CollidableEnvironment("wallturn1.png", kitchenOffset[0] + 20, kitchenOffset[1] + 148 - 12 * 32));
            collidableEnvironmentObjects.append(new CollidableEnvironment("wallturn2.png", kitchenOffset[0] - 384 + 33 * 32, kitchenOffset[1] + 148 - 12 * 32));
            for (int i = 0; i < 8 * 32; i = i + 32)
                collidableEnvironmentObjects.append(new CollidableEnvironment("rightwall.png", kitchenOffset[0] - 384 + 33 * 32, kitchenOffset[1] - i));

            // stove
            CollidableEnvironment stove = new CookingStation("stovetop.png", kitchenOffset[0] + 32, kitchenOffset[1] - 32 - 3 * 32, 1);
            collidableEnvironmentObjects.append(stove);
            cookingStations.append(stove);
            stove = new CookingStation("stovetop.png", kitchenOffset[0] + 400, kitchenOffset[1] - 32 - 3 * 32, 2);
            collidableEnvironmentObjects.append(stove);
            cookingStations.append(stove);

            // tabletop
            for (int i = 0; i < 6 * 64; i = i + 64) {
                Table table = new Table("tabletop.png", kitchenOffset[0] + 32 + i, kitchenOffset[1] - 5 * 32);
                collidableEnvironmentObjects.append(table);
                tableObjects.append(table);
            }
            for (int i = 32; i < 6 * 64; i = i + 64) {
                Table table = new Table("tabletop2.png", kitchenOffset[0] + 32 + i, kitchenOffset[1] - 5 * 32);
                collidableEnvironmentObjects.append(table);
                tableObjects.append(table);
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
            //It ain't a duplicate stoooooppppp T-T
            tableObjects.toFirst();
            while (tableObjects.hasAccess()) {
                viewController.draw(tableObjects.getContent());
                tableObjects.next();
            }
        } catch (Exception e) {
            System.out.println("Creating Environment object went wrong!");
        }
    }

    public List<Table> getTableObjects() {
        return tableObjects;
    }

    public List<CollidableEnvironment> getCollidableEnvironmentObjects() {
        return collidableEnvironmentObjects;
    }

    public List<CollidableEnvironment> getCookingStations() {
        return cookingStations;
    }
}