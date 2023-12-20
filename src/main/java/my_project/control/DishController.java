package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Stack;
import my_project.model.Dish;
import my_project.model.Dishes.Coffee;
import my_project.model.Dishes.Mikado;
import my_project.model.Dishes.Muffin;
import my_project.model.Dishes.Spaghetti;
import my_project.model.Item;

import java.io.File;


public class DishController {
    private List<Dish> flyingDishes;
    private Dish[] storedDishes;
    private Stack<Item> heldItems;
    private ProgramController programController;
    private int currentDishIndex;

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

        for (int i = 0; i < storedDishes.length; i++) {
            storedDishes[i] = createDish(1300 + 45d / 2d + 45 * i, 838, "Spaghetti");
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
        long yLength = (long) (yPos - (programController.getEntityController().getShooter().getY() + programController.getEntityController().getShooter().getImage().getHeight() / 2));
        long xLength = (long) (xPos - (programController.getEntityController().getShooter().getX() + programController.getEntityController().getShooter().getImage().getWidth() / 2));
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
     * finds next index in storedDishes array that is not null
     *
     * @return next occupied index
     */
    private int nextOccupiedIndex(int startingIndex, int modifiedIndex) {
        if(startingIndex == -1)
            startingIndex++;
        modifiedIndex++;

        if(modifiedIndex >= storedDishes.length)
            return nextOccupiedIndex(startingIndex, -1);

        if(storedDishes[modifiedIndex] != null)
            return modifiedIndex;

        if(modifiedIndex == startingIndex)
            return -1;

        return nextOccupiedIndex(startingIndex, modifiedIndex);
    }

    /**
     * creates a dish and draws it
     *
     * @param pX       starting position
     * @param pY       starting position
     * @param dishType int between 1-4, each int is a set dishtype f.e. coffee
     * @return returns drawn dish
     */
    public Dish createDish(double pX, double pY, String dishType) {
        File folder = new File("src/main/resources/graphic/Dishes/");
        File[] dishTypes = folder.listFiles();
        Dish dish = null;
        for (int i = 0; i < dishTypes.length; i++) {
            if(dishType.equals(dishTypes[i].toString().replaceAll("src\\\\main\\\\resources\\\\graphic\\\\Dishes\\\\", "").replaceAll(".png", ""))) {
                switch (dishType){
                    case "Coffee" -> dish = new Coffee(dishType,pX,pY);
                    case "Mikado" -> dish = new Mikado(dishType,pX,pY);
                    case "Muffin" -> dish = new Muffin(dishType,pX,pY);
                    case "Spaghetti" -> dish = new Spaghetti(dishType,pX,pY);
                }
                i = dishTypes.length;
            }
        }
        if (dish == null) System.out.println("nu uh wrong dishtype");

        return dish;
    }

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
        currentDishIndex = nextOccupiedIndex(currentDishIndex, currentDishIndex);
        programController.getUiController().moveAmmoIndicator(currentDishIndex);
    }

    /**
     * adds dish to pendingDishes stack
     *
     * @param dish dish that gets added
     */
    public void addToHeldItemStack(Item dish) {
        programController.getViewController().removeDrawable(heldItems.top());
        heldItems.push(dish);
        programController.getViewController().draw(heldItems.top());
    }


    public int getCurrentDishIndex() {
        return currentDishIndex;
    }

    public Item getFirstHeldItem() {
        return heldItems.top();
    }

    public void removeFirstHeldItem() {
        programController.getViewController().removeDrawable(heldItems.top());
        heldItems.pop();
        programController.getViewController().draw(heldItems.top());
    }

    public List<Dish> getFlyingDishes() {
        return flyingDishes;
    }
}
