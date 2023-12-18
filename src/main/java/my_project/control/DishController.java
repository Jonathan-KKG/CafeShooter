package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Stack;
import my_project.model.Dish;


public class DishController {
    private List<Dish> flyingDishes;
    private Dish[] storedDishes;
    private Stack<Dish> heldDishes;

    private ProgramController programController;
    private int currentDish;

    /**
     * creates an array with 5 indexes and fills it with random dishes
     * creates several other dish-data-structures
     *
     * @param pProgramController required to access other controllers
     */
    public DishController(ProgramController pProgramController) {
        currentDish = 0;
        flyingDishes = new List<>();
        heldDishes = new Stack<>();
        storedDishes = new Dish[5];
        programController = pProgramController;

        for (int i = 0; i < storedDishes.length; i++) {
            storedDishes[i] = createDish(1300 + 45d / 2d + 45 * i, 838, "muffin.png");
        }
    }

    public void putDown() {
        Dish tempObj = heldDishes.top();
        //queuedDishes.enqueue(tempObj);
        heldDishes.pop();
    }

    /**
     * Calculate velocity of thrown Dish and adds it to the List of thrown Dishes
     *
     * @param xPos x-Position of the Cursor
     * @param yPos y-Position of the Cursor
     */
    public void shoot(double xPos, double yPos) {
        if (currentDish == -1 || storedDishes[currentDish] == null)
            return;

        Dish objCurrentDish = storedDishes[currentDish];

        objCurrentDish.setX(programController.getShooter().getX());
        objCurrentDish.setY(programController.getShooter().getY());
        long yLength = (long) (yPos - (programController.getShooter().getY() + programController.getShooter().getImage().getHeight() / 2));
        long xLength = (long) (xPos - (programController.getShooter().getX() + programController.getShooter().getImage().getWidth() / 2));
        double playerRotation = Math.atan2(yLength, xLength);
        double xDir = Math.cos(playerRotation);
        double yDir = Math.sin(playerRotation);
        objCurrentDish.setDirection(new double[]{xDir, yDir});

        flyingDishes.append(objCurrentDish);
        storedDishes[currentDish] = null;
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
     * sets the current bullet on the next element in the array.
     * If current bullet is last element, it starts searching from the beginning
     */
    public void nextBullet() {
        currentDish = nextOccupiedIndex();
    }

    /**
     * finds next index in storedDishes array that is not null
     *
     * @return next occupied index
     */
    private int nextOccupiedIndex() {
        if (currentDish == -1)
            return -1;

        for (int i = currentDish + 1; i != currentDish; i++) {
            if (i < storedDishes.length && i > -1 && storedDishes[i] != null)
                return i;
            if (i == storedDishes.length)
                i = -1;
        }
        return -1;
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
        Dish dish = null;
        if (dishType.equals("muffin.png") || dishType.equals("spaghet.png") ||
            dishType.equals("mikado.png") || dishType.equals("cawfee.png")) {
            dish = new Dish(dishType, pX, pY);
        } else {
            System.out.println("nu uh wrong dishtype");
        }

        programController.getViewController().draw(dish);
        return dish;
    }

    /**
     * adds dish to pendingDishes stack
     *
     * @param dish dish that gets added
     */
    public void addToHeldDishStack(Dish dish) {
        heldDishes.push(dish);
    }


    public int getCurrentDishIndex() {
        return currentDish;
    }

    public Dish getFirstHeldDish() {
        return heldDishes.top();
    }

    public void removeFirstHeldDish() {
        heldDishes.pop();
    }

    public List<Dish> getFlyingDishes() {
        return flyingDishes;
    }
}
