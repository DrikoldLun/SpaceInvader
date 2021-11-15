package model;

import controller.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Bullet4 extends Bullet{
    public Bullet4(double doubleX, Sprite sprite) throws IOException {
        super(doubleX,sprite);
        setDamage(20);
        setWidth(20);
        setHeight(40);
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
        setAttackgap(400/ Game.ANI_DELAY);
        if (getUser()==Team.MYSHIP) {
            setDeltaX(10);
            setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/missile.png")));
        } else if (getUser()==Team.FOE) {
            setDeltaX(5);
            setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/missile_inv.png")));
        }
    }

    @Override
    public void move() {
        setbMoveleft(false);
        setbMoveright(false);
        if (getUser()==Team.MYSHIP) {
            int cx = getX()+getWidth()/2;
            int cy = getY()+getHeight()/2;
            double mindist = Double.MAX_VALUE;
            double dist;
            int cx_foe;
            int cy_foe;
            for (Sprite foe:CommandCenter.getInstance().getFoes()) {
                cx_foe = foe.getX()+foe.getWidth()/2;
                cy_foe = foe.getY()+foe.getHeight()/2;
                dist = Math.hypot(cx-cx_foe,cy-cy_foe);
                if (dist<mindist && getY()>=foe.getY() && foe.getY()+foe.getHeight()>=0) {
                    if (getX()>=foe.getX()+foe.getWidth()) {
                        setbMoveleft(true);
                    } else if (getX()+getWidth()<=foe.getX()) {
                        setbMoveright(true);
                    }
                    mindist = dist;
                }
            }
        } else if (getUser()==Team.FOE) {
            Myship myship = CommandCenter.getInstance().getFalcon();
            if (getY()+getHeight()<=myship.getY()+myship.getHeight()) {
                if (getX()>=myship.getX()+myship.getWidth()) {
                    setbMoveleft(true);
                } else if (getX()+getWidth()<=myship.getX()) {
                    setbMoveright(true);
                }
            }
        }

        double dX=0, dY=0;
        if (isbMoveleft())
            dX -= getDeltaX();
        if (isbMoveright())
            dX += getDeltaX();
        if (isbMoveup())
            dY -= getDeltaY();
        if (isbMovedown())
            dY += getDeltaY();

        if (getDoubleX() + dX >= 0 && getDoubleX() + dX + getWidth() <= getDim().width) {
            setDoubleX(getDoubleX() + dX);
            setX((int) Math.round(getDoubleX()));
        }
        if (getDoubleY() + dY + getHeight() >= 0 && getDoubleY()+dY <= getDim().height) {
            setDoubleY(getDoubleY() + dY);
            setY((int) Math.round(getDoubleY()));
        } else {
            CommandCenter.getInstance().getOpsList().enqueue(this,CollisionOp.Operation.REMOVE);
        }

    } //end move

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(),null);
    }
}
