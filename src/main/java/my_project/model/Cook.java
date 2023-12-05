package my_project.model;

import KAGO_framework.control.ViewController;

import java.awt.event.KeyEvent;

public class Cook extends Player{
    public Cook(double pX, double pY, ViewController pViewController) {
        super(pX, pY, pViewController);
    }

    @Override
    protected void move(double dt) {
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
