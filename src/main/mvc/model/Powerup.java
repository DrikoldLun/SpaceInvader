package model;

import controller.Game;

import java.util.Random;

public class Powerup extends Sprite{
    private int life;

    public Powerup () {
        super();
        Random rd = new Random();
        setWidth(40);
        setHeight(40);
        setX(rd.nextInt(getDim().width-getWidth()+1));
        setY(rd.nextInt(getDim().height-getHeight()+1));
        setDeltaX(0);
        setDeltaY(0);
        setbMoveleft(false);
        setbMoveright(false);
        setbMoveup(false);
        setbMovedown(false);
        setTeam(Team.POWERUP);
        life = 9000/ Game.ANI_DELAY;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
