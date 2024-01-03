package my_project.model.GUI.GameStates;

import KAGO_framework.view.DrawTool;
import my_project.model.GUI.UI;

/**
 * All UIs that should be drawn during a specific state of the game
 */
public abstract class GameStateUI extends UI {

    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     */
    public GameStateUI(double pX, double pY) {
        super(pX, pY);
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public abstract void draw(DrawTool drawTool);
}
