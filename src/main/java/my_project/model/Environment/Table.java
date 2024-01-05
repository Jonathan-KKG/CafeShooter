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

    /**
     * Scales dish down and places it on a table,as long as there are less than three dishes on it already. Adds it to the tableDishes queue.
     * @param newDish Dish that is added
     * @param viewController used to draw dishes on table
     */
    public void addToTable(Dish newDish, ViewController viewController) {
        tableDishes.enqueue(newDish);
        newDish.setScale(0.65);
        queueLength++;
        System.out.println(queueLength);
        if(queueLength<=3) {
            viewController.draw(newDish);
            newDish.setY(y -12+ (newDish.getHeight() * newDish.getScale()-10.5)  * (queueLength - 1) );
            System.out.println(y -11+ 16  * (queueLength - 1)   );
            newDish.setX(x);
        }

    }

    public Dish getFirstDish() {
        return tableDishes.front();
    }

    public boolean isTableEmpty(){
        return tableDishes.isEmpty();
    }

    /**
     * Put the dish back into its original state and returns it
     * @param viewController Required to draw the next element on the table
     */
    public void removeFirstDish(ViewController viewController){
        tableDishes.front().setScale(1);
        tableDishes.dequeue();
        queueLength--;
    }
}
