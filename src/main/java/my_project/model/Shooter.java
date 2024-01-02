package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Environment.CollidableEnvironment;

/**
 * The "Shooter" player
 */
public class Shooter extends Player {

    private List<CollidableEnvironment> objectsInRange;

    /**
     * @param pX the start x-Position
     * @param pY the stard y-Position
     */
    public Shooter(double pX, double pY) {
        super("roeckrath", pX, pY);
    }

    public List<CollidableEnvironment> getObjectsInRange() {
        return objectsInRange;
    }

    public void setObjectsInRange(List<CollidableEnvironment> pObjectsInRange) {
        objectsInRange = pObjectsInRange;
    }
}
