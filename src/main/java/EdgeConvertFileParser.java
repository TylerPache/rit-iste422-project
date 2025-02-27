import java.io.*;
import java.util.*;
import javax.swing.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class EdgeConvertFileParser {
   //private String filename = "test.edg";
   private File parseFile;
   private FileReader fr;
   private BufferedReader br;
   private String currentLine;
   private ArrayList alTables, alFields, alConnectors;
   private EdgeTable[] tables;
   private EdgeField[] fields;
   private EdgeField tempField;
   private EdgeConnector[] connectors;
   private String style;
   private String text;
   private String tableName;
   private String fieldName;
   private boolean isEntity, isAttribute, isUnderlined = false;
   private int numFigure, numConnector, numFields, numTables, numNativeRelatedFields;
   private int endPoint1, endPoint2;
   private int numLine;
   private String endStyle1, endStyle2;
   private static Logger logger = LogManager.getLogger(EdgeConvertFileParser.class.getName());
   public static final String EDGE_ID = "EDGE Diagram File"; //first line of .edg files should be this
   public static final String SAVE_ID = "EdgeConvert Save File"; //first line of save files should be this
   public static final String DELIM = "|";

   public EdgeConvertFileParser(File constructorFile) {
      numFigure = 0;
      numConnector = 0;
      alTables = new ArrayList();
      alFields = new ArrayList();
      alConnectors = new ArrayList();
      isEntity = false;
      isAttribute = false;
      parseFile = constructorFile;
      numLine = 0;
      this.openFile(parseFile);
	  logger.debug("Creating EdgeConvertFileParser object using " + constructorFile.toString());
   }

   public void resolveConnectors() { //Identify nature of Connector endpoints
	  logger.info("Resolving connectors...");
      int endPoint1, endPoint2;
      int fieldIndex = 0, table1Index = 0, table2Index = 0;
      for (int cIndex = 0; cIndex < connectors.length; cIndex++) {
         endPoint1 = connectors[cIndex].getEndPoint1();
         endPoint2 = connectors[cIndex].getEndPoint2();
         fieldIndex = -1;
         for (int fIndex = 0; fIndex < fields.length; fIndex++) { //search fields array for endpoints
            if (endPoint1 == fields[fIndex].getNumFigure()) { //found endPoint1 in fields array
               connectors[cIndex].setIsEP1Field(true); //set appropriate flag
               fieldIndex = fIndex; //identify which element of the fields array that endPoint1 was found in
            }
            if (endPoint2 == fields[fIndex].getNumFigure()) { //found endPoint2 in fields array
               connectors[cIndex].setIsEP2Field(true); //set appropriate flag
               fieldIndex = fIndex; //identify which element of the fields array that endPoint2 was found in
            }
         }
         for (int tIndex = 0; tIndex < tables.length; tIndex++) { //search tables array for endpoints
            if (endPoint1 == tables[tIndex].getNumFigure()) { //found endPoint1 in tables array
               connectors[cIndex].setIsEP1Table(true); //set appropriate flag
               table1Index = tIndex; //identify which element of the tables array that endPoint1 was found in
            }
            if (endPoint2 == tables[tIndex].getNumFigure()) { //found endPoint1 in tables array
               connectors[cIndex].setIsEP2Table(true); //set appropriate flag
               table2Index = tIndex; //identify which element of the tables array that endPoint2 was found in
            }
         }

         if (connectors[cIndex].getIsEP1Field() && connectors[cIndex].getIsEP2Field()) { //both endpoints are fields, implies lack of normalization
            JOptionPane.showMessageDialog(null, "The Edge Diagrammer file\n" + parseFile + "\ncontains composite attributes. Please resolve them and try again.");
            EdgeConvertGUI.setReadSuccess(false); //this tells GUI not to populate JList components
            break; //stop processing list of Connectors
         }

         if (connectors[cIndex].getIsEP1Table() && connectors[cIndex].getIsEP2Table()) { //both endpoints are tables
            if ((connectors[cIndex].getEndStyle1().indexOf("many") >= 0) &&
                (connectors[cIndex].getEndStyle2().indexOf("many") >= 0)) { //the connector represents a many-many relationship, implies lack of normalization
               JOptionPane.showMessageDialog(null, "There is a many-many relationship between tables\n\"" + tables[table1Index].getName() + "\" and \"" + tables[table2Index].getName() + "\"" + "\nPlease resolve this and try again.");
               EdgeConvertGUI.setReadSuccess(false); //this tells GUI not to populate JList components
               break; //stop processing list of Connectors
            } else { //add Figure number to each table's list of related tables
               tables[table1Index].addRelatedTable(tables[table2Index].getNumFigure());
               tables[table2Index].addRelatedTable(tables[table1Index].getNumFigure());
               continue; //next Connector
            }
         }

         if (fieldIndex >=0 && fields[fieldIndex].getTableID() == 0) { //field has not been assigned to a table yet
            if (connectors[cIndex].getIsEP1Table()) { //endpoint1 is the table
               tables[table1Index].addNativeField(fields[fieldIndex].getNumFigure()); //add to the appropriate table's field list
               fields[fieldIndex].setTableID(tables[table1Index].getNumFigure()); //tell the field what table it belongs to
            } else { //endpoint2 is the table
               tables[table2Index].addNativeField(fields[fieldIndex].getNumFigure()); //add to the appropriate table's field list
               fields[fieldIndex].setTableID(tables[table2Index].getNumFigure()); //tell the field what table it belongs to
            }
         } else if (fieldIndex >=0) { //field has already been assigned to a table
            JOptionPane.showMessageDialog(null, "The attribute " + fields[fieldIndex].getName() + " is connected to multiple tables.\nPlease resolve this and try again.");
            EdgeConvertGUI.setReadSuccess(false); //this tells GUI not to populate JList components
            break; //stop processing list of Connectors
         }
      } // connectors for() loop
   } // resolveConnectors()

   public void makeArrays() { //convert ArrayList objects into arrays of the appropriate Class type
      if (alTables != null) {
         tables = (EdgeTable[])alTables.toArray(new EdgeTable[alTables.size()]);
      }
      if (alFields != null) {
         fields = (EdgeField[])alFields.toArray(new EdgeField[alFields.size()]);
      }
      if (alConnectors != null) {
         connectors = (EdgeConnector[])alConnectors.toArray(new EdgeConnector[alConnectors.size()]);
      }
   }

   public EdgeTable[] getEdgeTables() {
      return tables;
   }

   public EdgeField[] getEdgeFields() {
      return fields;
   }

   public int getNumFigure() {
      return numFigure;
   }

   public void setNumFigure(int numFigure) { this.numFigure = numFigure; }

   public int getNumConnector() {
      return numConnector;
   }

   public void setNumConnector(int numConnector) { this.numConnector = numConnector; }

   public ArrayList getAlTables() {
      return alTables;
   }

   public void setAlTables(ArrayList alTables) { this.alTables = alTables; }

   public ArrayList getAlFields() {
      return alFields;
   }

   public ArrayList getAlConnectors() {
      return alConnectors;
   }

   public String getStyle() { return style; }

   public void setStyle(String style) { this.style = style; }

   public boolean getIsEntity() {
      return isEntity;
   }

   public void setIsEntity(boolean isEntity) { this.isEntity = isEntity; }

   public boolean getIsAttribute() {
      return isAttribute;
   }

   public void setIsAttribute(boolean isAttribute) { this.isAttribute = isAttribute; }

   public File getParseFile() {
      return parseFile;
   }

   public int getNumLine() {
      return numLine;
   }

   public FileReader getFileReader() {
      return fr;
   }

   public BufferedReader getBufferedReader() {
      return br;
   }

   public EdgeTable[] getTables() { return tables; }

   public EdgeField[] getFields() {
      return fields;
   }

   public EdgeConnector[] getConnectors() {
      return connectors;
   }

   public String getText() {
      return text;
   }

   public void setText(String text) { this.text = text; }

   public String getCurrentLine() { return currentLine; }

   public void setCurrentLine(String currentLine) { this.currentLine = currentLine; }

   public EdgeField getTempField() { return tempField; }

   public void setTempField(EdgeField tempField) { this.tempField = tempField; }

   public boolean getIsUnderlined() { return isUnderlined; }

   public void setIsUnderlined(boolean isUnderlined) { this.isUnderlined = isUnderlined; }

   public void setEndPoint1(int endPoint1) { this.endPoint1 = endPoint1; }

   public int getEndPoint1() {
      return endPoint1;
   }

   public void setEndPoint2(int endPoint2) { this.endPoint2 = endPoint2; }

   public int getEndPoint2() {
      return endPoint2;
   }

   public void setEndStyle1(String endStyle1) { this.endStyle1 = endStyle1; }

   public String getEndStyle1() { return endStyle1; }

   public void setEndStyle2(String endStyle2) { this.endStyle2 = endStyle2; }

   public String getEndStyle2() { return endStyle2; }

   public String getTableName() { return tableName;   }

   public void setTableName(String tableName) { this.tableName = tableName; }

   public int getNumFields() { return numFields; }

   public void setNumFields(int numFields) { this.numFields = numFields; }

   public int getNumTables() { return numTables; }

   public void setNumTables(int numTables) { this.numTables = numTables; }

   public String getFieldName() { return fieldName; }

   public void setFieldName(String fieldName) { this.fieldName = fieldName; }

   public void openFile(File inputFile) {
      try {
         fr = new FileReader(inputFile);
         br = new BufferedReader(fr);
         //test for what kind of file we have
         currentLine = br.readLine().trim();
         numLine++;
//         } else {
//            if (currentLine.startsWith(SAVE_ID)) { //the file chosen is a Save file created by this application
//
//               br.close();
//               this.makeArrays(); //convert ArrayList objects into arrays of the appropriate Class type
//            } else { //the file chosen is something else
//               JOptionPane.showMessageDialog(null, "Unrecognized file format");
//            }
      } // try
      catch (FileNotFoundException fnfe) {
         System.out.println("Cannot find \"" + inputFile.getName() + "\".");
		 logger.error("Cannot find \"" + inputFile.getName() + "\".");
         System.exit(0);
      } // catch FileNotFoundException
      catch (IOException ioe) {
         System.out.println(ioe);
		 logger.error("IOException: " + ioe);
         System.exit(0);
      } // catch IOException
   } // openFile()
} // EdgeConvertFileHandler
