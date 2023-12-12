package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class DishUI extends GraphicalObject {
    public DishUI(double px, double py) {
        x = px;
        y = py;
    }

    @Override
    public void draw(DrawTool drawTool) {

        //UI Background
        drawTool.setLineWidth(10);
        drawTool.setCurrentColor(40,40,40,255);
        drawTool.drawRectangle(1415,820,195, 50);
        drawTool.setCurrentColor(40,40,40,200);
        drawTool.drawFilledRectangle(1415,820,195, 50);

        //UI Seperators
        drawTool.setLineWidth(3);
        drawTool.setCurrentColor(230,50,50,255);
        drawTool.drawLine(1460,835,1460,855);
        drawTool.drawLine(1495,835,1495,855);
        drawTool.drawLine(1530,835,1530,855);
        drawTool.drawLine(1565,835,1565,855);

        //UI Index
        drawTool.setCurrentColor(20,240,20,255);
        drawTool.drawRectangle(x,y,35, 25);
    }
}
