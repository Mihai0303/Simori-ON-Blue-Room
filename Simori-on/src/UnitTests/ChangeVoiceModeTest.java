package UnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Simori.ChangeVoiceMode;
import Simori.GUI;

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
	 * @author Nicholas Higgins
	 */
	@Test
	public final void testChangeVoiceMode() {
		GUI.setCurrentMode(CHANGEVOICEMODE);
		ChangeVoiceMode.setTempInstrument(1, 1);
		assertEquals(ChangeVoiceMode.getTempInstrument(),17);		
	}
	
	/**
	 * Test method for setTempInstrument(int, int)}.
	 * Tests the case when the argument value is bigger than
	 * 128 (maximum number of instruments). Temporary instrument
	 * should not be set.
	 * @author Nicholas Higgins
	 */
	@Test
	public final void testChangeVoiceModeExceedInstruments() {
		GUI.setCurrentMode(CHANGEVOICEMODE);
		ChangeVoiceMode.setTempInstrument(14,15);
		assertEquals(ChangeVoiceMode.getTempInstrument(),0);		
	}
	
	
	
}
