import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RunEdgeConvert {
	public static Logger logger = LogManager.getLogger(RunEdgeConvert.class.getName());
	
   public static void main(String[] args) {
	logger.info("MY FIRST LOG :D");
	logger.info("Starting EdgeConvertGUI");
	EdgeConvertGUI edge = new EdgeConvertGUI();
   }
}