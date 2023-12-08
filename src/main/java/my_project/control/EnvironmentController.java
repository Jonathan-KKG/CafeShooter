package my_project.control;
import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Environment;

public class EnvironmentController {
    //Referenzen
    private List<Environment> environmentObjects = new List<>();
    private List<Environment> collidableEnvironmentObjects = new List<>();

    /**
     * @param viewController Required to draw EnvironmentObjects
     */
    public EnvironmentController(ViewController viewController) {
        createObjects(viewController);
    }

    /**creates all Environment objects and adds them to environmentObjects list
     * @param viewController Required to draw Objects
     */
    private void createObjects(ViewController viewController) {
        int[] kitchenOffset = {400,1026};
        try{
            // sprite = 32px :^)
            for (int i = 0; i < 5; i++)
                for (int j = 0; j < 4; j++)
                    environmentObjects.append(new Environment("grass.png", i*500, j*500));

            for (int i = 0; i < 12*32; i = i + 32)
                for (int j = 0; j < 32*32; j = j + 32)
                    environmentObjects.append(new Environment("floortile.png", kitchenOffset[0]+32+j, kitchenOffset[1]-i));

            for (int i = 0; i < 12*32; i = i + 32)
                collidableEnvironmentObjects.append(new Environment("leftwall.png", kitchenOffset[0]+20, kitchenOffset[1]-i));

            // Calculating coordinates [#####-50%-/////]
            for (int i = 0; i < 32*32; i = i + 32)
                collidableEnvironmentObjects.append(new Environment("topwall.png", kitchenOffset[0]-10+42+i, kitchenOffset[1]+20-12*32));
            collidableEnvironmentObjects.append(new Environment("wallturn1.png", kitchenOffset[0]+20, kitchenOffset[1]+20-12*32));
            collidableEnvironmentObjects.append(new Environment("wallturn2.png", kitchenOffset[0]+33*32, kitchenOffset[1]+20-12*32));
            for (int i = 0; i < 12*32; i = i + 32)
                collidableEnvironmentObjects.append(new Environment("rightwall.png", kitchenOffset[0]+33*32, kitchenOffset[1]-i));
            collidableEnvironmentObjects.append(new Environment("stovetop.png", kitchenOffset[0]+32, kitchenOffset[1]-32-3*32));
            for (int i = 0; i < 6*64; i = i + 64)
                collidableEnvironmentObjects.append(new Environment("tabletop.png", kitchenOffset[0]+32+i, kitchenOffset[1]-5*32));
            for (int i = 32; i < 6*64; i = i + 64)
                collidableEnvironmentObjects.append(new Environment("tabletop2.png", kitchenOffset[0]+32+i, kitchenOffset[1]-5*32));

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