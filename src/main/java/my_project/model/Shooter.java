package my_project.model;

import KAGO_framework.control.ViewController;

import java.awt.event.KeyEvent;

public class Shooter extends Player{
    public Shooter(double pX, double pY, ViewController pViewController) {
        super(pX, pY, pViewController);
    }

    @Override
    public void move(double dt, int xDir, int yDir) {
        x += xDir * speed * dt;
        y += yDir * speed * dt;
    }


}
