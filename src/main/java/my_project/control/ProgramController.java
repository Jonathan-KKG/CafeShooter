package my_project.control;

import KAGO_framework.control.ViewController;
import my_project.model.Enemy;
import my_project.model.Environment;
import my_project.model.Player;

/**
 * Ein Objekt der Klasse ProgramController dient dazu das Programm zu steuern. Die updateProgram - Methode wird
 * mit jeder Frame im laufenden Programm aufgerufen.
 */
public class ProgramController {

    //Attribute
    private Player player;
    private Environment myEnv;
    private Enemy[] enemy;
    private EnemyControler enemyControler;

    // Referenzen
    private ViewController viewController;  // diese Referenz soll auf ein Objekt der Klasse viewController zeigen. Über dieses Objekt wird das Fenster gesteuert.

    /**
     * Konstruktor
     * Dieser legt das Objekt der Klasse ProgramController an, das den Programmfluss steuert.
     * Damit der ProgramController auf das Fenster zugreifen kann, benötigt er eine Referenz auf das Objekt
     * der Klasse viewController. Diese wird als Parameter übergeben.
     * @param viewController das viewController-Objekt des Programms
     */
    public ProgramController(ViewController viewController){
        this.viewController = viewController;
    }

    /**
     * Diese Methode wird genau ein mal nach Programmstart aufgerufen.
     * Sie erstellt die leeren Datenstrukturen, zu Beginn nur eine Queue
     */
    public void startProgram() {
        // Erstelle ein Objekt der Klasse Ball und lasse es zeichnen
        player = new Player(150,150);
        enemy = new Enemy[2];
        try {
            myEnv = new Environment( 100,100);
            enemy[0] = new Enemy(100.,300.);
            enemy[1] = new Enemy(300.,100.);
        } catch (Exception e){
            System.out.println("oopsies");
        }
        viewController.draw(myEnv);
        viewController.draw(player);
        viewController.draw(enemy[0]);
        viewController.draw(enemy[1]);
        enemyControler = new EnemyControler(enemy,player);
    }

    /**
     * Aufruf mit jeder Frame
     * @param dt Zeit seit letzter Frame
     */
    public void updateProgram(double dt){
        enemyControler.updateEnemies(dt);
        System.out.println(enemy[0].getX());
    }
}
