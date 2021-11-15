package model;

import controller.Game;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class Boss extends Sprite{

    private boolean israndomwalk;

    private int randomwalkcount;

    public Boss() throws IOException {
        super();
        setHealth(Game.MAXBOSSHEALTH);
        setWidth(400);
        setHeight(400);
        setDoubleX(getDim().width/2-getWidth()/2);
        setDoubleY(-getHeight());
        setDeltaX(5);
        setDeltaY(5);
        setbMoveleft(false);
        setbMoveup(false);
        setbMovedown(true);
        setbMoveright(false);
        setTeam(Team.FOE);
        setBoss(true);
        setbFire(false);
        setBullettype(1);
        setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/boss.png")));
        randomwalkcount = 0;
        israndomwalk = false;
    }

    @Override
    public void move() throws IOException {
        if (israndomwalk) {
            Random rd = new Random();
            if (randomwalkcount%10==0) {
                setbMovedown(rd.nextBoolean());
                setbMoveup(!isbMovedown());
                Myship myship = CommandCenter.getInstance().getFalcon();
                if (getX()>=myship.getX()+myship.getWidth()) {
                    setbMoveleft(true);
                    setbMoveright(false);
                } else if (getX()+getWidth()<=myship.getX()) {
                    setbMoveright(true);
                    setbMoveleft(false);
                }
            }
            if (randomwalkcount%50==0) {
                setBullettype(rd.nextInt(4) + 1);
            }
            randomwalkcount += 1;
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

        if (!israndomwalk) {
            if (getDoubleX() + dX >= 0 && getDoubleX() + dX + getWidth() <= getDim().width && getDoubleY() + dY + getHeight() <= getDim().height / 2) {
                setDoubleX(getDoubleX() + dX);
                setDoubleY(getDoubleY() + dY);
                setX((int) Math.round(getDoubleX()));
                setY((int) Math.round(getDoubleY()));
            } else {
                israndomwalk = true;
            }
        } else {
            if (getDoubleX() + dX >= -getWidth()/2 && getDoubleX() + dX + getWidth() <= getDim().width+getWidth()/2 && getDoubleY() + dY >= 0 && getDoubleY() + dY + getHeight() <= getDim().height-200 ) {
                setDoubleX(getDoubleX() + dX);
                setDoubleY(getDoubleY() + dY);
                setX((int) Math.round(getDoubleX()));
                setY((int) Math.round(getDoubleY()));
            }
        }
    }
}
