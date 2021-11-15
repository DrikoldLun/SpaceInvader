package model;

import controller.Sound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CommandCenter {

	private  int nNumFalcon;
	private  int nLevel;
	private  long lScore;
	private Myship falShip;
	private  boolean bPlaying;
	private  boolean bPaused;
	private  boolean bWin;
	
	// These ArrayLists with capacities set
	private List<Sprite> foes = new ArrayList<Sprite>(300);
	private List<Sprite> powerups = new ArrayList<Sprite>(100);
	private List<Sprite> bullets = new ArrayList<Sprite>(3000);
	private List<Sprite> explosions = new ArrayList<Sprite>(300);
	private List<Sprite> foebullets = new ArrayList<Sprite>(3000);

	private GameOpsList opsList = new GameOpsList();


	private static CommandCenter instance = null;

	// Constructor made private - static Utility class only
	private CommandCenter() {}


	public static CommandCenter getInstance(){
		if (instance == null){
			instance = new CommandCenter();
		}
		return instance;
	}


	public void initGame() throws IOException {
		setScore(0);
		setbWin(false);
		setNumFalcons(3);
		spawnMyship(true);
		setLevel(1);
	}
	
	// The parameter is true if this is for the beginning of the game, otherwise false
	// When you spawn a new falcon, you need to decrement its number
	public  void spawnMyship(boolean bFirst) throws IOException {
		if (getNumFalcons() != 0) {
			falShip = new Myship();

			opsList.enqueue(falShip, CollisionOp.Operation.ADD);
			if (!bFirst)
			    setNumFalcons(getNumFalcons() - 1);
		}
		
		Sound.playSound("shipspawn.wav");

	}

	public GameOpsList getOpsList() {
		return opsList;
	}

	public void setOpsList(GameOpsList opsList) {
		this.opsList = opsList;
	}

	public  void clearAll(){
		foes.clear();
		powerups.clear();
		bullets.clear();
		explosions.clear();
		foebullets.clear();
	}

	public  boolean isPlaying() {
		return bPlaying;
	}

	public  void setPlaying(boolean bPlaying) {
		this.bPlaying = bPlaying;
	}

	public  boolean isPaused() {
		return bPaused;
	}

	public  void setPaused(boolean bPaused) {
		this.bPaused = bPaused;
	}
	
	public  boolean isGameOver() {		//if the number of falcons is zero, then game over
		if (getNumFalcons() == 0) {
			return true;
		}
		return false;
	}

	public  int getLevel() {
		return nLevel;
	}

	public   long getScore() {
		return lScore;
	}

	public  void setScore(long lParam) {
		lScore = lParam;
	}

	public  void setLevel(int n) {
		nLevel = n;
	}

	public boolean isbWin() {
		return bWin;
	}

	public void setbWin(boolean bWin) {
		this.bWin = bWin;
	}

	public  int getNumFalcons() {
		return nNumFalcon;
	}

	public  void setNumFalcons(int nParam) {
		nNumFalcon = nParam;
	}
	
	public Myship getFalcon(){
		return falShip;
	}
	
	public  void setFalcon(Myship falParam){
		falShip = falParam;
	}

	public List<Sprite> getFoes() {
		return foes;
	}

	public List<Sprite> getPowerups() {
		return powerups;
	}

	public List<Sprite> getBullets() {
		return bullets;
	}

	public List<Sprite> getExplosions() {return explosions;}

	public List<Sprite> getFoebullets() {
		return foebullets;
	}
}
