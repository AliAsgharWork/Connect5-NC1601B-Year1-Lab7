package p3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Storage {
	static //Variables
	ArrayList<String> storage = new ArrayList<String>();
	static int StoGamNum=0;
	static boolean x =true;
	static boolean firsttimesave=true;


	public static void store_move(String input) {storage.add(input);}//get the id to identify its position the status to get the type 
	//of coin should be inserted 

	public static void saveIncrementStatus() {
		
		String dir_name = "C:\\Users\\User\\Desktop\\Connect5Save"; 
		//Or another directory. Be careful with the “” that MSWord uses.
		File dir = new File(dir_name);
		File[] dir_list = dir.listFiles();
		ArrayList<Integer> a =new ArrayList<Integer>();
		a.add(0);
		Integer number = null;
		for(int i=0;i<dir_list.length;++i)
		{
			if(dir_list[i].getName().startsWith("Connect5save"))
				{
				String [] tempo=dir_list[i].getName().split(" ");
				System.out.println("tempo[1]"+tempo[1]);
				String [] tempo2=tempo[1].split("\\.");
				number = Integer.valueOf(tempo2[0]);
				}
			a.add(number);
			
		}
		
		StoGamNum=Collections.max(a)+1;
		
	}
	public static void save() throws IOException 
	{
		if(firsttimesave){AdditionalStuff.createFolder("Connect5Save");saveIncrementStatus();}
		FileWriter writehandle = new FileWriter("C:\\Users\\User\\Desktop\\Connect5Save\\Connect5save "+StoGamNum+".txt",true);
		BufferedWriter bw = new BufferedWriter(writehandle);
		
		int i = 0;
		do 
		{
			bw.write(storage.get(i));
			bw.newLine();
			i++;
		}
		while( i < storage.size());
		StoGamNum++;
		firsttimesave=false;
		bw.close();
		writehandle.close();


	}

	public static void load(int returnVal) throws IOException
	{
		Connect5.startAgain_AKA_reset();//reset board

		String filedestination="";
		File file = Connect5.fc.getSelectedFile();
		


		if (returnVal == JFileChooser.APPROVE_OPTION) //Learned about JFileChooser from https://docs.oracle.com/javase/tutorial/uiswing/components/filechooser.html
		{
			filedestination=file.getCanonicalPath();//This is where a real application would open the file.
			System.out.println(filedestination);
			if(file.getName().endsWith(".txt"))
			{
				FileReader readhandle =new FileReader(filedestination);
				BufferedReader br = new BufferedReader(readhandle);

				String line =null;
				ArrayList <String> output_recall =new ArrayList <String>();
				while ((line = br.readLine())!=null){ output_recall.add(line); }//Makes a ArrayList of the saved results

				for (int j = 0; j < output_recall.size(); j++) 
				{
					if (output_recall.get(j).startsWith("P"))
					{
						System.out.println("Number of execute:"+j);
						String[] tempoarray =output_recall.get(j).split(":");
						reload_execute(tempoarray[1]);
						Connect5.myframe.setVisible(false);
					}

				}	

				Connect5.myframe.setVisible(true);

				br.close();
				readhandle.close();
			}
			else
			{
				JFrame errorFrame = new JFrame();       
				JOptionPane.showMessageDialog(errorFrame,"\nThe file selected is not recognised to be a"
						+ " Game File\n\n","Not Recognised as Game File",JOptionPane.ERROR_MESSAGE);
			}
		}
		else
		{
			System.out.println("Loading Canceled");
		}

	}

	public static void reload_execute(String Coindetail) {

		

		String[] tempoarray2=Coindetail.split("");//position(i,j) and the status

		System.out.println("coin coodinates:"+Integer.valueOf(tempoarray2[0]+Integer.valueOf(tempoarray2[1])));
		Computer_AI.getAItoaddcoin(Integer.valueOf(tempoarray2[0]), Integer.valueOf(tempoarray2[1]));//add the coins according to the the array list

	}

}
