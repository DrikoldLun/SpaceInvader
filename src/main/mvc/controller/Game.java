package controller;

import model.*;
import view.GamePanel;

import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Random;

// ===============================================
// == This Game class is the CONTROLLER
// ===============================================

public class Game implements Runnable, KeyListener {

	// ===============================================
	// FIELDS
	// ===============================================

	public static final Dimension DIM = new Dimension(1100, 800); //the dimension of the game.
	public static final int MAXMYHEALTH = 100;
	public static final int MAXBOSSHEALTH = 5000;
	private GamePanel gmpPanel;
	public static Random R = new Random();
	public final static int ANI_DELAY = 45; // milliseconds between screen
											// updates (animation)
	private Thread thrAnim;
	private int nTick = 0;
	private int winTick = 0;

	private boolean bMuted = false;
	

	private final int PAUSE = 80, // p key
			QUIT = 81, // q key
			LEFT = 37, // move left; left arrow
			RIGHT = 39, // move right; right arrow
			UP = 38, // move up; up arrow
			DOWN = 40, // move down; down arrow
			START = 83, // s key
			FIRE = 32, // space key
			MUTE = 77, // m-key mute
			BULLET1 = 49, // 1
			BULLET2 = 50, // 2
			BULLET3 = 51, // 3
			BULLET4 = 52; // 4

	// for possible future use
	// HYPER = 68, 					// d key
	// SHIELD = 65, 				// a key arrow
	// NUM_ENTER = 10, 				// hyp
	 // SPECIAL = 70; 					// fire special weapon;  F key

	private Clip clpThrust;
	private Clip clpMusicBackground;

	private static final int SPAWN_NEW_SHIP_FLOATER = 1200;



	// ===============================================
	// ==CONSTRUCTOR
	// ===============================================

	public Game() {

		gmpPanel = new GamePanel(DIM);
		gmpPanel.addKeyListener(this);
		clpThrust = Sound.clipForLoopFactory("whitenoise.wav");
		clpMusicBackground = Sound.clipForLoopFactory("music-background.wav");
	

	}

	// ===============================================
	// ==METHODS
	// ===============================================

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() { // uses the Event dispatch thread from Java 5 (refactored)
					public void run() {
						try {
							Game game = new Game(); // construct itself
							game.fireUpAnimThread();

						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
	}

	private void fireUpAnimThread() { // called initially
		if (thrAnim == null) {
			thrAnim = new Thread(this); // pass the thread a runnable object (this)
			thrAnim.start();
		}
	}

	// implements runnable - must have run method
	public void run() {

		// lower this thread's priority; let the "main" aka 'Event Dispatch'
		// thread do what it needs to do first
		thrAnim.setPriority(Thread.MIN_PRIORITY);

		// and get the current time
		long lStartTime = System.currentTimeMillis();

		// this thread animates the scene
		while (Thread.currentThread() == thrAnim) {

			tick();
			if (CommandCenter.getInstance().isPlaying())
				CommandCenter.getInstance().setLevel(CommandCenter.getInstance().getLevel()+1);
			// spawnNewShipFloater();
			gmpPanel.update(gmpPanel.getGraphics()); // update takes the graphics context we must 
														// surround the sleep() in a try/catch block
														// this simply controls delay time between 
														// the frames of the animation
			//this might be a good place to check for collisions
			try {
				checkCollisions();
			} catch (IOException e) {
				e.printStackTrace();
			}
			//this might be a god place to check if the level is clear (no more foes)
			//if the level is clear then spawn some big asteroids -- the number of asteroids 
			//should increase with the level. 
			try {
				checkNewLevel();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (CommandCenter.getInstance().isbWin() && getTick()==winTick+100) {
				CommandCenter.getInstance().setPlaying(false);
			}

			try {
				// The total amount of time is guaranteed to be at least ANI_DELAY long.  If processing (update) 
				// between frames takes longer than ANI_DELAY, then the difference between lStartTime - 
				// System.currentTimeMillis() will be negative, then zero will be the sleep time
				lStartTime += ANI_DELAY;
				Thread.sleep(Math.max(0,
						lStartTime - System.currentTimeMillis()));
			} catch (InterruptedException e) {
				// just skip this frame -- no big deal
				continue;
			}
		} // end while
	} // end run

	private void checkCollisions() throws IOException {

		Myship myship = CommandCenter.getInstance().getFalcon();

		for (Sprite foe : CommandCenter.getInstance().getFoes()) {

			if (myship.getY()-foe.getY()-foe.getHeight()<DIM.height/2 && myship.getY()>=foe.getY()+foe.getHeight()) {
				foe.setbFire(true);
			} else {
				foe.setbFire(false);
			}
			//detect collision
			if (foe.isbFire() && foe.getBullettype()==3 && foe.getLaser()!=null) {
				if (foe.getLaser().getRect().intersects(myship.getRect())) {
					if (CommandCenter.getInstance().getFalcon().getProtectDuration()<=0){
						myship.setHealth(myship.getHealth()-foe.getLaser().getDamage());
						killMyship();
					}
				}
			}
			if (myship.getRect().intersects(foe.getRect())) {
				//myship
				if (myship.getProtectDuration()<=0){
					if (CommandCenter.getInstance().getFalcon().getProtectDuration()<=0){
						myship.setHealth(myship.getHealth()-foe.getHealth());
						killMyship();
					}
				}
				//kill the foe and if asteroid, then spawn new asteroids
				if ((!foe.isBoss()) && myship.getProtectDuration()>0 || myship.getHealth()>=foe.getHealth()) {
					CommandCenter.getInstance().getOpsList().enqueue(foe, CollisionOp.Operation.REMOVE);
					CommandCenter.getInstance().getOpsList().enqueue(new Explosion(foe), CollisionOp.Operation.ADD);
					Sound.playSound("kapow.wav");
					CommandCenter.getInstance().setScore(CommandCenter.getInstance().getScore()+1);
				}

			}//end if
			else if (myship.isbFire() && myship.getBullettype()==3 && myship.getLaser()!=null) {
				if (myship.getLaser().getRect().intersects(foe.getRect())) {
					foe.setHealth(foe.getHealth()-myship.getLaser().getDamage());
					if (foe.getHealth()<=0) {
						CommandCenter.getInstance().getOpsList().enqueue(foe, CollisionOp.Operation.REMOVE);
						CommandCenter.getInstance().getOpsList().enqueue(new Explosion(foe), CollisionOp.Operation.ADD);
						Sound.playSound("kapow.wav");
						if (foe.isBoss()){
							CommandCenter.getInstance().setbWin(true);
						} else {
							CommandCenter.getInstance().setScore(CommandCenter.getInstance().getScore()+1);
						}
						break;
					}
				}
			}
			else {
				for (Sprite bullet : CommandCenter.getInstance().getBullets()) {
					if (foe.getRect().intersects(bullet.getRect())) {
						CommandCenter.getInstance().getOpsList().enqueue(bullet, CollisionOp.Operation.REMOVE);
						foe.setHealth(foe.getHealth()-bullet.getDamage());
						if (foe.getHealth()<=0) {
							CommandCenter.getInstance().getOpsList().enqueue(foe, CollisionOp.Operation.REMOVE);
							CommandCenter.getInstance().getOpsList().enqueue(new Explosion(foe), CollisionOp.Operation.ADD);
							Sound.playSound("kapow.wav");
							if (foe.isBoss()){
								CommandCenter.getInstance().setbWin(true);
							} else {
								CommandCenter.getInstance().setScore(CommandCenter.getInstance().getScore()+1);
							}
							break;
						}
					}
				}
			}
		}

		for (Sprite foebullet : CommandCenter.getInstance().getFoebullets()) {
			if (myship.getRect().intersects(foebullet.getRect())) {
				CommandCenter.getInstance().getOpsList().enqueue(foebullet, CollisionOp.Operation.REMOVE);
				if (myship.getProtectDuration()<=0){
					myship.setHealth(myship.getHealth()-foebullet.getDamage());
					killMyship();
				}
			}
		}

		for (Sprite powerup : CommandCenter.getInstance().getPowerups()) {
			if (CommandCenter.getInstance().getFalcon().getRect().intersects(powerup.getRect())) {
				powerup.buff();
			}
		}


		//we are dequeuing the opsList and performing operations in serial to avoid mutating the movable arraylists while iterating them above
		while(!CommandCenter.getInstance().getOpsList().isEmpty()){
			CollisionOp cop =  CommandCenter.getInstance().getOpsList().dequeue();
			Sprite sprite = cop.getSprite();
			CollisionOp.Operation operation = cop.getOperation();

			switch (sprite.getTeam()){

				case FOE:
					if (operation == CollisionOp.Operation.ADD){
						CommandCenter.getInstance().getFoes().add(sprite);
					} else {
						CommandCenter.getInstance().getFoes().remove(sprite);
					}

					break;
				case POWERUP:
					if (operation == CollisionOp.Operation.ADD){
						CommandCenter.getInstance().getPowerups().add(sprite);
					} else {
						CommandCenter.getInstance().getPowerups().remove(sprite);
					}
					break;

				case BULLET:
					if (operation == CollisionOp.Operation.ADD){
						CommandCenter.getInstance().getBullets().add(sprite);
					} else {
						CommandCenter.getInstance().getBullets().remove(sprite);
					}
					break;

				case FOEBULLET:
					if (operation == CollisionOp.Operation.ADD){
						CommandCenter.getInstance().getFoebullets().add(sprite);
					} else {
						CommandCenter.getInstance().getFoebullets().remove(sprite);
					}
					break;

				case EXPLOSION:
					if (operation == CollisionOp.Operation.ADD){
						CommandCenter.getInstance().getExplosions().add(sprite);
					} else {
						CommandCenter.getInstance().getExplosions().remove(sprite);
					}
					break;
			}

		}
		//a request to the JVM is made every frame to garbage collect, however, the JVM will choose when and how to do this
		System.gc();
		
	}//end meth

	private void killMyship() throws IOException {
		Myship myship = CommandCenter.getInstance().getFalcon();
		if (myship.getHealth()<=0) {
			CommandCenter.getInstance().getOpsList().enqueue(myship, CollisionOp.Operation.REMOVE);
			CommandCenter.getInstance().getOpsList().enqueue(new Explosion(myship), CollisionOp.Operation.ADD);
			Sound.playSound("kapow.wav");
			CommandCenter.getInstance().spawnMyship(false);
		}
	}

	private void killFoe(Movable movFoe) {
		/*
		if (movFoe instanceof Asteroid){

			//we know this is an Asteroid, so we can cast without threat of ClassCastException
			Asteroid astExploded = (Asteroid)movFoe;
			//big asteroid 
			if(astExploded.getSize() == 0){
				//spawn two medium Asteroids
				CommandCenter.getInstance().getOpsList().enqueue(new Asteroid(astExploded), CollisionOp.Operation.ADD);
				CommandCenter.getInstance().getOpsList().enqueue(new Asteroid(astExploded), CollisionOp.Operation.ADD);

			} 
			//medium size aseroid exploded
			else if(astExploded.getSize() == 1){
				//spawn three small Asteroids
				CommandCenter.getInstance().getOpsList().enqueue(new Asteroid(astExploded), CollisionOp.Operation.ADD);
				CommandCenter.getInstance().getOpsList().enqueue(new Asteroid(astExploded), CollisionOp.Operation.ADD);
				CommandCenter.getInstance().getOpsList().enqueue(new Asteroid(astExploded), CollisionOp.Operation.ADD);

			}

		} 

		//remove the original Foe
		CommandCenter.getInstance().getOpsList().enqueue(movFoe, CollisionOp.Operation.REMOVE);
		*/
	}

	//some methods for timing events in the game,
	//such as the appearance of UFOs, floaters (power-ups), etc. 
	public void tick() {
		if (nTick == Integer.MAX_VALUE)
			nTick = 0;
		else
			nTick++;
	}

	public int getTick() {
		return nTick;
	}

	/*
	private void spawnNewShipFloater() {
		//make the appearance of power-up dependent upon ticks and levels
		//the higher the level the more frequent the appearance
		if (nTick % (SPAWN_NEW_SHIP_FLOATER - nLevel * 7) == 0) {
			//CommandCenter.getInstance().getMovFloaters().enqueue(new NewShipFloater());
			CommandCenter.getInstance().getOpsList().enqueue(new NewShipFloater(), CollisionOp.Operation.ADD);
		}
	}
	 */

	// Called when user presses 's'
	private void startGame() throws IOException {
		winTick = 0;
		nTick = 0;
		CommandCenter.getInstance().clearAll();
		CommandCenter.getInstance().initGame();
		CommandCenter.getInstance().setLevel(0);
		CommandCenter.getInstance().setPlaying(true);
		CommandCenter.getInstance().setPaused(false);
		if (!bMuted)
			clpMusicBackground.loop(Clip.LOOP_CONTINUOUSLY);
	}

	//this method spawns new asteroids
	private void spawnBoss() throws IOException {
		CommandCenter.getInstance().getOpsList().enqueue(new Boss(), CollisionOp.Operation.ADD);
	}

	private int spawnFoes(int foetype,int num,int yshift) throws IOException {
		int xshift = R.nextInt(DIM.width-Math.min(num,5)*70+11);
		Foe foe;
		for(int i=0;i<num;i++) {
			if (foetype == 1) {
				foe = new Foe1();
			} else if (foetype == 2) {
				foe = new Foe2();
			} else if (foetype == 3) {
				foe = new Foe3();
			} else if (foetype == 4) {
				foe = new Foe4();
			} else {
				foe = null;
			}
			if (foe!=null) {
				foe.setDoubleX(foe.getDoubleX()+xshift+i%5*(foe.getWidth()+20));
				foe.setDoubleY(foe.getDoubleY()-i/5*(foe.getHeight()+10)-yshift);
				CommandCenter.getInstance().getOpsList().enqueue(foe, CollisionOp.Operation.ADD);
			}
		}
		return yshift + (num/5+1)*70;
	}

	private void spawnFoesgrp() throws IOException {
		int ngrps = R.nextInt(5)+1;
		int ylo = 0;
		for (int i=0;i<ngrps;i++) {
			if (ylo >= DIM.height)
				break;
			ylo = spawnFoes(R.nextInt(4)+1,R.nextInt(15)+1,R.nextInt(DIM.height-ylo)+ylo);
		}
	}

	private void spawnPowerup() throws IOException {
		int poweruptype = R.nextInt(2)+1;
		Powerup powerup = null;
		if (poweruptype == 1) {
			powerup = new Powerup1();
		} else if (poweruptype == 2) {
			powerup = new Powerup2();
		}
		if (powerup!=null)
			CommandCenter.getInstance().getOpsList().enqueue(powerup, CollisionOp.Operation.ADD);
	}
	
	private boolean isLevelClear(){
		//if there are no more Asteroids on the screen
		boolean bFoeFree = true;
		for (Movable foe : CommandCenter.getInstance().getFoes()) {
			if (foe instanceof Sprite){
				bFoeFree = false;
				break;
			}
		}
		
		return bFoeFree&&CommandCenter.getInstance().getFalcon()!=null;

		
	}
	
	private void checkNewLevel() throws IOException {
		if (CommandCenter.getInstance().isPlaying() && CommandCenter.getInstance().getLevel()%500==0) {
			spawnPowerup();
		}
		if (isLevelClear()){
			//if (CommandCenter.getInstance().getFalcon() !=null)
				//CommandCenter.getInstance().getFalcon().setProtected(true);
			if (!CommandCenter.getInstance().isbWin()) {
				if (CommandCenter.getInstance().getScore() < 50) {
					spawnFoesgrp();
				} else {
					spawnBoss();
				}
			} else if (winTick == 0) {
				winTick = getTick();
			}
		}
	}
	
	
	

	// Varargs for stopping looping-music-clips
	private static void stopLoopingSounds(Clip... clpClips) {
		for (Clip clp : clpClips) {
			clp.stop();
		}
	}

	// ===============================================
	// KEYLISTENER METHODS
	// ===============================================

	@Override
	public void keyPressed(KeyEvent e) {
		Myship fal = CommandCenter.getInstance().getFalcon();
		int nKey = e.getKeyCode();
		// System.out.println(nKey);

		if (nKey == START && !CommandCenter.getInstance().isPlaying()) {
			try {
				startGame();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		if (fal != null) {

			switch (nKey) {
			case PAUSE:
				CommandCenter.getInstance().setPaused(!CommandCenter.getInstance().isPaused());
				if (CommandCenter.getInstance().isPaused())
					stopLoopingSounds(clpMusicBackground, clpThrust);
				else
					clpMusicBackground.loop(Clip.LOOP_CONTINUOUSLY);
				break;
			case QUIT:
				System.exit(0);
				break;
			case UP:
				fal.setbMoveup(true);
				if (!CommandCenter.getInstance().isPaused())
					clpThrust.loop(Clip.LOOP_CONTINUOUSLY);
				break;
			case DOWN:
				fal.setbMovedown(true);
				if (!CommandCenter.getInstance().isPaused())
					clpThrust.loop(Clip.LOOP_CONTINUOUSLY);
				break;
			case LEFT:
				fal.setbMoveleft(true);
				if (!CommandCenter.getInstance().isPaused())
					clpThrust.loop(Clip.LOOP_CONTINUOUSLY);
				break;
			case RIGHT:
				fal.setbMoveright(true);
				if (!CommandCenter.getInstance().isPaused())
					clpThrust.loop(Clip.LOOP_CONTINUOUSLY);
				break;

			case FIRE:
				fal.setbFire(true);
				if (!CommandCenter.getInstance().isPaused())
					Sound.playSound("laser.wav");
				break;
			// possible future use
			// case KILL:
			// case SHIELD:
			// case NUM_ENTER:

			default:
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Myship fal = CommandCenter.getInstance().getFalcon();
		int nKey = e.getKeyCode();
		 //System.out.println(nKey);

		if (fal != null) {
			switch (nKey) {
				/*
			case FIRE:
				CommandCenter.getInstance().getOpsList().enqueue(new Bullet(fal), CollisionOp.Operation.ADD);
				Sound.playSound("laser.wav");
				break;
				
			//special is a special weapon, current it just fires the cruise missile. 
			case SPECIAL:
				CommandCenter.getInstance().getOpsList().enqueue(new Cruise(fal), CollisionOp.Operation.ADD);
				//Sound.playSound("laser.wav");
				break;
				*/
			case LEFT:
				fal.setbMoveleft(false);
				clpThrust.stop();
				break;
			case RIGHT:
				fal.setbMoveright(false);
				clpThrust.stop();
				break;
			case UP:
				fal.setbMoveup(false);
				clpThrust.stop();
				break;
			case DOWN:
				fal.setbMovedown(false);
				clpThrust.stop();
				break;
				
			case MUTE:
				if (!bMuted){
					stopLoopingSounds(clpMusicBackground);
					bMuted = !bMuted;
				} 
				else {
					clpMusicBackground.loop(Clip.LOOP_CONTINUOUSLY);
					bMuted = !bMuted;
				}
				break;

			case FIRE:
				fal.setbFire(false);
				break;

			case BULLET1:
				if (fal.getAvailbullet()[BULLET1-49]) {
					try {
						fal.setBullettype(BULLET1-48);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				break;

			case BULLET2:
				if (fal.getAvailbullet()[BULLET2-49]) {
					try {
						fal.setBullettype(BULLET2-48);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				break;

			case BULLET3:
				if (fal.getAvailbullet()[BULLET3-49]) {
					try {
						fal.setBullettype(BULLET3-48);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				break;

			case BULLET4:
				if (fal.getAvailbullet()[BULLET4-49]) {
					try {
						fal.setBullettype(BULLET4-48);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
				break;

			default:
				break;
			}
		}
	}

	@Override
	// Just need it b/c of KeyListener implementation
	public void keyTyped(KeyEvent e) {
	}

}


