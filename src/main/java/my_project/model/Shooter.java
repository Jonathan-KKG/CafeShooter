package my_project.model;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.Queue;

import java.awt.event.KeyEvent;
import java.io.IOException;

public class Shooter extends Player{
    private Queue<Dish> dishes;
    public Shooter(double pX, double pY, ViewController pViewController) throws IOException {
        super(pX, pY, pViewController);
        dishes = new Queue<>();
        dishes.enqueue(new Dish("Houerglass.png",x,y));
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

    public Queue<Dish> getDishes() {
        return dishes;
    }
}
