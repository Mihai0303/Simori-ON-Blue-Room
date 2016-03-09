package Simori;
/**
 * @author Airidas Juskaitis, Ollie McLean, Nicholas Higgins, Mihai Bratosin,
 * Alonso-Lopez Mendoza
 */
public class ChangeVoiceMode {

	
	// the temporary and permanent values for the instrument playing
	 private static int tempInstrument;
	
	/**
	 * Sets the temporary value for the current instrument playing
	 * based on the button pressed on the grid. The value represents the button number
	 * on the grid. Value should be smaller than 128
	 * @param x The x coordinate of the button pressed - the x-th column
	 * @param y The y coordinate of the button pressed - the y-th row
	 */
	public static void setTempInstrument(int x, int y) {
		GUI.clearBoard();
		for(int i = 0; i < GUI.display.length; i++){
			for(int j = 0; j < GUI.display[i].length; j++){
				if(j==x || i == y){
					GUI.display[i][j].setSelected(true);
				}
				if(j==x && i == y){
					if(x+y*16<128){
						tempInstrument = j+i*16;
						GUI.textField.setText(GUI.instruments[j+i*16].getName());
					}
					else{
						GUI.textField.setText("No instrument");
					}
				}
			}
		}
	}
	
	/**
	 * @return The temporary value of the instrument
	 */
	public static int getTempInstrument() {
		return tempInstrument;
	}
	
	/**
	 * Sets the current instrument playing
	 * @param ch The instrument playing - integer is a midi encoding
	 */
	public static void setInstrument(int ch) {
		ChangeLayer.Layers[ChangeLayer.getCurrentLayer()].setInstrument(ch);
	}
	
	
}
