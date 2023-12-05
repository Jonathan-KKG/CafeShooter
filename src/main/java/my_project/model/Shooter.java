package my_project.model;

import KAGO_framework.control.ViewController;

import java.awt.event.KeyEvent;

public class Shooter extends Player{
    public Shooter(double pX, double pY, ViewController pViewController) {
        super(pX, pY, pViewController);
    }

    @Override
    protected void move(double dt) {
        if(viewController.isKeyDown(KeyEvent.VK_LEFT)){
            x -= speed*dt;
        }
        if(viewController.isKeyDown(KeyEvent.VK_RIGHT)){
            x += speed*dt;
        }
        if(viewController.isKeyDown(KeyEvent.VK_UP)){
            y -= speed*dt;
        }
        if(viewController.isKeyDown(KeyEvent.VK_DOWN)){
            y += speed*dt;
        }
    }


}
