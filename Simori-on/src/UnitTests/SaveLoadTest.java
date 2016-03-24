package UnitTests;
import static org.junit.Assert.*;
import org.junit.Test;
import Simori.GUI;
import Simori.Modes;
import Simori.SaveLoad;

public class SaveLoadTest {
	
	/**
	 * Saves a configuration and then loads it onto the board
	 * Chacks if the configuration is loaded
	 */
	@Test
	public final void testSaveLoad(){
		GUI a = new GUI();
		GUI.setButtonSelected(0,0,true);
		GUI.currentMode = Modes.SAVECONFIGURATIONMODE;
		SaveLoad.save();
		GUI.setButtonSelected(0,0,false);
		GUI.currentMode = Modes.LOADCONFIGURATIONMODE;
		SaveLoad.load("");
		assert(GUI.getButtonSelected(0,0));
	}
}
