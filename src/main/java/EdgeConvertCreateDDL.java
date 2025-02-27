import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.util.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class EdgeConvertCreateDDL {
   static String[] products = {"MySQL"};
   protected EdgeTable[] tables; //master copy of EdgeTable objects
   protected EdgeField[] fields; //master copy of EdgeField objects
   protected int[] numBoundTables;
   protected int maxBound;
   protected StringBuffer sb;
   protected int selected;
   private static Logger logger = LogManager.getLogger(EdgeConvertCreateDDL.class.getName());

   public EdgeConvertCreateDDL(EdgeTable[] tables, EdgeField[] fields) {
      this.tables = tables;
      this.fields = fields;
      initialize();
	  logger.debug("Creating EdgeConvertCreateDDL object using " + tables.toString() + " and " + fields.toString());
   } //EdgeConvertCreateDDL(EdgeTable[], EdgeField[])

   public EdgeConvertCreateDDL() { //default constructor with empty arg list for to allow output dir to be set before there are table and field objects

   } //EdgeConvertCreateDDL()

   public void initialize() {
      numBoundTables = new int[tables.length];
      maxBound = 0;
      sb = new StringBuffer();

      for (int i = 0; i < tables.length; i++) { //step through list of tables
         int numBound = 0; //initialize counter for number of bound tables
         int[] relatedFields = tables[i].getRelatedFieldsArray();
         for (int j = 0; j < relatedFields.length; j++) { //step through related fields list
            if (relatedFields[j] != 0) {
               numBound++; //count the number of non-zero related fields
            }
         }
         numBoundTables[i] = numBound;
         if (numBound > maxBound) {
            maxBound = numBound;
         }
      }
   }

   protected EdgeTable getTable(int numFigure) {
	  logger.info("Attempting to get edge table.");
	  logger.debug("Getting table using figure number of " + numFigure);
      for (int tIndex = 0; tIndex < tables.length; tIndex++) {
         if (numFigure == tables[tIndex].getNumFigure()) {
			logger.debug("Found table at index " + tIndex + " with value of " + tables[tIndex].getNumFigure());
            return tables[tIndex];
         }
      }
	  logger.debug("Unable to find edge table.");
      return null;
   }

   protected EdgeField getField(int numFigure) {
	  logger.info("Attempting to get edge field.");
	  logger.debug("Getting field using figure number of " + numFigure);
      logger.info("length: "+ fields.length);
      for (int fIndex = 0; fIndex < fields.length; fIndex++) {
         logger.info("HELLO THERE: "+ fIndex);
         try {
            logger.info(numFigure + " | " + fields[fIndex].getNumFigure());
            if (numFigure == fields[fIndex].getNumFigure()) {
               logger.debug("Found table at index " + fIndex + " with value of " + fields[fIndex].getNumFigure());
               return fields[fIndex];
            }
         } catch (IndexOutOfBoundsException e) {
            logger.info("Error: ");
         }
      }
	  logger.debug("Unable to find edge field.");
      return null;
   }

   public abstract String getDatabaseName();

   public abstract String getProductName();

   public abstract String getSQLString();

   public abstract void createDDL();

}//EdgeConvertCreateDDL
