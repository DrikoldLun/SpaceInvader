package model;

import java.awt.*;

public class Bullet3 extends Bullet{
    public Bullet3(double doubleX, Sprite sprite) {
        super(doubleX,sprite);
        setDamage(5);
        setWidth(10);
        if (sprite.isBoss()) {
            setWidth(getWidth()*2);
        }
        if (getUser()==Team.MYSHIP) {
            setHeight(sprite.getY());
            setY(0);
        } else if (getUser()==Team.FOE) {
            setHeight(getDim().height-sprite.getY()-sprite.getHeight());
            setY(sprite.getY()+ sprite.getHeight());
        }
        setX(getX()-getWidth()/2);
    }

    @Override
    public void draw(Graphics g) {
        if (getUser()==Team.MYSHIP) {
            g.setColor(Color.yellow);
        } else if (getUser()==Team.FOE) {
            g.setColor(Color.red);
        }
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
}
