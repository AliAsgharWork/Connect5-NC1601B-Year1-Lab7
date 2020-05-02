package p3;

import java.awt.List;
import java.io.ObjectOutputStream.PutField;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Computer_AI {

	static Random random = new Random();
	final static String humanplaytoken ="1";

	public static void getAItoaddcoin(int i,int j) {
		Connect5.buttons[i][j].doClick();
	}
	public static void addDelayAi() {
		try {
			TimeUnit.MILLISECONDS.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}






	public static String firstMoveByAI() {//When the game starts it will choose a random spot to drop the coin
		//BUG:	IT TRYS TO PUT A COIN IN A PLACE WHERE A COIN IS ALREADY PLACED
		//addDelayAi();
		int randomJ = random.nextInt(Connect5.board_width_row);
		if(LogicalDecisionclass.getCoinStatus(Connect5.board_lenght_col-1, randomJ)=="0")
		{
			getAItoaddcoin(Connect5.board_lenght_col-1, randomJ);
		}
		else 
		{
			firstMoveByAI();
			return null;
		}
		return null;


	}


	public static void moveComputer()
	{
		int l,m;
		boolean flag=false;

		if(AI_Block()!=true){
			System.out.println("Computer is playing code");
			for(l=Connect5.board_lenght_col-1; (l>=0)&& !flag; --l)
			{ 
				for(m=0; (m<Connect5.board_width_row) && !flag; ++m)
				{
					if(LogicalDecisionclass.getCoinStatus(l, m) == "0")
					{
						getAItoaddcoin(l, m);//SHOULD TRY TO MAKE A CONNECT 5 UPWARDS 
						//IF TRY FAILS START AGIN ON THE SIDE WHERE SPACE IS AVALIABLE
						flag = true; 
					}	

				}
			} 
		}
	}



































	public static boolean AI_Block() {

		//if()//Connect 4 from right to left horizontall add coin to right if space available
		//Connect 4 from left to right horizontall add coin to left if space available
		//Connect 4 from down to up  add coin on top of the section
		//Connect 4 from right to left diang upwards add coin to right if space available and base made(one down from the position where the connect 5 will happen is not empty )
		//Connect 4 from left to right diagn downwards add coin to left if space available and base made(one down from the position where the connect 5 will happen is not empty )
		for (int i = 0; i < Connect5.board_lenght_col; i++){
			for (int j = 0; j < Connect5.board_width_row; j++) {

				// Checking from Up to Down for Vertical check and add a coin on the top if position open
				//					if(LogicalDecisionclass.getCoinStatus(i,j) == "0")
				//	                {    
				// CHECK UP TO DOWN POSITIONS
				if(i+4<Connect5.board_lenght_col)
				{
					if(LogicalDecisionclass.getCoinStatus(i+1,j) == humanplaytoken &&
							LogicalDecisionclass.getCoinStatus(i+2,j) == humanplaytoken &&
							LogicalDecisionclass.getCoinStatus(i+3,j) == humanplaytoken&&
							LogicalDecisionclass.getCoinStatus(i+4,j) == humanplaytoken)
					{
						if(LogicalDecisionclass.getCoinStatus(i,j)=="0")
						{getAItoaddcoin(i, j);System.out.println("blocked on top4");return true;}

					}
				}


				// Checking from Left to right for Horizontal check and add coin in left (if not avaliable right) and if both not avaliable normal
				if(j + 4 < Connect5.board_width_row)
				{ 
					if(LogicalDecisionclass.getCoinStatus(i,j+1) ==humanplaytoken&& 
					   LogicalDecisionclass.getCoinStatus(i,j+2) ==humanplaytoken&&
					   LogicalDecisionclass.getCoinStatus(i,j+3) ==humanplaytoken)
					{ 

						if(LogicalDecisionclass.getCoinStatus(i,j)=="0")
						{getAItoaddcoin(i, j);System.out.println("blocked on left 3");return true;}

					}
				}
				if(j + 4 < Connect5.board_width_row)//left making 3 right insert coin at left
				{ 
					if(LogicalDecisionclass.getCoinStatus(i,j+1) ==humanplaytoken&& 
					   LogicalDecisionclass.getCoinStatus(i,j+2) ==humanplaytoken&&
					   LogicalDecisionclass.getCoinStatus(i,j+3) ==humanplaytoken&&
					   LogicalDecisionclass.getCoinStatus(i,j+4) == humanplaytoken) 
					{ 
//						if(LogicalDecisionclass.getCoinStatus(i+1, j+5)=="0"&& i+1>Connect5.board_width_row&& j+5<Connect5.board_width_row)
//						{
//							{getAItoaddcoin(i+1, j+5);;System.out.println("blocked on right 4");return true;}
//						}
//						else
						{
							if(LogicalDecisionclass.getCoinStatus(i,j+5)=="0"&& j+5<Connect5.board_width_row)
							{getAItoaddcoin(i, j+5);;System.out.println("blocked on right 4");return true;}
						}
					}
				}

				//					// Checking the winning position from LEFT to RIGHT diagonally
				//					if(i < Connect5.board_lenght_col - 4 && j < Connect5.board_width_row - 4)
				//					{
				//						if(LogicalDecisionclass.getCoinStatus(i+1,j+1) ==humanplaytoken&& 
				//						   LogicalDecisionclass.getCoinStatus(i+2,j+2) ==humanplaytoken&& 
				//						   LogicalDecisionclass.getCoinStatus(i+3,j+3) ==humanplaytoken&&
				//						   LogicalDecisionclass.getCoinStatus(i+4,j+4) ==humanplaytoken  )
				//						{  System.out.println("DIAGNOAL LEFT AND RIGHT");
				//						if(humanplaytoken=="1")
				//							Connect5.displayResult("1");
				//						else
				//							Connect5.displayResult("2");
				//						}   
				//					}
				//
				//					// Checking the winning position from RIGHT to LEFT diagonally
				//					if(i < Connect5.board_lenght_col- 4 && j - 4 >= 0 )
				//					{
				//						if(LogicalDecisionclass.getCoinStatus(i+1,j-1)==humanplaytoken&& 
				//						   LogicalDecisionclass.getCoinStatus(i+2,j-2)==humanplaytoken&&
				//						   LogicalDecisionclass.getCoinStatus(i+3,j-3)==humanplaytoken&&
				//						   LogicalDecisionclass.getCoinStatus(i+4,j-4)==humanplaytoken  )
				//						{  System.out.println("DIAGONAL RIGHT TO LEFT");
				//						if(humanplaytoken=="1")
				//							Connect5.displayResult("1");
				//						else
				//							Connect5.displayResult("2");
				//						}   
				//					}


				//					//Draw Condition
				//					if(Connect5.whenAIGoesFirst==false)
				//					{
				//						if(turnCounterVar == Connect5.board_lenght_col * Connect5.board_width_row)
				//						{
				//							Connect5.displayResult("Draw");
				//						}
				//					}
				//					else
				//					{
				//						if(turnCounterVar == (Connect5.board_lenght_col * Connect5.board_width_row)-1)
				//						{
				//							Connect5.displayResult("Draw");
				//						}
				//					}

				//			}
			}
		}
		return false;
	}

























	//	public static void score_position() {
	//		int score =0;
	//		ArrayList<String> row_array= new ArrayList<String>();
	//		ArrayList<String> row_array_by4=  new ArrayList<String>();
	//		for (int i = 0; i < Connect5.board_lenght_col; i++)
	//		{
	//			for (int j = 0; j < Connect5.board_width_row; j++)
	//			{
	//				if(j + 3 < Connect5.board_lenght_col){
	//					
	////					if(LogicalDecisionclass.getCoinStatus(i,j+1)=="2"&& 
	////					   LogicalDecisionclass.getCoinStatus(i,j+2)=="2"&&
	////					   LogicalDecisionclass.getCoinStatus(i,j+3)=="2"&&
	////				       LogicalDecisionclass.getCoinStatus(i,j+4)=="2"  )
	//					{
	//						
	//							Connect5.buttons[i][j].setEnabled(true);;
	//							row_array.add(LogicalDecisionclass.coinID(i,j));
	//							row_array.add(LogicalDecisionclass.coinID(i,j+1));
	//							row_array.add(LogicalDecisionclass.coinID(i,j+2));
	//							row_array.add(LogicalDecisionclass.coinID(i,j+3));
	//							row_array.add(LogicalDecisionclass.coinID(i,j+4));
	//								
	//					}
	//							
	//						
	//				}
	//			}
	//		}
	//		
	//	
	//			
	//		
	//		
	//		
	//		
	//		
	////		
	//	}
	//			

























}
