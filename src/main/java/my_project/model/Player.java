package my_project.model;

import my_project.model.Environment.CollidableEnvironment;

/**
 * Blueprint for both player instances
 */
public abstract class Player extends Entity {

    private boolean isBusy;
    private CollidableEnvironment closestObjectInRange;

    /**
     * @param filename Sprite the Player should apply
     * @param pX the start x-Position
     * @param pY the stard y-Position
     */
    public Player(String filename, double pX, double pY) {
        super(filename, pX, pY);
        isBusy = false;
    }

    public void setClosestObjectInRange(CollidableEnvironment pClosestObjectInRange) {
        this.closestObjectInRange = pClosestObjectInRange;
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public CollidableEnvironment getClosestObjectInRange() {
        return closestObjectInRange;
    }
}
