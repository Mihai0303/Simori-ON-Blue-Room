package UnitTests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import Simori.GUI;


/**
 * @author Airidas Juskaitis, Ollie McLean, Nicholas Higgins, Mihai Bratosin,
 * Alonso-Lopez Mendoza
 */
public class TestGUI {
	
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
	 * Test method for loadLayer(int).
	 * Tests to see if the correct layer is loaded
	 * when method is run.
	 */
	@Test
	public final void testLoadLayer() {
	}

}