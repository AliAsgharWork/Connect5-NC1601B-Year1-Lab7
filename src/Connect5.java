
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.UIManager;

import GIFcreation.ScreenImage;



public class Connect5 extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	JMenuBar menuBar;
	static JMenu menu;


	JMenu submenu;
	JMenuItem menuItem;
	JCheckBoxMenuItem cbMenuItem;
	JRadioButtonMenuItem rbMenuItem;


	//Create a file chooser
	final static JFileChooser fc = new JFileChooser(AdditionalStuff.dir_Desktop+"\\Connect5Save");



	static JFrame myframe;
	static JPanel  board;
	static JButton [][]buttons;
	static int board_lenght_col=7;
	static int board_width_row=8;
	static JPanel Sidespace;


	//ActionEvent Variables
	static Object buttonpressed;
	static boolean firsttimerun=true ;
	static boolean AIyes=true;
	static boolean whenAIGoesFirst=false;
	static boolean check_box_ai_yellow=false;
	
	
	//Button Icon Source 
	static ImageIcon emptyButton = new ImageIcon(Connect5.class.getResource("/resources/emptycoin.png"));
	static ImageIcon redButton = new ImageIcon(Connect5.class.getResource("/resources/red coin v2.png"));
	static ImageIcon yellowButton = new ImageIcon(Connect5.class.getResource("/resources/yellow coin v2.png"));

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		myframe = new Connect5();
		myframe.setTitle("Ali's Connect5");
		myframe.setSize(700,500);
		myframe.setLocationRelativeTo(null);;
		myframe.setVisible(true);
		myframe.setResizable(false);
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public Connect5() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			//UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		/**GAME MENU BAR*///Learned this from http://www.cs.fsu.edu/~jtbauer/cis3931/tutorial/ui/swing/menu.html

		//Create the menu bar.
		menuBar = new JMenuBar();
		//menuBar.setBackground(Color.black);
		setJMenuBar(menuBar);

		//Build the first menu.
		menu = new JMenu("Options");
		menuBar.add(menu);

		//a group of JMenuItems
		menuItem = new JMenuItem("Pause");
		menu.add(menuItem);
		menuItem.addActionListener(this);
		
		menuItem = new JMenuItem("Restart");
		menu.add(menuItem);
		menuItem.addActionListener(this);

		menuItem = new JMenuItem("Save Game");
		menu.add(menuItem);
		menuItem.addActionListener(this);

		menuItem = new JMenuItem("Load Game");
		menu.add(menuItem);
		menuItem.addActionListener(this);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Screenshot Game");
		menu.add(menuItem);
		menuItem.addActionListener(this);
		
		menuItem = new JMenuItem("Create a GIF");//creates a gif file of the moves currently played
		menu.add(menuItem);
		menuItem.addActionListener(this);
		
		
		//a check box menu items for the AI Turn Selector
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("AI Goes First");
		menu.add(cbMenuItem);
		cbMenuItem.addActionListener(this);
		
		//2 Player Multiplayer option(H vs H)
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("H vs H Multiplayer");
		menu.add(cbMenuItem);
		cbMenuItem.addActionListener(this);
		
		//Help menu
		menu = new JMenu("Help");
		menuBar.add(menu);
		
		menuItem = new JMenuItem("How to play ?");
		menu.add(menuItem);
		menuItem.addActionListener(this);
		
		//Build second menu in the menu bar to record the turns.
		menu = new JMenu("Turn:");
		menuBar.add(menu);

		/**GAME STATISTICAL PANEL*/
		Sidespace = new JPanel();
		Sidespace.setBackground(Color.white);
		add(Sidespace);


		/**PLAYING BOARD PANEL*/
		board=new JPanel(new GridLayout(board_lenght_col,board_width_row));
		board.setBackground(Color.BLUE);
		add(board);

		/**PLAYING BOARD SLOTS*/
		buttons=new JButton[board_lenght_col][board_width_row];//BUTTONSLOTS
		populateButtons();

	}

	 
	
	public void actionPerformed(ActionEvent e)
	{
		buttonpressed = e.getSource();
		System.out.println(e.getActionCommand());
		
		if(e.getActionCommand()=="Restart")//Condition for Restart MenuItem
		{
			startAgain_AKA_reset();
		}
    
		
		AbstractButton aButton = (AbstractButton) e.getSource();  
        boolean selected = aButton.getModel().isSelected();
		if(e.getActionCommand()=="AI Goes First")//Condition for Restart MenuItem
		{
			
			if(selected)
			{check_box_ai_yellow=true;startAgain_AKA_reset();}
			else 
			{check_box_ai_yellow=false;startAgain_AKA_reset();}
		}
		if(e.getActionCommand()=="H vs H Multiplayer")//Condition for Restart MenuItem
		{
			
			if(selected)
			{AIyes=false;startAgain_AKA_reset();}
			else 
			{AIyes=true;startAgain_AKA_reset();}
		}
		
	
		if(e.getActionCommand()=="Load Game")//Condition for Loading Game File MenuItem
		{
			int returnVal = fc.showOpenDialog(Connect5.this);
			try {
				Storage.load(returnVal);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if(e.getActionCommand()=="Save Game")//Condition for Saving Game File MenuItem
		{
			try {
				Storage.save();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if(e.getActionCommand()=="Screenshot Game")//Condition for Saving Game File MenuItem
		{
			BufferedImage ss = ScreenImage.createImage(board);
			AdditionalStuff.takeShot(ss);
			
		}
		if(e.getActionCommand()=="How to play ?")//Condition for Saving Game File MenuItem
		{
			displayhelpMessage();
		}
		
		if(e.getActionCommand()=="Pause")//Condition for Saving Game File MenuItem
		{
			pausehelpMessage();
		}
		if(e.getActionCommand()=="Create a GIF")//Condition for Saving Game File MenuItem
		{
			try {
				CreateGif.createGif();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		
		
		
		NextSlotOpenner();
		run_Game();
		
		


	}
	public static void run_Game() {

		LogicalDecisionclass logic =new LogicalDecisionclass(board_lenght_col,board_width_row);
		logic.winningCondition();      //does the player win ? display win and end : continue

		displayturncounter();//changes the text on the board to display the number of turns that have been executed
		
		if(LogicalDecisionclass.TurnDecider()=="computer" && AIyes)//responsibke 
		{
			System.out.println("Computer played");
			Computer_AI.moveComputer();//AI Computer's turn
		}
		
		
	}

	public static void displayturncounter() {
		//Turn Displayer
		//menu.setText("Turn:"+LogicalDecisionclass.turnCounterVar);
		int AiVersionTurnCounter=LogicalDecisionclass.turnCounterVar;
		menu.setText("Turn:"+AiVersionTurnCounter );
				
				if(whenAIGoesFirst)
				{
					AiVersionTurnCounter++;
					menu.setText("Turn:"+AiVersionTurnCounter );
				
				}
		
	}

	public void populateButtons() {//starting layout of the game
		for (int i = 0; i < board_lenght_col; i++) {

			for (int j = 0; j < board_width_row; j++) {

				buttons[i][j]=new JButton();
				buttons[i][j].setIcon(emptyButton);
				board.add(buttons[i][j]);
				buttons[i][j].setOpaque(false);
				buttons[i][j].setContentAreaFilled(false);
				buttons[i][j].setBorderPainted(false);
				buttons[i][j].putClientProperty("id",String.valueOf(i).concat(String.valueOf(j)));
				buttons[i][j].putClientProperty("status","0");
				//buttons[i][j].setText("place"+i+","+j);//REMOVE LATER
				buttons[i][j].addActionListener(this);
				if(i!=board_lenght_col-1){buttons[i][j].setEnabled(false);}//Starting row should be active to play
			}
		}
	}
	
	
	public static void checkAIFirstMove() {
		//If the Ai gets to play the first turn then it will make sure to set the "turnCountervar"
		//as a 1 to register it as a computer turn it executes the play and then resets the value to 
		//continue the game.
		System.out.println("executing ai first check");
		if( check_box_ai_yellow && AIyes )
		{
			LogicalDecisionclass.turnCounterVar=101;
			
			whenAIGoesFirst=true;//Used for Draw Condition
			Computer_AI.firstMoveByAI();
		
			LogicalDecisionclass.turnCounterVar=0;
		}


	}


	public void NextSlotOpenner() {//Opens the slot above the place coin
		for (int i = 1; i < board_lenght_col; i++) 
		{
			for (int j = 0; j < board_width_row; j++)
			{
				if (buttons[i][j].equals(buttonpressed))
				{
					buttons[i-1][j].setEnabled(true);
				}	
			}
		}
	}


	public static void displayResult(String playerWon)//Display the winning window
	{
		JFrame theResultFrame = new JFrame();       
		
		if(playerWon=="1")
		{
			JOptionPane.showMessageDialog(theResultFrame,"\nWinner : Player 1\n\nPress OK to start new game.\n\n","Game Finised",JOptionPane.PLAIN_MESSAGE);
			startAgain_AKA_reset(); 
		}
		else if (playerWon=="2")
		{
			JOptionPane.showMessageDialog(theResultFrame,"\nWinner : Player 2\n\nPress OK to start new game.\n\n","Game Finised",JOptionPane.PLAIN_MESSAGE); 
			startAgain_AKA_reset();    
		}
		else
		{
			JOptionPane.showMessageDialog(theResultFrame,"\nIt's a Draw, Good Game \nPress OK to start new game.\n\n","Game Finised",JOptionPane.PLAIN_MESSAGE); 
			startAgain_AKA_reset();

		}

	}
	public static void displayhelpMessage()
	{
		JFrame helpFrame = new JFrame(); 
		JOptionPane.showMessageDialog(helpFrame,"This is a game for two players(Human against the computer) where, taking it in turns, the winner is the player that\n"
				+ " succeeds in forming a line of five discs of the same colour.\n\n"
				+ " (1)The rules of the game are very simple. The board consists of a seven row by eight column grid (7 Rows X 8 Columns).\n"
				+ " (2)Each player takes a turn with a single coloured disc (each player is allocated a colour) that they slot into the top of any of the non-full columns.\n"
				+ " (3)The disc then falls either to the bottom row or lands on top of another disc of either colour.\n"
				+ " (4)The winner is the player who is the first to create/form a contiguous line of five discs of their allocated colour.\n"
				+ " (5)This line can be horizontal, vertical or diagonal. If the board becomes full and there is no winner, then the game is drawn. ","How To Play ?",JOptionPane.INFORMATION_MESSAGE);
		
	}
	public static void pausehelpMessage()
	{
		JFrame pauseFrame = new JFrame(); 
		JOptionPane.showMessageDialog(pauseFrame,"\nThe Game is Paused. \n Press OK to continue\n\n","Pause",JOptionPane.PLAIN_MESSAGE);
	}

	public static void startAgain_AKA_reset()
	{
		for(int i=0; i<board_lenght_col; ++i)
		{         
			for(int j=0; j<board_width_row; ++j)
			{
				buttons[i][j].putClientProperty("status","0");  		//Initial slot variable as neutral
				buttons[i][j].setIcon(emptyButton);      				//Setup empty slot
				if(i!=board_lenght_col-1){buttons[i][j].setEnabled(false);}	//disable buttons from 
			}
		}
		Storage.storage.clear();
		LogicalDecisionclass.turnCounterVar=0;
		menu.setText("Turn:"+LogicalDecisionclass.turnCounterVar);
		firsttimerun=true;
		whenAIGoesFirst=false;
		checkAIFirstMove();
		
	}



	//===================END OF CLASS============================\\
}
