package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.List;
import KAGO_framework.model.abitur.datenstrukturen.Stack;
import my_project.model.Dish;
import my_project.model.Enemy;


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
        flyingDishes = new List<>();
        heldDishes = new Stack<>();
        storedDishes = new Dish[5];
        programController = pProgramController;
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
        double xVel = Math.cos(playerRotation);
        double yVel = Math.sin(playerRotation);
        objCurrentDish.setXVel(xVel);
        objCurrentDish.setYVel(yVel);

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
            flyingDishes.getContent().setX((flyingDishes.getContent().getX() + flyingDishes.getContent().setXVel() * 500 * dt));
            flyingDishes.getContent().setY((flyingDishes.getContent().getY() + flyingDishes.getContent().getYVel() * 500 * dt));
            flyingDishes.next();
        }
    }

    /**
     * creates a dish
     * @param pX starting position
     * @param pY starting position
     * @param dishType int between 1-4, each int is a set dishtype f.e. coffee
     * @return returns drawn dish
     */
    public Dish createDish(double pX, double pY, int dishType) {
        Dish dish = null;
        if(dishType>0 && dishType< 5) {
            if (dishType == 1)
                dish = new Dish("Muffin.png", pX, pY);
            if (dishType == 2)
                dish = new Dish("Spaghet.png", pX, pY);
            if (dishType == 3)
                dish = new Dish("Mikado.png", pX, pY);
            if (dishType == 4)
                dish = new Dish("Cawfee.png", pX, pY);
        } else{
            System.out.println("nu uh wrong dishtype");
        }
        return dish;
    }

    /**
     * adds dish to pendingDishes stack
     * @param dish dish that gets added
     */
    public void addToHeldDishStack(Dish dish){
        heldDishes.push(dish);

    }


    /**
     * Checks collision between Enemy and Dishes. If a Dish hits an Enemy, it gets deleted and if it has the right type the Enemy dies.
     * All Dishes outside the map get deleted.
     *
     * @param pEnemies an Array of all existing Enemies
     */
    public void checkCollisions(Enemy[] pEnemies) {
        flyingDishes.toFirst();
        boolean removed = false;
        while (flyingDishes.hasAccess()) {
            for (int i = 0; i < pEnemies.length; i++) {
                if (pEnemies[i] != null && flyingDishes.getContent().collidesWith(pEnemies[i])) {
                    if (pEnemies[i].getRequiredDish().equals(flyingDishes.getContent().getType())) {
                        programController.removeDrawableFromScene(pEnemies[i]);
                        pEnemies[i] = null;
                        removed = true;
                    }
                    i = pEnemies.length;
                    programController.removeDrawableFromScene(flyingDishes.getContent());
                    flyingDishes.remove();
                }
            }
            if (!removed) {
                flyingDishes.next();
                if (flyingDishes.hasAccess() && (flyingDishes.getContent().getY() + flyingDishes.getContent().getHeight() < 0 ||
                        flyingDishes.getContent().getY() > 1109 ||
                        flyingDishes.getContent().getX() + flyingDishes.getContent().getWidth() < 0 ||
                        flyingDishes.getContent().getX() > 1920)
                ) {
                    programController.removeDrawableFromScene(flyingDishes.getContent());
                    flyingDishes.remove();
                }
            }
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
        for (int i = currentDish + 1; i != currentDish; i++) {
            System.out.println(i);
            if (i < storedDishes.length && storedDishes[i] != null)
                return i;
            if (i  == storedDishes.length)
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
    public Dish createDish(double pX, double pY, int dishType) {
        Dish dish = null;
        if (dishType > 0 && dishType < 5) {
            if (dishType == 1)
                dish = new Dish("Muffin.png", pX, pY);
            if (dishType == 2)
                dish = new Dish("Spaghet.png", pX, pY);
            if (dishType == 3)
                dish = new Dish("Mikado.png", pX, pY);
            if (dishType == 4)
                dish = new Dish("Cawfee.png", pX, pY);
        } else {
            System.out.println("nu uh wrong dishtype");
        }
        programController.getViewController().draw(dish);
        return dish;
    }


    public int getCurrentDishIndex() {
        return currentDish;
    }

    public Dish getFirstHeldDish(){
        return heldDishes.top();
    }

}
