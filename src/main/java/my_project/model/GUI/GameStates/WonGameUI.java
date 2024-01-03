package my_project.model.GUI.GameStates;

import KAGO_framework.view.DrawTool;

/**
 * Potential last frame of the game: The end screen for if the players have won
 */
public class WonGameUI extends GameStateUI{
    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     */
    public WonGameUI(double pX, double pY) {
        super(pX, pY);
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {

    }
}
