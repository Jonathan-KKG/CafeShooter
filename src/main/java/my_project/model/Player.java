package my_project.model;

/**
 * Blueprint for both player instances
 */
public abstract class Player extends Entity {

    private CollidableEnvironment closestObjectInRange;

    /**
     * @param filename Sprite the Player should apply
     * @param pX the start x-Position
     * @param pY the stard y-Position
     */
    public Player(String filename, double pX, double pY) {
        super(filename, pX, pY);
    }

    public void setClosestObjectInRange(CollidableEnvironment pClosestObjectInRange) {
        this.closestObjectInRange = pClosestObjectInRange;
    }

    public CollidableEnvironment getClosestObjectInRange() {
        return closestObjectInRange;
    }
}
