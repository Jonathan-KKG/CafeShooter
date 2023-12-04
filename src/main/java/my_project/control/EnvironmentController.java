package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Environment;

public class EnvironmentController {
    //Referenzen
    private List<Environment> environmentObjects = new List<>();


    public EnvironmentController(ViewController viewController){
        try{
            environmentObjects.append(new Environment("floortile.png", 100, 100));
            environmentObjects.append(new Environment("leftwall.png", 100, 100));

            environmentObjects.toFirst();
            while(environmentObjects.hasAccess()){
                viewController.draw(environmentObjects.getContent());
                environmentObjects.next();
            }
        } catch (Exception e){
            System.out.println("Creating Environment object went wrong!");
        }



    }
}
