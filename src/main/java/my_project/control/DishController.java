package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Dish;
import my_project.model.Enemy;

public class DishController {
    private List<Dish> flyingDishes;
    private Dish[] storedDishes;

    private ProgramController programController;
    private int currentDish;

    public DishController(ProgramController pProgramController, ViewController viewController) {
        flyingDishes = new List<>();
        programController = pProgramController;


        storedDishes = new Dish[5];
        for (int i = storedDishes.length; i > 0; i--) {
            int dishType = (int) (Math.random() * 4 + 1);
            if (dishType == 1)
                storedDishes[i - 1] = new Dish("muffin.png", 1400 + 35 * i , 840);
            if (dishType == 2)
                storedDishes[i - 1] = new Dish("spaghet.png", 1400 + 35 * i, 840);
            if (dishType == 3)
                storedDishes[i - 1] = new Dish("mikado.png", 1400 + 35 * i, 840);
            if (dishType == 4)
                storedDishes[i - 1] = new Dish("cawfee.png", 1400 + 35 * i, 840);
            viewController.draw(storedDishes[i - 1]);
        }

    }

    /**
     * Calculate velocity of thrown Dish and adds it to the List of thrown Dishes
     *
     * @param xPos x-Position of the Cursor
     * @param yPos y-Position of the Cursor
     */
    public void shoot(double xPos, double yPos) {
        if(currentDish == -1 || storedDishes[currentDish] == null)
            return;

        Dish objCurrentDish = storedDishes[currentDish];

        objCurrentDish.setX(programController.getShooter().getX());
        objCurrentDish.setY(programController.getShooter().getY()) ;
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

    public int getCurrentDishIndex() {
        return currentDish;
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
     * @return next occupied index
     */
    private int nextOccupiedIndex() {
        if (currentDish != storedDishes.length - 1)
            for (int i = currentDish + 1; i < storedDishes.length; i++)
                if (storedDishes[i] != null)
                    return i;

        for (int i = 0; i < currentDish; i++)
            if (storedDishes[i] != null)
                return i;

        return -1;
    }

}
