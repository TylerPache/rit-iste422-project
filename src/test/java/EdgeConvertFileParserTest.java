// ***********
// SINCE REFACTORING, EdgeConvertFileParser CAN NO LONGER CAN BE INSTANTIATED
// ***********

//import static org.junit.Assert.*;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//
//import java.io.*;
//import java.util.ArrayList;
//
//public class EdgeConvertFileParserTest {
//    private EdgeConvertFileParser testOpenObj;
//    private EdgeConvertFileParser testSaveObj;
//    private EdgeConvertFileParser testOtherObj;
//    private File testOpenFile;
//    private File testSaveFile;
//    private File testOtherFile;
//    private static Logger logger = LogManager.getLogger(EdgeConvertFileParserTest.class.getName());
//
//    @Before
//    public void setUp() throws Exception {
//        logger.info("Setting up test using EdgeConvertFileParser");
//        testOpenFile = new File("./src/test/resources/mocks/Courses.edg");
//        testOpenObj = new EdgeConvertFileParser(testOpenFile);
//
//        testSaveFile = new File("./src/test/resources/mocks/Courses_save.edg.sav");
//        testSaveObj = new EdgeConvertFileParser(testSaveFile);
//    }
//
//    @Test
//    public void testNumFigureFieldSettingInConstructor() throws Exception {
//        assertEquals("Testing that the numFigure was set correctly. ", 19, testOpenObj.getNumFigure());
//        logger.debug("Testing that the numFigure was set correctly. Should be set to 0, is set to : " + testOpenObj.getNumFigure());
//    }
//
//    @Test
//    public void testNumConnectorFieldSettingInConstructor() throws Exception {
//        assertEquals("Testing that the numConnector was set correctly. ", 24, testOpenObj.getNumConnector());
//        logger.debug("Testing that the numConnector was set correctly. Should be set to 0, is set to : " + testOpenObj.getNumConnector());
//    }
//
//    // @Test
//    // public void testAlTablesFieldSettingInConstructor() throws Exception {
//    //     assertEquals("Testing that the alTables was set correctly. ", new ArrayList<>(), testOpenObj.getAlTables().size());
//    //     logger.debug("Testing that the alTables was set correctly. Should be set to to empty ArrayList, is set to :" +
//    //     testOpenObj.getAlTables());
//    // }
//
//    @Test
//    public void testAlFieldsFieldSettingInConstructor() throws Exception {
//        assertEquals("Testing that the alFields was set correctly. ", 7, testOpenObj.getAlFields().size());
//        logger.debug("Testing that the alFields was set correctly. Should be set to to empty ArrayList, is set to : " + testOpenObj.getAlFields());
//    }
//
//    @Test
//    public void testAlConnectorsFieldSettingInConstructor() throws Exception {
//        assertEquals("Testing that the alConnectors was set correctly. ", 10, testOpenObj.getAlConnectors().size());
//        logger.debug("Testing that the alConnectors was set correctly. Should be set to empty ArrayList, is set to : " + testOpenObj.getAlConnectors());
//    }
//
//    @Test
//    public void testIsEntityFieldSettingInConstructor() throws Exception {
//        assertEquals("Testing that the isEntity was set correctly. ", false, testOpenObj.getIsEntity());
//        logger.debug("Testing that the isEntity was set correctly. Should be set to false, is set to : " + testOpenObj.getIsEntity());
//    }
//
//    @Test
//    public void testIsAttributeFieldSettingInConstructor() throws Exception {
//        assertEquals("Testing that the isAttribute was set correctly. ", false, testOpenObj.getIsAttribute());
//        logger.debug("Testing that the isAttribute was set correctly. Should be set to false, is set to : " + testOpenObj.getIsAttribute());
//    }
//
//    @Test
//    public void testParseFileFieldSettingInConstructor() throws Exception {
//        assertEquals("Testing that the parseFile was set correctly. ", testOpenFile, testOpenObj.getParseFile());
//        logger.debug("Testing that the parseFile was set correctly. Should be set to File object argument, is set to : " + testOpenObj.getParseFile());
//    }
//
//    @Test
//    public void testNumLineFieldSettingInConstructor() throws Exception {
//        assertEquals("Testing that the numLine was set correctly. ", 1, testOpenObj.getNumLine());
//        logger.debug("Testing that the numLine was set correctly. Should be set to 0, is set to : " + testOpenObj.getNumLine());
//    }
//
//    @Test
//    public void testOpenFileWithEdgeId() throws Exception {
//        assertTrue("Testing that first line in sample open file starts with a EDGE_ID. ",
//            new BufferedReader (new FileReader(testOpenFile)).readLine().trim().startsWith("EDGE Diagram File"));
//        logger.debug("Testing that first line in sample open starts with a EDGE_ID. Should be set to 'EDGE Diagram File', is set to : " +
//            new BufferedReader (new FileReader(testOpenFile)).readLine().trim().startsWith("EDGE Diagram File"));
//
//        assertNotNull("Testing that FileReader was established. ", testOpenObj.getFileReader());
//        logger.debug("Testing that FileReader was established. Should be set to FileReader object, is set to : " + testOpenObj.getFileReader());
//
//        assertNotNull("Testing that BufferedReader was established. ", testOpenObj.getBufferedReader());
//        logger.debug("Testing that BufferedReader was established. Should be set to BufferedReader object, is set to : " + testOpenObj.getBufferedReader());
//
//        boolean testBufferedStreamIsClosed = false;
//        try {
//            testOpenObj.getBufferedReader().ready();
//        } catch (IOException e) {
//            testBufferedStreamIsClosed = true;
//        } finally {
//            assertTrue("Testing that used BufferedReader is closed. ", testBufferedStreamIsClosed);
//            logger.debug("Testing that used BufferedReader is closed. Should be closed (t,f) : " + testBufferedStreamIsClosed);
//        }
//
//        assertArrayEquals("Testing that EdgeTable array was established. ",
//            (EdgeTable[])testOpenObj.getAlTables().toArray(new EdgeTable[testOpenObj.getAlTables().size()]), testOpenObj.getTables());
//        logger.debug("Testing that EdgeTable array was established. Should be set to EdgeTable array, is set to : " + testOpenObj.getTables());
//
//        assertArrayEquals("Testing that EdgeField array was established. ",
//            (EdgeField[])testOpenObj.getAlFields().toArray(new EdgeField[testOpenObj.getAlFields().size()]), testOpenObj.getFields());
//        logger.debug("Testing that EdgeField array was established. Should be set to EdgeField array, is set to : " + testOpenObj.getFields());
//
//        assertArrayEquals("Testing that EdgeConnector array was established. ",
//            (EdgeConnector[])testOpenObj.getAlConnectors().toArray(new EdgeConnector[testOpenObj.getAlConnectors().size()]), testOpenObj.getConnectors());
//        logger.debug("Testing that EdgeConnector array was established. Should be set to EdgeConnector array, is set to : " + testOpenObj.getConnectors());
//
//        assertEquals("Testing that connectors are created. ", 10, testOpenObj.getConnectors().length);
//        logger.debug("Testing that connectors are created. Should be empty, is set to : " + testOpenObj.getConnectors().length);
//
//        assertEquals("Testing that style field was used. ", "Entity", testOpenObj.getStyle());
//        logger.debug("Testing that style field was used. Should be \"Entity\", is set to : " + testOpenObj.getStyle());
//
//        assertEquals("Testing that isEntity field was used. ", false, testOpenObj.getIsEntity());
//        logger.debug("Testing that isEntity field was used. Should be true, is set to : " + testOpenObj.getIsEntity());
//
//        assertEquals("Testing that isAttribute field was used. ", false, testOpenObj.getIsAttribute());
//        logger.debug("Testing that connectors are created. Should be false, is set to : " + testOpenObj.getIsAttribute());
//    }
//
//    @Test
//    public void testOpenFileWithSaveId() throws Exception {
//        assertTrue("Testing that first line in sample open file starts with a SAVE_ID. ",
//            new BufferedReader (new FileReader(testSaveFile)).readLine().trim().startsWith("EdgeConvert Save File"));
//        logger.debug("Testing that first line in sample open starts with a SAVE_ID. Should be set to 'EdgeConvert Save File', is set to : " +
//            new BufferedReader (new FileReader(testSaveFile)).readLine().trim().startsWith("EdgeConvert Save File"));
//
//        assertNotNull("Testing that FileReader was established. ", testSaveObj.getFileReader());
//        logger.debug("Testing that FileReader was established. Should be set to FileReader object, is set to : " + testSaveObj.getFileReader());
//
//        assertNotNull("Testing that BufferedReader was established. ", testSaveObj.getBufferedReader());
//        logger.debug("Testing that BufferedReader was established. Should be set to BufferedReader object, is set to : " + testSaveObj.getBufferedReader());
//
//        boolean testBufferedStreamIsClosed = false;
//        try {
//            testSaveObj.getBufferedReader().ready();
//        } catch (IOException e) {
//            testBufferedStreamIsClosed = true;
//        } finally {
//            assertTrue("Testing that used BufferedReader is closed. ", testBufferedStreamIsClosed);
//            logger.debug("Testing that used BufferedReader is closed. Should be closed (t,f) : " + testBufferedStreamIsClosed);
//        }
//
//        assertArrayEquals("Testing that EdgeTable array was established. ",
//            (EdgeTable[])testSaveObj.getAlTables().toArray(new EdgeTable[testSaveObj.getAlTables().size()]), testSaveObj.getTables());
//        logger.debug("Testing that EdgeTable array was established. Should be set to EdgeTable array, is set to : " + testSaveObj.getTables());
//
//        assertArrayEquals("Testing that EdgeField array was established. ",
//            (EdgeField[])testSaveObj.getAlFields().toArray(new EdgeField[testSaveObj.getAlFields().size()]), testSaveObj.getFields());
//        logger.debug("Testing that EdgeField array was established. Should be set to EdgeField array, is set to : " + testSaveObj.getFields());
//
//        assertArrayEquals("Testing that EdgeConnector array was established. ",
//            (EdgeConnector[])testSaveObj.getAlConnectors().toArray(new EdgeConnector[testSaveObj.getAlConnectors().size()]), testSaveObj.getConnectors());
//        logger.debug("Testing that EdgeConnector array was established. Should be set to EdgeConnector array, is set to : " + testSaveObj.getConnectors());
//    }
//
//    @Test
//    public void testOpenFileWithNeitherSaveNorOpenHandling() throws Exception {
//        testOtherFile = new File("./src/test/resources/mocks/Courses_other_file.edg");
//        // System.exit(0); ** this stops popup from occurring, but also breaks some testing envs **
//        testOtherObj = new EdgeConvertFileParser(testOtherFile);
//
//        assertFalse("Testing that first line in sample open file starts with something other than EDGE_ID and SAVE_ID. ",
//            new BufferedReader (new FileReader(testOtherFile)).readLine().trim().startsWith("EDGE Diagram File") ||
//            new BufferedReader (new FileReader(testOtherFile)).readLine().trim().startsWith("EdgeConvert Save File"));
//
//        assertNotNull("Testing that FileReader was established. ", testOtherObj.getFileReader());
//        logger.debug("Testing that FileReader was established. Should be set to FileReader object, is set to : " + testOtherObj.getFileReader());
//
//        assertNotNull("Testing that BufferedReader was established. ", testOtherObj.getBufferedReader());
//        logger.debug("Testing that BufferedReader was established. Should be set to BufferedReader object, is set to : " + testOtherObj.getBufferedReader());
//
//        assertEquals("Testing that no alFields are created. ", 0, testOtherObj.getAlFields().size());
//        logger.debug("Testing that no alFields are created. Should be empty, is set to : " + testOtherObj.getAlFields().size());
//
//        assertEquals("Testing that no alTables are created. ", 0, testOtherObj.getAlTables().size());
//        logger.debug("Testing that no alTables are created. Should be empty, is set to : " + testOtherObj.getAlTables().size());
//
//        assertEquals("Testing that no alConnectors are created. ", 0,testOtherObj.getAlConnectors().size());
//        logger.debug("Testing that no alConnectors are created. Should be empty, is set to : " + testOtherObj.getAlConnectors().size());
//
//        assertNull("Testing that no connectors are created. ",testOtherObj.getConnectors());
//        logger.debug("Testing that no connectors are created. Should be null, is set to : " + testOtherObj.getConnectors());
//    }
//}