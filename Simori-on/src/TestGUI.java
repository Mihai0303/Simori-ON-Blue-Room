import static org.junit.Assert.*;

import java.awt.event.WindowEvent;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * @author Mihai
 *
 */
public class TestGUI {
	
	private GUI a;
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
	public final void testSetTempInstrument() {
		GUI.setCurrentMode(CHANGEVOICEMODE);
		GUI.setTempInstrument(1, 1);
		assertEquals(GUI.getTempInstrument(),17);		
	}
	
	/**
	 * Test method for setTempInstrument(int, int)}.
	 * Tests the case when the argument value is bigger than
	 * 128 (maximum number of instruments). Temporary instrument
	 * should not be set.
	 * @author Nicholas Higgins
	 */
	@Test
	public final void testSetTempInstrumentExceedInstruments() {
		GUI.setCurrentMode(CHANGEVOICEMODE);
		GUI.setTempInstrument(14,15);
		assertEquals(GUI.getTempInstrument(),0);		
	}
	
	/**
	 * Test method for setTempVelocity(int, int).
	 * Tests a normal use of the method
	 * @author Mihai Bratosin
	 */
	@Test
	public final void testSetTempVelocity() {
		GUI.setCurrentMode(CHANGEVELOCITYMODE);
		GUI.setTempVelocity(1, 1);
		assertEquals(GUI.getTempVelocity(),17);
	}
	/**
	 * Test method for setTempVelocity(int, int).
	 * Tests the method in the case where the button pressed
	 * represents a value bigger than 128 (maximum velocity).
	 * Temporary velocity should not be set.
	 * @author Mihai Bratosin
	 */
	@Test
	public final void testSetTempVelocityBiggerThanMax() {
		GUI.setCurrentMode(CHANGEVELOCITYMODE);
		GUI.setTempVelocity(14, 15);
		assertEquals(GUI.getTempVelocity(),0);
	}

	/**
	 * Test method for setTempLoopSpeed(int, int).
	 * Test regular use of the method
	 * @author Ollie McLean
	 */
	@Test
	public final void testSetTempLoopSpeed() {
		GUI.setCurrentMode(CHANGELOOPSPEEDMODE);
		GUI.setTempLoopSpeed(1, 1);
		assertEquals(GUI.getTempLoopSpeed(),17);
	}
	
	/**
	 * Test method for setTempLoopSpeed(int, int).
	 * Tests the method with an argument that represents
	 * a value bigger than the one allowed. Temporary loop speed
	 * should not be set
	 * @author Ollie McLean
	 */
	@Test
	public final void testSetTempLoopSpeedExceedMax() {
		GUI.setCurrentMode(CHANGELOOPSPEEDMODE);
		GUI.setTempLoopSpeed(14, 14);
		assertEquals(GUI.getTempLoopSpeed(),0);
	}
	
	/**
	 * Test method for setTempLoopPoint(int, int).
	 * Test for a regular use of the method. Sets 
	 * the temporary loop point to a value in the allowed range
	 * 0-15
	 * @author Mihai Bratosin
	 */
	@Test
	public final void testSetTempLoopPoint() {
		GUI.setCurrentMode(CHANGELOOPPOINTMODE);
		GUI.setTempLoopPoint(1, 1);
		assertEquals(GUI.getTempLoopPoint(),1);
	}

	/**
	 * Test method for setTempLayer(int, int).
	 * Tests the method for a regular use. Sets
	 * the temporary layer for an allowed value (0-15).
	 * @author Airidas Juskaitis
	 */
	@Test
	public final void testSetTempLayer() {
		GUI.setCurrentMode(CHANGELAYERMODE);
		GUI.setTempLayer(1, 1);
		assertEquals(GUI.getTempLayer(),1);
	}

	/**
	 * Test method for loadLayer(int).
	 * Tests to see if the correct layer is loaded
	 * when method is run.
	 */
	@Test
	public final void testLoadLayer() {
	}

}*/