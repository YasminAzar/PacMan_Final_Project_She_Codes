package Log_Package;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PacmanLog {
	
	/*private static void createLogFile() {
		// EB move here
		String logFileName = "src\\log_files\\log_messages.txt";
		try {
		     File myObj = new File(logFileName);
		     if (myObj.createNewFile()) {
		       System.out.println("File created: " + myObj.getName());
		     } else {
		       System.out.println("File already exists.");
		     }
		   } catch (IOException e) {
		     System.out.println("An error occurred "+e.getMessage());
		     e.printStackTrace();
		   }
		}*/

		public static void log(String functionName, String msg) {
			// EB move here too and also move to a log package
			String logFileName = "src\\log_files\\log_messages.txt";
		try {
		     FileWriter myWriter = new FileWriter(logFileName, true);
		     DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		     LocalDateTime now = LocalDateTime.now();
		     System.out.println(dtf.format(now));
		     myWriter.write(dtf.format(now) + ":" + functionName + ' ' + msg);
		     myWriter.write("\n");
		     myWriter.close();
		     System.out.println("Successfully wrote to the file.");
		   } catch (IOException e) {
		     System.out.println("An error occurred.");
		     e.printStackTrace();
		   }
		}
}
