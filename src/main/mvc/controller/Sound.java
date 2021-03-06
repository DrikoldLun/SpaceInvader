package controller;



import java.io.BufferedInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Sound {

	//for individual wav sounds (not looped)
	//http://stackoverflow.com/questions/26305/how-can-i-play-sound-in-java
	public static synchronized void playSound(final String strPath) {
	    new Thread(new Runnable() { 
	      public void run() {
	        try {
	          Clip clp = AudioSystem.getClip();

			  BufferedInputStream myStream = new BufferedInputStream(Sound.class.getClassLoader().getResourceAsStream("sounds/"+strPath));
	          AudioInputStream aisStream = 
	        		  AudioSystem.getAudioInputStream(myStream);
     
	          
	          clp.open(aisStream);
	          clp.start(); 
	        } catch (Exception e) {
	          System.err.println(e.getMessage());
	        }
	      }
	    }).start();
	  }
	
	
	//for looping wav clips
	//http://stackoverflow.com/questions/4875080/music-loop-in-java
	public static Clip clipForLoopFactory(String strPath){
		
		Clip clp = null;
		
		// this line caused the original exceptions
		
		try {
			BufferedInputStream myStream = new BufferedInputStream(Sound.class.getClassLoader().getResourceAsStream("sounds/"+strPath));
			AudioInputStream aisStream =
					AudioSystem.getAudioInputStream(myStream);
			clp = AudioSystem.getClip();
		    clp.open( aisStream );
				
		} catch (UnsupportedAudioFileException exp) {
			
			exp.printStackTrace();
		} catch (IOException exp) {
			
			exp.printStackTrace();
		} catch (LineUnavailableException exp) {
			
			exp.printStackTrace();
			
		//the next three lines were added to catch all exceptions generated
		}catch(Exception exp){
			System.out.println("error");
		}
		
		return clp;
		
	}
	
	


}
