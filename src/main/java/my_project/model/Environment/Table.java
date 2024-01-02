package my_project.model.Environment;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import my_project.model.Dishes.Dish;

/**
 * Table object that can store Dishes
 */
public class Table extends CollidableEnvironment {

    private Queue<Dish> tableDishes;
    private int queueLength;

    public Table(String filename, double pX, double pY) {
        super(filename, pX, pY);
        tableDishes = new Queue<>();
    }

    /**
     * Deletes all elements of the queue by overwriting the queue
     */
    public void emptyQueue(ViewController viewController){
        while (!tableDishes.isEmpty()){
            viewController.removeDrawable(tableDishes.front());
            tableDishes.dequeue();
        }
        queueLength = 0;
    }

        //TODO change "newDish.setY(y)"
    public void addToTable(Dish newDish) {
        tableDishes.enqueue(newDish);
        newDish.setScale(0.5);
        queueLength++;
        if(queueLength<3) {
            newDish.setY(y + (newDish.getHeight() * newDish.getScale() - 5) * (queueLength - 1) - 10);
            newDish.setX(x);
        }
        newDish.setY(y);
    }

    public Dish getFirstDish() {
        return tableDishes.front();
    }

    public boolean isTableEmpty(){
        return tableDishes.isEmpty();
    }

    public void removeFirstDish(){
        tableDishes.front().setScale(1);
        tableDishes.dequeue();
    }
}
