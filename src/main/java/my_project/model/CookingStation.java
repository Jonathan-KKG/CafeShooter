package my_project.model;

public class CookingStation extends CollidableEnvironment {

    private String cookableObjs;

    public CookingStation(String filename, double pX, double pY, int help) {
        super(filename, pX, pY);
        switch (filename) {
            case "stovetop.png":
                cookableObjs = "spaghet.png";
                break;
        }
        if (help == 1)
            cookableObjs = "spaghet.png";
        else if (help == 2)
            cookableObjs = "cawfee.png";
    }

    public String getCookableObjs() {
        return cookableObjs;
    }
}
