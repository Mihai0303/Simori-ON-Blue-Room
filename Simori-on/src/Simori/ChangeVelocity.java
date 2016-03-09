package Simori;
/**
 * @author Airidas Juskaitis, Ollie McLean, Nicholas Higgins, Mihai Bratosin,
 * Alonso-Lopez Mendoza
 */
public class ChangeVelocity {
	
	 // the temporary and permanent values for the velocity
	 private static int velocity, tempVelocity;

	/**
	 * Sets the temporary value for the velocity of the playing notes
	 * based on the button pressed on the grid.The value represents the button number
	 * on the grid. Value should be smaller than 128.
	 * @param x The x coordinate of the button pressed - the x-th column
	 * @param y The y coordinate of the button pressed - the y-th row
	 */
	public static void setTempVelocity(int x, int y) {
		GUI.clearBoard();          
		 // Set the temporary velocity to the old value of the velocity
		 // so if the temp is not acceptable we can go back to the old one
		 tempVelocity = velocity;            
		 // If the temporary velocity is in the accepted range (<127)
		 if(x+y*16<=127){
		     // Set the temporary velocity and change the display
		     tempVelocity = x+y*16;
		     GUI.textField.setText(String.valueOf(tempVelocity));
		         
		     // Sets the buttons on the same row and column to selected
		     for(int i = 0; i < GUI.display.length; i++)
		         for(int j = 0; j < GUI.display[i].length; j++)
		             if(i*16+j==tempVelocity || j==x || i==y)
		            	 GUI.display[i][j].setSelected(true);
		 }
		 else
			 GUI.textField.setText(String.valueOf(velocity));
		
	}
	
	/**
	 * @return The temporary value of the velocity
	 */
	public static int getTempVelocity() {
		return tempVelocity;
		
	}
	
	/**
	 * Sets the current velocity
	 * @param vl The current velocity
	 */
	public static void setVelocity(int vl) {
		ChangeLayer.Layers[ChangeLayer.getCurrentLayer()].setVelocity(vl);
	}
	
}
