package p3;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logging {
	
	static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");

	public static void print(String actionResult) {
		//result_outputCon=actionResult;
		System.out.println(actionResult);
		
	}
	
	
	public void general_Log() throws IOException{
		
		//Selects the file where it is going to store the logs
		FileWriter handle = new FileWriter("C:\\Users\\User\\Desktop\\FinchJavaEclipse\\logger.txt",true);
		BufferedWriter bw = new BufferedWriter(handle);
		
		bw.write("Command entered:");
		bw.newLine();
		bw.write("RESULT:- \n");
		bw.newLine();

		bw.close();
		handle.close();

	}
	
	
	
	
	public static void initialtimeStamplog() throws IOException{/*start a session when the code runs and end that session with time and date*/
		LocalDateTime startdateTime= LocalDateTime.now();
		//The Date and Time is recorded at the start of a session.
		FileWriter handle = new FileWriter("C:\\Users\\User\\Desktop\\Connect5log.txt",true);
		BufferedWriter bw = new BufferedWriter(handle);


		bw.newLine();
		bw.write("---------------------------SESSION START: "+dtf.format(startdateTime)+"----------------------------");
		bw.newLine();

		bw.close();
		handle.close();

	}

	public static void finishtimeStamplog() throws IOException{/* when the section stopped stopped or terminated with time and date*/
		LocalDateTime enddateTime=LocalDateTime.now();

		FileWriter handle = new FileWriter("C:\\Users\\User\\Desktop\\Connect5log.txt",true);
		BufferedWriter bw = new BufferedWriter(handle);


		bw.newLine();
		bw.write("----------------------------SESSION END: "+dtf.format(enddateTime)+"----------------------------");
		bw.newLine();
		//bw.write("GAME:"+StoGamNum);	DECIDE WHICH GAME SAVE TO LOAD

		bw.close();
		handle.close();

	}


}
