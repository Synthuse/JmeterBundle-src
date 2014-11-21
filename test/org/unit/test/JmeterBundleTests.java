package org.unit.test;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import org.apache.jmeter.JMeter;
import org.apache.jmeter.engine.JMeterEngine;
import org.apache.jmeter.engine.StandardJMeterEngine;
import org.apache.jmeter.save.SaveService;
import org.apache.jmeter.util.JMeterUtils;
import org.apache.jorphan.collections.HashTree;

public class JmeterBundleTests {

    public static String jmeterHomePath = ".";
	public static String jmeterPropertiesFile = "./jmeter/jmeter.properties";
	public static String testPlanFilename = "./test/SimpleHttpTest1.jmx";
	public JMeterEngine jEngine = null;
	public HashTree currentHashTree = null;
	
	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void createJmeterEngine() {
		//JMeterEngine jEngine = null;
		try {
			String currentPath = new File(jmeterHomePath).getCanonicalPath();
			JMeterUtils.setJMeterHome(currentPath);
			System.out.println("Setting Jmeter Home: " + currentPath);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.setProperty(JMeter.JMETER_NON_GUI, "true");
		jEngine = new StandardJMeterEngine();
		assertNotNull(jEngine);
        // jmeter.properties
        JMeterUtils.loadJMeterProperties(jmeterPropertiesFile);
 		JMeterUtils.initLogging();// you can comment this line out to see extra log messages of i.e. DEBUG level
		JMeterUtils.initLocale();
        //return jEngine;
	}
	
	@Test
	public void loadTestPlan() {
		//JMeterEngine jEngine = createJmeterEngine();
		FileInputStream reader = null;
		try {
			reader = new FileInputStream(new File(JmeterBundleTests.testPlanFilename));
			currentHashTree = SaveService.loadTree(reader);
			assertNotNull(currentHashTree);
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
			assertNotNull(null);
		}
	}
	
	@Test
	public void runTestPlan() {
		createJmeterEngine();
        File f = new File(JmeterBundleTests.testPlanFilename);
        if (!f.exists() || !f.isFile()) {
            System.out.println("Could not open " + testPlanFilename);
            return ;
        }

		FileInputStream reader = null;
		try {
			reader = new FileInputStream(new File(testPlanFilename));
			currentHashTree = SaveService.loadTree(reader);
			assertNotNull(currentHashTree);
			reader.close();
	        jEngine.configure(currentHashTree);
	        jEngine.runTest();
	        System.out.println("Test " + testPlanFilename + " completed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	void startJmeterGui() {
		//org.apache.jmeter.NewDriver.main(new String[]{"-pjmeter/jmeter.properties"});
		//JMeter jm = new JMeter();
		//jm.start(new String[]{"-pjmeter/jmeter.properties"});
	}
	
}


