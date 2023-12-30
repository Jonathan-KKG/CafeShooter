package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Stack;
import my_project.model.Dishes.*;
import my_project.model.Environment.Table;
import my_project.model.Ingredients.*;
import my_project.model.Item;

import java.lang.reflect.InvocationTargetException;

/**
 * Controls every instance of Dish-class and ensures proper handling
 */
public class DishController {
    private List<Dish> flyingDishes;
    private Dish[] storedDishes;
    private Stack<Item> heldItems;
    private ProgramController programController;
    private int currentDishIndex;
    private Class<? extends Dish>[] dishClasses;
    private Class<? extends Ingredient>[] ingredientClasses;

    /**
     * creates an array with 5 indexes and fills it with random dishes
     * creates several other dish-data-structures
     *
     * @param pProgramController required to access other controllers
     */
    public DishController(ProgramController pProgramController) {
        currentDishIndex = 0;
        flyingDishes = new List<>();
        heldItems = new Stack<>();
        storedDishes = new Dish[5];
        programController = pProgramController;

        dishClasses = new Class[]{ApplePie.class, CheeseCake.class, ChocolateCheeseCake.class, ChocolateCake.class, Coffee.class, SpaghettiCarbonara.class, StrawberryWaffles.class, Waffles.class, IceCreamWaffles.class};
        ingredientClasses = new Class[]{Apple.class, Bacon.class, Cheese.class, Chocolate.class, CoffeePowder.class, Cookie.class,Cream.class, Cream.class, Egg.class, Flour.class, IceCream.class,Spaghetti.class,Strawberry.class};

        for (int i = 0; i < storedDishes.length; i++) {
            storedDishes[i] = createDish(1300 + 45d / 2d + 45 * i, 838, "Waffles");
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

        objCurrentDish.setX(programController.getEntityController().getShooter().getX());
        objCurrentDish.setY(programController.getEntityController().getShooter().getY());
        long yLength = (long) (yPos - (programController.getEntityController().getShooter().getY() + programController.getEntityController().getShooter().getHeight() / 2));
        long xLength = (long) (xPos - (programController.getEntityController().getShooter().getX() + programController.getEntityController().getShooter().getWidth() / 2));
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
     * creates a dish
     *
     * @param pX       starting position
     * @param pY       starting position
     * @param dishType int between 1-4, each int is a set dishtype f.e. coffee
     * @return returns drawn dish
     */
    public Dish createDish(double pX, double pY, String dishType) {
        Dish dish = null;

        for (int i = 0; i < dishClasses.length; i++) {
            if(dishType.equals(dishClasses[i].getSimpleName())) {
                try {
                    dish = dishClasses[i].getDeclaredConstructor(double.class, double.class).newInstance(pX, pY);
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
                    e.printStackTrace();
                }
                i = dishClasses.length;
            }
        }
        if (dish == null) System.out.println("nu uh wrong dishtype");

        return dish;
    }
    /**
     * creates a ingredient
     *
     * @param pX       starting position
     * @param pY       starting position
     * @param ingredientType neme of the requierd ingredient
     * @return returns dish
     */
    public Ingredient createIngredient(double pX, double pY, String ingredientType) {
        Ingredient ingredient = null;

        for (int i = 0; i < ingredientClasses.length; i++) {
            if(ingredientType.equals(ingredientClasses[i].getSimpleName())) {
                try {
                    ingredient = ingredientClasses[i].getDeclaredConstructor(double.class, double.class).newInstance(pX, pY);
                } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
                    e.printStackTrace();
                }
                i = ingredientClasses.length;
            }
        }
        if (ingredient == null) System.out.println("nu uh wrong ingredientType");

        return ingredient;
    }

    /**
     * Moves the topmost item in the HeldDishes stack
     */
    public void moveHeldItems(){
        if (heldItems.top() != null) {
            heldItems.top().setX(programController.getEntityController().getCook().getX());
            heldItems.top().setY(programController.getEntityController().getCook().getY());
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
     * Removes the first item of the HeldDishes stack, draws the new topmost element and updates UI
     */
    public void removeFirstHeldItem() {
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

        Dish dishToBeAdded = table.getFirstDish();
        table.removeFirstDish();
        storedDishes[tempInt] = dishToBeAdded;
        programController.getUIController().moveAmmoIndicator(tempInt);
        dishToBeAdded.setX(1300 + 45d / 2d + 45 * tempInt);
        dishToBeAdded.setY(838);
    }

    public Item getFirstHeldItem() {
        return heldItems.top();
    }

    public List<Dish> getFlyingDishes() {
        return flyingDishes;
    }
}
