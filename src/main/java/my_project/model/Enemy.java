package my_project.model;

/**
 * An enemy entity that is able to damage environment, follow the player and are able to lead to game over
 */
public abstract class Enemy extends Entity {
    protected String requiredDish;

    /**
     * Creates Enemy object of a specific type
     *
     * @param pX the start x-Position
     * @param pY the start y-Position
     */
    public Enemy(String filename, Double pX, Double pY) {
        super(filename, pX, pY);
        speed = 50;
    }

    public String getRequiredDish() {
        return requiredDish;
    }
}
