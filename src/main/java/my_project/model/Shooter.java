package my_project.model;

public class Shooter extends Player {

    private boolean isRepairing;

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
