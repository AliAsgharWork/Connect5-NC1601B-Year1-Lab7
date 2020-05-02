package Singleplayer;

public class LogicalDecisionclass {

	
	
	static int board_lenghtCon;
	static int board_widthCon;
	
	public static void main(String[] args) {
		
	}
	
	
	public LogicalDecisionclass(int bL,int bW)
	{
		board_lenghtCon=bL;
		board_widthCon=bW;
		
	}
	
	static int turnCounterVar;
	
	
	
	public void winningCondition()
	{
		String playertoken=PlayerMoveSetter();
		for (int i = 0; i < board_lenghtCon; i++){
			for (int j = 0; j < board_widthCon; j++) {

				if(coinStatus(i,j) ==playertoken && coinStatus(i,j)!="0")
				{
					if(i + 4 < board_lenghtCon)
					{
						if (coinStatus(i+1,j)==playertoken&&
							coinStatus(i+2,j)==playertoken&&
							coinStatus(i+3,j)==playertoken&&
							coinStatus(i+4,j)==playertoken  )
						{
							//System.out.println(buttons[i][j].getClientProperty("id"));
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
					if(i < board_lenghtCon- 4 && j - 4 >= 0 )
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
	
	public static void turncounter() {
		turnCounterVar++;
	} 
	
	public static String TurnDecider() {
		String decision = (turnCounterVar%2==0)?"player":"computer";
		return decision;
	}
	
	public static String coinStatus(int i,int j) {
		return (String) Connect5.buttons[i][j].getClientProperty("status");
	}
	
	
	public String PlayerMoveSetter() {

		for (int i = 0; i < board_lenghtCon; i++)
		{
			for (int j = 0; j < board_widthCon; j++)
			{
				if(coinStatus(i,j)=="0")
				{
					if(TurnDecider()=="player")
					{
							if (Connect5.buttons[i][j].equals(Connect5.buttonpressed))//which button
							{
								Connect5.buttons[i][j].putClientProperty("status","1");//what status to set
								Connect5.buttons[i][j].setIcon(Connect5.redButton);
								turncounter();System.out.println("count "+turnCounterVar);
								return "1";
							}
					}
					else
					{       if (Connect5.buttons[i][j].equals(Connect5.buttonpressed))//which button
							{
							Connect5.buttons[i][j].putClientProperty("status","2");//what status to set
							Connect5.buttons[i][j].setIcon(Connect5.yellowButton);
							turncounter();System.out.println("count "+turnCounterVar);
							return "2";
							}
					}
				}

			}
		}
		return null;

	}
}
