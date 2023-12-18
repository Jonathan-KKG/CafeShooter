package my_project.model.GUI;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class DishUI extends GraphicalObject {

    private double[] position;

    public DishUI(double px, double py) {
        position = new double[]{1300, 820};
        x = px;
        y = py;
    }

    @Override
    public void draw(DrawTool drawTool) {

        //UI Background
        drawTool.setLineWidth(5);
        drawTool.setCurrentColor(40,40,40,200);
        drawTool.drawRectangle(position[0]+5,position[1],5 * 45+ 45d/2, 100);
        drawTool.setCurrentColor(40,40,40,180);
        drawTool.drawFilledRectangle(position[0]+5,position[1],5 * 45 + 45d/2, 80);

        //UI Seperators
        drawTool.setLineWidth(3);
        drawTool.setCurrentColor(230,50,50,255);

        for(int i = 1; i < 5; i++)
            drawTool.drawLine(position[0]-60 + 75 + 45 * i, position[1]-30 + 45, position[0]-60 + 75 + 45 * i, position[1]-30 + 75);

        // lets fix this later.
        //UI Index
        drawTool.setCurrentColor(20,240,20,180);
        drawTool.drawRectangle(x,y,40, 40);
    }

    public double[] getPosition() {
        return position;
    }
}
