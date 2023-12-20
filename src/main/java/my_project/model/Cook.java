package my_project.model;

public class Cook extends Player {
    private boolean isCooking;

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
