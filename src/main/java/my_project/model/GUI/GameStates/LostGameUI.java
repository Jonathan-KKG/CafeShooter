package my_project.model.GUI.GameStates;

import KAGO_framework.view.DrawTool;

import java.awt.*;

/**
 * Potential last frame of the game: The end screen for if the players have lost
 */
public class LostGameUI extends GameStateUI {

    private double[][] restartButtonPosAndSize;

    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     */
    public LostGameUI(double pX, double pY) {
        super(pX, pY);
        restartButtonPosAndSize = new double[][]{
                new double[]{startingPosition[0] + 490, startingPosition[1] + 200},
                new double[]{155, 45}
        };
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        drawDefaultBorder(drawTool, 10, startingPosition[0] - 30, startingPosition[1] - 35 - 50, 1200, 105 + 50);

        drawTool.setCurrentColor(255, 255, 255, 255);
        drawTool.formatText("Arial", Font.BOLD, 30);
        drawTool.drawText(startingPosition[0] + 300, startingPosition[1] - 35, "Ihr habt Kostas-Femboy-Cafe™ enttäuscht.");
        drawTool.drawText(startingPosition[0] + 0, startingPosition[1], "Eure mangelnden Bemühungen haben die Auslöschung der Menschheit und ");
        drawTool.drawText(startingPosition[0] + 270, startingPosition[1] + 35, " den Kollaps unseres Sonnensystems zur Folge.");

        drawDefaultBorder(drawTool, 6, restartButtonPosAndSize[0][0], restartButtonPosAndSize[0][1], restartButtonPosAndSize[1][0], restartButtonPosAndSize[1][1]);
        drawTool.setCurrentColor(255, 255, 255, 255);
        drawTool.drawText(restartButtonPosAndSize[0][0] + 8, restartButtonPosAndSize[0][1] + 30, "Try Again");
    }

    /**
     * Checks whether mouse is hovering over the restart button.
     * @param mouseX absolute mouse x pos
     * @param mouseY absolute mouse y pos
     * @return Whether mouse is hovering over it or nah
     */
    public boolean isOnRestartButton(double mouseX, double mouseY) {
        return mouseX < restartButtonPosAndSize[0][0] + restartButtonPosAndSize[1][0] &&
                mouseX > restartButtonPosAndSize[0][0] &&
                mouseY < restartButtonPosAndSize[0][1] + restartButtonPosAndSize[1][1] &&
                mouseY > restartButtonPosAndSize[0][1];
    }

}
