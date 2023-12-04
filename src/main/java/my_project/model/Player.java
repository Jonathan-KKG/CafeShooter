package my_project.model;

import KAGO_framework.view.DrawTool;

import java.awt.*;
import java.awt.event.KeyEvent;
import KAGO_framework.control.ViewController;

public class Player extends Entity {
    ViewController viewController;
    int speed = 90;

    public Player(double pX, double pY, ViewController pViewController){
        viewController = pViewController;
        x = pX;
        y = pY;
    }

    public void draw(DrawTool drawTool) {
        drawTool.drawFilledCircle(x,y,20);
        drawTool.setCurrentColor(Color.RED);
        drawTool.drawCircle(x,y,10);
        drawTool.drawCircle(x,y,5);
    }

    public void update(double dt){
        move(dt);
    }

    public void move(double dt){
        if(viewController.isKeyDown(KeyEvent.VK_A)){
            x -= speed*dt;
        }
        if(viewController.isKeyDown(KeyEvent.VK_D)){
            x += speed*dt;
        }
        if(viewController.isKeyDown(KeyEvent.VK_W)){
            y -= speed*dt;
        }
        if(viewController.isKeyDown(KeyEvent.VK_S)){
            y += speed*dt;
        }

    }
}
