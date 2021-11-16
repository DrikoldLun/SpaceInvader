package model;

import controller.Game;
import controller.Sound;

import java.awt.*;
import java.io.IOException;

public abstract class Sprite implements Movable {

	private int x; // left x coordinate (int)

	private int y; // top y coordinate (int)

	private double doubleX;  // left x coordinate (double)

	private double doubleY; // top y coordinate (double)

	private double deltaX;  // x change rate

	private double deltaY; // y change rate

	private boolean bMoveup;

	private boolean bMoveleft;

	private boolean bMovedown;

	private boolean bMoveright;

	private int width;

	private int height;

	private boolean bFire;

	private int attackcounter;

	private int attackgap;

	private int bullettype;

	private boolean isBoss;

	private Bullet3 laser;

	//every sprite needs to know about the size of the gaming environ
	private Dimension dim; //dim of the gaming environment

	//we need to know what team we're on
	private Team team;

	private int health; // for myship and foe

	private int damage; // for bullet

	private Image image;

	@Override
	public Team getTeam() {
		//default
	  return team;
	}

	public void setTeam(Team team){
		this.team = team;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Dimension getDim() {
		return dim;
	}

	public void setDim(Dimension dim) {
		this.dim = dim;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public Rectangle getRect() {
		return new Rectangle(x,y,width,height);
	}

	public double getDoubleX() {
		return doubleX;
	}

	public void setDoubleX(double doubleX) {
		this.doubleX = doubleX;
	}

	public double getDoubleY() {
		return doubleY;
	}

	public void setDoubleY(double doubleY) {
		this.doubleY = doubleY;
	}

	public double getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}

	public double getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}

	public boolean isbMoveup() {
		return bMoveup;
	}

	public void setbMoveup(boolean bMoveup) {
		this.bMoveup = bMoveup;
	}

	public boolean isbMoveleft() {
		return bMoveleft;
	}

	public void setbMoveleft(boolean bMoveleft) {
		this.bMoveleft = bMoveleft;
	}

	public boolean isbMovedown() {
		return bMovedown;
	}

	public void setbMovedown(boolean bMovedown) {
		this.bMovedown = bMovedown;
	}

	public boolean isbMoveright() {
		return bMoveright;
	}

	public void setbMoveright(boolean bMoveright) {
		this.bMoveright = bMoveright;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public boolean isbFire() {
		return bFire;
	}

	public void setbFire(boolean bFire) {
		this.bFire = bFire;
		if (!bFire) {
			attackcounter = 0;
		}
	}

	public int getBullettype() {
		return bullettype;
	}

	public void setBullettype(int bullettype) throws IOException {
		this.bullettype = bullettype;
		attackgap = switch (bullettype) {
			case 1 -> new Bullet1(0,this).getAttackgap();
			case 2 -> new Bullet2(0,this).getAttackgap();
			case 4 -> new Bullet4(0,this).getAttackgap();
			default -> 20;
		};
		if (team==Team.FOE)
			if (isBoss) {
				attackgap *= 4;
			} else {
				attackgap *= 10;
			}
	}

	public Bullet3 getLaser() {
		return laser;
	}

	public void setLaser(Bullet3 laser) {
		this.laser = laser;
	}

	public boolean isBoss() {
		return isBoss;
	}

	public void setBoss(boolean boss) {
		isBoss = boss;
	}

	public Sprite() {
		//you can override this and many more in the subclasses
		setDim(Game.DIM);
	}

	public void buff() {}

	// must be overridden for Falcon and Boss
	@Override
	public void move() throws IOException {
		double dX=0, dY=0;
		if (bMoveleft)
			dX -= deltaX;
		if (bMoveright)
			dX += deltaX;
		if (bMoveup)
			dY -= deltaY;
		if (bMovedown)
			dY += deltaY;

		if (doubleX + dX + width < 0 || doubleX + dX > dim.width || doubleY + dY > dim.height || (doubleY + dY + height < 0 && team == Team.BULLET)) {
			CommandCenter.getInstance().getOpsList().enqueue(this, CollisionOp.Operation.REMOVE);
		} else {
			doubleX += dX;
			doubleY += dY;
			x = (int) Math.round(doubleX);
			y = (int) Math.round(doubleY);
		}
	}

	@Override
    public void draw(Graphics g) throws IOException {
		attack();
		if (bFire&&bullettype==3&&laser!=null) {
			laser.draw(g);
		}
		move();
		g.drawImage(image,x,y,width,height,null);
    }

	public void attack() throws IOException {
		if (bFire) {
			if (attackcounter==0 || (isBoss && bullettype==3)) {
				switch (bullettype) {
					case 1 : CommandCenter.getInstance().getOpsList().enqueue(new Bullet1(doubleX+width/2.,this), CollisionOp.Operation.ADD); break;
					case 2 :
						Bullet2 left = new Bullet2(doubleX+width/4.,this);
						left.setbMoveleft(true);
						Bullet2 middle = new Bullet2(doubleX+width/2.,this);
						Bullet2 right = new Bullet2(doubleX+3*width/4.,this);
						right.setbMoveright(true);
						CommandCenter.getInstance().getOpsList().enqueue(left, CollisionOp.Operation.ADD);
						CommandCenter.getInstance().getOpsList().enqueue(middle, CollisionOp.Operation.ADD);
						CommandCenter.getInstance().getOpsList().enqueue(right, CollisionOp.Operation.ADD);
						break;

					case 3 : laser = new Bullet3(doubleX+width/2.,this); break;

					case 4 : CommandCenter.getInstance().getOpsList().enqueue(new Bullet4(doubleX+width/2.,this), CollisionOp.Operation.ADD); break;
					default : break;
				}
			} else if (!isBoss && attackcounter>4 && bullettype==3 && team==Team.FOE) {
				laser = null;
			}
			if (bullettype!=3 || team==Team.FOE)
				attackcounter = (attackcounter + 1) % attackgap;
		}
	}

}
