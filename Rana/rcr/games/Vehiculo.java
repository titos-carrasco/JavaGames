package rcr.games;

import java.awt.Dimension;
import java.awt.Point;

import rcr.lge.GameObject;
import rcr.lge.LittleGameEngine;
import rcr.lge.Sprite;

public class Vehiculo extends Sprite {
    private LittleGameEngine lge;
    private char dir;
    private int pixels;

    public Vehiculo(String iname, char dir, int x, int y, int pixels) {
        super(iname, new Point(x, y));

        // acceso a LGE
        lge = LittleGameEngine.getInstance();

        // los eventos que recibiremos
        setOnEvents(LittleGameEngine.E_ON_UPDATE);
        setOnEvents(LittleGameEngine.E_ON_COLLISION);

        // mis atributos
        enableCollider(true);
        this.dir = dir;
        this.pixels = pixels;
        setTag("Vehiculo");
    }

    @Override
    public void onUpdate(double dt) {
        Dimension csize = lge.getCameraSize();
        int cw = csize.width;
        // int ch = csize.height;

        Point position = getPosition();
        int x = position.x;
        int y = position.y;

        Dimension size = getSize();
        int w = size.width;
        // int h = size.height;

        if (dir == 'R') {
            x = x + pixels;
            if (x > cw)
                x = 0 - w;
        } else {
            x = x - pixels;
            if (x + w < 0)
                x = cw - 1;
        }

        setPosition(x, y);
    }

    @Override
    public void onCollision(double dt, GameObject[] gobjs) {
    }
}