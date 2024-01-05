package my_project.model.GUI.GameStates;

import KAGO_framework.view.DrawTool;

/**
 * First UI that is drawn on program start which introduces the players to the game
 */
public class StartScreenUI extends GameStateUI {

    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     */
    public StartScreenUI(double pX, double pY) {
        super(pX, pY);
        setNewImage("src/main/resources/graphic/StartScreen.png");
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        drawTool.drawImage(myImage,x,y);
    }
}
