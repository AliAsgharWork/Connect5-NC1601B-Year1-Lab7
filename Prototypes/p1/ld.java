package p1;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ld {

	
	static C5 game = new C5();//access methods 
	
	
	static int board_lenghtCon;
	static int board_widthCon;
	static JButton buttonpressedCon;
	
	


	// CONSTRUCTOR
	public ld(int board_Lenght,int board_Width,JButton buttonPressed) {
		
		board_lenghtCon=board_Lenght;
		board_widthCon=board_Lenght;
		buttonpressedCon=buttonPressed;
	}
	
	 //Other Method Variables
		static ArrayList<String> storage;
		static int count=0;
		static int Turncounter;	
		
		
		//Button Icon Source 
		static ImageIcon redButton = new ImageIcon(C5.class.getResource("/resources/red coin v2.png"));
		static ImageIcon yellowButton = new ImageIcon(C5.class.getResource("/resources/yellow coin v2.png"));
		
		
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}

	
	public static String PlayerMoveSetter() {

		for (int i = 0; i < board_lenghtCon; i++)
		{
			for (int j = 0; j < board_widthCon; j++)
			{

				if(C5.buttons[i][j].getClientProperty("status")=="0")
				{
					if(TurnDecider()=="player")
					{
						if (C5.buttons[i][j].equals(buttonpressedCon))//which button
						{
							//buttons[i-1][j].setText("1");
							C5.buttons[i][j].putClientProperty("status","1");//what status to set
							C5.buttons[i][j].setIcon(redButton);
							turncounter();System.out.println("count "+count);
							return "1";

						}
					}
					else
					{
						if (C5.buttons[i][j].equals(buttonpressedCon))//which button
						{
							//buttons[i-1][j].setText("2");
							C5.buttons[i][j].putClientProperty("status","2");//what status to set
							C5.buttons[i][j].setIcon(yellowButton);
							turncounter();System.out.println("count "+count);
							return "2";
						}
					}
				}

			}
		}
		return null;

	}

	public static String TurnDecider() {
		String decision = (Turncounter%2==0)?"player":"computer";
		return decision;
	}



	public static void winningCondition(String playertoken)
	{

		for (int i = 0; i < board_lenghtCon; i++){
			for (int j = 0; j < board_widthCon; j++) {

				if(coinStatus(i,j) ==playertoken && C5.buttons[i][j].getClientProperty("status")!="0")
				{
					if(i+4<board_lenghtCon)
					{
						if (coinStatus(i+1,j)==playertoken&&
							coinStatus(i+2,j)==playertoken&&
							coinStatus(i+3,j)==playertoken&&
							coinStatus(i+4,j)==playertoken  )
						{
							System.out.println(C5.buttons[i][j].getClientProperty("id"));
							System.out.println("UP AND DOWN");
							if(playertoken=="1")
								game.displayResult("1");
							else
								game.displayResult("2");
						}
					}



					// CHECK LEFT TO RIGHT POSITION
					if(j + 4 <board_widthCon)
					{ 
						if(coinStatus(i,j+1) ==playertoken&& 
						   coinStatus(i,j+2) ==playertoken&&
						   coinStatus(i,j+3) ==playertoken&&
						   coinStatus(i,j+4) ==playertoken  )
						{ System.out.println("LEFT AND RIGHT");
						if(playertoken=="1")
							game.displayResult("1");
						else
							game.displayResult("2");
						}
					}

					// Checking the winning position from LEFT to RIGHT diagonally
					if(i < board_lenghtCon - 4 && j < board_widthCon - 4)
					{
						if(coinStatus(i+1,j+1) ==playertoken&& 
						   coinStatus(i+2,j+2) ==playertoken&& 
						   coinStatus(i+3,j+3) ==playertoken&&
						   coinStatus(i+4,j+4) ==playertoken  )
						{  System.out.println("DIAGNOAL LEFT AND RIGHT");
						if(playertoken=="1")
							game.displayResult("1");
						else
							game.displayResult("2");
						}   
					}

					// Checking the winning position from RIGHT to LEFT diagonally
					if(i  < board_lenghtCon- 4 && j - 4 >= 0 )
					{
						if(coinStatus(i+1,j-1)==playertoken&& 
						   coinStatus(i+2,j-2)==playertoken&&
						   coinStatus(i+3,j-3)==playertoken&&
						   coinStatus(i+4,j-4)==playertoken  )
						{  System.out.println("DIAGONAL RIGHT TO LEFT");
						if(playertoken=="1")
							game.displayResult("1");
						else
							game.displayResult("2");
						}   
					}



				}
			}

		}

	}

	public static String coinStatus(int i,int j) {
		return (String) C5.buttons[i][j].getClientProperty("status");
	}
	public static void store_move(String input) {
		storage = new ArrayList<String>();
		storage.add(input);

	} 
	public static void turncounter() {
		count++;
		Turncounter=count;
	} 
	
}

