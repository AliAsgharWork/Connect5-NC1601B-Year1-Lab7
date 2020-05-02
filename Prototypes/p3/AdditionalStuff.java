package p3;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;

import GIFcreation.ScreenImage;

public class AdditionalStuff {
	
	static JFrame myframe;
	JPanel  scoreboard;
	
	//SCREENSHOT VARIABLES
	static int StoShotNum=0;
	static boolean firsttimescreenshot=true;
	
	//GIF CREATION VARIABLES
	
	
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//	
//	public AdditionalStuff() {
//		// TODO Auto-generated constructor stub
//	}
	
	
	
	public static void createFolder(String foldername) {//If the folder is not present it will make the folder to store the actions output
		String storagePath = "C:\\Users\\User\\Desktop\\"+foldername;
	      //Creating a File object
	      File file = new File(storagePath);
	      //Creating the directory
	      boolean fileexaminier = file.mkdir();
	      if(fileexaminier){
	         System.out.println("Requested Directory created successfully");
	      }else{
	         System.out.println("Sorry couldn’t create specified directory.Maybe it's there check on the desktop");
	      }
	} 
	
	
	
	
	public static void takeShot(BufferedImage ss) {
		if(firsttimescreenshot)createFolder("Connect5Screenshots");screenshotIncrementStatus();
			try {
				ScreenImage.writeImage(ss, "C:\\Users\\User\\Desktop\\Connect5Screenshots\\Connect5Screenshot "+StoShotNum+".jpg");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			StoShotNum++;
			firsttimescreenshot=false;
		
	}
	
	
	public static void screenshotIncrementStatus() {
		
		String dir_name = "C:\\Users\\User\\Desktop\\Connect5Screenshots"; 
		//Or another directory. Be careful with the “” that MSWord uses.
		File dir = new File(dir_name);
		File[] dir_list = dir.listFiles();
		ArrayList<Integer> a =new ArrayList<Integer>();
		a.add(0);
		Integer number = null;
		for(int i=0;i<dir_list.length;++i)
		{
			if(dir_list[i].getName().startsWith("Connect5Screenshot"))
				{
				String [] tempo=dir_list[i].getName().split(" ");
				System.out.println("tempo[1]"+tempo[1]);
				String [] tempo2=tempo[1].split("\\.");
				number = Integer.valueOf(tempo2[0]);
				}
			a.add(number);
			
		}
	
		StoShotNum=Collections.max(a)+1;
	}
	
	
	
	
	

}
