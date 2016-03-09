package Simori;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JToggleButton;

public class GridListener implements ActionListener{
    
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
     * Method performed when an ActionEvent is fired by one of the
     * 256 grid buttons. If the board is in one of the specified modes
     * the method reacts appropriately (setting voice, velocity, loop speed
     * and so on).
     * @param e The Action Event fired
     * @author Mihai Bratosin
     */
    public void actionPerformed(ActionEvent e) {
        // Gets the button that was pressed
        JToggleButton buttonPressed = (JToggleButton) e.getSource();
        
        // Get the column and row of the pressed button
        int x = (buttonPressed.getX()-1)/28;
        int y = (buttonPressed.getY()-1)/28;
        
        
        switch(GUI.currentMode){
        	case ONOFFMODE:
        		break;
        	case PERFORMANCEMODE:
        		// sets the value of truth in the currently selected layer to the opposite
        		// if the button is selected changes it to true and if it is de-selected
        		// sets it to false
        		if(buttonPressed.isSelected()){
        			ChangeLayer.getLayers(ChangeLayer.getCurrentLayer()).setContents(x,y,true);
        		}
        		else
        			ChangeLayer.getLayers(ChangeLayer.getCurrentLayer()).setContents(x,y,false);
        		break;
        	case CHANGEVOICEMODE:
        		ChangeVoiceMode.setTempInstrument(x,y);
        		break;
        	case CHANGEVELOCITYMODE:
        		ChangeVelocity.setTempVelocity(x, y);
        		break;
        	case CHANGELOOPSPEEDMODE:
        		ChangeLoopSpeed.setTempLoopSpeed(x,y);
        		break;
        	case CHANGELOOPPOINTMODE:
        		ChangeLoopPoint.setTempLoopPoint(x,y);
        		break;
        	case CHANGELAYERMODE:
        		ChangeLayer.setTempLayer(x,y);
        		System.out.println(ChangeLayer.getCurrentLayer());
        		break;
        	case SAVECONFIGURATIONMODE:
        		SaveLoad.setFileName(x,y);
        		break;
        	case LOADCONFIGURATIONMODE:
        		SaveLoad.setFileName(x, y);
        		break;
        	case SLAVEMASTERMODE:
        		break;
        }
    }

}
