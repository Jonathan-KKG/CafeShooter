package my_project.model;

public abstract class Item extends Entity {
    private String type;

    /**
     * @param filename the Immage that should be Drawn.
     * @param pX       the start x-Position
     * @param pY       the stard y-Position
     */
    public Item(String filename, double pX, double pY) {
        super(filename, pX, pY);
        type = filename;
    }

    /**
     * This constructor should be used for dishes as they are in a subfolder and filename != represent type.
     * @param filename the Immage that should be Drawn.
     * @param pX       the start x-Position
     * @param pY       the stard y-Position
     */
    public Item(String filename, double pX, double pY, String pType) {
        super(filename, pX, pY);
        type = pType;
    }

    public String getType() {
        return type;
    }
}
