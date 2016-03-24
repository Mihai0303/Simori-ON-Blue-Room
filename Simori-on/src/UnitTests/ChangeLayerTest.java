package UnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Simori.ChangeLayer;
import Simori.GUI;
import Simori.Modes;
/**
 * @author Airidas Juskaitis, Ollie McLean, Nicholas Higgins, Mihai Bratosin,
 * Alonso-Lopez Mendoza
 */
public class ChangeLayerTest {
	
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
	 * Test method for setTempLayer(int, int).
	 * Tests the method for a regular use. Sets
	 * the temporary layer for an allowed value (0-15).
	 * @author Airidas Juskaitis
	 */
	@Test
	public final void testSetTempLayer() {
		GUI.setCurrentMode(Modes.CHANGELAYERMODE);
		ChangeLayer.setTempLayer(1, 1);
		assertEquals(ChangeLayer.getTempLayer(),1);
	}
	
	/**
	 * Tests setTempLayer with an index out of bounds
	 */
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public final void testSetTempLayer_OutOfBounds(){
		GUI.setCurrentMode(Modes.CHANGELAYERMODE);
		ChangeLayer.setTempLayer(17, 17);
		assertEquals(ChangeLayer.getTempLayer(),1);
	}
	
	/**
	 * Tests load layers and the subsequent method
	 * call (on pressing OK)
	 */
	@Test
	public final void testLoadLayer(){
		GUI.setCurrentMode(Modes.CHANGELAYERMODE);
		ChangeLayer.loadLayer(2);
		assertEquals(ChangeLayer.getCurrentLayer(),0);
		ChangeLayer.setCurrentLayer(2);
		assertEquals(ChangeLayer.getCurrentLayer(),2);
	}
	
}
