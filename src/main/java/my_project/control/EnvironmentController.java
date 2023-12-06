package my_project.control;
import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Environment;

public class EnvironmentController {
    //Referenzen
    private List<Environment> environmentObjects = new List<>();
    private List<Environment> collidableEnvironmentObjects = new List<>();

    /**
     * @param viewController Needed to draw EnvironmentObjects
     */
    public EnvironmentController(ViewController viewController) {
        createObjects(viewController);
    }

    /**creates all Environment objects and adds them to environmentObjects list
     * @param viewController Needed to draw Objects
     *
     */
    private void createObjects(ViewController viewController) {
        try{
            // sprite = 32px :^)
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    environmentObjects.append(new Environment("grass.png", i*500, j*500));
                }
            }
            for (int i = 0; i < 12*32; i = i + 32) {
                for (int j = 0; j < 32*32; j = j + 32) {
                    environmentObjects.append(new Environment("floortile.png", 282+j, 932-i));
                }
            }
            for (int i = 0; i < 12*32; i = i + 32) {
                collidableEnvironmentObjects.append(new Environment("leftwall.png", 250, 932-i));
            }
            // Calculating coordinates [#####-50%-/////]
            collidableEnvironmentObjects.append(new Environment("wallturn1.png", 250, 932 - 12 * 32));
            for (int i = 0; i < 32*32; i = i + 32) {
                collidableEnvironmentObjects.append(new Environment("topwall.png", 250+32+i, 932 - 12 * 32));
            }
            collidableEnvironmentObjects.append(new Environment("wallturn2.png", 250+33*32, 932 - 12 * 32));
            for (int i = 0; i < 12*32; i = i + 32) {
                collidableEnvironmentObjects.append(new Environment("rightwall.png", 250+33*32, 932-i));
            }
            collidableEnvironmentObjects.append(new Environment("stovetop.png", 250+32, 900-3*32));

            environmentObjects.toFirst();
            while(environmentObjects.hasAccess()){
                viewController.draw(environmentObjects.getContent());
                environmentObjects.next();
            }
            collidableEnvironmentObjects.toFirst();
            while(collidableEnvironmentObjects.hasAccess()){
                viewController.draw(collidableEnvironmentObjects.getContent());
                collidableEnvironmentObjects.next();
            }
        } catch (Exception e){
            System.out.println("Creating Environment object went wrong!");
        }
    }

    public List<Environment> getCollidableEnvironmentObjects() {
        return collidableEnvironmentObjects;
    }
}