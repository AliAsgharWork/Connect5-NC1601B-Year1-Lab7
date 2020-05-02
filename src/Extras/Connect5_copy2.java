package Extras;




import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class Connect5_copy2 extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static JFrame myframe;
	JPanel  board;
	static JButton [][]buttons;
	static int board_lenght=7;
	static int board_width=8;

	//ActionEvent Variables
	static JButton buttonpressed;
	static int Turncounter;

	//Other Method Variables
	ArrayList<String> storage;
	static int count=0;


	//Button Icon Source 
	static ImageIcon emptyButton = new ImageIcon(Connect5_copy2.class.getResource("/resources/emptycoin.png"));
	static ImageIcon redButton = new ImageIcon(Connect5_copy2.class.getResource("/resources/red coin v2.png"));
	static ImageIcon yellowButton = new ImageIcon(Connect5_copy2.class.getResource("/resources/yellow coin v2.png"));

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myframe = new Connect5_copy2();
		//myframe.setLayout();
		myframe.setTitle("Ali's Connect5");
		myframe.setSize(1000,650);
		myframe.setLocationRelativeTo(null);;
		myframe.setVisible(true);
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public Connect5_copy2() {


		board=new JPanel(new GridLayout(board_lenght,board_width));
		board.setBackground(Color.BLUE);
		add(board);
		buttons=new JButton[board_lenght][board_width];

		populateButtons();




	}






	public void actionPerformed(ActionEvent e)
	{
		buttonpressed =  (JButton) e.getSource();

		//String input = buttonpressed.getActionCommand();
		System.out.println(buttonpressed.getClientProperty("id"));
		System.out.println(buttonpressed.getClientProperty("status"));

		NextSlotOpenner();
		String currentToken =PlayerMoveSetter();
		winningCondition(currentToken);//does the player win ? display win and end : continue
		//computer turn
		//does computer win ? display win and end : continue
		//wait for next insert by player
	}

	public static String coinStatus(int i,int j) {
		return (String) buttons[i][j].getClientProperty("status");
	}
	public void store_move(String input) {
		storage = new ArrayList<String>();
		storage.add(input);

	} 
	public static void turncounter() {
		count++;
		Turncounter=count;
	} 

	public void populateButtons() {//starting layout of the game
		for (int i = 0; i < board_lenght; i++) {

			for (int j = 0; j < board_width; j++) {

				buttons[i][j]=new JButton();
				buttons[i][j].setIcon(emptyButton);
				board.add(buttons[i][j]);
				buttons[i][j].setOpaque(false);
				buttons[i][j].setContentAreaFilled(false);
				buttons[i][j].setBorderPainted(false);
				buttons[i][j].putClientProperty("id",String.valueOf(i).concat(String.valueOf(j)));
				buttons[i][j].putClientProperty("status","0");
				buttons[i][j].setText("place"+i+","+j);//REMOVE LATER
				buttons[i][j].addActionListener(this);
				if(i!=6){buttons[i][j].setEnabled(false);}//Starting row should be active to play
			}
		}
	}

	public void NextSlotOpenner() {
		for (int i = 1; i < board_lenght; i++) 
		{
			for (int j = 0; j < board_width; j++)
			{

				if (buttons[i][j].equals(buttonpressed))
				{
					buttons[i-1][j].setEnabled(true);
				}	
			}
		}
	}



	public static String PlayerMoveSetter() {

		for (int i = 0; i < board_lenght; i++)
		{
			for (int j = 0; j < board_width; j++)
			{
				if(buttons[i][j].getClientProperty("status")=="0")
				{
					if(TurnDecider()=="player")
					{
						if (buttons[i][j].equals(buttonpressed))//which button
						{
							//buttons[i-1][j].setText("1");
							buttons[i][j].putClientProperty("status","1");//what status to set
							buttons[i][j].setIcon(redButton);
							turncounter();System.out.println("count "+count);
							return "1";
						}
					}
					else
					{
						if (buttons[i][j].equals(buttonpressed))//which button
						{
							//buttons[i-1][j].setText("2");
							buttons[i][j].putClientProperty("status","2");//what status to set
							buttons[i][j].setIcon(yellowButton);
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

		for (int i = 0; i < board_lenght; i++){
			for (int j = 0; j < board_width; j++) {

				if(coinStatus(i,j) ==playertoken && buttons[i][j].getClientProperty("status")!="0")
				{
					if(i+4<board_lenght)
					{
						if (coinStatus(i+1,j)==playertoken&&
							coinStatus(i+2,j)==playertoken&&
							coinStatus(i+3,j)==playertoken&&
							coinStatus(i+4,j)==playertoken  )
						{
							System.out.println(buttons[i][j].getClientProperty("id"));
							System.out.println("UP AND DOWN");
							if(playertoken=="1")
								displayResult("1");
							else
								displayResult("2");
						}
					}



					// CHECK LEFT TO RIGHT POSITION
					if(j + 4 <board_width)
					{ 
						if(coinStatus(i,j+1) ==playertoken&& 
						   coinStatus(i,j+2) ==playertoken&&
						   coinStatus(i,j+3) ==playertoken&&
						   coinStatus(i,j+4) ==playertoken  )
						{ System.out.println("LEFT AND RIGHT");
						if(playertoken=="1")
							displayResult("1");
						else
							displayResult("2");
						}
					}

					// Checking the winning position from LEFT to RIGHT diagonally
					if(i < board_lenght - 4 && j < board_width - 4)
					{
						if(coinStatus(i+1,j+1) ==playertoken&& 
						   coinStatus(i+2,j+2) ==playertoken&& 
						   coinStatus(i+3,j+3) ==playertoken&&
						   coinStatus(i+4,j+4) ==playertoken  )
						{  System.out.println("DIAGNOAL LEFT AND RIGHT");
						if(playertoken=="1")
							displayResult("1");
						else
							displayResult("2");
						}   
					}

					// Checking the winning position from RIGHT to LEFT diagonally
					if(i  < board_lenght- 4 && j - 4 >= 0 )
					{
						if(coinStatus(i+1,j-1)==playertoken&& 
						   coinStatus(i+2,j-2)==playertoken&&
						   coinStatus(i+3,j-3)==playertoken&&
						   coinStatus(i+4,j-4)==playertoken  )
						{  System.out.println("DIAGONAL RIGHT TO LEFT");
						if(playertoken=="1")
							displayResult("1");
						else
							displayResult("2");
						}   
					}



				}
			}

		}

	}






	public static void displayResult(String playerWon)
	{
		JFrame theResultFrame = new JFrame();       
		if(playerWon=="1")
		{
			JOptionPane.showMessageDialog(theResultFrame,"\nWinner : Player 1\n\nPress OK to start new game.\n\n","Game Finised",JOptionPane.PLAIN_MESSAGE);
			 startAgain(); 
		}
		else
		{
			JOptionPane.showMessageDialog(theResultFrame,"\nWinner : Player 2\n\nPress OK to start new game.\n\n","Game Finised",JOptionPane.PLAIN_MESSAGE); 
			startAgain();    
		}
	}

	 public static void startAgain()
	   {
	       
	        for(int i=0; i<board_lenght; ++i)
	        {         
	            for(int j=0; j<board_width; ++j)
	            {
	            	buttons[i][j].putClientProperty("status","0");  // Initial Value
	                buttons[i][j].setIcon(emptyButton);       // Put the empty cell icon
	            }
	        }
	        
	        myframe.setVisible(false);                            // Unvisible previous game board
	        //ConnectFour newGame = new ConnectFour();           // New Game Object
	   }















	//===================END OF CLASS============================
}
