package Simori;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Soundbank;
import javax.sound.midi.Synthesizer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.Timer;

/**
 * @author Airidas Juskaitis, Ollie McLean, Nicholas Higgins, Mihai Bratosin,
 * Alonso-Lopez Mendoza
 */
public class GUI extends JFrame{


	//this two dimensional ToggleButton array represents  the display
	static JToggleButton[][] display = new JToggleButton[16][16];
	
	// the starting position for the clock hand
	private static int clockHandPosition = 0;
	
	//this ToggleButton array holds all the menu buttons (ON, L1-4, R1-4, OK)
	private static JToggleButton[] menuButtons = new JToggleButton[10];
	
	//the text field to display information at the bottom of the board
	static JTextField textField = new JTextField();
	 	 
	private final static Color DEFAULT = null;
	 
	private static Synthesizer synthesizer = getSynthesizer();
	private static Soundbank bank = synthesizer.getDefaultSoundbank();
	static Instrument[] instruments = bank.getInstruments();
	 
	private static Timer timer;

	 
	 public static int currentMode = 0;
	 
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
		
		ChangeLayer.Layers = new Layer[16];
		for(int i = 0; i < ChangeLayer.Layers.length; i++){
			ChangeLayer.Layers[i] = new Layer();
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
		OnOff.disableMenuButtons();
		OnOff.disableGridButtons();
		
		this.setSize(570, 590);
		this.setResizable(false);
		this.setVisible(true);
		
	}
	
	/**
	 * Returns the synthesizer of the MidiSystem.
	 * @return The Synthesizer of the MidiSystem
	 */
	private static Synthesizer getSynthesizer(){
		try{
			return MidiSystem.getSynthesizer();
		}catch(Exception e){}
		return null;
	}
	
	/**
	 * Sets the current mode of the board
	 * @param mode The mode of the board
	 */
	public static void setCurrentMode(int mode){
		currentMode = mode;
	}
	
	/**
	 * Clears the entire 16x16 grid of the board
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
	 * Sets all the menu buttons to unselected other than 
	 * the button passed as an argument
	 * @param button The menu button that will not be de-selected
	 */
	public static void clearMenuButtons(JToggleButton button) {		
		for(JToggleButton menuButton : menuButtons)
			if(!menuButton.equals(button) && !menuButton.getText().equals("ON"))
				menuButton.setSelected(false);		
	}
	
	/**
	 * Displays alphanumeric characters over the first 36 buttons of the
	 * board
	 */
	public static void displayKeyboard(){
		String[] keyboardArray = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0",
		       "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m",
		       "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		for(int i=0;i<16;i++)
			for(int j=0;j<16;j++)
				if(i*16+j<36)
					display[i][j].setText(keyboardArray[i*16+j]);
	}
	
	/**
	 * Erases the text displayed over the first 36 buttons of the
	 * board
	 */
	public static void eraseKeyboard(){
		for(int i=0;i<16;i++){
			for(int j=0;j<16;j++){
				if(i*16+j<36){
					display[i][j].setText(null);
				}
			}
		}
	}
	
	/**
	 * Clears the contents of all the layers
	 */
	public static void clearLayers(){
		for(int i=0;i<ChangeLayer.Layers.length;i++){
			ChangeLayer.Layers[i].clear();
			}
	}
	
	/**
	 * @return The position of the clock hand
	 */
	public static int getClockHandPosition(){
		return clockHandPosition;
	}
	
	/**
	 * Sets the position of the clock hand
	 * @param chp
	 */
	public static void setClockHandPosition(int chp){
		clockHandPosition = chp;
	}
	
	/**
	 * @return The array of menu buttons
	 */
	public static JToggleButton[] getMenuButtons(){
		return menuButtons;
	}
	
	/**
	 * @return The timer of the board
	 */
	public static Timer getTimer(){
		return timer;
	}
	
	/**
	 * @param t The new timer
	 */
	public static void setTimer(Timer t){
		timer = t;
	}
}