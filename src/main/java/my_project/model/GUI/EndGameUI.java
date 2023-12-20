package my_project.model.GUI;

import KAGO_framework.view.DrawTool;
import my_project.model.UI;

import java.awt.*;

public class EndGameUI extends UI {

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
        drawTool.setLineWidth(10);
        setDrawToolColorToRGBABorderVal(drawTool);
        drawTool.drawRectangle(startingPosition[0] - 30, startingPosition[1] - 35 - 50, 1200, 105 + 50);

        setDrawToolColorToRGBABackgroundVal(drawTool);
        drawTool.drawFilledRectangle(startingPosition[0]  - 30, startingPosition[1] - 35 - 50, 1200, 105 + 50);

        drawTool.setCurrentColor(0,0,0,255);
        drawTool.formatText("Arial", Font.BOLD, 30);
        drawTool.drawText(startingPosition[0] + 300, startingPosition[1] - 35, "Ihr habt Kostas-Femboy-Cafe™ enttäuscht.");
        drawTool.drawText(startingPosition[0] + 0, startingPosition[1],"Eure mangelnden Bemühungen haben die Auslöschung der Menschheit und ");
        drawTool.drawText(startingPosition[0] + 270, startingPosition[1] + 35, " den Kollaps unseres Sonnensystems zur Folge.");
    }
}
