package my_project.model.Environment;

import KAGO_framework.model.abitur.datenstrukturen.Queue;
import my_project.model.Dishes.Dish;

/**
 * Table object that can store Dishes
 */
public class Table extends CollidableEnvironment {

    public Queue<Dish> tableDishes;

    public Table(String filename, double pX, double pY) {
        super(filename, pX, pY);
    }


    public void putDownReal() {

    }
}
