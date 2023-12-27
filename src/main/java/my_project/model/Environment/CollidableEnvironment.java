package my_project.model.Environment;

/**
 * Environment object that can be collided with and hence can be damaged and has HP
 */
public class CollidableEnvironment extends Environment {
    protected boolean isColliderActive;
    private int hp;

    /**
     * Additionally activates the collider
     */
    public CollidableEnvironment(String filename, double pX, double pY) {
        super(filename, pX, pY);
        isColliderActive = true;
        hp = 100;
    }

    /**
     * Increases HP of this
     */
    public void increaseHP(double amount) {
        hp += amount;
    }

    public int getHp() {
        return hp;
    }

    public boolean isColliderActive() {
        return isColliderActive;
    }

    public void setColliderActive(boolean colliderActive) {
        isColliderActive = colliderActive;
    }

}
