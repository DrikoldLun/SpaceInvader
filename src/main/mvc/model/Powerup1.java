package model;

import controller.Game;

import java.awt.*;

public class Powerup1 extends Powerup{

    @Override
    public void buff() {
        Myship myship = CommandCenter.getInstance().getFalcon();
        myship.setHealth(Math.min(myship.getHealth()+50, Game.MAXMYHEALTH));
        CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
    }

    @Override
    public void draw(Graphics g) {
        if (getLife()>0) {
            g.setColor(Color.green);
            g.drawOval(getX(), getY(), getWidth(), getHeight());
            g.fillRect(getX() + getWidth() / 2 - 7, getY() + 3, 14, getHeight() - 6);
            g.fillRect(getX() + 3, getY() + getHeight() / 2 - 7, getWidth() - 6, 14);
            setLife(getLife() - 1);
        } else {
            CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
        }
    }
}
