package my_project.model.Environment;

public class Bin extends CollidableEnvironment{

    /**
     * Additionally activates the collider
     *
     * @param pX Starting positon
     * @param pY Starting positon
     */
    public Bin(double pX, double pY) {
        super("bin", pX, pY);
    }
}
