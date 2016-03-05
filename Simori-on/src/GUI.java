import java.awt.Color;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.Timer;

import java.net.ServerSocket;
import java.net.Socket;

public class GUI extends JFrame{

	public static Boolean slaveMaster = false;
	public final static int port = 20160;
	//this two dimensional ToggleButton array represents  the display
	private static JToggleButton[][] display = new JToggleButton[16][16];
	private static Layer[] Layers = new Layer[16];
	
	// the starting position for the clock hand
	public static int clockHandPosition = 0;
	
	//this ToggleButton array holds all the menu buttons (ON, L1-4, R1-4, OK)
	private static JToggleButton[] menuButtons = new JToggleButton[10];
	
	//the text field to display information at the bottom of the board
	private static JTextField textField = new JTextField();
	
	// the temporary and permanent values for the instrument playing
	 private static int instrument, tempInstrument;
	 // the temporary and permanent values for the velocity
	 private static int velocity, tempVelocity;
	 // the temporary and permanent values of the loop speed
	 private static int loopSpeed = 100,tempLoopSpeed;
	 // the temporary and permanent value of the loop point
	 private static int loopPoint = 15, tempLoopPoint;
	 // a variable for storing 
	 private static int tempLayer, currentLayer;
	 
	 private static boolean clockHandVisible;
	 	 
	 private final static Color SCANNED = new Color(255,245,105);
	 private final static Color DEFAULT = null;
	 
	 private static Synthesizer synthesizer = getSynthesizer();
	 private static Soundbank bank = synthesizer.getDefaultSoundbank();
	 private static Instrument[] instruments = bank.getInstruments();
	 
	 private static Timer timer;

	 
	 public static int currentMode = 0;
	private static Boolean[][][] contents;
	 
	 /**
	  * Sets up the GUI for the board. Creates the 16x16 grid of buttons,
	  * the menu buttons and adds them to the board.
	  * @author Alonso Lopez Mendoza
	  */
	public GUI(){
		
		super("Simori");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		
		JPanel central = new JPanel();
		central.setSize(450,450);
		central.setLayout(new GridLayout(16,16));		
				
		ActionListener modeListener = new ChangeModeListener();
		ActionListener gridListener = new GridListener();
		


		
		for(int i = 0; i < display.length; i++){
			for(int j = 0; j < display[i].length; j++){
				JToggleButton button = new JToggleButton();
								
				
				button.setOpaque(true);
				button.setContentAreaFilled(true);
				button.setBorderPainted(true);
				
				button.setBackground(DEFAULT);
				
				display[i][j] = button;
							
				button.addActionListener(gridListener);
						
						
				central.add(button);
			}
		}
		
		this.add(central);
		central.setBounds(60, 60, 450, 450);
		
		Layers = new Layer[16];
		for(int i = 0; i < Layers.length; i++){
			Layers[i] = new Layer();
		}

		
		JToggleButton ON = new JToggleButton("ON");
		menuButtons[0] = ON;
		
		JToggleButton L1 = new JToggleButton("L1");
		menuButtons[1] = L1;
		JToggleButton L2 = new JToggleButton("L2");
		menuButtons[2] = L2;
		JToggleButton L3 = new JToggleButton("L3");
		menuButtons[3] = L3;
		JToggleButton L4 = new JToggleButton("L4");
		menuButtons[4] = L4;
		
		JToggleButton R1 = new JToggleButton("R1");
		menuButtons[5] = R1;
		JToggleButton R2 = new JToggleButton("R2");
		menuButtons[6] = R2;
		JToggleButton R3 = new JToggleButton("R3");
		menuButtons[7] = R3;
		JToggleButton R4 = new JToggleButton("R4");
		menuButtons[8] = R4;
		
		JToggleButton OK = new JToggleButton("OK");
		menuButtons[9] = OK;
					
				
		this.add(ON);
		ON.setBounds(255, 0, 60, 60);
		ON.addActionListener(modeListener);
		
		this.add(L1);
		L1.setBounds(0, 60, 60, 60);
		L1.addActionListener(modeListener);
		this.add(L2);
		L2.setBounds(0, 150, 60, 60);
		L2.addActionListener(modeListener);
		this.add(L3);
		L3.setBounds(0, 240, 60, 60);
		L3.addActionListener(modeListener);
		this.add(L4);
		L4.setBounds(0, 330, 60, 60);
		L4.addActionListener(modeListener);
		
		this.add(R1);
		R1.setBounds(510, 60, 60, 60);
		R1.addActionListener(modeListener);
		this.add(R2);
		R2.setBounds(510, 150, 60, 60);
		R2.addActionListener(modeListener);
		this.add(R3);
		R3.setBounds(510, 240, 60, 60);
		R3.addActionListener(modeListener);
		this.add(R4);
		R4.setBounds(510, 330, 60, 60);
		R4.addActionListener(modeListener);
		
		this.add(OK);
		OK.setBounds(450, 510, 60, 60);
		OK.addActionListener(modeListener);
		
		this.add(textField);
		textField.setEditable(false);
		textField.setText("LCD");
		textField.setBounds(120, 510, 240, 60);
		

		//sets the initial behaviour
		disableMenuButtons();
		disableGridButtons();
		
		this.setSize(570, 590);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
	/**
	 * Returns the synthesizer of the MidiSystem.
	 * @return The Synthesizer of the MidiSystem
	 * @author Mihai Bratosin
	 */
	private static Synthesizer getSynthesizer(){
		try{
			return MidiSystem.getSynthesizer();
		}catch(Exception e){}
		return null;
	}
		
	/**
	 * Starts running the 16 layers of the board in parallel
	 * @author Alonso Lopez Mendoza,Mihai Bratosin, Nick Higgins
	 */
	public static void startThreads(){
		for(int i = 0; i < Layers.length; i++){
			Layers[i] = new Layer();
		}
		for(int i = 0; i < Layers.length; i++){
			new Thread(Layers[i]).start();
		}
		
		
	}
	
	public static void stopThreads(){
		for(int i = 0; i < Layers.length; i++){
			Layers[i].setDone(true);
			clockHandPosition = 15;
			timer.stop();
		}
	}
	
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
			if(clockHandVisible){
				if(clockHandPosition == 0){
					
				
					display[0][loopPoint].setBackground(DEFAULT);
					display[5][loopPoint].setBackground(DEFAULT);
					display[10][loopPoint].setBackground(DEFAULT);
					display[15][loopPoint].setBackground(DEFAULT);
					
					
					
					
					display[0][clockHandPosition].setBackground(SCANNED);
					display[5][clockHandPosition].setBackground(SCANNED);
					display[10][clockHandPosition].setBackground(SCANNED);
					display[15][clockHandPosition].setBackground(SCANNED);
	
				}
				else{
					
					display[0][clockHandPosition-1].setBackground(DEFAULT);
					display[5][clockHandPosition-1].setBackground(DEFAULT);
					display[10][clockHandPosition-1].setBackground(DEFAULT);
					display[15][clockHandPosition-1].setBackground(DEFAULT);
					
					display[0][clockHandPosition].setBackground(SCANNED);
					display[5][clockHandPosition].setBackground(SCANNED);
					display[10][clockHandPosition].setBackground(SCANNED);
					display[15][clockHandPosition].setBackground(SCANNED);
					
				}
				
			}
				clockHandPosition++;
				if(clockHandPosition > loopPoint) clockHandPosition = 0;
				
				
			}
			
		};
		
		timer = new Timer(loopSpeed, ClockTimerPerformer);
		timer.start();
		
		
	}
	
	
	

	/**
	 * Enables all the menu buttons on the board
	 * @author Mihai Bratosin 
	 */
	public static void enableMenuButtons() {
		for(JToggleButton menuButton : menuButtons){
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
		for(int i=1;i<menuButtons.length-1;i++){
			if(!menuButtons[i].equals(button))
				menuButtons[i].setEnabled(false);
		}
	
	}
	
	/**
	 * Disables all the menu buttons on the board other
	 * than the ON button and the OK button
	 * @author Mihai Bratosin
	 */
	protected static void disableMenuButtons(){
		// Does not disable the OK button - the last button in the array
		for(int i=1;i<menuButtons.length-1;i++){
			menuButtons[i].setEnabled(false);
		}
	
	}

	/**
	 * Enables all the buttons on the 16x16 grid
	 * @author Ollie McLean
	 */
	public static void enableGridButtons() {
		for(int i = 0; i < display.length; i++){
			for(int j = 0; j < display[i].length; j++){
				display[i][j].setEnabled(true);
			}
		}
		
	}
	
	/**
	 * Disables all the buttons on the 16x16 grid
	 * @author Ollie McLean
	 */
	public static void disableGridButtons() {
		for(int i = 0; i < display.length; i++){
			for(int j = 0; j < display[i].length; j++){
				display[i][j].setEnabled(false);
			}
		}
	}

	/**
	 * Sets the temporary value for the current instrument playing
	 * based on the button pressed on the grid.The value represents the button number
	 * on the grid. Value should be smaller than 128
	 * @param x The x coordinate of the button pressed - the x-th column
	 * @param y The y coordinate of the button pressed - the y-th row
	 * @author Nicholas Higgins
	 */
	public static void setTempInstrument(int x, int y) {
		clearBoard();
		for(int i = 0; i < display.length; i++){
			for(int j = 0; j < display[i].length; j++){
				if(j==x || i == y){
					display[i][j].setSelected(true);
				}
				if(j==x && i == y){
					if(x+y*16<128){
						tempInstrument = i;
						textField.setText(instruments[i].getName());
					}
					else{
						textField.setText("No instrument");
					}
				}
			}
		}
	}

	
	/**
	 * Sets the temporary value for the velocity of the playing notes
	 * based on the button pressed on the grid.The value represents the button number
	 * on the grid. Value should be smaller than 128.
	 * @param x The x coordinate of the button pressed - the x-th column
	 * @param y The y coordinate of the button pressed - the y-th row
	 * @author Mihai Bratosin
	 */
	public static void setTempVelocity(int x, int y) {
		clearBoard();          
		 // Set the temporary velocity to the old value of the velocity
		 // so if the temp is not acceptable we can go back to the old one
		 tempVelocity = velocity;            
		 // If the temporary velocity is in the accepted range (<127)
		 if(x+y*16<=127){
		     // Set the temporary velocity and change the display
		     tempVelocity = x+y*16;
		     textField.setText(String.valueOf(tempVelocity));
		         
		     // Sets the buttons on the same row and column to selected
		     for(int i = 0; i < display.length; i++)
		         for(int j = 0; j < display[i].length; j++)
		             if(i*16+j==tempVelocity || j==x || i==y)
		                 display[i][j].setSelected(true);
		 }
		 else
		     textField.setText(String.valueOf(velocity));
		
	}

	/**
	 * Sets the temporary loop speed for the clock hand based on the
	 * button pressed on the grid. The value represents the button number
	 * on the grid. Value should be smaller than 160.
	 * @param x The x coordinate of the button - the x-th column
	 * @param y The y coordinate of the button - the y-th row
	 * @author Ollie McLean
	 */
	public static void setTempLoopSpeed(int x, int y) {
		clearBoard();
		 tempLoopSpeed = loopSpeed;
		 
		 if(x+y*16<160){
		     tempLoopSpeed = x+y*16;
		     textField.setText(String.valueOf(tempLoopSpeed));
		     
		     for(int i=0;i<display.length;i++)
		         for(int j=0;j<display[i].length;j++)
		             if(j==x || i==y)
		                 display[i][j].setSelected(true);
		 }
		 else
		     textField.setText(String.valueOf(loopSpeed));
		
	}
	
	/**
	 * Sets the temporary value of the loop point based on the column
	 * pressed 
	 * @param x The x coordinate of the button pressed
	 * @param y The y coordinate of the button pressed
	 * @author Mihai Bratosin
	 */
	public static void setTempLoopPoint(int x, int y){
		clearBoard();
		tempLoopPoint = x;
		textField.setText(String.valueOf(tempLoopPoint));
		
		for(int i = 0; i < display[tempLoopPoint].length;i++){
			display[i][tempLoopPoint].setSelected(true);
		}
	}
	
	/**
	 * Sets the temporary value of the current layer based on the
	 * button pressed on the grid. The value represents the button number
	 * on the grid.
	 * @param x The x coordinate of the button pressed - the x-th column
	 * @param y The y coordinate of the button pressed - the y-th column
	 * @author Airidas Juskaitis
	 */
	public static void setTempLayer(int x,int y){
		clearBoard();
		tempLayer = y;
		textField.setText(String.valueOf(tempLayer));
		
		for(int i = 0; i < display[tempLayer].length;i++){
			display[tempLayer][i].setSelected(true);
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
	 * @return The temporary value of the velocity
	 * @author Mihai Bratosin
	 */
	public static int getTempVelocity() {
		return tempVelocity;
		
	}

	/**
	 * @return The temporary value of the loop speed
	 * @author Ollie McLean
	 */
	public static int getTempLoopSpeed() {
		return tempLoopSpeed;
		
	}
	
	/**
	 * @return The temporary value of the loop point
	 */
	public static int getTempLoopPoint(){
		return tempLoopPoint;
	}
	
	/**
	 * @return The temporary value of the layer
	 * @author Airidas Juskaitis
	 */
	public static int getTempLayer(){
		return tempLayer;
	}
	
	/**
	 * @return the current layer of the board
	 */
	public static int getCurrentLayer(){
		return currentLayer;
	}
	
	/**
	 * @return the permanent value of the loop point
	 * @author Alonso Lopez Mondoza
	 */
	public static int getLoopPoint(){
		return loopPoint;
	}
	/**
	 * Gets the given position in the
	 * array of layers for the board
	 * @param l The layer to return
	 * @return The layer for the position given
	 */
	public static Layer getLayers(int l){
		return Layers[l];
	}
	
	/**
	 * Sets the current instrument playing
	 * @param ch The instrument playing - integer is a midi encoding
	 * @author Nicholas Higgins
	 */
	public static void setInstrument(int ch) {
		Layers[currentLayer].setInstrument(ch);
	}
	
	/**
	 * Sets the current velocity
	 * @param vl The current velocity
	 * @author Mihai Bratosin
	 */
	public static void setVelocity(int vl) {
		Layers[currentLayer].setVelocity(vl);
	}
	
	/**
	 * Sets the loop speed of the clock hand
	 * @param ls The loop speed
	 * @author Ollie McLean
	 */
	public static void setLoopSpeed(int ls) {
		loopSpeed = ls*10;
		timer.setDelay(loopSpeed);
		
	}
	
	/**
	 * Sets the value of the current layer
	 * @param l The value of the layer
	 * @author Airidas Juskaitis
	 */
	public static void setCurrentLayer(int l){
		currentLayer = l;
	}
	
	public static void setLoopPoint(int p){
		loopPoint = p;
	}
	
	/**
	 * Sets the current mode of the board
	 * @param mode The mode of the board
	 * @author Mihai Bratosin
	 */
	public static void setCurrentMode(int mode){
		currentMode = mode;
	}
	
	/**
	 * Clears the entire 16x16 grid of the board
	 * @author Mihai Bratosin 
	 */
	public static void clearBoard() {
		for(int i = 0; i < display.length; i++){
			for(int j = 0; j < display[i].length; j++){
				display[i][j].setSelected(false);
				display[i][j].setBackground(DEFAULT);
			}
		}	
		
	}
	
	/**
	 * Loads a layer on to the board
	 * @param l The layer to be loaded
	 * @author Mihai Bratosin
	 */
	public static void loadLayer(int l){
		for(int i = 0; i < display.length; i++)
			for(int j = 0; j < display[i].length; j++)
				if(Layers[l].getContents(i,j))
					display[j][i].setSelected(true);
	}

	
	/**
	 * Sets all the menu buttons to unselected other than 
	 * the button passed as an argument
	 * @param button The menu button that will not be de-selected
	 * @author Alonso Lopez Mendoza
	 */
	public static void clearMenuButtons(JToggleButton button) {		
		for(JToggleButton menuButton : menuButtons)
			if(!menuButton.equals(button) && !menuButton.getText().equals("ON"))
				menuButton.setSelected(false);
		
	}

	/**
	 * Sets the visiblity of the clock hand
	 * @param a True for visible
	 * @author Mihai Bratosin
	 */
	public static void setClockHandVisible(boolean a){
		clockHandVisible = a;
	}
	
	/**
	 * Clears the contents of all the layers
	 * @author Mihai Bratosin
	 */
	public static void clearLayers(){
		for(int i=0;i<Layers.length;i++){
			Layers[i].clear();
			}
	}
	
	/**
	 * Returns the full contents of the 16 layers
	 * @return the contents of all the layers of the board
	 * @author Nicholas Higgins
	 */
	public static Boolean[][][] allContents(){
		contents = null;
		for(int i =0;i<Layers.length;i++){
			contents[i] = Layers[i].getContentArray();
		}
		return contents;
	}
	
	/*public static void master() throws IOException{
		ServerSocket ss = new ServerSocket (port);
		while(true){
			Socket s = ss.accept();
			InputStream 
		}
	}*/
}
