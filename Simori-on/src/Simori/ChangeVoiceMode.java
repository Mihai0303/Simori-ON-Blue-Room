package Simori;

import javax.sound.midi.Instrument;

public class ChangeVoiceMode {

	
	// the temporary and permanent values for the instrument playing
	 private static int instrument, tempInstrument;
	
	/**
	 * Sets the temporary value for the current instrument playing
	 * based on the button pressed on the grid. The value represents the button number
	 * on the grid. Value should be smaller than 128
	 * @param x The x coordinate of the button pressed - the x-th column
	 * @param y The y coordinate of the button pressed - the y-th row
	 * @author Nicholas Higgins
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
						tempInstrument = i;
						GUI.textField.setText(GUI.instruments[i].getName());
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
	 * @author Nicholas Higgins
	 */
	public static int getTempInstrument() {
		return tempInstrument;
	}
	
	/**
	 * Sets the current instrument playing
	 * @param ch The instrument playing - integer is a midi encoding
	 * @author Nicholas Higgins
	 */
	public static void setInstrument(int ch) {
		ChangeLayer.Layers[ChangeLayer.currentLayer].setInstrument(ch);
	}
	
	
}
