package my_project.model.GUI.GameStates;

import KAGO_framework.view.DrawTool;

import java.awt.*;

/**
 * First UI that is drawn on program start which introduces the players to the game
 */
public class StartScreenUI extends GameStateUI {
    private int textSize = 15;

    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     */
    public StartScreenUI(double pX, double pY) {
        super(pX, pY);
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        setDefaultSettings(drawTool);

        double[] pos = new double[]{480,700};
        String text = "Interact with your surroundings using ";
        drawDefaultBorder(drawTool, 2, pos[0] - 10, pos[1] - (textSize + 25) / 2d - 5, text.length() * textSize * 0.6, textSize + 23);

        setDefaultSettings(drawTool);
        drawTool.drawText(pos[0], pos[1], text);
        drawVisualButton(drawTool,'A',new double[]{pos[0] + text.length() * textSize * 0.5, pos[1] - textSize * 1.5});
        drawArrow(drawTool,120,new double[]{pos[0]+ + text.length() * textSize * 0.25,pos[1] + textSize});

    }

    /**
     * Resets drawing color to black, format text to Arial, Bold, textSize and lineWidth to 2;
     * the commonly used settings across StartScreenUI.java
     * @param drawTool Required to adjust its settings
     */
    private void setDefaultSettings(DrawTool drawTool){
        drawTool.setLineWidth(2);
        drawTool.setCurrentColor(255, 255, 255,255);
        drawTool.formatText("Arial", Font.BOLD, textSize);
    }

    /**
     * Draws an arrow
     * @param drawTool Required to draw the arrow
     * @param length Length of the arrow
     * @param position {x,y} position of the arrow
     */
    private void drawArrow(DrawTool drawTool, int length, double[] position){
        drawTool.setCurrentColor(0,0,0,255);
        double[] arrowTipPos = new double[]{position[0] + length - 10, position[1] + length};

        drawTool.drawLine(position[0], position[1], arrowTipPos[0], arrowTipPos[1]);
        drawTool.drawLine(arrowTipPos[0], arrowTipPos[1], arrowTipPos[0]-length/2f, arrowTipPos[1] - 5);
        drawTool.drawLine(arrowTipPos[0], arrowTipPos[1], arrowTipPos[0] - 5, arrowTipPos[1]-length/2f);
    }

    /**
     * Draws a square with a char in the middle - CANNOT BE PRESSED
     * @param drawTool Required to draw
     * @param key key that should be displayed
     * @param position position of the button {x,y}
     */
    private void drawVisualButton(DrawTool drawTool, char key, double[] position){
        int size = textSize + 18;

        drawTool.setCurrentColor(42, 71, 219, 200); // blue
        drawTool.drawFilledRectangle(position[0], position[1], size, size);

        drawTool.setCurrentColor(255,255,255,255);
        drawTool.formatText("Arial", Font.PLAIN, size - 10);
        drawTool.drawText(position[0] + (size - 10) / 2.5d, position[1] + size / 1.25d, String.valueOf(key));

        int borderOffset = 3;
        drawTool.setCurrentColor(15, 28, 97, 200); // darker blue
        drawTool.drawRectangle(position[0] + borderOffset, position[1] + borderOffset, size - borderOffset * 2);

        setDefaultSettings(drawTool);
    }
}
