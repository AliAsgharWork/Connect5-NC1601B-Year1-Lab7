package p1;


import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class C5 extends JFrame implements ActionListener{

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

	
	//Button Icon Source 
	static ImageIcon emptyButton = new ImageIcon(C5.class.getResource("/resources/emptycoin.png"));


	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myframe = new C5();
		//myframe.setLayout();
		myframe.setTitle("Ali's Connect5");
		myframe.setSize(1000,650);
		myframe.setLocationRelativeTo(null);;
		myframe.setVisible(true);
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public C5() {


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
		ld logic =new ld(board_lenght,board_width ,buttonpressed);//logical object which calles these methods below
		String currentToken =ld.PlayerMoveSetter();
		ld.winningCondition(currentToken);//does the player win ? display win and end : continue
		//computer turn
		//does computer win ? display win and end : continue
		//wait for next insert by player
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
				buttons[i][j].setText("place"+i+","+j);
				buttons[i][j].addActionListener(this);
				if(i!=6){buttons[i][j].setEnabled(false);}//Starting row should be active to play
			}
		}
	}

	public static void NextSlotOpenner() {
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



	





	public void displayResult(String playerWon)
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

	 public void startAgain()
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
	  
	   }















	//===================END OF CLASS============================
}

