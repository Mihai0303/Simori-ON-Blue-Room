package UnitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * @author Airidas Juskaitis, Ollie McLean, Nicholas Higgins, Mihai Bratosin,
 * Alonso-Lopez Mendoza
 */
@RunWith(Suite.class)
@SuiteClasses({ ChangeLayerTest.class, ChangeLoopPointTest.class, ChangeLoopSpeedTest.class,
		ChangeVelocityModeTest.class, ChangeVoiceModeTest.class, TestGUI.class })
public class AllTests {

}
