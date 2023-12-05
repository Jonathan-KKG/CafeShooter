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

    public void createProjectile(double xPos, double yPos){
        double xDir = xPos - shooter.getX();
        double yDir = yPos - shooter.getY();


    }



}
