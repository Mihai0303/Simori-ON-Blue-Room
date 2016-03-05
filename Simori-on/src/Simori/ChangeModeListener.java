package Simori;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

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
	  * @author Alonso Lopez Mendoza,Mihai Bratosin , Nick Higgins
	  */
    @Override
    public void actionPerformed(ActionEvent e) {
        JToggleButton button = (JToggleButton) e.getSource();
        if(GUI.currentMode != PERFORMANCEMODE) GUI.clearBoard();
        if(button.isSelected())
            GUI.disableMenuButtons(button);
        else
            GUI.enableMenuButtons();
    
        
        
        switch(ButtonText.valueOf(button.getText())){
	    	case ON:
	    		if(button.isSelected()){
	    			GUI.enableGridButtons();
	    			GUI.enableMenuButtons();
	    			GUI.startClockHand();
	    			GUI.setClockHandVisible(true);
	    			GUI.startThreads();
	    			GUI.currentMode = PERFORMANCEMODE;
	    		}
	    		else{
	    			GUI.disableGridButtons();
	    			GUI.disableMenuButtons();
	    			GUI.clearBoard();
	    			GUI.clearLayers();
	    			GUI.stopThreads();
	    			GUI.setClockHandVisible(false);
	    			GUI.currentMode = ONOFFMODE;
	    		}
	    		break;
	    	case L1:
	    		if(button.isSelected()){
	    			GUI.currentMode = CHANGEVOICEMODE;
	    			GUI.clearBoard();
	    			//insert code for Change Voice Mode
	    		}
	    		else{
	    			GUI.currentMode = PERFORMANCEMODE;
	    		}
	    		break;
	    	case L2:
	    		if(button.isSelected()){
	    			GUI.currentMode = CHANGEVELOCITYMODE;
	    			GUI.clearBoard();
	    		}
	    		else{
	    			GUI.currentMode = PERFORMANCEMODE;
	    		}
	    		break;
	    	case L3:
	    		if(button.isSelected()){
	    			GUI.currentMode = CHANGELOOPSPEEDMODE;
	    			GUI.clearBoard();
	    		}
	    		else{
	    			GUI.currentMode = PERFORMANCEMODE;
	    		}
	    		break;
	    	case L4:
	    		if(button.isSelected()){
	    			GUI.currentMode = CHANGELOOPPOINTMODE;
	    		}
	    		else{
	    			GUI.currentMode = PERFORMANCEMODE;
	    		}
	    		break;
	    	case R1:
	    		if(button.isSelected()){
	    			GUI.currentMode = CHANGELAYERMODE;
	    		}
	    		else{
	    			GUI.currentMode = PERFORMANCEMODE;
	    		}
	    		break;
	    	case R2:
	    		if(button.isSelected()){
	    			GUI.currentMode = SAVECONFIGURATIONMODE;
	    		}
	    		else{
	    			GUI.currentMode = PERFORMANCEMODE;
	    		}
	    		break;
	    	case R3:
	    		if(button.isSelected()){
	    			GUI.currentMode = LOADCONFIGURATIONMODE;
	    		}
	    		else{
	    			GUI.currentMode = PERFORMANCEMODE;
	    		}
	    		break;
	    	case R4:
	    		if(button.isSelected()){
	    			GUI.currentMode = SLAVEMASTERMODE;
	    			SlaveMaster sm = new SlaveMaster();
	    		}
	    		else{
	    			GUI.currentMode = PERFORMANCEMODE;
	    		}
	    		break;
	    	case OK:

	    		
	    		
	    		if(GUI.currentMode == CHANGEVOICEMODE){
                    GUI.setInstrument(GUI.getTempInstrument());
                }
                if(GUI.currentMode == CHANGEVELOCITYMODE){
                    GUI.setVelocity(GUI.getTempVelocity());
                }
                if(GUI.currentMode == CHANGELOOPSPEEDMODE){
                    GUI.setLoopSpeed(GUI.getTempLoopSpeed());
                }
                if(GUI.currentMode == CHANGELOOPPOINTMODE){
                	GUI.setLoopPoint(GUI.getTempLoopPoint());
                }
                if(GUI.currentMode == CHANGELAYERMODE){
                	GUI.setCurrentLayer(GUI.getTempLayer());
                	GUI.loadLayer(GUI.getCurrentLayer());
                }
                GUI.currentMode = PERFORMANCEMODE;
                GUI.enableMenuButtons();
                GUI.clearMenuButtons(null);
                GUI.loadLayer(GUI.getCurrentLayer());
	    		break;
	    		
        }
	   //System.out.println("The current mode is " + GUI.currentMode);	
    }
}