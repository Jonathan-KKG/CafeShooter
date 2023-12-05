package my_project.control;

import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Projectile;
import my_project.model.Shooter;

public class DishController {
    private List<Projectile> projectiles;
    private Shooter shooter;

    public DishController(Shooter pShooter) {
        projectiles = new List<>();
        shooter = pShooter;
    }

    public void createProjectile(double xPos, double yPos){
        double xDir = xPos - shooter.getX();
        double yDir = yPos - shooter.getY();

        //projectiles.append(new Projectile(shooter.getX(), shooter.getY(), xDir, yDir));
    }

    public void deleteProjectile(Projectile pProjectile){

    }

}
