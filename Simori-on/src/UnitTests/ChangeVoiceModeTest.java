package UnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Simori.ChangeLayer;
import Simori.ChangeVoiceMode;
import Simori.GUI;
import Simori.Modes;
/**
 * @author Airidas Juskaitis, Ollie McLean, Nicholas Higgins, Mihai Bratosin,
 * Alonso-Lopez Mendoza
 */
public class ChangeVoiceModeTest {
	
	private GUI a;
	private final int CHANGEVOICEMODE = 2;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		a = new GUI();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		a.setVisible(false);
		a.dispose();
	}

	/**
	 * Test method for setTempInstrument(int, int)}.
	 * Tests a regular use for the method.
	 */
	@Test
	public final void testChangeVoiceMode() {
		GUI.setCurrentMode(Modes.CHANGEVOICEMODE);
		ChangeVoiceMode.setTempInstrument(1, 1);
		assertEquals(ChangeVoiceMode.getTempInstrument(),17);		
	}
	
	/**
	 * Test method for setTempInstrument(int, int)}.
	 * Tests the case when the argument value is bigger than
	 * 128 (maximum number of instruments). Temporary instrument
	 * is set.
	 */
	@Test
	public final void testChangeVoiceModeExceedInstruments() {
		GUI.setCurrentMode(Modes.CHANGEVOICEMODE);
		ChangeVoiceMode.setTempInstrument(14,15);
		assertEquals(ChangeVoiceMode.getTempInstrument(),254);		
	}
	
	/**
	 * Regular usage for setInstrument
	 */
	@Test
	public final void testSetInstrument(){
		GUI.setCurrentMode(Modes.CHANGEVOICEMODE);
		ChangeVoiceMode.setTempInstrument(1,1);
		ChangeLayer.Layers[ChangeLayer.getCurrentLayer()].setInstrument(ChangeVoiceMode.getTempInstrument());
		assertEquals(ChangeLayer.Layers[ChangeLayer.getCurrentLayer()].getInstrument(),17);	
	}
	
	/**
	 * Instrument over the limit
	 */
	@Test
	public final void testSetInstrument_Exceeds(){
		GUI.setCurrentMode(Modes.CHANGEVOICEMODE);
		ChangeVoiceMode.setTempInstrument(14,15);
		
		//default instrument
		assertEquals(ChangeLayer.Layers[ChangeLayer.getCurrentLayer()].getInstrument(),22);	
	} 
	
	
}
