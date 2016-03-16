package UnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Simori.ChangeLoopSpeed;
import Simori.GUI;
import Simori.Modes;
/**
 * @author Airidas Juskaitis, Ollie McLean, Nicholas Higgins, Mihai Bratosin,
 * Alonso-Lopez Mendoza
 */
public class ChangeLoopSpeedTest {
	
	private GUI a;
	private final int CHANGELOOPSPEEDMODE = 4 ;
	
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
	 * Test method for setTempLoopSpeed(int, int).
	 * Test regular use of the method
	 */
	@Test
	public final void testSetTempLoopSpeed() {
		GUI.setCurrentMode(Modes.CHANGELOOPSPEEDMODE);
		ChangeLoopSpeed.setTempLoopSpeed(1, 1);
		assertEquals(ChangeLoopSpeed.getTempLoopSpeed(),17);
	}
	
	/**
	 * Test method for setTempLoopSpeed(int, int).
	 * Tests the method with an argument that represents
	 * a value bigger than the one allowed. Temporary loop speed
	 * should not be set
	 */
	@Test
	public final void testSetTempLoopSpeedExceedMax() {
		GUI.setCurrentMode(Modes.CHANGELOOPSPEEDMODE);
		ChangeLoopSpeed.setTempLoopSpeed(14, 14);
		assertEquals(ChangeLoopSpeed.getTempLoopSpeed(),0);
	}
	
}
