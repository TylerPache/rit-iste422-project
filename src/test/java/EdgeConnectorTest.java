import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EdgeConnectorTest {
	EdgeConnector testObj;
	private static Logger logger = LogManager.getLogger(EdgeConnectorTest.class.getName());

	@Before
	public void setUp() throws Exception {
		logger.info("Setting up test using EdgeConnector");
		testObj = new EdgeConnector("1|2|3|testStyle1|testStyle2");
	}

	@Test
	public void testGetNumConnector() {
		// Example of how a value can be passed into a test
		String opt1Str = System.getProperty("optionone");
		final long opt1;
		if (opt1Str == null) {
			opt1 = 1;
		}
		else {
			opt1 = Long.parseLong(opt1Str);
		}
		assertEquals("numConnector was intialized to 1 so it should be 1",(long)opt1,testObj.getNumConnector());
		logger.debug("Run of testGetNumConnector opt1: " + (long)opt1 + " , getNumConnector: " + testObj.getNumConnector());
	}

	@Test
	public void testGetEndPoint1() {
		assertEquals("EndPoint1 was intialized to 2",2,testObj.getEndPoint1());
		logger.debug("EndPoint1 initialized to 2. testObj.getEndPoint1(): " + testObj.getEndPoint1());
	}

	@Test
	public void testGetEndPoint2() {
		assertEquals("EndPoint2 was intialized as 3",3,testObj.getEndPoint2());
		logger.debug("EndPoint2 initialized to 3. testObj.getEndPoint2(): " + testObj.getEndPoint2());
	}

	@Test
	public void testGetEndStyle1() {
		assertEquals("endStyle1 was intialized to \"testStyle1\"","testStyle1",testObj.getEndStyle1());
		logger.debug("endStyle1 initialized to \"testStyle1\". testObj.getEndStyle1(): " + testObj.getEndStyle1());
	}

	@Test
	public void testGetEndStyle2() {
		assertEquals("endStyle1 was intialized to \"testStyle1\"","testStyle2",testObj.getEndStyle2());
		logger.debug("endStyle1 initialized to \"testStyle2\". testObj.getEndStyle2(): " + testObj.getEndStyle2());
	}

	@Test
	public void testGetIsEP1Field() {
		assertEquals("isEP1Field should be false",false,testObj.getIsEP1Field());
		logger.debug("isEP1Field test should be false. testObj.getIsEP1Field(): " + testObj.getIsEP1Field());
	}

	@Test
	public void testGetIsEP2Field() {
		assertEquals("IsEP2Field should be false",false,testObj.getIsEP2Field());
		logger.debug("isEP2Field test should be false. testObj.getIsEP2Field(): " + testObj.getIsEP2Field());
	}

	@Test
	public void testGetIsEP1Table() {
		assertEquals("isEP1Table should be false",false,testObj.getIsEP1Table());
		logger.debug("isEP1Table test should be false. testObj.getIsEP1Table(): " + testObj.getIsEP1Table());
	}

	@Test
	public void testGetIsEP2Table() {
		assertEquals("isEP2Table should be false",false,testObj.getIsEP2Table());
		logger.debug("isEP2Table test should be false. testObj.getIsEP2Table(): " + testObj.getIsEP2Table());
	}

	@Test
	public void testSetIsEP1Field() {
		testObj.setIsEP1Field(false);
		assertEquals("isEP1Field should be what you set it to",false,testObj.getIsEP1Field());
		logger.debug("isEP1Field test should be set manually. testObj.getIsEP1Field(): " + testObj.getIsEP1Field());
	}

	@Test
	public void testSetIsEP2Field() {
		testObj.setIsEP2Field(false);
		assertEquals("isEP2Field should be what you set it to",false,testObj.getIsEP2Field());
		logger.debug("isEP2Field test should be set manually. testObj.getIsEP2Field(): " + testObj.getIsEP2Field());
	}

	@Test
	public void testSetIsEP1Table() {
		testObj.setIsEP1Table(false);
		assertEquals("isEp1Table should be what you set it to",false,testObj.getIsEP1Table());
		logger.debug("isEP1Table test should be set manually. testObj.getIsEP1Table(): " + testObj.getIsEP1Table());
	}

	@Test
	public void testSetIsEP2Table() {
		testObj.setIsEP2Table(false);
		assertEquals("isEp2Table should be what you set it to",false,testObj.getIsEP2Table());
		logger.debug("isEP2Table test should be set manually. testObj.getIsEP2Table(): " + testObj.getIsEP2Table());
	}

}
