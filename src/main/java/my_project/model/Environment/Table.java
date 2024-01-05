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
    public void emptyQueue(ViewController viewController) {
        while (!tableDishes.isEmpty()) {
            viewController.removeDrawable(tableDishes.front());
            tableDishes.dequeue();
        }
        queueLength = 0;
    }

    /**
     * Scales dish down and places it on a table,as long as there are less than three dishes on it already. Adds it to the tableDishes queue.
     *
     * @param newDish        Dish that is added
     * @param viewController used to draw dishes on table
     */
    public void addToTable(Dish newDish, ViewController viewController) {
        tableDishes.enqueue(newDish);
        queueLength++;

        if(queueLength>3)
            return;

        viewController.draw(newDish);
        adjustDish(newDish);
    }

    /**
     * Put the dish back into its original state and dequeues it
     * Also adjusts position and "drawing state" of the first 3 dishes (if existing)
     * @param viewController Required to draw the next element on the table
     */
    public void removeFirstDish(ViewController viewController) {
        tableDishes.front().setScale(1);
        tableDishes.dequeue();

        // draw next 3 objects
        Queue<Dish> tempQueue = new Queue<>();
        queueLength = 0;

        // empty tableDishes
        while (!tableDishes.isEmpty()) {
            tempQueue.enqueue(tableDishes.front());
            viewController.removeDrawable(tableDishes.front());
            tableDishes.dequeue();
        }

        // Draw, adjust and enqueue first 3 Dishes
        for (int i = 1; i < 4 && !tempQueue.isEmpty(); i++) {
            queueLength++;
            viewController.draw(tempQueue.front());
            adjustDish(tempQueue.front());
            tableDishes.enqueue(tempQueue.front());
            tempQueue.dequeue();
        }

        // Refill other dishes
        while (!tempQueue.isEmpty()){
            tableDishes.enqueue(tempQueue.front());
            tempQueue.dequeue();
            queueLength++;
        }
    }

    /**
     * Adjusts position and scale of the dish so it fits on the table
     * @param dish position to be adjusted
     */
    private void adjustDish(Dish dish) {
        dish.setScale(0.65);
        dish.setX(x);
        dish.setY(y - 12 + (dish.getHeight() * dish.getScale() - 10.5) * (queueLength - 1));
    }

    public Dish getFirstDish() {
        return tableDishes.front();
    }

    public boolean isTableEmpty() {
        return tableDishes.isEmpty();
    }

}
