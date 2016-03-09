package Simori;

public class ChangeLoopSpeed {

	 // the temporary and permanent values of the loop speed
	 private static int loopSpeed = 100;
	 private static int tempLoopSpeed;

	/**
	 * Sets the temporary loop speed for the clock hand based on the
	 * button pressed on the grid. The value represents the button number
	 * on the grid. Value should be smaller than 160.
	 * @param x The x coordinate of the button - the x-th column
	 * @param y The y coordinate of the button - the y-th row
	 * @author Ollie McLean
	 */
	public static void setTempLoopSpeed(int x, int y) {
		GUI.clearBoard();
		 tempLoopSpeed = loopSpeed;
		 
		 if(x+y*16<160){
		     tempLoopSpeed = x+y*16;
		     GUI.textField.setText(String.valueOf(tempLoopSpeed));
		     
		     for(int i=0;i<GUI.display.length;i++)
		         for(int j=0;j<GUI.display[i].length;j++)
		             if(j==x || i==y)
		            	 GUI.display[i][j].setSelected(true);
		 }
		 else
			 GUI.textField.setText(String.valueOf(loopSpeed));
		
	}
	
	/**
	 * @return The temporary value of the loop speed
	 * @author Ollie McLean
	 */
	public static int getTempLoopSpeed() {
		return tempLoopSpeed;
		
	}
	
	/**
	 * Sets the loop speed of the clock hand
	 * @param ls The loop speed
	 * @author Ollie McLean
	 */
	public static void setLoopSpeed(int ls) {
		int nls = 159 - ls;
		loopSpeed = nls*3;
		GUI.timer.setDelay(loopSpeed);
		
	}
	
	/**
	 * Gets the loop speed
	 * @return Loop speed
	 * @author Ollie McLean
	 */
	public static int getLoopSpeed(){
		return loopSpeed;
	}
	
}
