package model;

import controller.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Explosion extends Sprite{
    private int life;

    public Explosion(Sprite sprite) throws IOException {
        super();
        setWidth(sprite.getWidth());
        setHeight(sprite.getHeight());
        setX(sprite.getX());
        setY(sprite.getY());
        life = 1500/ Game.ANI_DELAY;
        setTeam(Team.EXPLOSION);
        setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/explosion.png")));
    }

    @Override
    public void draw(Graphics g) {
        if (life > 0) {
            g.drawImage(getImage(),getX(),getY(),getWidth(),getHeight(),null);
            life -= 1;
        } else {
            CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
        }
    }
}
