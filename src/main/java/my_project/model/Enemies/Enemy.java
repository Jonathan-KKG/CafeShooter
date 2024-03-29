package my_project.model.Enemies;

import my_project.model.Entity;

/**
 * An enemy entity that is able to damage environment, follow the player and are able to lead to game over
 */
public abstract class Enemy extends Entity {
    protected String requiredDish;
    private boolean isActive;
    protected double stunDuration;

    /**
     * Creates Enemy object of a specific type
     *
     * @param pX the start x-Position
     * @param pY the start y-Position
     */
    public Enemy(String filename, double pX, double pY) {
        super("Enemies/" + filename, pX, pY);
        speed = 25;
        isActive = false;
        stunDuration = 0;
    }

    public String getRequiredDish() {
        return requiredDish;
    }

    public boolean isActive() {
        return isActive;
    }

    public void activate() {
        isActive = true;
    }

    public double getStunDuration() {
        return stunDuration;
    }

    public void setStunDuration(double stunDuration) {
        this.stunDuration = stunDuration;
    }
}
