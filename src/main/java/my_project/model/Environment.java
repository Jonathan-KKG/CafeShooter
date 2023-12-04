package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Environment extends GraphicalObject {
    private Image floorTileSprite;
    private Image grassBg;
    private Image wallTileL;

    public Environment(double pX, double pY) throws IOException {
        try{
            File leftWallTile = new File("src/main/resources/graphic/leftwall.png");
            wallTileL = ImageIO.read(leftWallTile);
            File grassTile = new File("src/main/resources/graphic/grass.png");
            grassBg = ImageIO.read(grassTile);
            File floorTile = new File("src/main/resources/graphic/floortile.png");
            floorTileSprite = ImageIO.read(floorTile);
        } catch (Exception e){
            System.out.println("Creating sprite from pathname went wrong!");
        }
        x = pX;
        y = pY;
    }

    public void draw(DrawTool drawTool) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                drawTool.drawImage((BufferedImage) grassBg,500*i,500*j);
            }
        }
        drawTool.drawImage((BufferedImage) floorTileSprite,x,y);
        drawTool.drawImage((BufferedImage) wallTileL,x,y);
    }
}
