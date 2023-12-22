package my_project.model;

/**
 * Items that can be held by the cook and interacted with
 */
public abstract class Item extends Entity {

    /**
     * @param filename the Immage that should be Drawn.
     * @param pX       the start x-Position
     * @param pY       the stard y-Position
     */
    public Item(String filename, double pX, double pY) {
        super(filename, pX, pY);
    }

}
