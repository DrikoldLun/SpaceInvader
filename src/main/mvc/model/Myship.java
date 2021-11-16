package model;

import controller.Game;
import controller.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;


public class Myship extends Sprite {

	// ==============================================================
	// FIELDS 
	// ==============================================================

	private int health;

	private int protectDuration;

	private boolean[] availbullet;

	// ==============================================================
	// CONSTRUCTOR 
	// ==============================================================
	
	public Myship() throws IOException {
		super();
		//these are myship specific
		setWidth(60);
		setHeight(60);
		setDoubleX(getDim().width/2-getWidth()/2);
		setDoubleY(getDim().height-100);
		setDeltaX(10);
		setDeltaY(10);
		setbMoveleft(false);
		setbMoveup(false);
		setbMoveright(false);
		setbMovedown(false);
		setTeam(Team.MYSHIP);
		setProtectDuration(4500/ Game.ANI_DELAY);
		setImage(ImageIO.read(getClass().getClassLoader().getResourceAsStream("images/mycraft.jpg")));
		availbullet = new boolean[4];
		availbullet[0] = availbullet[1] = availbullet[2] = availbullet[3] = true;
		setBullettype(1);
		setbFire(false);
		setHealth(Game.MAXMYHEALTH);
	}
	
	// ==============================================================
	// METHODS 
	// ==============================================================


	public boolean[] getAvailbullet() {
		return availbullet;
	}

	public void setAvailbullet(boolean[] availbullet) {
		this.availbullet = availbullet;
	}

	@Override
	public void move() {
		double dX=0, dY=0;
		if (isbMoveleft())
			dX -= getDeltaX();
		if (isbMoveright())
			dX += getDeltaX();
		if (isbMoveup())
			dY -= getDeltaY();
		if (isbMovedown())
			dY += getDeltaY();

		boolean touchBoss = false;

		for (Sprite foe:CommandCenter.getInstance().getFoes()) {
			if (foe.isBoss()) {
				if (new Rectangle((int) Math.round(getDoubleX()+dX),(int) Math.round(getDoubleY()+dY),getWidth(),getHeight()).intersects(foe.getRect())) {
					touchBoss = true;
					break;
				}
			}
		}

		if (!touchBoss && getDoubleX() + dX >= 0 && getDoubleX() + dX + getWidth() <= getDim().width && getDoubleY() + dY >= 0 && getDoubleY() + dY + getHeight() <= getDim().height) {
			setDoubleX(getDoubleX() + dX);
			setDoubleY(getDoubleY() + dY);
			setX((int) Math.round(getDoubleX()));
			setY((int) Math.round(getDoubleY()));
		}

	} //end move

	public void setProtectDuration(int duration) {
		protectDuration += duration;
	}

	@Override
	public void draw(Graphics g) throws IOException {
		super.draw(g);
		if (protectDuration > 0) {
			g.setColor(Color.white);
			g.drawOval(getX(),getY(),getWidth(),getHeight());
			protectDuration -= 1;
		}
	}

	public int getProtectDuration() {
		return protectDuration;
	}

} //end class
