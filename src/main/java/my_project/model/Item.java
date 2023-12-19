package my_project.model;

import KAGO_framework.view.DrawTool;

public abstract class Item extends Entity {
    private String type;
    /**
     * @param filename the Immage that should be Drawn.
     * @param pX       the start x-Position
     * @param pY       the stard y-Position
     */
    public Item(String filename, double pX, double pY,String pType) {
        super(filename, pX, pY);
        type = pType;
    }

    /**
     * Drawes the Enemy
     *
     * @param drawTool the tool used to draw things
     */
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(myImage, x, y);
    }

    public String getType() {
        return type;
    }
}
