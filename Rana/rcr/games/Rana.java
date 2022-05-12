package rcr.games;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import rcr.lge.GameObject;
import rcr.lge.LittleGameEngine;
import rcr.lge.Sprite;

public class Rana extends Sprite {
    private LittleGameEngine lge;
    private boolean moving;

    public Rana(int x, int y) {
        super("rana-idle", new Point(x, y));

        // acceso a LGE
        lge = LittleGameEngine.getInstance();
        Dimension winSize = lge.getCameraSize();
        setBounds(new Rectangle(new Point(3, 20), new Dimension(winSize.width - 6, winSize.height - 22)));

        // los eventos que recibiremos
        setOnEvents(LittleGameEngine.E_ON_UPDATE);
        setOnEvents(LittleGameEngine.E_ON_COLLISION);

        // mis atributos
        enableCollider(true);
        moving = false;
    }

    @Override
    public void onUpdate(double dt) {
        int pixels = 36;

        Point position = getPosition();
        int x = position.x;
        int y = position.y;

        if (!moving) {
            if (lge.keyPressed(KeyEvent.VK_UP)) {
                setPosition(x, y - pixels);
                setImage("rana-up", 1);
                moving = true;
            } else if (lge.keyPressed(KeyEvent.VK_DOWN)) {
                setPosition(x, y + pixels);
                setImage("rana-down", 1);
                moving = true;
            } else if (lge.keyPressed(KeyEvent.VK_LEFT)) {
                setPosition(x - pixels, y);
                setImage("rana-left", 1);
                moving = true;
            } else if (lge.keyPressed(KeyEvent.VK_RIGHT)) {
                setPosition(x + pixels, y);
                setImage("rana-right", 1);
                moving = true;
            }
        } else {
            if (!lge.keyPressed(KeyEvent.VK_UP) && !lge.keyPressed(KeyEvent.VK_DOWN)
                    && !lge.keyPressed(KeyEvent.VK_LEFT) && !lge.keyPressed(KeyEvent.VK_RIGHT)) {
                nextImage();
                moving = false;
            }
        }
    }

    @Override
    public void onCollision(double dt, GameObject[] gobjs) {
    }
}