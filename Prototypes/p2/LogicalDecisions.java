package p2;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class LogicalDecisions {
	
	
	static int board_lenghtCon;
	static int board_widthCon;
	static JButton buttonpressedCon;
	
	


	// CONSTRUCTOR
	public LogicalDecisions(int board_Lenght,int board_Width,JButton buttonPressed) {
		
		board_lenghtCon=board_Lenght;
		board_widthCon=board_Lenght;
		buttonpressedCon=buttonPressed;
	}
	
	//Other Method Variables
		ArrayList<String> storage;
		static int count=0;
		static int Turncounter;	
		
		
		//Button Icon Source 
		ImageIcon emptyButton = new ImageIcon(Connect5.class.getResource("/resources/emptycoin.png"));
		static ImageIcon redButton = new ImageIcon(Connect5.class.getResource("/resources/red coin v2.png"));
		static ImageIcon yellowButton = new ImageIcon(Connect5.class.getResource("/resources/yellow coin v2.png"));
		
		

	
	public String PlayerMoveSetter() {
		System.out.println("ButtonPressed: "+buttonpressedCon);
		for (int i = 0; i < board_lenghtCon; i++)
		{
			for (int j = 0; j < board_widthCon; j++)
			{

				if(Connect5.buttons[i][j].getClientProperty("status")=="0")
				{
					if(TurnDecider()=="player")
					{	//System.out.println("i and j "+i +""+j);
						if (Connect5.buttons[i][j].equals(Connect5.buttonpressed)) System.out.println("true");
						if (Connect5.buttons[i][j].equals(buttonpressedCon))//which button
						{
							//buttons[i-1][j].setText("1");
							Connect5.buttons[i][j].putClientProperty("status","1");//what status to set
							Connect5.buttons[i][j].setIcon(redButton);
							turncounter();System.out.println("count "+count);
							return "1";

						}
					}
					else
					{
						if (Connect5.buttons[i][j].equals(buttonpressedCon))//which button
						{
							//buttons[i-1][j].setText("2");
							Connect5.buttons[i][j].putClientProperty("status","2");//what status to set
							Connect5.buttons[i][j].setIcon(yellowButton);
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



	public void winningCondition(String playertoken)
	{

		for (int i = 0; i < board_lenghtCon; i++){
			for (int j = 0; j < board_widthCon; j++) {

				if(coinStatus(i,j) ==playertoken && Connect5.buttons[i][j].getClientProperty("status")!="0")
				{
					if(i+4<board_lenghtCon)
					{
						if (coinStatus(i+1,j)==playertoken&&
							coinStatus(i+2,j)==playertoken&&
							coinStatus(i+3,j)==playertoken&&
							coinStatus(i+4,j)==playertoken  )
						{
							System.out.println(Connect5.buttons[i][j].getClientProperty("id"));
							System.out.println("UP AND DOWN");
							if(playertoken=="1")
								Connect5.displayResult("1");
							else
								Connect5.displayResult("2");
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
							Connect5.displayResult("1");
						else
							Connect5.displayResult("2");
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
							Connect5.displayResult("1");
						else
							Connect5.displayResult("2");
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
							Connect5.displayResult("1");
						else
							Connect5.displayResult("2");
						}   
					}



				}
			}

		}

	}

	public static String coinStatus(int i,int j) {
		return (String) Connect5.buttons[i][j].getClientProperty("status");
	}
	public void store_move(String input) {
		storage = new ArrayList<String>();
		storage.add(input);

	} 
	public static void turncounter() {
		count++;
		Turncounter=count;
	} 
	
	
	
	
	
}
