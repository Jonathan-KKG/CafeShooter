package my_project.model.GUI;

import KAGO_framework.view.DrawTool;


public class DishUI extends UI {

    /**
     * Initializes DishUI and sets starting position for the moveable UI Index
     */
    public DishUI(double pX, double pY) {
        super(pX, pY);
        x = pX + 17;
        y = pY + 14;

    }

    @Override
    public void draw(DrawTool drawTool) {

        //UI Background
        drawTool.setLineWidth(5);
        setDrawToolColorToRGBABackgroundVal(drawTool);
        drawTool.drawRectangle(absolutePosition[0] + 5, absolutePosition[1], 5 * 45 + 45d / 2, 100);
        setDrawToolColorToRGBABorderVal(drawTool);
        drawTool.drawFilledRectangle(absolutePosition[0] + 5, absolutePosition[1], 5 * 45 + 45d / 2, 80);

        //UI Seperators
        drawTool.setLineWidth(3);
        drawTool.setCurrentColor(230, 50, 50, 255);

        for (int i = 1; i < 5; i++)
            drawTool.drawLine(absolutePosition[0] - 60 + 75 + 45 * i, absolutePosition[1] - 30 + 45, absolutePosition[0] - 60 + 75 + 45 * i, absolutePosition[1] - 30 + 75);

        //UI Index
        drawTool.setCurrentColor(20, 240, 20, 180);
        drawTool.drawRectangle(x, y, 40, 40);
    }

    public double[] getPosition() {
        return absolutePosition;
    }
}
