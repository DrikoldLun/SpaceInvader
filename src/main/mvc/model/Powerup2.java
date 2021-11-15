package model;

import controller.Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Powerup2 extends Powerup{

    public Powerup2() throws IOException {
        super();
        setImage(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/shield.png"))).getImage());
    }

    @Override
    public void buff() {
        Myship myship = CommandCenter.getInstance().getFalcon();
        myship.setProtectDuration(myship.getProtectDuration()+13500/ Game.ANI_DELAY);
        CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
    }

    @Override
    public void draw(Graphics g) {
        if (getLife()>0) {
            g.setColor(Color.green);
            g.drawOval(getX(), getY(), getWidth(), getHeight());
            g.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
            setLife(getLife()-1);
        } else {
            CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
        }
    }
}
