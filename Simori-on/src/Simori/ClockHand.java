package Simori;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Simori.GUI;
import javax.swing.Timer;
/**
 * @author Airidas Juskaitis, Ollie McLean, Nicholas Higgins, Mihai Bratosin,
 * Alonso-Lopez Mendoza
 */
public class ClockHand {
	
	private final static Color SCANNED = new Color(255,245,105);
	private final static Color DEFAULT = null;
	private static boolean clockHandVisible;
	/**
	 * Starts moving the clock hand over the board
	 * Colors each grid button it passes over appropriately and
	 * increments the position of the clock hand.
	 * Starts a timer determining how fast the clock hand is going
	 */
	public static void startClockHand(){
		
		ActionListener ClockTimerPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent){
					
				//System.out.println(clockHandPosition);
			if(clockHandVisible){
				if(GUI.getClockHandPosition() == 0){
					
				
					GUI.display[0][ChangeLoopPoint.getLoopPoint()].setBackground(DEFAULT);
					GUI.display[5][ChangeLoopPoint.getLoopPoint()].setBackground(DEFAULT);
					GUI.display[10][ChangeLoopPoint.getLoopPoint()].setBackground(DEFAULT);
					GUI.display[15][ChangeLoopPoint.getLoopPoint()].setBackground(DEFAULT);
					
					
					
					
					GUI.display[0][GUI.getClockHandPosition()].setBackground(SCANNED);
					GUI.display[5][GUI.getClockHandPosition()].setBackground(SCANNED);
					GUI.display[10][GUI.getClockHandPosition()].setBackground(SCANNED);
					GUI.display[15][GUI.getClockHandPosition()].setBackground(SCANNED);
	
				}
				else{
					
					GUI.display[0][GUI.getClockHandPosition()-1].setBackground(DEFAULT);
					GUI.display[5][GUI.getClockHandPosition()-1].setBackground(DEFAULT);
					GUI.display[10][GUI.getClockHandPosition()-1].setBackground(DEFAULT);
					GUI.display[15][GUI.getClockHandPosition()-1].setBackground(DEFAULT);
					
					GUI.display[0][GUI.getClockHandPosition()].setBackground(SCANNED);
					GUI.display[5][GUI.getClockHandPosition()].setBackground(SCANNED);
					GUI.display[10][GUI.getClockHandPosition()].setBackground(SCANNED);
					GUI.display[15][GUI.getClockHandPosition()].setBackground(SCANNED);
					
				}
				
			}
			GUI.setClockHandPosition(GUI.getClockHandPosition()+1);
			if(GUI.getClockHandPosition() > ChangeLoopPoint.getLoopPoint()) 
				GUI.setClockHandPosition(0);				
				
			}			
		};		
		GUI.setTimer( new Timer(ChangeLoopSpeed.getLoopSpeed(), ClockTimerPerformer));
		GUI.getTimer().start();	
	}	
	
	/**
	 * Sets the visiblity of the clock hand
	 * @param a True for visible
	 */
	public static void setClockHandVisible(boolean a){
		clockHandVisible = a;
	}
}