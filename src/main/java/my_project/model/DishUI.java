package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class DishUI extends GraphicalObject {

    private double[] position;

    public DishUI(double px, double py) {
        position = new double[]{1415, 820};
        x = px;
        y = py;
    }

    @Override
    public void draw(DrawTool drawTool) {

        //UI Background
        drawTool.setLineWidth(5);
        drawTool.setCurrentColor(40,40,40,200);
        drawTool.drawRectangle(position[0],position[1],195, 50);
        drawTool.setCurrentColor(40,40,40,180);
        drawTool.drawFilledRectangle(position[0],position[1],195, 50);

        //UI Seperators
        drawTool.setLineWidth(3);
        drawTool.setCurrentColor(230,50,50,255);

        for(int i = 1; i < 5; i++)
            drawTool.drawLine(position[0] + 10 + 35 * i, position[1] + 15, position[0] + 10 + 35 * i, position[1] + 35);

        //UI Index
        drawTool.setCurrentColor(20,240,20,180);
        drawTool.drawRectangle(x+5.5,y,25, 25);
    }

    public double[] getPosition() {
        return position;
    }
}
