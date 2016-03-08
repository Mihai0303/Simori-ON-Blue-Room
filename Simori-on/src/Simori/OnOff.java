package Simori;

import javax.swing.JToggleButton;

public class OnOff {

	/**
	 * Enables all the menu buttons on the board
	 * @author Mihai Bratosin 
	 */
	public static void enableMenuButtons() {
		for(JToggleButton menuButton : GUI.menuButtons){
			menuButton.setEnabled(true);
		}
	}
	
	/**
	 * Disables all the buttons on the board except for the
	 * ON button, OK button and the button passed as a parameter
	 * @param button A button that will not be disabled
	 * @author Mihai Bratosin
	 */
	protected static void disableMenuButtons(JToggleButton button){
		// Does not disable the OK button - the last button in the array
		for(int i=1;i<GUI.menuButtons.length-1;i++){
			if(!GUI.menuButtons[i].equals(button))
				GUI.menuButtons[i].setEnabled(false);
		}
	
	}
	
	/**
	 * Disables all the menu buttons on the board other
	 * than the ON button and the OK button
	 * @author Mihai Bratosin
	 */
	protected static void disableMenuButtons(){
		// Does not disable the OK button - the last button in the array
		for(int i=1;i<GUI.menuButtons.length-1;i++){
			GUI.menuButtons[i].setEnabled(false);
		}
	
	}

	/**
	 * Enables all the buttons on the 16x16 grid
	 * @author Ollie McLean
	 */
	public static void enableGridButtons() {
		for(int i = 0; i < GUI.display.length; i++){
			for(int j = 0; j < GUI.display[i].length; j++){
				GUI.display[i][j].setEnabled(true);
			}
		}
		
	}
	
	/**
	 * Disables all the buttons on the 16x16 grid
	 * @author Ollie McLean
	 */
	public static void disableGridButtons() {
		for(int i = 0; i < GUI.display.length; i++){
			for(int j = 0; j < GUI.display[i].length; j++){
				GUI.display[i][j].setEnabled(false);
			}
		}
	}
	
	/**
	 * Starts running the 16 layers of the board in parallel
	 * @author Alonso Lopez Mendoza,Mihai Bratosin, Nick Higgins
	 */
	public static void startThreads(){
		for(int i = 0; i < ChangeLayer.Layers.length; i++){
			ChangeLayer.Layers[i] = new Layer();
		}
		for(int i = 0; i < ChangeLayer.Layers.length; i++){
			new Thread(ChangeLayer.Layers[i]).start();
		}
		
		
	}
	
	public static void stopThreads(){
		for(int i = 0; i < ChangeLayer.Layers.length; i++){
			ChangeLayer.Layers[i].setDone(true);
			GUI.clockHandPosition = 15;
			GUI.timer.stop();
		}
	}
	
	
	
}
