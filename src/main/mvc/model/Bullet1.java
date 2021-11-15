package model;

import controller.Game;

import java.awt.*;
import java.io.IOException;

public class Bullet1 extends Bullet{

    public Bullet1(double doubleX,Sprite sprite) {
        super(doubleX,sprite);
        setDamage(10);
        setWidth(6);
        setHeight(10);
        if (sprite.isBoss()) {
            setWidth(getWidth()*2);
            setHeight(getHeight()*2);
        }
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
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
