package my_project.model.Dishes;

import KAGO_framework.view.DrawTool;
import my_project.model.Item;

/**
 * a Dish Item that is able to satisfy enemies
 */
public abstract class Dish extends Item {
    private double[] direction;
    private double scale;


    /**
     * set starting position & sprite of Dish
     *
     * @param filename filename of the sprite, is the type of Dish at the same time
     * @param pX       x position at start
     * @param pY       y position at start
     */
    public Dish(String filename, double pX, double pY) {
        super("Dishes/" + filename, pX, pY);
        speed = 500;
        scale = 1;
    }

    public void draw(DrawTool drawTool){
        drawTool.drawTransformedImage(myImage, x, y, 0, scale);
    }

    public void setDirection(double[] dir) {
        direction = dir;
    }

    public double[] getDirection() {
        return direction;
    }

    public void setScale(double pScale){
        scale = pScale;
    }

    public double getScale() {
        return scale;
    }
}
