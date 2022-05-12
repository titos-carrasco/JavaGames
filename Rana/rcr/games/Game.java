package rcr.games;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.KeyEvent;

import rcr.lge.Canvas;
import rcr.lge.IEvents;
import rcr.lge.LittleGameEngine;
import rcr.lge.Sprite;

public class Game implements IEvents {
    private LittleGameEngine lge;

    public Game() {
        Dimension winSize = new Dimension(544, 416);

        lge = new LittleGameEngine(winSize, "La Rana", new Color(0, 0, 0));
        lge.setOnMainUpdate(this);
        lge.showColliders(new Color(255, 0, 0));

        // cargamos los recursos que usaremos
        String resourceDir = lge.getRealPath(this, "../../resources");
        lge.loadImage("fondo", resourceDir + "/world.png", 1, false, false);
        lge.loadImage("rana-idle", resourceDir + "/rana-idle.png", false, false);
        lge.loadImage("rana-up", resourceDir + "/rana-up*.png", false, false);
        lge.loadImage("rana-down", resourceDir + "/rana-up*.png", false, true);
        lge.loadImage("rana-left", resourceDir + "/rana-left*.png", false, false);
        lge.loadImage("rana-right", resourceDir + "/rana-left*.png", true, false);
        lge.loadImage("auto-azul", resourceDir + "/auto_azul.png", false, false);
        lge.loadImage("auto-rojo", resourceDir + "/auto_rojo.png", true, false);
        lge.loadImage("tortuga", resourceDir + "/tortuga_*.png", false, false);
        lge.loadImage("tronco-largo", resourceDir + "/tronco_largo.png", false, false);

        // el fondo
        Sprite fondo = new Sprite("fondo", new Point(0, 0));
        lge.addGObject(fondo, 0);

        // el hogar
        Canvas home;
        for (int i = 0; i < 5; i++) {
            home = new Canvas(new Point(42 + i * 108, 22), new Dimension(30, 30));
            home.enableCollider(true);
            home.setTag("home-" + i);
            lge.addGObject(home, 1);
        }

        // el agua
        Canvas agua = new Canvas(new Point(0, 53), new Dimension(544, 144));
        agua.enableCollider(true);
        agua.setTag("agua");
        lge.addGObject(agua, 1);

        // las tortugas
        Tortuga tortuga = new Tortuga("tortuga", 'R', 10, 60, 1);
        lge.addGObject(tortuga, 1);

        // los troncos
        Tronco tronco = new Tronco("tronco-largo", 'R', 80, 100, 2);
        lge.addGObject(tronco, 1);

        // los vehiculos
        Vehiculo car = new Vehiculo("auto-azul", 'R', 10, 310, 2);
        lge.addGObject(car, 1);

        car = new Vehiculo("auto-rojo", 'L', 190, 238, 1);
        lge.addGObject(car, 1);

        // la rana
        Rana rana = new Rana(255, 380);
        lge.addGObject(rana, 1);
    }

    public void onMainUpdate(double dt) {
        // abortamos con la tecla Escape
        if (lge.keyPressed(KeyEvent.VK_ESCAPE))
            lge.quit();
    }

    // main loop
    public void Run(int fps) {
        lge.run(fps);
    }

    // show time
    public static void main(String[] args) {
        Game game = new Game();
        game.Run(60);
        System.out.println("Eso es todo!!!");
    }
}
