package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.view.DrawTool;
import my_project.model.Environment.CollidableEnvironment;

/**
 * The "Shooter" player
 */
public class Shooter extends Player {

    private List<CollidableEnvironment> objectsInRange;
    private double stunCooldown;
    private double maxStunCooldown;

    private double stunRadius;
    private double radiusCooldown;
    private boolean showStunRadius;

    /**
     * @param pX the start x-Position
     * @param pY the stard y-Position
     */
    public Shooter(double pX, double pY) {
        super("roeckrath", pX, pY);
        stunCooldown = 0;
        maxStunCooldown = 10;
        stunRadius = 70;
        showStunRadius = false;
    }

    /**
     * Draws the player and, if showStunRadius is true, draws the area in which enemies are affected by stuns
     * @param drawTool Required to draw
     */
    @Override
    public void draw(DrawTool drawTool) {
        super.draw(drawTool);

        if(!showStunRadius)
            return;
        drawTool.setCurrentColor(0,0,0,50);
        drawTool.drawFilledCircle(x + getWidth() / 2, y + getHeight() / 2, stunRadius);
        drawTool.setCurrentColor(0,0,0,100);
        drawTool.drawCircle(x + getWidth() / 2, y + getHeight() / 2, stunRadius);
    }

    /**
     * sets stun radius to true or false
     * @param show whether the radius is supposed to be activated or deactivated (true = activated)
     */
    public void isStunRadius(boolean show){
        if(!show) {
            showStunRadius = false;
            radiusCooldown = 0;
            return;
        }

        showStunRadius = true;
        radiusCooldown = 0.4;
    }

    public double getRadiusCooldown() {
        return radiusCooldown;
    }

    public void setRadiusCooldown(double pRadiusCooldown) {
        radiusCooldown = pRadiusCooldown;
    }

    public List<CollidableEnvironment> getObjectsInRange() {
        return objectsInRange;
    }

    public void setObjectsInRange(List<CollidableEnvironment> pObjectsInRange) {
        objectsInRange = pObjectsInRange;
    }

    public double getStunCooldown() {
        return stunCooldown;
    }

    public void setStunCooldown(double stunCooldown) {
        this.stunCooldown = stunCooldown;
    }

    public double getMaxStunCooldown() {
        return maxStunCooldown;
    }

    public double getStunRadius() {
        return stunRadius;
    }
}
