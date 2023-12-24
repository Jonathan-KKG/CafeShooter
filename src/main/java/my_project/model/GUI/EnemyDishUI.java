package my_project.model.GUI;

import KAGO_framework.view.DrawTool;

/**
 * The Enemy "Wants-Bubble"
 * Displays what Dish the Enemy needs to be defeated
 */
public class EnemyDishUI extends UI {

    private int[] bubbleRadius = new int[]{70,40};
    private int[] markerRadius = new int[]{46,46};
    private int[] entitySize = new int[]{32,32};

    /**
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     * @param filename The filename of the dish that the Enemy this UI belongs to requires
     */
    public EnemyDishUI(double pX, double pY, String filename) {
        super(pX, pY);
        setNewImage("src/main/resources/graphic/Dishes/" + filename + ".png");
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        drawTool.setLineWidth(2);

        // Bubble
        setDrawToolColorToRGBABorderVal(drawTool);
        drawTool.drawEllipticArc(x-(entitySize[0]-4)*2,y-(entitySize[1]-2)*2, bubbleRadius[0],bubbleRadius[1],-43,340,0);
        setDrawToolColorToRGBABackgroundVal(drawTool);
        drawTool.drawFilledEllipticArc(x-(entitySize[0]-4)*2,y-(entitySize[1]-2)*2, bubbleRadius[0],bubbleRadius[1],-43,340,1);

        // Marker
        setDrawToolColorToRGBABorderVal(drawTool);
        drawTool.drawLine(x+0.375 * entitySize[0],y-0.1875 * entitySize[1],x+5, y-0.1875 * entitySize[1]-19);
        drawTool.drawLine(x+0.375 * entitySize[0],y-0.1875 * entitySize[1],x-5, y-0.1875 * entitySize[1]-16);
        setDrawToolColorToRGBABackgroundVal(drawTool);

        // Fill Marker
        drawTool.drawFilledEllipticArc(x+12-markerRadius[0] / 2f,y-6-markerRadius[0] / 2f, markerRadius[0], markerRadius[1],105,35,2);

        // Dish
        drawTool.drawImage(myImage,x - 32 * 1.125, y - 32 * 1.125 - bubbleRadius[1] / 2f);
    }

}
