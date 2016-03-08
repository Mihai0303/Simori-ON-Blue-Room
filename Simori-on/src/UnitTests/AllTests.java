package UnitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ChangeLayerTest.class, ChangeLoopPointTest.class, ChangeLoopSpeedTest.class,
		ChangeVelocityModeTest.class, ChangeVoiceModeTest.class, TestGUI.class })
public class AllTests {

}
