package my_project.model.GUI;

import KAGO_framework.view.DrawTool;

/**
 * Displays the ammo count in the bottom right and the current element
 */
public class DishUI extends UI {

    /**
     * Initializes DishUI and sets starting position for the moveable UI Index
     */
    public DishUI(double pX, double pY) {
        super(pX, pY);
        x = pX + 17;
        y = pY + 14;

    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        drawDefaultBorder(drawTool, -1, startingPosition[0] + 5, startingPosition[1], 5 * 55 + 30d / 2, 100);

        //UI Seperators
        drawTool.setLineWidth(3);
        drawTool.setCurrentColor(230, 50, 50, 255);

        for (int i = 1; i < 5; i++)
            drawTool.drawLine(startingPosition[0] - 60 + 70 + 55 * i, startingPosition[1] - 30 + 45, startingPosition[0] - 60 + 70 + 55 * i, startingPosition[1] - 30 + 75);

        //UI Index
        drawTool.setCurrentColor(20, 240, 20, 180);
        drawTool.drawRectangle(x+1, y, 40, 40);

        // Pacman
        // Remove comment to find Pacman
        // Pacman Will Haunt You If You D̜͝e̮̚ľ̷é̷͓t̸͎̂e̵ ̟̀H͎̓i̶̫͂m̸̹.̵....

        //drawTool.setCurrentColor(255,255,0,255);
        //drawTool.drawFilledEllipticArc(400,400, 80,80, 45f / 2,360-45,2);
        //drawTool.setCurrentColor(0,0,0,255);
        //drawTool.drawEllipticArc(400,400, 80,80, 45f / 2,360-45,2);
        //drawTool.setCurrentColor(0,0,255,255);
        //drawTool.drawFilledEllipticArc(400,400, 80,80, -45 / 2f,45f ,2);
        //
        //drawTool.setCurrentColor(0,0,0,255);
        //drawTool.drawCircle(435,415,6);
        //
        //drawTool.setCurrentColor(255,255,255,255);
        //drawTool.drawFilledCircle(435,415,5);
        //drawTool.setCurrentColor(0,0,0,255);
        //drawTool.drawFilledCircle(435,415,1);


    }

    public double[] getPosition() {
        return startingPosition;
    }
}
