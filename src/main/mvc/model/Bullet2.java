package model;

import controller.Game;

import java.awt.*;
import java.io.IOException;

public class Bullet2 extends Bullet{

    public Bullet2(double doubleX, Sprite sprite) {
        super(doubleX,sprite);
        setDamage(10);
        setWidth(10);
        setHeight(10);
        if (sprite.isBoss()) {
            setWidth(getWidth()*2);
            setHeight(getHeight()*2);
        }
        setDeltaX(5);
        setDeltaY(10);
        setX(getX()-getWidth()/2);
        setDoubleX(getDoubleX()-getWidth()/2);
        if (getUser()==Team.MYSHIP) {
            setY(sprite.getY()+getHeight());
            setDoubleY(sprite.getDoubleY()+getHeight());
        }
        setAttackgap(200/ Game.ANI_DELAY);
    }

    @Override
    public void draw(Graphics g) throws IOException {
        move();
        if (getUser()==Team.MYSHIP) {
            g.setColor(Color.yellow);
        } else if (getUser()==Team.FOE) {
            g.setColor(Color.red);
        }
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }
}
