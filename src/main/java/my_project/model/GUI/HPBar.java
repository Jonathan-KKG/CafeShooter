package my_project.model.GUI;

import KAGO_framework.view.DrawTool;
import my_project.model.Environment.CollidableEnvironment;
import my_project.model.UI;

public class HPBar extends UI {

    private int health;
    private CollidableEnvironment environment;

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
        drawUsualBorder(drawTool, 3, x,y,18,6);

        drawTool.setCurrentColor((int) (240 - health * 0.01 * 220), (int) (20 + health * 0.01 * 220), 20, 220);
        drawTool.drawFilledRectangle(x+1.5,y+1,health * 0.01 * 16 - 0.5,4);
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
