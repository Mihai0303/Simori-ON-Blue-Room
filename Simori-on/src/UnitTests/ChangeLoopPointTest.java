package UnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Simori.ChangeLoopPoint;
import Simori.GUI;
import Simori.Modes;
/**
 * @author Airidas Juskaitis, Ollie McLean, Nicholas Higgins, Mihai Bratosin,
 * Alonso-Lopez Mendoza
 */
public class ChangeLoopPointTest {
	
	private GUI a;
	
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
	 * Test method for setTempLoopPoint(int, int).
	 * Test for a regular use of the method. Sets 
	 * the temporary loop point to a value in the allowed range
	 * 0-15
	 */
	@Test	
	public final void testSetTempLoopPoint() {
		GUI.setCurrentMode(Modes.CHANGELOOPPOINTMODE);
		ChangeLoopPoint.setTempLoopPoint(1, 1);
		assertEquals(ChangeLoopPoint.getTempLoopPoint(),1);
	}
	
	/**
	 * Tests setLoopPoint with an out of bounds value
	 */
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public final void testSetTempLoopPoint_OutOfBounds(){
		GUI.setCurrentMode(Modes.CHANGELOOPPOINTMODE);
		ChangeLoopPoint.setTempLoopPoint(17, 17);
	}
	
	/**
	 * Tests if the loop point is set appropriately
	 */
	@Test
	public final void testSetLoopPoint(){
		GUI.setCurrentMode(Modes.CHANGELOOPPOINTMODE);
		ChangeLoopPoint.setLoopPoint(15);
		assertEquals(ChangeLoopPoint.getLoopPoint(),15);
	}
	
	/**
	 * Tests reaction to bigger than 15 loop point value
	 * Should keep previous value
	 */
	@Test
	public final void testSetLoopPoint_BiggerValue(){
		GUI.setCurrentMode(Modes.CHANGELOOPPOINTMODE);
		ChangeLoopPoint.setLoopPoint(20);
		assertEquals(ChangeLoopPoint.getLoopPoint(),15);
	}
}
