package my_project.model;

public class CollidableEnvironment extends Environment{
    private boolean isColliderActive;

    /**
     * Additionally activates the collider
     */
    public CollidableEnvironment(String filename, double pX, double pY) {
        super(filename, pX, pY);
        isColliderActive = true;
    }

    /**
     * Second constructor to create invisible environmentalObjects
     */
    public CollidableEnvironment(double pX, double pY, double pWidth, double pHeight) {
        super( pX, pY ,pWidth,pHeight);
        isColliderActive = true;
    }

    public boolean isColliderActive() {
        return isColliderActive;
    }
}
