package my_project.model;

import KAGO_framework.view.DrawTool;

import java.awt.image.BufferedImage;

public abstract class Player extends Entity {

    private CollidableEnvironment closestObjectInRange;

    /**
     * @param pX the start x-Position
     * @param pY the stard y-Position
     */
    public Player(String filename, double pX, double pY) {
        super(filename, pX, pY);
    }

    /**
     * Drawes the Enemy
     *
     * @param drawTool the tool used to draw things
     */
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(myImage, x, y);
    }

    public BufferedImage getImage() {
        return myImage;
    }

    public void setClosestObjectInRange(CollidableEnvironment pClosestObjectInRange) {
        this.closestObjectInRange = pClosestObjectInRange;
    }

    public CollidableEnvironment getClosestObjectInRange() {
        return closestObjectInRange;
    }
}
