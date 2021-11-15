package model;

public class Bullet extends Sprite{
    private Team user;

    private int attackgap;

    public Bullet(double doubleX,Sprite sprite) {
        super();
        setX((int) Math.round(doubleX));
        setDoubleX(doubleX);
        setDoubleX(doubleX);
        user = sprite.getTeam();
        if (user==Team.MYSHIP) {
            setbMovedown(false);
            setbMoveup(true);
        } else if (user==Team.FOE) {
            setY(sprite.getY()+ sprite.getHeight());
            setDoubleY(sprite.getDoubleY()+sprite.getHeight());
            setbMovedown(true);
            setbMoveup(false);
        }
        setbMoveleft(false);
        setbMoveright(false);
        if (user==Team.MYSHIP) {
            setTeam(Team.BULLET);
        } else if (user==Team.FOE) {
            setTeam(Team.FOEBULLET);
        }
    }

    public Team getUser() {
        return user;
    }

    public int getAttackgap() {
        return attackgap;
    }

    public void setAttackgap(int attackgap) {
        this.attackgap = attackgap;
    }
}
