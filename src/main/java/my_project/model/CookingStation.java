package my_project.model;

public class CookingStation extends CollidableEnvironment {

    private String cookableObjs;

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
