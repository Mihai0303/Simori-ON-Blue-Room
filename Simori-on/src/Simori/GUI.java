package Simori;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectOutputStream;

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

public class GUI extends JFrame{


	//this two dimensional ToggleButton array represents  the display
	static JToggleButton[][] display = new JToggleButton[16][16];
	
	// the starting position for the clock hand
	public static int clockHandPosition = 0;
	
	//this ToggleButton array holds all the menu buttons (ON, L1-4, R1-4, OK)
	static JToggleButton[] menuButtons = new JToggleButton[10];
	
	//the text field to display information at the bottom of the board
	static JTextField textField = new JTextField();
	 
	 static boolean clockHandVisible;
	 	 
	 final static Color SCANNED = new Color(255,245,105);
	 final static Color DEFAULT = null;
	 
	 private static Synthesizer synthesizer = getSynthesizer();
	 private static Soundbank bank = synthesizer.getDefaultSoundbank();
	 static Instrument[] instruments = bank.getInstruments();
	 
	 static Timer timer;

	 
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
	 * @author Mihai Bratosin
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
		for(int i=0;i<ChangeLayer.Layers.length;i++){
			ChangeLayer.Layers[i].clear();
			}
	}
	
	
	
}