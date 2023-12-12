package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Dish;
import my_project.model.DishUI;
import my_project.model.Enemy;

public class DishController {
    private List<Dish> flyingDishes;
    private Dish[] storedDishes;

    private ProgramController programController;
    private DishUI dishUI;
    private int currentDish;

    public DishController(ProgramController pProgramController, ViewController viewController) {
        flyingDishes = new List<>();
        programController = pProgramController;

        dishUI = new DishUI(1425,834);
        viewController.draw(dishUI);

        storedDishes = new Dish[5];
        for (int i = storedDishes.length; i > 0; i--) {
            storedDishes[i - 1] = new Dish("floortile.png", 1400 + 35 * i, 840);
            int help = (int) (Math.random() * 4 +1);
            if (help == 1)
                storedDishes[i - 1] = new Dish("Muffin.png", 1400 + 35 * i, 840);
            if (help == 2)
                storedDishes[i - 1] = new Dish("Spaghet.png", 1400 + 35 * i, 840);
            if (help == 3)
                storedDishes[i - 1] = new Dish("Mikado.png", 1400 + 35 * i, 840);
            if (help == 4)
                storedDishes[i - 1] = new Dish("Cawfee.png", 1400 + 35 * i, 840);
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
        Dish currentDish = getCurrentDish();
        if(currentDish == null)
            return;

        currentDish.setX(programController.getShooter().getX());
        currentDish.setY(programController.getShooter().getY()) ;
        long yLength = (long) (yPos - (programController.getShooter().getY() + programController.getShooter().getImage().getHeight() / 2));
        long xLength = (long) (xPos - (programController.getShooter().getX() + programController.getShooter().getImage().getWidth() / 2));
        double playerRotation = Math.atan2(yLength, xLength);
        double xVel = Math.cos(playerRotation);
        double yVel = Math.sin(playerRotation);
        currentDish.setXVel(xVel);
        currentDish.setYVel(yVel);
        flyingDishes.append(currentDish);

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
     * Returns the current Dish and removes it from the array
     *
     * @return the current Dish
     */
    public Dish getCurrentDish() {
        if (currentDish == -1)
            return null;

        Dish output = storedDishes[currentDish];
        storedDishes[currentDish] = null;
        nextBullet();
        return output;
    }

    public Dish[] getAllStoredDishes() {
        return storedDishes;
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
                if (storedDishes[i] != null) {
                    dishUI.setX(1425+35*i);
                    return i;
                }

        for (int i = 0; i < currentDish; i++) {
            if (storedDishes[i] != null) {
                dishUI.setX(1425+35*i);
                return i;
            }
        }
        return -1;
    }

}
