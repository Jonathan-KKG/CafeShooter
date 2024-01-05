package my_project.model.GUI.SkillChecks;

import KAGO_framework.view.DrawTool;
import my_project.model.GUI.UI;

/**
 * UI element that is created on cooking: "testing ('skill-checking') the player"  if you will
 */
public abstract class SkillCheckUI extends UI {
    protected double progress; // progress value between 0 and 1
    protected double increment; // by how much the progress should be incremented each time

    /**
     * Initializes UI Model
     *
     * @param pX    Starting location of the Model
     * @param pY    Starting location of the Model
     * @param dish SimpleClassName of what dish is being cooked
     */
    public SkillCheckUI(double pX, double pY, String dish) {
        super(pX, pY);
        width = 80;
        height = 200;
        progress = 0;
        myImage = createImage("src/main/resources/graphic/Dishes/" + dish + ".png");
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    public void draw(DrawTool drawTool){
        drawDefaultSCBorder(drawTool);
    }

    /**
     * This method should be used for drawing the border of all SkillChecks that require it
     * @param drawTool DrawTool that should be drawn with
     */
    protected void drawDefaultSCBorder(DrawTool drawTool) {
        super.drawDefaultBorder(drawTool, 2, startingPosition[0] - width * 0.5 + 16, startingPosition[1] - height * 0.9 - 32, width, height + 32);

        drawTool.drawImage(myImage, startingPosition[0] + 16 - myImage.getWidth() / 2d, startingPosition[1] - height * 0.9 - myImage.getHeight() + 8);

        // "Progress Bar"
        drawTool.setCurrentColor(120, 117, 117, 255);
        drawTool.drawRectangle(startingPosition[0] - width * 0.45 + 16 + width / 4, startingPosition[1] - height * 0.8, width * 0.4, height * 0.8);
        drawTool.setCurrentColor(120, 117, 117, 80);
        drawTool.drawFilledRectangle(startingPosition[0] - width * 0.45 + 16 + width / 4, startingPosition[1] - height * 0.8, width * 0.4, height * 0.8);
    }

    /**
     * increases the progress attribute and returns whether the skillcheck has not reached 100% completion or yes
     *
     * @return true if not finished (i.e. if progress was increased) and false otherwise
     */
    public boolean increaseProgress() {
        progress += increment;
        return !(progress >= 1);
    }

    /**
     * Required for some skillchecks
     * I.e. constantly moving parts
     * @param time Time passed since creation of the skillcheck (in seconds)
     */
    public abstract void updateSkillCheck(double time);
}
