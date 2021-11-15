package model;

import java.awt.*;
import java.io.IOException;

public interface Movable {

	public static enum Team {
		MYSHIP, POWERUP, FOE, BULLET, EXPLOSION, FOEBULLET
	}

	//for the game to move and draw movable objects
	public void move() throws IOException;
	public void draw(Graphics g) throws IOException;

	//for collision detection
	public Rectangle getRect();
	public Team getTeam();

} //end Movable
