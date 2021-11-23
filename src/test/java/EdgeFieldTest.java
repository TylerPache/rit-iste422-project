import static org.junit.Assert.*;

import java.beans.Transient;

import org.junit.Before;
import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EdgeFieldTest {
	EdgeField testObj;
	private static Logger logger = LogManager.getLogger(EdgeConnectorTest.class.getName());
	@Before
	public void setUp() throws Exception {
		logger.info("Setting up test using EdgeConnector");
		// testObj = new EdgeField("1|2|3|testStyle1|testStyle2"); <- Original
        testObj = new EdgeField("6|Test|0|0|0|0|1|false|false|");
	}


    // Testing for invalid Input
    // should fail and pass NumberFormatException
	@Test(expected = NumberFormatException.class)
	public void testInvalidInput() throws Exception {
        logger.info("Testing invalid string format. ");
        testObj = new EdgeField("a|HelloWorld|0|b|0|zero|1|yes|false|");
	}


	@Test
	public void testValidName() throws Exception {
		assertEquals("Testing for a valid name", "Test", testObj.getName());
		logger.info("Testing that the Name was set correctly. Should be set to 'Test', is set to : " + testObj.getName());

	}


	@Test
	public void testValidTableId() throws Exception {
		assertEquals("Testing for valid table ID", 0, testObj.getTableID());
		logger.info("Testing that the table ID is set correctly.  Should be set to 0, is set to : " + testObj.getTableID());
	}


	@Test(expected = AssertionError.class)
	public void testInvalidTableId() throws Exception {
		assertEquals("Testing for valid table ID", "tableID", testObj.getTableID());
		logger.info("Testing that the table ID is set correctly.  Should throw AssertionError");
	}


	@Test
	public void testValidTableBound() throws Exception {
		assertEquals("Testing for valid table bound", 0, testObj.getTableBound());

		logger.info("Testing that the table bound is set correctly.  Should be set to 0, is set to: " + testObj.getTableBound());
	}


	@Test(expected = AssertionError.class)
	public void testInvalidTableBound() throws Exception {
		assertEquals("Testing for valid table bound", "Error", testObj.getTableBound());

		logger.info("Testing that the table bound throws error on incorrect input.  Should throw AssertionError.");
	}


	@Test
	public void testSetTableBound() throws Exception {
		testObj.setTableBound(3);
		assertEquals("Testing for set table bound", 3, testObj.getTableBound());
		logger.info("Testing that you can set the table bound.  Should be set to 3, is set to: " + testObj.getTableBound());

	}


	@Test
	public void testValidFieldBound() throws Exception {
		assertEquals("Testing for valid field bound", 0, testObj.getFieldBound());
		logger.info("Testing that the field bound is set properly.  Should be set to 0, is set to: " + testObj.getFieldBound());
	}


	@Test(expected = AssertionError.class)
	public void testInvalidFieldBound() throws Exception {
		assertEquals("Testing for valid field bound", "Error", testObj.getFieldBound());
		logger.info("Testing that the field bound throws error on incorrect input.  Should throw AssertionError.");
	}


	@Test
	public void testSetFieldBound() throws Exception {
		testObj.setFieldBound(3);
		assertEquals("Testing for valid field bound", 3, testObj.getFieldBound());
		logger.info("Testing that you can set the field bound.  Should be set to 3, is set to: " + testObj.getFieldBound());
	}


	@Test
	public void testValidDisallowNull() throws Exception {
		assertEquals("Testing for bool value for DisallowNull", false, testObj.getDisallowNull());
		logger.info("Testing that DisallowNull is bool. Should be set to false, is set to: " + testObj.getDisallowNull());
	}


	@Test(expected = AssertionError.class)
	public void testInvalidDisallowNull() throws Exception {
		assertEquals("Testing for bool value for DisallowNull", 3, testObj.getDisallowNull());

		logger.info("Testing that the DisallowNull throws error on incorrect input.  Should throw AssertionError.");
	}


	@Test
	public void testSetDisallowNull() throws Exception {
		testObj.setDisallowNull(true);
		assertEquals("Testing for set value for DisallowNull", true, testObj.getDisallowNull());
		logger.info("Testing that DisallowNull is set properly. Should be set to true, is set to: " + testObj.getDisallowNull());
	}


	@Test
	public void testValidIsPrimaryKey() throws Exception {
		assertEquals("Testing for bool value for IsPrimaryKey", false, testObj.getIsPrimaryKey());
		logger.info("Testing that IsPrimaryKey is bool. Should be set to false, is set to: " + testObj.getIsPrimaryKey());
	}


	@Test(expected = AssertionError.class)
	public void testInvalidIsPrimaryKey() throws Exception {
		assertEquals("Testing for bool value for IsPrimaryKey", 3, testObj.getIsPrimaryKey());

		logger.info("Testing that the IsPrimaryKey throws error on incorrect input.  Should throw AssertionError.");
	}


	@Test
	public void testSetIsPrimaryKey() throws Exception {
		testObj.setIsPrimaryKey(true);
		assertEquals("Testing for set value for IsPrimaryKey", true, testObj.getIsPrimaryKey());
		logger.info("Testing that IsPrimaryKey is set properly. Should be set to true, is set to: " + testObj.getIsPrimaryKey());
	}


	@Test
	public void testValidDefaultValue() throws Exception {
		assertEquals("Testing for string value for DefaultValue", "", testObj.getDefaultValue());
		logger.info("Testing that DefaultValue is a string. Should be set to '', is set to: " + testObj.getDefaultValue());
	}


	@Test(expected = AssertionError.class)
	public void testInvalidDefaultValue() throws Exception {
		assertEquals("Testing for string value for DefaultValue", 3, testObj.getDefaultValue());

		logger.info("Testing that the DefaultValue throws error on incorrect input.  Should throw AssertionError.");
	}
	@Test
	public void testSetDefaultValue() throws Exception {
		testObj.setDefaultValue("test");
		assertEquals("Testing for set value for DefaultValue", "test", testObj.getDefaultValue());
		logger.info("Testing that DefaultValue is set properly. Should be set to test, is set to: " + testObj.getDefaultValue());
	}


	@Test
	public void testValidVarcharValue() throws Exception {
		assertEquals("Testing for int value for VarcharValue", 1, testObj.getVarcharValue());
		logger.info("Testing that VarcharValue is an int. Should be set to 1, is set to: " + testObj.getVarcharValue());
	}


	@Test(expected = AssertionError.class)
	public void testInvalidVarcharValue() throws Exception {
		assertEquals("Testing for int value for VarcharValue", "test", testObj.getVarcharValue());

		logger.info("Testing that the VarcharValue throws error on incorrect input.  Should throw AssertionError.");
	}


	@Test
	public void testSetVarcharValue() throws Exception {
		testObj.setVarcharValue(3);
		assertEquals("Testing for set value for VarcharValue", 3, testObj.getVarcharValue());
		logger.info("Testing that VarcharValue is set properly. Should be set to 3, is set to: " + testObj.getVarcharValue());
	}


	@Test
	public void testValidDataType() throws Exception {
		assertEquals("Testing for int value for DataType", 0, testObj.getDataType());
		logger.info("Testing that DefaultValue is an int. Should be set to 0, is set to: " + testObj.getDataType());
	}


	@Test(expected = AssertionError.class)
	public void testInvalidTypeDataType() throws Exception {
		assertEquals("Testing for int value for DataType", "test", testObj.getDataType());

		logger.info("Testing that the DefaultValue throws error on incorrect input.  Should throw AssertionError.");
	}



	@Test(expected = AssertionError.class)
	public void testInvalidValueDataType() throws Exception {
		assertEquals("Testing for int value for DataType", 6, testObj.getDataType());

		logger.info("Testing that the DefaultValue throws error on correct type but incorrect value input.  Should throw AssertionError.");
	}



	@Test
	public void testSetDataType() throws Exception {
		testObj.setDataType(3);
		assertEquals("Testing for set value for DataType", 3, testObj.getDataType());
		logger.info("Testing that DataType is set properly. Should be set to 3, is set to: " + testObj.getDataType());
	}


	@Test
	public void testReturnStrDataType() throws Exception {
		String[] returnVal = {"Varchar", "Boolean", "Integer", "Double"};
		assertEquals("Testing for return values in StrDataType", returnVal, testObj.getStrDataType() );
		logger.info("Testing that strDataType returns proper values. Should be set to {\"Varchar\", \"Boolean\", \"Integer\", \"Double\"}, is set to: " + testObj.getStrDataType());
	}

}

