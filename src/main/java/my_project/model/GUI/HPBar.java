package my_project.model.GUI;

import KAGO_framework.view.DrawTool;
import my_project.model.CollidableEnvironment;
import my_project.model.UI;

public class HPBar extends UI {

    private double health;
    CollidableEnvironment environment;

    /**
     * Initializes UI Model
     * @param env the environment object of which the HPBar is displaying its hp
     */
    public HPBar(CollidableEnvironment env) {
        super(env.getX()+env.getWidth()/4, env.getY()+env.getHeight()/4);
        environment = env;
        x = startingPosition[0];
        y = startingPosition[1];
        health = 100;
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        setDrawToolColorToRGBABorderVal(drawTool);
        drawTool.drawRectangle(x,y,20,6);

        setDrawToolColorToRGBABackgroundVal(drawTool);
        drawTool.drawFilledRectangle(x,y,20,6);

        drawTool.setCurrentColor((int) (240 - health * 0.01 * 220), (int) (20 + health * 0.01 * 220), 20, 220);
        drawTool.drawFilledRectangle(x+1,y+1,health * 0.01 * 18,4);
    }

    /**
     * updates health display
     */
    public void updateHealth() {
        health = environment.getHp();
    }

    public double getHealth() {
        return health;
    }
}
