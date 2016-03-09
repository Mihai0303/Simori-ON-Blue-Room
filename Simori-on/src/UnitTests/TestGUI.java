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
	private final int ONOFFMODE = 0;
	private final int PERFORMANCEMODE = 1;
//	private final int CHANGEVOICEMODE = 2;
//	private final int CHANGEVELOCITYMODE = 3;
//	private final int CHANGELOOPSPEEDMODE = 4;
//	private final int CHANGELOOPPOINTMODE = 5;
//	private final int CHANGELAYERMODE = 6;
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
	 * Test method for loadLayer(int).
	 * Tests to see if the correct layer is loaded
	 * when method is run.
	 */
	@Test
	public final void testLoadLayer() {
	}

}