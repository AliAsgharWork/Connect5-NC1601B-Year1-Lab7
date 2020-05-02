package p3;

public class LogicalDecisionclass {

	//CONSTRUCTOR VARIABLES
	static int board_lenghtCon;
	static int board_widthCon;
	
	//CONSTRUCTOR
	public LogicalDecisionclass(int bL,int bW)
	{
		board_lenghtCon=bL;
		board_widthCon=bW;
		
	}
	
	static int turnCounterVar;//Counting the number of move that have been executed.
	
	public void winningCondition()
	{
		String playertoken=PlayerMoveSetter();
		for (int i = 0; i < board_lenghtCon; i++){
			for (int j = 0; j < board_widthCon; j++) {

				if(getCoinStatus(i,j) == playertoken && getCoinStatus(i,j)!="0")
				{
					// Checking from Up to Down for Vertical check
					if(i + 4 < board_lenghtCon)
					{
						if (getCoinStatus(i+1,j)==playertoken&&
							getCoinStatus(i+2,j)==playertoken&&
							getCoinStatus(i+3,j)==playertoken&&
							getCoinStatus(i+4,j)==playertoken  )
						{
							System.out.println("UP AND DOWN");
							if(playertoken=="1")
								Connect5.displayResult("1");
							else
								Connect5.displayResult("2");
						}
					}

					// Checking from Left to right for Horizontal check 
					if(j + 4 < board_widthCon)
					{ 
						if(getCoinStatus(i,j+1) ==playertoken&& 
						   getCoinStatus(i,j+2) ==playertoken&&
						   getCoinStatus(i,j+3) ==playertoken&&
						   getCoinStatus(i,j+4) ==playertoken  )
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
						if(getCoinStatus(i+1,j+1) ==playertoken&& 
						   getCoinStatus(i+2,j+2) ==playertoken&& 
						   getCoinStatus(i+3,j+3) ==playertoken&&
						   getCoinStatus(i+4,j+4) ==playertoken  )
						{  System.out.println("DIAGNOAL LEFT AND RIGHT");
						if(playertoken=="1")
							Connect5.displayResult("1");
						else
							Connect5.displayResult("2");
						}   
					}

					// Checking the winning position from RIGHT to LEFT diagonally
					if(i < board_lenghtCon- 4 && j - 4 >= 0 )
					{
						if(getCoinStatus(i+1,j-1)==playertoken&& 
						   getCoinStatus(i+2,j-2)==playertoken&&
						   getCoinStatus(i+3,j-3)==playertoken&&
						   getCoinStatus(i+4,j-4)==playertoken  )
						{  System.out.println("DIAGONAL RIGHT TO LEFT");
						if(playertoken=="1")
							Connect5.displayResult("1");
						else
							Connect5.displayResult("2");
						}   
					}
					//Draw Condition
					if(Connect5.whenAIGoesFirst==false)
					{
						if(turnCounterVar == board_lenghtCon * board_widthCon)
						{
							Connect5.displayResult("Draw");
						}
					}
					else
					{
						if(turnCounterVar == (board_lenghtCon * board_widthCon)-1)
						{
							Connect5.displayResult("Draw");
						}
					}
				}
			}
		}
	}
	
	public static void turncounter() {
		turnCounterVar++;
	} 
	
	public static String TurnDecider() {
		String decision = (turnCounterVar%2==0)?"player":"computer";
		return decision;
	}
	
	public static String getCoinStatus(int i,int j) {
		return (String) Connect5.buttons[i][j].getClientProperty("status");
	}
	public static String coinID(int i,int j) {
		return (String) Connect5.buttons[i][j].getClientProperty("id");
	}
	
	
	public String PlayerMoveSetter() {

		for (int i = 0; i < board_lenghtCon; i++)
		{
			for (int j = 0; j < board_widthCon; j++)
			{
				if(getCoinStatus(i,j)=="0")
				{
					if(TurnDecider()=="player")
					{
							if (Connect5.buttons[i][j].equals(Connect5.buttonpressed))//which button
							{
								Connect5.buttons[i][j].putClientProperty("status","1");//what status to set
								Connect5.buttons[i][j].setIcon(Connect5.redButton);System.out.println("Added red coin");
								turncounter();System.out.println("red Turncount "+turnCounterVar);
								Storage.store_move("P:"+coinID(i, j)+getCoinStatus(i, j));
								return "1";
							}
					}
					else
					{       if (Connect5.buttons[i][j].equals(Connect5.buttonpressed))//which button
							{
								Connect5.buttons[i][j].putClientProperty("status","2");//what status to set
								Connect5.buttons[i][j].setIcon(Connect5.yellowButton);System.out.println("Added Yellow Coin");
								turncounter();System.out.println("yello Turncount "+turnCounterVar);
								Storage.store_move("P:"+coinID(i, j)+getCoinStatus(i, j));
								return "2";
							}
					}
				}

			}
		}
		return null;

	}
}
