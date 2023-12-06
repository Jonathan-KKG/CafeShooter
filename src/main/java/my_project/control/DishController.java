package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Dish;
import my_project.model.Shooter;

public class DishController {
    private List<Dish> dishes;
    private Shooter shooter;

    public DishController(Shooter pShooter) {
        dishes = new List<>();
        shooter = pShooter;
    }

    public void shoot(double xPos, double yPos) {
        if (!shooter.getDishes().isEmpty()) {
            long yLength = (long) (yPos - (shooter.getY() + shooter.getImage().getHeight() / 2));
            long xLength = (long) (xPos - (shooter.getX() + shooter.getImage().getWidth() / 2));
            double playerRotation = Math.atan2(yLength, xLength);
            double xVel = Math.cos(playerRotation);//xVel
            double yVel = Math.sin(playerRotation);//yVel
            shooter.getDishes().front().setXVel(xVel);
            shooter.getDishes().front().setYVel(yVel);
            dishes.append(shooter.getDishes().front());
            shooter.getDishes().dequeue();
        }
    }

    /**
     * Updates Dish movement
     *
     * @param dt Benötigt um jeden Frame zu Updaten
     */
    public void dishUpdate(double dt) {
        dishes.toFirst();
        while (dishes.hasAccess()) {
            dishes.getContent().setX((dishes.getContent().getX() + dishes.getContent().setXVel() * 500 * dt));
            dishes.getContent().setY((dishes.getContent().getY() + dishes.getContent().getYVel() * 500 * dt));
            dishes.next();
        }
    }


}
