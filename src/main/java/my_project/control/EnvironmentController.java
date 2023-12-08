package my_project.control;
import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.CollidableEnvironment;
import my_project.model.Environment;

public class EnvironmentController {
    //Referenzen
    private List<Environment> environmentObjects = new List<>();
    private List<CollidableEnvironment> collidableEnvironmentObjects = new List<>();

    //Attribute
    private int[] kitchenOffset = {(int) (1080 * 0.85) - 480, (int) (1920 * 0.85) - 785};

    /** Creates and draws all Environment objects
     * @param viewController Required to draw EnvironmentObjects
     */
    public EnvironmentController(ViewController viewController) {
        createObjects(viewController);
    }

    /**creates all Environment objects and adds them to environmentObjects list
     * @param viewController Required to draw Objects
     */
    private void createObjects(ViewController viewController) {
        try{
            // Grass Floor
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    environmentObjects.append(new Environment("grass.png", i*500, j*500));

            for (int i = 0; i < 8*32; i = i + 32)
                for (int j = 0; j < 20*32; j = j + 32)
                    environmentObjects.append(new Environment("floortile.png", kitchenOffset[0]+32+j, kitchenOffset[1]-i));

            for (int i = 0; i < 8*32; i = i + 32)
                collidableEnvironmentObjects.append(new CollidableEnvironment("leftwall.png", kitchenOffset[0]+20, kitchenOffset[1]-i));

            // Calculating coordinates [#####-50%-/////]
            for (int i = 0; i < 20*32; i = i + 32)
                collidableEnvironmentObjects.append(new CollidableEnvironment("topwall.png", kitchenOffset[0]-10+42+i, kitchenOffset[1]+148-12*32));
            collidableEnvironmentObjects.append(new CollidableEnvironment("wallturn1.png", kitchenOffset[0]+20, kitchenOffset[1]+148-12*32));
            collidableEnvironmentObjects.append(new CollidableEnvironment("wallturn2.png", kitchenOffset[0]-384+33*32, kitchenOffset[1]+148-12*32));
            for (int i = 0; i < 8*32; i = i + 32)
                collidableEnvironmentObjects.append(new CollidableEnvironment("rightwall.png", kitchenOffset[0]-384+33*32, kitchenOffset[1]-i));
            collidableEnvironmentObjects.append(new CollidableEnvironment("stovetop.png", kitchenOffset[0]+32, kitchenOffset[1]-32-3*32));
            for (int i = 0; i < 6*64; i = i + 64)
                collidableEnvironmentObjects.append(new CollidableEnvironment("tabletop.png", kitchenOffset[0]+32+i, kitchenOffset[1]-5*32));
            for (int i = 32; i < 6*64; i = i + 64)
                collidableEnvironmentObjects.append(new CollidableEnvironment("tabletop2.png", kitchenOffset[0]+32+i, kitchenOffset[1]-5*32));

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

    public List<CollidableEnvironment> getCollidableEnvironmentObjects() {
        return collidableEnvironmentObjects;
    }
}