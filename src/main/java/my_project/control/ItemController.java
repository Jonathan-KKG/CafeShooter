package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Stack;
import my_project.model.Cook;
import my_project.model.Dishes.*;
import my_project.model.Environment.Table;
import my_project.model.Ingredients.*;
import my_project.model.Item;
import my_project.model.Shooter;

import java.lang.reflect.InvocationTargetException;

/**
 * Controls every instance of Dish-class and ensures proper handling
 */
public class ItemController {
    private List<Dish> flyingDishes;
    private Dish[] storedDishes;
    private Stack<Item> heldItems;
    private ProgramController programController;
    private int currentDishIndex;
    private Class<? extends Item>[] itemClasses;

    /**
     * creates an array with 5 indexes and fills it with random dishes
     * creates several other dish-data-structures
     *
     * @param pProgramController required to access other controllers
     */
    public ItemController(ProgramController pProgramController) {
        currentDishIndex = 0;
        flyingDishes = new List<>();
        heldItems = new Stack<>();
        storedDishes = new Dish[5];
        programController = pProgramController;

        itemClasses = new Class[]{ApplePie.class, CheeseCake.class, ChocolateCheeseCake.class, ChocolateCake.class, Coffee.class, SpaghettiCarbonara.class, StrawberryWaffles.class, Waffles.class, IceCreamWaffles.class,
                                Apple.class, Bacon.class, Cheese.class, Chocolate.class, CoffeePowder.class, WhippedCream.class, WhippedCream.class, Egg.class, Flour.class, IceCream.class,Spaghetti.class,Strawberry.class};

        for (int i = 0; i < storedDishes.length; i++) {
            storedDishes[i] = (Dish) createItem(1304 + 30d / 2d + 55 * i, 838, "Waffles");
            storedDishes[i].setX(storedDishes[i].getX() + 20 - storedDishes[i].getWidth() / 2);
            storedDishes[i].setY(storedDishes[i].getY() + 20 - storedDishes[i].getHeight() / 2 - 4);
            programController.getViewController().draw(storedDishes[i]);
        }

    }

    /**
     * Calculate velocity of thrown Dish and adds it to the List of thrown Dishes
     *
     * @param xPos x-Position of the Cursor
     * @param yPos y-Position of the Cursor
     */
    public void shoot(double xPos, double yPos) {
        if (currentDishIndex == -1 || storedDishes[currentDishIndex] == null)
            return;

        Dish objCurrentDish = storedDishes[currentDishIndex];
        Shooter shooter = programController.getEntityController().getShooter();
        
        objCurrentDish.setX(shooter.getX());
        objCurrentDish.setY(shooter.getY());
        long yLength = (long) (yPos - (shooter.getY() + shooter.getHeight() / 2));
        long xLength = (long) (xPos - (shooter.getX() + shooter.getWidth() / 2));
        double playerRotation = Math.atan2(yLength, xLength);
        double xDir = Math.cos(playerRotation);
        double yDir = Math.sin(playerRotation);
        objCurrentDish.setDirection(new double[]{xDir, yDir});

        flyingDishes.append(objCurrentDish);
        storedDishes[currentDishIndex] = null;
        nextBullet();
    }

    /**
     * Moves all Dishes that are thrown (all in the List dishes)
     *
     * @param dt the Time passed between this and the last call of the method
     */
    public void dishUpdate(double dt) {
        flyingDishes.toFirst();
        while (flyingDishes.hasAccess()) {
            flyingDishes.getContent().setX((flyingDishes.getContent().getX() + flyingDishes.getContent().getDirection()[0] * flyingDishes.getContent().getSpeed() * dt));
            flyingDishes.getContent().setY((flyingDishes.getContent().getY() + flyingDishes.getContent().getDirection()[1] * flyingDishes.getContent().getSpeed() * dt));
            flyingDishes.next();
        }
    }

    /**
     * recursively finds next index in storedDishes array that is or is not null
     *
     * @param startingIndex the current index that is viewed
     * @param modifiedIndex Required to recall the method; insert same as startingIndex for first iteration
     * @param type whether to search for the next occupied or for the next unoccupied index (0 = occupied, 1 = unoccupied)
     * @return next (un-)occupied index
     */
    private int findNextIndex(int startingIndex, int modifiedIndex, int type) {
        if(startingIndex == -1)
            startingIndex++;
        modifiedIndex++;

        if(modifiedIndex >= storedDishes.length)
            return findNextIndex(startingIndex, -1, type);

        if(type == 0 && storedDishes[modifiedIndex] != null)
            return modifiedIndex;
        else if (type == 1 && storedDishes[modifiedIndex] == null)
            return modifiedIndex;

        if(modifiedIndex == startingIndex)
            return -1;

        return findNextIndex(startingIndex, modifiedIndex, type);
    }

    /**
     * creates an iteem
     *
     * @param pX       starting position
     * @param pY       starting position
     * @param itemType simpleClassName of the item's class
     * @return returns item
     */
    public Item createItem(double pX, double pY, String itemType) {
        Item item = null;

        for (int i = 0; i < itemClasses.length; i++) {
            if(itemType.equals(itemClasses[i].getSimpleName())) {
                try {
                    item = itemClasses[i].getDeclaredConstructor(double.class, double.class).newInstance(pX, pY);
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
                    e.printStackTrace();
                }
                i = itemClasses.length;
            }
        }
        if (item == null) System.out.println("nu uh wrong itemtype");

        return item;
    }

    /**
     * Moves the topmost item in the HeldDishes stack
     */
    public void moveHeldItems(){
        if (heldItems.top() != null) {
            Cook cook = programController.getEntityController().getCook();
            heldItems.top().setX(cook.getX() + cook.getWidth() / 5);
            heldItems.top().setY(cook.getY() + cook.getHeight() / 3);
        }
    }

    /**
     * sets the current bullet on the next element in the array.
     * If current bullet is last element, it starts searching from the beginning
     */
    public void nextBullet() {
        currentDishIndex = findNextIndex(currentDishIndex, currentDishIndex, 0);
        programController.getUIController().moveAmmoIndicator(currentDishIndex);
    }

    /**
     * adds dish to pendingDishes stack, draws it and increases heldDishStack
     *
     * @param item dish that gets added
     */
    public void addToHeldItemStack(Item item) {
        programController.getViewController().removeDrawable(heldItems.top());
        heldItems.push(item);
        programController.getViewController().draw(heldItems.top());
        programController.getUIController().updateHeldItemsAmount(true);
    }

    /**
     * Removes the first item of the HeldDishes stack, stops drawing it, draws the new topmost element and updates UI
     */
    public void removeFirstHeldItem() {
        if(heldItems.isEmpty())
            return;
        
        programController.getViewController().removeDrawable(heldItems.top());
        heldItems.pop();
        programController.getViewController().draw(heldItems.top());
        programController.getUIController().updateHeldItemsAmount(false);
    }

    /**
     * Removes the first dish of a table and adds it to the storedDishes array
     * @param table the table where the Dish should be taken from
     */
    public void moveToStoredDishes(Table table){
        int tempInt = findNextIndex(currentDishIndex, currentDishIndex, 1);
        if(table.getFirstDish() == null || tempInt == -1)
            return;

        boolean arrWasEmpty = findNextIndex(currentDishIndex,currentDishIndex,0) == -1;

        Dish dishToBeAdded = table.getFirstDish();
        table.removeFirstDish(programController.getViewController());
        storedDishes[tempInt] = dishToBeAdded;
        storedDishes[tempInt].setX(1300 + 35d / 2d + 55 * tempInt + 20 - storedDishes[tempInt].getWidth() / 2);
        storedDishes[tempInt].setY(838 + 20 - storedDishes[tempInt].getHeight() / 2 - 4);
        if(arrWasEmpty)
            nextBullet();
    }

    public Item getFirstHeldItem() {
        return heldItems.top();
    }

    public List<Dish> getFlyingDishes() {
        return flyingDishes;
    }
}
