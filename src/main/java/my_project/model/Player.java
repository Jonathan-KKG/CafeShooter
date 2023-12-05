package my_project.model;

import KAGO_framework.view.DrawTool;

import java.awt.*;

public abstract class Player extends Entity {
    public Player(double pX, double pY){
        super(pX, pY);
    }

    public void draw(DrawTool drawTool) {
        drawTool.drawFilledCircle(x,y,20);
        drawTool.setCurrentColor(Color.RED);
        drawTool.drawCircle(x,y,10);
        drawTool.drawCircle(x,y,5);
    }

}
