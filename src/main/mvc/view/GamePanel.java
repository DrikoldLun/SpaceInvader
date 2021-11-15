package view;

import controller.Game;
import model.*;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


public class GamePanel extends Panel {
	
	// ==============================================================
	// FIELDS 
	// ============================================================== 
	 
	// The following "off" vars are used for the off-screen double-bufferred image. 
	private Dimension dimOff;
	private Image imgOff;
	private Graphics grpOff;
	
	private GameFrame gmf;
	private Font fnt = new Font("SansSerif", Font.BOLD, 12);
	private Font fntBig = new Font("SansSerif", Font.BOLD + Font.ITALIC, 36);
	private Font fntWin = new Font("SansSerif", Font.BOLD + Font.ITALIC, 50);
	private FontMetrics fmt; 
	private int nFontWidth;
	private int nFontHeight;
	private String strDisplay = "";
	

	// ==============================================================
	// CONSTRUCTOR 
	// ==============================================================
	
	public GamePanel(Dimension dim){
	    gmf = new GameFrame();
		gmf.getContentPane().add(this);
		gmf.pack();
		initView();
		
		gmf.setSize(dim);
		gmf.setTitle("Space Invader");
		gmf.setResizable(false);
		gmf.setVisible(true);
		this.setFocusable(true);
	}
	
	
	// ==============================================================
	// METHODS 
	// ==============================================================
	
	private void drawScore(Graphics g) {
		g.setColor(Color.white);
		g.setFont(fnt);
		if (CommandCenter.getInstance().getScore() != 0) {
			g.drawString("SCORE :  " + CommandCenter.getInstance().getScore(), nFontWidth, nFontHeight);
		} else {
			g.drawString("NO SCORE", nFontWidth, nFontHeight);
		}
	}

	@SuppressWarnings("unchecked")
	public void update(Graphics g) {
		if (grpOff == null || Game.DIM.width != dimOff.width
				|| Game.DIM.height != dimOff.height) {
			dimOff = Game.DIM;
			imgOff = createImage(Game.DIM.width, Game.DIM.height);
			grpOff = imgOff.getGraphics();
		}
		// Fill in background with black.
		grpOff.setColor(Color.black);
		grpOff.fillRect(0, 0, Game.DIM.width, Game.DIM.height);

		drawScore(grpOff);
		
		if (!CommandCenter.getInstance().isPlaying()) {
			displayTextOnScreen();
		} else if (CommandCenter.getInstance().isPaused()) {
			strDisplay = "Game Paused";
			grpOff.drawString(strDisplay,
					(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4);
		}
		//playing and not paused!
		else {
			if (CommandCenter.getInstance().isbWin()) {
				strDisplay = "You beat all enemies! Congratulations!";
				grpOff.setFont(fntWin);
				FontMetrics fmtWin = grpOff.getFontMetrics();
				grpOff.drawString(strDisplay,
						(Game.DIM.width-fmtWin.stringWidth(strDisplay))/2, (Game.DIM.height - fmtWin.getHeight()) / 2);
			}
			//draw them in decreasing level of importance
			//friends will be on top layer and debris on the bottom
			try {
				iterateMovables(grpOff,
						(ArrayList<Sprite>)  CommandCenter.getInstance().getBullets(),
						(ArrayList<Sprite>)  CommandCenter.getInstance().getFoes(),
						(ArrayList<Sprite>)  CommandCenter.getInstance().getPowerups(),
						(ArrayList<Sprite>)  CommandCenter.getInstance().getExplosions(),
						(ArrayList<Sprite>)  CommandCenter.getInstance().getFoebullets());
			} catch (IOException e) {
				e.printStackTrace();
			}


			try {
				CommandCenter.getInstance().getFalcon().draw(grpOff);
			} catch (IOException e) {
				e.printStackTrace();
			}

			Sprite boss = null;
			for (Sprite foe:CommandCenter.getInstance().getFoes()) {
				if (foe.isBoss()) {
					boss = foe;
				}
			}
			if (boss!=null) {
				grpOff.setColor(Color.blue);
				grpOff.drawRect(50, 20, Game.DIM.width-100, 30);
				grpOff.fillRect(50, 20, (int) ((Game.DIM.width-100)*((double) boss.getHealth()/Game.MAXBOSSHEALTH)), 30);
			}

			grpOff.setColor(Color.green);
			grpOff.drawRect(20, Game.DIM.height-50, 200, 30);
			grpOff.fillRect(20, Game.DIM.height-50, (int) (200*((double) CommandCenter.getInstance().getFalcon().getHealth()/Game.MAXMYHEALTH)), 30);
			if (CommandCenter.getInstance().isGameOver()) {
				CommandCenter.getInstance().setPlaying(false);
				//bPlaying = false;
			}
		}
		//draw the double-Buffered Image to the graphics context of the panel
		g.drawImage(imgOff, 0, 0, this);
	} 


	
	//for each movable array, process it.
	private void iterateMovables(Graphics g, ArrayList<Sprite>...spritez) throws IOException {
		
		for (ArrayList<Sprite> sprites : spritez) {
			for (Sprite sprite : sprites) {

				sprite.draw(g);

			}
		}
		
	}
	
	private void initView() {
		Graphics g = getGraphics();			// get the graphics context for the panel
		g.setFont(fnt);						// take care of some simple font stuff
		fmt = g.getFontMetrics();
		nFontWidth = fmt.getMaxAdvance();
		nFontHeight = fmt.getHeight();
		g.setFont(fntBig);					// set font info
	}
	
	// This method draws some text to the middle of the screen before/after a game
	private void displayTextOnScreen() {

		strDisplay = "GAME OVER";
		grpOff.drawString(strDisplay,
				(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4);

		strDisplay = "use the arrow keys to move";
		grpOff.drawString(strDisplay,
				(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4
						+ nFontHeight + 40);

		strDisplay = "use the space bar to fire";
		grpOff.drawString(strDisplay,
				(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4
						+ nFontHeight + 80);

		strDisplay = "'S' to Start";
		grpOff.drawString(strDisplay,
				(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4
						+ nFontHeight + 120);

		strDisplay = "'P' to Pause/Resume";
		grpOff.drawString(strDisplay,
				(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4
						+ nFontHeight + 160);

		strDisplay = "'Q' to Quit";
		grpOff.drawString(strDisplay,
				(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4
						+ nFontHeight + 200);

		strDisplay = "use 1-4 to switch your weapon";
		grpOff.drawString(strDisplay,
				(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4
						+ nFontHeight + 240);

		strDisplay = "'M' to Mute/Unmute the background music";
		grpOff.drawString(strDisplay,
				(Game.DIM.width - fmt.stringWidth(strDisplay)) / 2, Game.DIM.height / 4
						+ nFontHeight + 280);
	}
	
	public GameFrame getFrm() {return this.gmf;}
	public void setFrm(GameFrame frm) {this.gmf = frm;}	
}