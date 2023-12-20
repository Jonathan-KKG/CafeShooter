package my_project.model.GUI;

import KAGO_framework.view.DrawTool;


public class SkillCheckUI extends UI {
    private String type;
    private double progress; // progress value between 0 and 1

    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     */
    public SkillCheckUI(double pX, double pY, String pType) {
        super(pX, pY);
        type = pType;
        width = 80;
        height = 200;
        y = startingPosition[1];
        progress = 0;
    }

    /**
     * Draws the skillcheck respective to its Dishtype
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setLineWidth(2);

        // Standard UI Template
        setDrawToolColorToRGBABorderVal(drawTool);
        drawTool.drawRectangle(startingPosition[0] - width * 0.5 + 16, startingPosition[1] - height * 0.9, width, height);
        setDrawToolColorToRGBABackgroundVal(drawTool);
        drawTool.drawFilledRectangle(startingPosition[0] - width * 0.5 + 16, startingPosition[1] - height * 0.9, width, height);

        // vary for each Dishtype
        switch (type) {
            case "Spaghetti":
                drawOvenSC(drawTool);
                break;
            case "Coffee":
                drawOvenSC(drawTool);
                break;
        }
    }

    private void drawOvenSC(DrawTool drawTool) {
        // "Progress Bar"
        drawTool.setCurrentColor(120, 117, 117, 255);
        drawTool.drawRectangle(startingPosition[0] - width * 0.45 + 16 + width / 4, startingPosition[1] - height * 0.8, width * 0.4, height * 0.8);
        drawTool.setCurrentColor(120, 117, 117, 80);
        drawTool.drawFilledRectangle(startingPosition[0] - width * 0.45 + 16 + width / 4, startingPosition[1] - height * 0.8, width * 0.4, height * 0.8);

        // moveable part of the "Progress Bar"
        drawTool.setCurrentColor(255, 0, 0, 255);
        drawTool.drawFilledRectangle(startingPosition[0] - width * 0.45 + 16 + width / 4 + 2, y - height * 0.8 * progress + 2,width * 0.4 - 4, height * 0.8 * progress - 4);
    }

    /**
     * increases the progress attribute and returns whether the skillcheck has not reached 100% completion or yes
     *
     * @return true if not finished (i.e. if progress was increased) and false otherwise
     */
    public boolean increaseProgress() {
        progress += 0.1;

        return !(progress + 0.1 >= 1);
    }

    public String getType() {
        return type;
    }
}
