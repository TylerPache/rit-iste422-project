import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class EdgeTableTest {
    EdgeTable testObj, testObj2, testObj3;
    private static Logger logger = LogManager.getLogger(EdgeConnectorTest.class.getName());

    @Before
    public void setUp() throws Exception {
        logger.info("Setting up test using EdgeTable");
        // testObj = new EdgeField("1|2|3|testStyle1|testStyle2"); <- Original
        testObj = new EdgeTable("3|Test");
    }

//  Test returns a valid string
    @Test
    public void testNumFigureField() throws Exception{
        assertEquals("Testing that the NumFigureField was set correctly. ", 3, testObj.getNumFigure());
        logger.debug("Testing that the NumFigureField was set correctly. Should be set to 3, is set to : " + testObj.getNumFigure());
    }

//  Test returns a valid string
    @Test
    public void testNameField() throws Exception{
        assertEquals("Testing that the NameField was set correctly. ", "Test", testObj.getName());
        logger.debug("Testing that the NameField was set correctly. Should be set to 'Test', is set to : " + testObj.getName());
    }

//  Test returns a valid array string after a valid int is added
    @Test
    public void testAddRelatedTables() throws Exception{
        testObj.addRelatedTable(4);
        testObj.makeArrays();

        assertEquals("Testing that the RelatedTablesField was set correctly.", "[4]", Arrays.toString(testObj.getRelatedTablesArray()));
        logger.info("Testing that the RelatedTablesField was set correctly. Should be set to [4] is set to : " + Arrays.toString(testObj.getRelatedTablesArray()));
    }

//  Test returns a valid array string after a valid int is added and the related field is also set
    @Test
    public void testAddRelatedFields() throws Exception{
        testObj.addNativeField(4);
        testObj.makeArrays();
        testObj.setRelatedField(0,4);

        assertEquals("Testing that the RelatedFields was set correctly.", "[4]", Arrays.toString(testObj.getRelatedFieldsArray()));
        logger.info("Testing that the RelatedFields was set correctly. Should be set to [4] is set to : " + Arrays.toString(testObj.getRelatedFieldsArray()));
    }

//  Test returns an error as there are no fields being added to the related field array
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testAddRelatedFields_withoutNativeField() throws Exception{
        logger.info("Running test with an index out of bound exception...");
        testObj.makeArrays();
        testObj.setRelatedField(0,4);
    }

//  Test returns a valid array string after a valid int is added
    @Test
    public void testAddNativeFields() throws Exception{
        testObj.addNativeField(4);
        testObj.makeArrays();

        assertEquals("Testing that the NativeFields was set correctly.", "[4]", Arrays.toString(testObj.getNativeFieldsArray()));
        logger.info("Testing that the NativeFields was set correctly. Should be set to [4] is set to : " + Arrays.toString(testObj.getNativeFieldsArray()));
    }

//  Test returns a valid array string after valid ints are added to an array and one int is moved up
    @Test
    public void testMoveFieldUp() throws Exception{

        testObj.addNativeField(4);
        testObj.addNativeField(5);
        testObj.addNativeField(6);

        testObj.makeArrays();

        testObj.setRelatedField(0,4);
        testObj.setRelatedField(1,5);
        testObj.setRelatedField(2,6);

        logger.info("RelatedField before moving field up : " + Arrays.toString(testObj.getRelatedFieldsArray()));

        testObj.moveFieldUp(1);

        assertEquals("Testing that the RelatedField at index 1 was moved up.", "[5, 4, 6]", Arrays.toString(testObj.getRelatedFieldsArray()));
        logger.info("RelatedField after moving the field up. Should be set to [5, 4, 6] is set to : " + Arrays.toString(testObj.getRelatedFieldsArray()));
    }

//  Test returns a valid array string after valid ints are added to an array
//  Array order doesn't change as the top field doesn't move
    @Test
    public void testMoveFieldUp_usingFirstField() throws Exception{

        testObj.addNativeField(4);
        testObj.addNativeField(5);
        testObj.addNativeField(6);

        testObj.makeArrays();

        testObj.setRelatedField(0,4);
        testObj.setRelatedField(1,4);
        testObj.setRelatedField(2,6);

        logger.info("RelatedField before moving field up : " + Arrays.toString(testObj.getRelatedFieldsArray()));

        testObj.moveFieldUp(0);

        assertEquals("Testing that the RelatedField at index 1 was moved up.", "[4, 4, 6]", Arrays.toString(testObj.getRelatedFieldsArray()));
        logger.info("RelatedField after moving the field up. Should be set to [4, 5, 6] is set to : " + Arrays.toString(testObj.getRelatedFieldsArray()));
    }

//  Test returns an out-of-bounds error
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testMoveFieldUp_usingInvalidField() throws Exception{

        testObj.addNativeField(4);
        testObj.addNativeField(5);
        testObj.addNativeField(6);

        testObj.makeArrays();

        testObj.setRelatedField(0,4);
        testObj.setRelatedField(1,5);
        testObj.setRelatedField(2,6);

        logger.info("RelatedField before moving field up : " + Arrays.toString(testObj.getRelatedFieldsArray()));

        testObj.moveFieldUp(3);
    }

//  Test returns a valid array string after valid ints are added to an array and one int is moved down
    @Test
    public void testMoveFieldDown() throws Exception{

        testObj.addNativeField(4);
        testObj.addNativeField(5);
        testObj.addNativeField(6);

        testObj.makeArrays();

        testObj.setRelatedField(0,4);
        testObj.setRelatedField(1,5);
        testObj.setRelatedField(2,6);

        logger.info("RelatedField before moving field down : " + Arrays.toString(testObj.getRelatedFieldsArray()));

        testObj.moveFieldDown(1);

        assertEquals("Testing that the RelatedField at index 1 was moved down.", "[4, 6, 5]", Arrays.toString(testObj.getRelatedFieldsArray()));
        logger.info("RelatedField after moving the field down. Should be set to [4, 6, 5] is set to : " + Arrays.toString(testObj.getRelatedFieldsArray()));
    }

//  Test returns a valid array string after valid ints are added to an array
//  Array order doesn't change as the bottom field doesn't move
    @Test
    public void testMoveFieldDown_usingLastField() throws Exception{

        testObj.addNativeField(4);
        testObj.addNativeField(5);
        testObj.addNativeField(6);

        testObj.makeArrays();

        testObj.setRelatedField(0,4);
        testObj.setRelatedField(1,5);
        testObj.setRelatedField(2,6);

        logger.info("RelatedField before moving field down : " + Arrays.toString(testObj.getRelatedFieldsArray()));

        testObj.moveFieldDown(2);

        assertEquals("Testing that the RelatedField at index 1 was moved down.", "[4, 5, 6]", Arrays.toString(testObj.getRelatedFieldsArray()));
        logger.info("RelatedField after moving the field down. Should be set to [4, 5, 6] is set to : " + Arrays.toString(testObj.getRelatedFieldsArray()));
    }

    //  Test returns an out-of-bounds error
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testMoveFieldDown_usingInvalidField() throws Exception{

        testObj.addNativeField(4);
        testObj.addNativeField(5);
        testObj.addNativeField(6);

        testObj.makeArrays();

        testObj.setRelatedField(0,4);
        testObj.setRelatedField(1,5);
        testObj.setRelatedField(2,6);

        logger.info("RelatedField before moving field down : " + Arrays.toString(testObj.getRelatedFieldsArray()));

        testObj.moveFieldDown(3);
    }

//  Test returns the EdgeTable info as a string
    @Test
    public void testToStringMethod() throws Exception{

        testObj.addRelatedTable(3);
        testObj.addRelatedTable(4);

        testObj.addNativeField(4);
        testObj.addNativeField(5);
        testObj.addNativeField(6);

        testObj.makeArrays();

        testObj.setRelatedField(0,4);
        testObj.setRelatedField(1,5);
        testObj.setRelatedField(2,6);

        assertEquals("Testing that the toString works properly",
                "Table: 3\r\n" +
                "{\r\n" +
                "TableName: Test\r\n" +
                "NativeFields: 4|5|6\r\n" +
                "RelatedTables: 3|4\r\n" +
                "RelatedFields: 4|5|6\r\n" +
                "}\r\n", testObj.toString());
        logger.info("Output of testObj.toString() : " + testObj.toString());
    }
}