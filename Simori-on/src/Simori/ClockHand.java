package Simori;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Simori.GUI;

import javax.swing.Timer;

public class ClockHand {

	/**
	 * Starts moving the clock hand over the board
	 * Colors each grid button it passes over appropriately and
	 * increments the position of the clock hand.
	 * Starts a timer determining how fast the clock hand is going
	 * @author Alonso Lopez Mendoza
	 */
	public static void startClockHand(){
		
		ActionListener ClockTimerPerformer = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent){
					
				//System.out.println(clockHandPosition);
			if(GUI.clockHandVisible){
				if(GUI.clockHandPosition == 0){
					
				
					GUI.display[0][ChangeLoopPoint.loopPoint].setBackground(GUI.DEFAULT);
					GUI.display[5][ChangeLoopPoint.loopPoint].setBackground(GUI.DEFAULT);
					GUI.display[10][ChangeLoopPoint.loopPoint].setBackground(GUI.DEFAULT);
					GUI.display[15][ChangeLoopPoint.loopPoint].setBackground(GUI.DEFAULT);
					
					
					
					
					GUI.display[0][GUI.clockHandPosition].setBackground(GUI.SCANNED);
					GUI.display[5][GUI.clockHandPosition].setBackground(GUI.SCANNED);
					GUI.display[10][GUI.clockHandPosition].setBackground(GUI.SCANNED);
					GUI.display[15][GUI.clockHandPosition].setBackground(GUI.SCANNED);
	
				}
				else{
					
					GUI.display[0][GUI.clockHandPosition-1].setBackground(GUI.DEFAULT);
					GUI.display[5][GUI.clockHandPosition-1].setBackground(GUI.DEFAULT);
					GUI.display[10][GUI.clockHandPosition-1].setBackground(GUI.DEFAULT);
					GUI.display[15][GUI.clockHandPosition-1].setBackground(GUI.DEFAULT);
					
					GUI.display[0][GUI.clockHandPosition].setBackground(GUI.SCANNED);
					GUI.display[5][GUI.clockHandPosition].setBackground(GUI.SCANNED);
					GUI.display[10][GUI.clockHandPosition].setBackground(GUI.SCANNED);
					GUI.display[15][GUI.clockHandPosition].setBackground(GUI.SCANNED);
					
				}
				
			}
			GUI.clockHandPosition++;
				if(GUI.clockHandPosition > ChangeLoopPoint.loopPoint) GUI.clockHandPosition = 0;
				
				
			}
			
		};
		
		GUI.timer = new Timer(ChangeLoopSpeed.loopSpeed, ClockTimerPerformer);
		GUI.timer.start();
		
		
	}
	
}
