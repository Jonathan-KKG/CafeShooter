package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Environment.CollidableEnvironment;

/**
 * The "Shooter" player
 */
public class Shooter extends Player {

    private boolean isRepairing; // inputs (w/ exception of repair-related inputs) will stop getting detected until isRepairing = false
    private List<CollidableEnvironment> closestObjectsInRange;

    /**
     * @param filename Sprite the Player should apply
     * @param pX the start x-Position
     * @param pY the stard y-Position
     */
    public Shooter(String filename, double pX, double pY) {
        super(filename, pX, pY);
        isRepairing = false;
    }

    public boolean isRepairing() {
        return isRepairing;
    }

    public void setRepairing(boolean repairing) {
        isRepairing = repairing;
    }
}
