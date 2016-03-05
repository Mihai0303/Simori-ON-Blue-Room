package Simori;
import java.util.ArrayList;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;

/**
 * Implements a Layer of the board as a two dimensional array
 * containing true for the buttons pressed in the layer and
 * false for the buttons that are not pushed 
 * @author Alonso Lopez Mendoza
 *
 */
public class Layer implements Runnable {
	
	private Boolean[][] contents;
	private int instrument = 111;
	private int velocity = 40;
	// determines when the thread stops
	private volatile boolean done = false;
	
	Synthesizer synth;
	MidiChannel[] channels;


	 
	 
	/**
	 * Constructor for the Layer object. 
	 * Sets up a two dimensional array and initializes the
	 * synthesizer for playing the sounds on the board.
	 * Opens the synthesizer and sets up the array of 
	 * channels for the board.
	 * @author Alonso Lopez Mendoza 
	 */
	public Layer(){
		contents = new Boolean[16][16];
				
		for(int i = 0; i < 16; i++){
			for(int j = 0; j < 16; j++){
				contents[i][j] = false;
			}
		}
		
		try {
			synth = MidiSystem.getSynthesizer();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
		try {
			synth.open();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
		channels = synth.getChannels();
	}
	
	/**
	 * Sets the value of done
	 * @param d True if the loop should finish playing
	 * @author Mihai Bratosin
	 */
	public void setDone(boolean d){
		done = d;
	}
	
	/**
	 * Sets the velocity of the layer
	 * @param v The velocity to be set
	 * @author Mihai Bratosin
	 */
	public void setVelocity(int v){
		velocity = v;
	}
	
	/**
	 * Returns the instrument that the current layer is
	 * playing
	 * @return The instrument currently playing
	 * @author Alonso Lopez Mendoza
	 */
	public int getInstrument(){ 
		return instrument;
	}
	
	/**
	 * Sets the instrument for the current layer
	 * @param i The instrument for the current layer
	 * @author Alonso Lopez Mendoza
	 */
	public void setInstrument(int i){
		instrument = i;
	}


	/**
	 * Run method for a Layer object
	 */
	@Override
	public void run() {
		while(!done){
		//System.out.println(GUI.clockHandPosition);
		
		for(int i = 0; i < contents.length; i++){
			
			for(int j = 0; j < contents[i].length; j++){
				
				int realCHP = GUI.clockHandPosition;
				if(realCHP == 16) realCHP = 15;

				if(contents[realCHP][j]){
					channels[1].programChange(instrument);
					channels[1].noteOn( 60-j, velocity ); 
				}		
				
			}
		}
		
		if(GUI.clockHandPosition == GUI.getLoopPoint()) 
			channels[1].allNotesOff();
		}
		return;
	}
	
	/**
	 * Clears the entire contents of the layer.
	 * Sets all values to false
	 * @author Mihai Bratosin
	 */
	public void clear(){
		for(int i = 0; i < contents.length; i++){			
			for(int j = 0; j < contents[i].length; j++){
				contents[i][j]=false;
			}
		}
	}
	
	/**
	 * Returns the value at the i, j point in the
	 * contents array
	 * @return The value at the i, j position in the
	 * array representing the layer
	 * @author Mihai Bratosin
	 */
	public boolean getContents(int i, int j){
		return this.contents[i][j];
	}
	
	/**
	 * Sets the value for the i, j position in the array
	 * @param a The value to be set
	 * @author Mihai Bratosin
	 */
	public void setContents(int i,int j, boolean a){
		this.contents[i][j] = a;
	}
	public Boolean[][] getContentArray(){
		return this.contents;
	}

}

