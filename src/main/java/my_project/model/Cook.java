package my_project.model;

/**
 * The "Cook" player
 */
public class Cook extends Player {
    private boolean isCooking; // inputs (w/ exception of cooking-related inputs) will stop getting detected until isCooking = false

    /**
     * @param filename Sprite the Player should apply
     * @param pX the start x-Position
     * @param pY the stard y-Position
     */
    public Cook(String filename, double pX, double pY) {
        super(filename, pX, pY);
        isCooking = false;
    }

    public void setCooking(boolean cooking) {
        isCooking = cooking;
    }

    public boolean isCooking() {
        return isCooking;
    }
}
