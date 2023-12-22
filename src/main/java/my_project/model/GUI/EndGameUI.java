package my_project.model.GUI;

import KAGO_framework.view.DrawTool;
import my_project.model.UI;

import java.awt.*;

public class EndGameUI extends UI {

    private double[][] restartButtonPosAndSize;

    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     */
    public EndGameUI(double pX, double pY) {
        super(pX, pY);
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        drawUsualBorder(drawTool, 10, startingPosition[0] - 30, startingPosition[1] - 35 - 50, 1200, 105 + 50);

        drawTool.setCurrentColor(0, 0, 0, 255);
        drawTool.formatText("Arial", Font.BOLD, 30);
        drawTool.drawText(startingPosition[0] + 300, startingPosition[1] - 35, "Ihr habt Kostas-Femboy-Cafe™ enttäuscht.");
        drawTool.drawText(startingPosition[0] + 0, startingPosition[1], "Eure mangelnden Bemühungen haben die Auslöschung der Menschheit und ");
        drawTool.drawText(startingPosition[0] + 270, startingPosition[1] + 35, " den Kollaps unseres Sonnensystems zur Folge.");

        restartButtonPosAndSize = new double[][]{
                new double[]{startingPosition[0] + 490, startingPosition[1] + 200},
                new double[]{155, 45}
        };
        drawUsualBorder(drawTool, 6, restartButtonPosAndSize[0][0], restartButtonPosAndSize[0][1], restartButtonPosAndSize[1][0], restartButtonPosAndSize[1][1]);
        drawTool.setCurrentColor(0, 0, 0, 255);
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
