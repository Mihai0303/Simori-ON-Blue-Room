package Simori;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.JToggleButton;
/**
 * @author Airidas Juskaitis, Ollie McLean, Nicholas Higgins, Mihai Bratosin,
 * Alonso-Lopez Mendoza
 */
public class ChangeModeListener implements ActionListener{
    
	
	// integer representing the mode the board is in
	 private final int ONOFFMODE = 0;
	 private final int PERFORMANCEMODE = 1;
	 private final int CHANGEVOICEMODE = 2;
	 private final int CHANGEVELOCITYMODE = 3;
	 private final int CHANGELOOPSPEEDMODE = 4;
	 private final int CHANGELOOPPOINTMODE = 5;
	 private final int CHANGELAYERMODE = 6;
	 private final int SAVECONFIGURATIONMODE = 7;
	 private final int LOADCONFIGURATIONMODE = 8;
	 private final int SLAVEMASTERMODE = 9;
	
	 

	 
	 /**
	  * Action performed when one of the menu buttons is
	  * pressed. Changes the mode of the board depending on the
	  * button. Disables every button other than the button
	  * pressed and the ON button.
	  */
    @Override
    public void actionPerformed(ActionEvent e) {
        JToggleButton button = (JToggleButton) e.getSource();
        if(GUI.currentMode != PERFORMANCEMODE) GUI.clearBoard();
        	
        
        if(button.isSelected()){
        	OnOff.disableMenuButtons(button);
        	ClockHand.setClockHandVisible(false);
        }
        else{
        	OnOff.enableMenuButtons();
        	ClockHand.setClockHandVisible(true);
        }
    
        
        
        switch(ButtonText.valueOf(button.getToolTipText())){
	    	case ON:
	    		if(button.isSelected()){
	    			OnOff.enableGridButtons();
	    			OnOff.enableMenuButtons();
	    			ClockHand.startClockHand();
	    			ClockHand.setClockHandVisible(true);
	    			OnOff.startThreads();
	    			GUI.currentMode = PERFORMANCEMODE;
	    			SaveLoad.load("on");
	    		}
	    		else{
	    			OnOff.disableGridButtons();
	    			OnOff.disableMenuButtons();
	    			GUI.clearMenuButtons(null);
	    			GUI.eraseKeyboard();
	    			GUI.clearBoard();
	    			GUI.clearLayers();
	    			OnOff.stopThreads();
	    			ClockHand.setClockHandVisible(false);
	    			ChangeLayer.setCurrentLayer(0);
	    			GUI.currentMode = ONOFFMODE;
	    		}
	    		break;
	    	case L1:
	    		if(button.isSelected()){
	    			GUI.currentMode = CHANGEVOICEMODE;
	    			GUI.clearBoard();
	    		}
	    		else{
	    			GUI.currentMode = PERFORMANCEMODE;
	    			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
	    		}
	    		break;
	    	case L2:
	    		if(button.isSelected()){
	    			GUI.currentMode = CHANGEVELOCITYMODE;
	    			GUI.clearBoard();
	    		}
	    		else{
	    			GUI.currentMode = PERFORMANCEMODE;
	    			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
	    		}
	    		break;
	    	case L3:
	    		if(button.isSelected()){
	    			GUI.currentMode = CHANGELOOPSPEEDMODE;
	    			GUI.clearBoard();
	    		}
	    		else{
	    			GUI.currentMode = PERFORMANCEMODE;
	    			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
	    		}
	    		break;
	    	case L4:
	    		if(button.isSelected()){
	    			GUI.currentMode = CHANGELOOPPOINTMODE;
	    			GUI.clearBoard();
	    		}
	    		else{
	    			GUI.currentMode = PERFORMANCEMODE;
	    			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
	    		}
	    		break;
	    	case R1:
	    		if(button.isSelected()){
	    			GUI.currentMode = CHANGELAYERMODE;
	    			GUI.clearBoard();
	    		}
	    		else{
	    			GUI.currentMode = PERFORMANCEMODE;
	    			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
	    		}
	    		break;
	    	case R2:
	    		if(button.isSelected()){
	    			GUI.currentMode = SAVECONFIGURATIONMODE;
	    			GUI.textField.setText(null);
	    			GUI.clearBoard();
	    			GUI.displayKeyboard();
	    		}
	    		else{
	    			GUI.currentMode = PERFORMANCEMODE;
	    			GUI.eraseKeyboard();
	    			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
	    		}
	    		break;
	    	case R3:
	    		if(button.isSelected()){
	    			GUI.currentMode = LOADCONFIGURATIONMODE;
	    			GUI.textField.setText(null);
	    			GUI.clearBoard();
	    			GUI.displayKeyboard();	    			
	    		}
	    		else{
	    			GUI.currentMode = PERFORMANCEMODE;
	    			GUI.eraseKeyboard();
	    			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
	    		}
	    		break;
	    	case R4:
	    		if(button.isSelected()){
	    			GUI.currentMode = SLAVEMASTERMODE;
	    			SlaveMaster sm = new SlaveMaster();
	    		}
	    		else{
	    			GUI.currentMode = PERFORMANCEMODE;
	    			ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
	    		}
	    		break;
	    	case OK:

	    		if(GUI.currentMode == CHANGEVOICEMODE){
                    ChangeVoiceMode.setInstrument(ChangeVoiceMode.getTempInstrument());
                }
                if(GUI.currentMode == CHANGEVELOCITYMODE){
                    ChangeVelocity.setVelocity(ChangeVelocity.getTempVelocity());
                }
                if(GUI.currentMode == CHANGELOOPSPEEDMODE){
                    ChangeLoopSpeed.setLoopSpeed(ChangeLoopSpeed.getTempLoopSpeed());
                }
                if(GUI.currentMode == CHANGELOOPPOINTMODE){
                	ChangeLoopPoint.setLoopPoint(ChangeLoopPoint.getTempLoopPoint());
                }
                if(GUI.currentMode == CHANGELAYERMODE){
                	ChangeLayer.setCurrentLayer(ChangeLayer.getTempLayer());
                	ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
                }
                if(GUI.currentMode == SAVECONFIGURATIONMODE){
                	SaveLoad.save();
                	GUI.eraseKeyboard();
                }
                if(GUI.currentMode == LOADCONFIGURATIONMODE){
                	SaveLoad.load();
                	GUI.eraseKeyboard();
                	ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
                }
                if(GUI.currentMode!=ONOFFMODE){
                	OnOff.enableMenuButtons();
                	GUI.currentMode = PERFORMANCEMODE;
                }
                GUI.clearMenuButtons(null);
                ClockHand.setClockHandVisible(true);
                ChangeLayer.loadLayer(ChangeLayer.getCurrentLayer());
	    		break;
	    		
        }
	   //System.out.println("The current mode is " + GUI.currentMode);	
    }
}