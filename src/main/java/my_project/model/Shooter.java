package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.Queue;

public class Shooter extends Player{
    private Queue<Dish> dishes;
    public Shooter(double pX, double pY){
        super(pX, pY);
        dishes = new Queue<>();
        dishes.enqueue(new Dish("Houerglass.png",x,y));
    }

    public Queue<Dish> getDishes() {
        return dishes;
    }
}
