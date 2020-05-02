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
	static String dir_Desktop = System.getProperty("user.home") + "\\Desktop";//Made this so if this project gets opened on another 
																			  //computer the paths are going to be according to that computer
	//SCREENSHOT VARIABLES
	static int StoShotNum=0;
	static boolean firsttimescreenshot=true;
	
	//GIF CREATION VARIABLES
	static int GifCaptureNum=0;
	static int GifFolderNum=0;
	static boolean firsttimeGif=true;
	
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//	
//	public AdditionalStuff() {
//		// TODO Auto-generated constructor stub
//	}
	
	
	
	public static void createFolder(String foldername) {//If the folder is not present it will make the folder to store the actions output
		String storagePath = dir_Desktop+"\\"+foldername;
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
	
	/**SCREENSHOT CREATION METHODS*/
	
	
	public static void takeShot(BufferedImage ss) {
		if(firsttimescreenshot)createFolder("Connect5Screenshots");screenshotIncrementStatus();
			try {
				ScreenImage.writeImage(ss, dir_Desktop+"\\Connect5Screenshots\\Connect5Screenshot "+StoShotNum+".jpg");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			StoShotNum++;
			firsttimescreenshot=false;
		
	}
	
	
	public static void screenshotIncrementStatus() {
		
		String dir_name = dir_Desktop+"\\Connect5Screenshots";
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
	
		StoShotNum=Collections.max(a)+1;//"1" is added because we want the max number's next increment
	}
												/**GIF CREATION METHODS*/
						
	public static void gifcaptures() {
		BufferedImage gifbuffer = ScreenImage.createImage(Connect5.board);
		AdditionalStuff.takeGifimg(gifbuffer);
	}
	
	
	
	public static void takeGifimg(BufferedImage Gifimg) {
		
		if(firsttimeGif)createFolder("GifStorage");GIFCaptureIncrementStatus();
			try {
				ScreenImage.writeImage(Gifimg, dir_Desktop+"\\GifStorage\\ali "+GifCaptureNum+".jpg");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GifCaptureNum++;
			
			firsttimeGif=false;
		
	}
	//if(newGif)createFolder("Gifno"+GifFolderNum);GifFolderIncrementStatus();
	
	public static int GIFCaptureIncrementStatus() {
		
		String dir_name = dir_Desktop+"\\GifStorage";
		File dir = new File(dir_name);
		File[] dir_list = dir.listFiles();
		ArrayList<Integer> a =new ArrayList<Integer>();
		a.add(0);
		Integer number = null;
		for(int i=0;i<dir_list.length;++i)
		{
			if(dir_list[i].getName().startsWith("ali"))
				{
				String [] tempo=dir_list[i].getName().split(" ");
				System.out.println("tempo[1]"+tempo[1]);
				String [] tempo2=tempo[1].split("\\.");
				number = Integer.valueOf(tempo2[0]);
				}
			a.add(number);
			
		}
	
		GifCaptureNum=Collections.max(a)+1;//"1" is added becasue we want the max number's next increment
		return GifCaptureNum;//To return the number of gif capture that need to be converted into a gif
	}
	
	
	
//public static int GifFolderIncrementStatus() {//CHANGE THE CODE FOR THIS TO BE ABLE TO DETECT FOLDER INSTEAD OF FILES
//		
//		String dir_name = dir_Desktop+"\\Ali";
//		File dir = new File(dir_name);
//		File[] dir_list = dir.listFiles();
//		ArrayList<Integer> a =new ArrayList<Integer>();
//		a.add(0);
//		Integer number = null;
//		for(int i=0;i<dir_list.length;++i)
//		{
//			if(dir_list[i].getName().startsWith("ali"))
//				{
//				String [] tempo=dir_list[i].getName().split(" ");
//				System.out.println("tempo[1]"+tempo[1]);
//				String [] tempo2=tempo[1].split("\\.");
//				number = Integer.valueOf(tempo2[0]);
//				}
//			a.add(number);
//			
//		}
//	
//		GifFolderNum=Collections.max(a)+1;
//		return GifFolderNum;
//	}
//
//	//MAKE BASE IMAGE FOR GIF WHEN THE PROGRAM STARTS
	
	

}
