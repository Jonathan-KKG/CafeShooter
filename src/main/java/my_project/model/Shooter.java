package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.Queue;

public class Shooter extends Player{
    private Dish[] dishes;
    public Shooter(double pX, double pY){
        super(pX, pY);
        dishes = new Dish[8];
        dishes[0] = new Dish("Houerglass.png",x,y);
    }

    public Queue<Dish> getDishes() {
        //<return dishes;
        return null;

    }
}
