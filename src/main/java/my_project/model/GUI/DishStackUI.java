package my_project.model.GUI;

import KAGO_framework.view.DrawTool;

import java.awt.*;

public class DishStackUI extends UI {
    private int dishStackAmount;

    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     */
    public DishStackUI(double pX, double pY) {
        super(816, 797);

    }

    /**
     * adds 1 to dishStackAmount if boolean is positive removes 1 if negative
     * @param increase used to calculate properly
     */
    public void setDishStackAmount(boolean increase) {
        if (increase) {
            dishStackAmount++;
            return;
        }
        dishStackAmount--;

    }

    @Override
    public void draw(DrawTool drawTool) {
        if(dishStackAmount<10) {
            drawTool.setCurrentColor(255,102,178,230);
            drawTool.drawFilledRectangle(x +14 , y - 17, 14, 16);
        }else{
            drawTool.setCurrentColor(255,102,178,230);
            drawTool.drawFilledRectangle(x +14 , y - 17, 21, 16);
        }
        drawTool.setCurrentColor(Color.green);
        drawTool.drawText(x +17.5 , y -4, String.valueOf(dishStackAmount));
    }



}
