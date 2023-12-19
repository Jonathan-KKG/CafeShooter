package my_project.model;

public class CookingStation extends CollidableEnvironment {

    private String cookableObjs;

    /**
     * Creates a new CookingStation
     * @param filename image name of the cooking station
     * @param pX Starting x pos
     * @param pY Starting y pos
     */
    public CookingStation(String filename, double pX, double pY) {
        super(filename, pX, pY);
        switch (filename) {
            case "stovetop.png":
                cookableObjs = "spaghet.png";
                break;
        }
    }

    public String getCookableObjs() {
        return cookableObjs;
    }
}
