package my_project.model.GUI.SkillChecks;

import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * the SkillCheck for the WaffleIron CookingStation
 */
public class WaffleIronSkillCheck extends SkillCheckUI{

    private BufferedImage buttonImage;
    private String[] images;

    /**
     * Initializes UI Model
     *
     * @param pX Starting location of the Model
     * @param pY Starting location of the Model
     * @param dish SimpleClassName of what dish is being cooked
     */
    public WaffleIronSkillCheck(double pX, double pY, String dish) {
        super(pX, pY, dish);
        increment = 0.25;
        width = 40;
        height = 65;
        images = new String[]{"A_Key_Dark.png", "D_Key_Dark.png","S_Key_Dark.png","W_Key_Dark.png"};
        setNeededKey();
    }

    /**
     * Graphical implementation of the UI
     *
     * @param drawTool Required to draw the object
     */
    @Override
    public void draw(DrawTool drawTool) {
        drawDefaultBorder(drawTool, 2, startingPosition[0], startingPosition[1], width, height);
        drawTool.drawImage(myImage, startingPosition[0] + 5, startingPosition[1]);
        drawTool.drawTransformedImage(buttonImage, startingPosition[0] - 18, startingPosition[1] + 5, 0 ,0.32);
    }

    @Override
    public void updateSkillCheck(double time) {

    }

    /**
     * Generates new random keys (a,w,s,d) until it is different from the last one, then sets it to currentKey and sets the according image.
     */
    public void setNeededKey() {
        int possibleNeededKey = (int)(Math.random() * 4);
        while(possibleNeededKey == neededKey){
            possibleNeededKey = (int)(Math.random() * 4);
        }
        neededKey=possibleNeededKey;
        try{
            buttonImage = ImageIO.read(new File("src/main/resources/graphic/" + images[neededKey]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
