package UnitTests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Simori.ChangeLayer;
import Simori.ChangeLoopPoint;
import Simori.GUI;

public class ChangeLayerTest {
	
	private GUI a;
	private final int CHANGELAYERMODE = 6;
	
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
		GUI.setCurrentMode(CHANGELAYERMODE);
		ChangeLayer.setTempLayer(1, 1);
		assertEquals(ChangeLayer.getTempLayer(),1);
	}
	
}
