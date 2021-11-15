package model;

import java.io.IOException;

public class Foe extends Sprite{
    public Foe() throws IOException {
        super();
        setWidth(60);
        setHeight(60);
        setDoubleX(0);
        setDoubleY(-getHeight());
        setDeltaX(0);
        setDeltaY(5);
        setbMoveleft(false);
        setbMoveup(false);
        setbMovedown(true);
        setbMoveright(false);
        setTeam(Team.FOE);
        setbFire(false);
        setBoss(false);
        setBullettype(1);
    }
}
