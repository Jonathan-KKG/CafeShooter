package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.Queue;

public class Table extends CollidableEnvironment{
    public Table(String filename, double pX, double pY) {
        super(filename, pX, pY);
    }
    public Queue<Dish> tableDishes;

    public void putDownReal(){

    }
}
