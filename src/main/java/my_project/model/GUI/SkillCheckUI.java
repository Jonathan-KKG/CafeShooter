package my_project.model.GUI;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;

public class SkillCheckUI extends GraphicalObject {
    private String type;

    public SkillCheckUI(double x, double y, String pType){
        type = pType;
        setX(x);
        setY(y);
    }

    public void interact(){

    }

    public void draw(DrawTool drawTool){
        drawTool.setLineWidth(2);
        drawTool.setCurrentColor(Color.BLACK);

        drawTool.drawRectangle(getX() - 150, getY() - 50, 200,100);
        drawTool.setCurrentColor(0,0,0,200);
        drawTool.drawFilledRectangle(getX() - 150, getY() - 50, 200,100);
    }
}
