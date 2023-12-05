package my_project.model;

import KAGO_framework.view.DrawTool;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import KAGO_framework.control.ViewController;

import javax.imageio.ImageIO;

public abstract class Player extends Entity {
    ViewController viewController;
    protected int speed = 90;
    protected BufferedImage image;

    public Player(double pX, double pY, ViewController pViewController) throws IOException {
        viewController = pViewController;
        x = pX;
        y = pY;
        image = ImageIO.read(new File("src/main/resources/graphic/img.png"));
    }

    public void draw(DrawTool drawTool) {
        drawTool.drawImage(image,x - image.getWidth()/2,y - image.getHeight()/2);
    }

    public void update(double dt){

    }

    protected abstract void move(double dt, int xDir, int yDir);

    public BufferedImage getImage() {
        return image;
    }
}
