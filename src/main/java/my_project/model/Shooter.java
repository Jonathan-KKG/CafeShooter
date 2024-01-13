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

    private double indicatorRadius;
    private double indicatorDisplayTime;
    private boolean isIndicatorActive;

    /**
     * @param pX the start x-Position
     * @param pY the stard y-Position
     */
    public Shooter(double pX, double pY) {
        super("roeckrath", pX, pY);
        stunCooldown = 0;
        maxStunCooldown = 10;
        indicatorRadius = 70;
        isIndicatorActive = false;
    }

    /**
     * Draws the player and, if isIndicatorActive, draws the area in which enemies are affected by stuns
     * @param drawTool Required to draw
     */
    @Override
    public void draw(DrawTool drawTool) {
        super.draw(drawTool);

        if(!isIndicatorActive)
            return;

        // stun area
        drawTool.setCurrentColor(0,0,0,50);
        drawTool.drawFilledCircle(x + getWidth() / 2, y + getHeight() / 2, indicatorRadius);
        drawTool.setCurrentColor(0,0,0,100);
        drawTool.drawCircle(x + getWidth() / 2, y + getHeight() / 2, indicatorRadius);
    }

    /**
     * sets indicator to to true or false
     * @param show whether the indicator is supposed to be activated or deactivated (true = activated)
     */
    public void setIndicator(boolean show){
        if(!show) {
            isIndicatorActive = false;
            indicatorDisplayTime = 0;
        } else {
            isIndicatorActive = true;
            indicatorDisplayTime = 0.4;
        }
    }

    public double getIndicatorDisplayTime() {
        return indicatorDisplayTime;
    }

    public void setIndicatorDisplayTime(double pIndicatorDisplayTime) {
        indicatorDisplayTime = pIndicatorDisplayTime;
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

    public double getIndicatorRadius() {
        return indicatorRadius;
    }
}
