package my_project.model.Environment;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.Queue;
import my_project.model.Dishes.Dish;

/**
 * Table object that can store Dishes
 */
public class Table extends CollidableEnvironment {

    private Queue<Dish> tableDishes;

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
    }

    public boolean isTableEmpty(){
        return tableDishes.isEmpty();
    }

    public void addToTable(Dish newDish) {
        tableDishes.enqueue(newDish);
    }

    public Dish getFirstDish() {
        return tableDishes.front();
    }

    public void removeFirstDish(){
        tableDishes.dequeue();
    }
}
