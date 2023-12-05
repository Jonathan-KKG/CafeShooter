package my_project.model;

import KAGO_framework.control.ViewController;

import java.awt.event.KeyEvent;

public class Cook extends Player{
    public Cook(double pX, double pY, ViewController pViewController) {
        super(pX, pY, pViewController);
    }

    /**
     * Bewegt den Spieler
     * @param dt Updatet jede Frame
     * @param xDir Für die Richtung der Bewegung
     * @param yDir Für die Richtung der Bewegung
     */
    @Override
    public void move(double dt, int xDir, int yDir) {
        x += xDir * speed * dt;
        y += yDir * speed * dt;
    }
}
