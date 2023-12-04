package my_project.model;

import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Entety {
    int speed = 5;
    public Player(double x, double y){
        this.x = x;
        this.y = y;
        System.out.println("player");
    }

    public void draw(DrawTool drawTool) {
        drawTool.drawFilledCircle(x,y,20);
        drawTool.setCurrentColor(Color.RED);
        drawTool.drawCircle(x,y,10);
        drawTool.drawCircle(x,y,5);
    }


    public void keyReleased(int key) {
        System.out.println(key);
        if (key == KeyEvent.VK_S) {
            y += speed;
        }
        if (key == KeyEvent.VK_W) {
            y -= speed;
        }
        if (key == KeyEvent.VK_A) {
            x -= speed;
        }
        if (key == KeyEvent.VK_D) {
            x += speed;
        }
    }

}
