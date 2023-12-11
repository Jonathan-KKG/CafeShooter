package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Dish;
import my_project.model.Enemy;
import my_project.model.Shooter;

public class DishController {
    private List<Dish> dishes;
    private Shooter shooter;
    private ViewController viewController;

    public DishController(Shooter pShooter, ViewController pViewController) {
        dishes = new List<>();
        shooter = pShooter;
        viewController = pViewController;
    }

    /**
     * Calculate velocity of thrown Dish and adds it to the List of thrown Dishes
     *
     * @param xPos x-Position of the Cursor
     * @param yPos y-Position of the Cursor
     */
    public void shoot(double xPos, double yPos) {
        Dish currentDish = shooter.getCurrentDish();
        currentDish.setX(shooter.getX());
        currentDish.setY(shooter.getY());
        long yLength = (long) (yPos - (shooter.getY() + shooter.getImage().getHeight() / 2));
        long xLength = (long) (xPos - (shooter.getX() + shooter.getImage().getWidth() / 2));
        double playerRotation = Math.atan2(yLength, xLength);
        double xVel = Math.cos(playerRotation);
        double yVel = Math.sin(playerRotation);
        currentDish.setXVel(xVel);
        currentDish.setYVel(yVel);
        dishes.append(currentDish);

    }

    /**
     * Moves all Dishes that are thrown (all in the List dishes)
     *
     * @param dt the Time passed between this and the last call of the method
     */
    public void dishUpdate(double dt) {
        dishes.toFirst();
        while (dishes.hasAccess()) {
            dishes.getContent().setX((dishes.getContent().getX() + dishes.getContent().setXVel() * 500 * dt));
            dishes.getContent().setY((dishes.getContent().getY() + dishes.getContent().getYVel() * 500 * dt));
            dishes.next();
        }
    }

    /**
     * Checks collision between Enemy and Dishes. If a Dish hits an Enemy, it gets deleted and if it has the right type the Enemy dies.
     * All Dishes outside the map get deleted.
     *
     * @param pEnemies an Array of all existing Enemies
     */
    public void checkCollisions(Enemy[] pEnemies) {
        dishes.toFirst();
        boolean removed = false;
        while (dishes.hasAccess()) {
            for (int i = 0; i < pEnemies.length; i++) {
                if (pEnemies[i] != null && dishes.getContent().collidesWith(pEnemies[i])) {
                    if (pEnemies[i].getRequiredDish().equals(dishes.getContent().getClass().toString())) {
                        viewController.removeDrawable(pEnemies[i]);
                        pEnemies[i] = null;
                        i = pEnemies.length;
                        removed = true;
                    }
                    viewController.removeDrawable(dishes.getContent());
                    dishes.remove();
                }
            }
            if (!removed) {
                dishes.next();
                if (dishes.hasAccess() && (dishes.getContent().getY() + dishes.getContent().getHeight() < 0 ||
                        dishes.getContent().getY() > 1109 ||
                        dishes.getContent().getX() + dishes.getContent().getWidth() < 0 ||
                        dishes.getContent().getX() > 1920)
                ) {
                    viewController.removeDrawable(dishes.getContent());
                    dishes.remove();
                }
            }
        }
    }

}
