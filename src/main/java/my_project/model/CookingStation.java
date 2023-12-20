package my_project.model;

/**
 * A collidableEnvironment that is able to create Dishes.
 */
public class CookingStation extends CollidableEnvironment {

    private String cookableObjs;

    /**
     * Creates a new CookingStation
     * @param filename image name of the cooking station
     * @param pX Starting x pos
     * @param pY Starting y pos
     */
    public CookingStation(String filename, double pX, double pY, int help) {
        super(filename, pX, pY);
        switch (filename) {
            case "stovetop.png":
                cookableObjs = "Spaghetti";
                break;
        }
        if (help == 1)
            cookableObjs = "Spaghetti";
        else if (help == 2)
            cookableObjs = "Coffee";
    }

    public String getCookableObjs() {
        return cookableObjs;
    }
}
