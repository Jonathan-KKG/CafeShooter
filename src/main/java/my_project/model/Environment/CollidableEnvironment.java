package my_project.model.Environment;

/**
 * Environment object that can be collided with and hence can be damaged and has HP
 */
public class CollidableEnvironment extends Environment {
    private boolean isColliderActive;
    private int hp;
    private String legacyFilename;

    /**
     * Additionally activates the collider
     */
    public CollidableEnvironment(String filename, double pX, double pY) {
        super(filename, pX, pY);
        legacyFilename = filename;
        isColliderActive = true;
        hp = 100;
    }

    /**
     * reduces hp of the Barriers. If hp is <=0 it stops colliding & switches img
     */
    public void reduceHP(double dt) {
        hp -= dt;
        if (hp <= 0) {
            isColliderActive = false;
            myImage = null;
        }
    }

    /**
     * Increases HP of this and updates the sprite if collider was inactive
     */
    public void increaseHP() {
        if (!isColliderActive) {
            isColliderActive = true;
            setNewImage("src/main/resources/graphic/Environment/" + legacyFilename + ".png");
        }

        if (hp < 100)
            hp += 5;
    }

    public int getHp() {
        return hp;
    }

    public boolean isColliderActive() {
        return isColliderActive;
    }
}
