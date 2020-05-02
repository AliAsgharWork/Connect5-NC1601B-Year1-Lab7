package p3;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
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
	JMenu menu, submenu;
	JMenuItem menuItem;
	JCheckBoxMenuItem cbMenuItem;
	JRadioButtonMenuItem rbMenuItem;


	//Create a file chooser
	final static JFileChooser fc = new JFileChooser("C:\\Users\\User\\Desktop\\Connect5Save");



	static JFrame myframe;
	JPanel  board;
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

		Logging.initialtimeStamplog();	//TIME START STAMP

		myframe = new Connect5();
		myframe.setTitle("Ali's Connect5");
		myframe.setSize(1250,650);//CHANGE THE SIZE WHEN DONE WITH THE AI
		myframe.setLocationRelativeTo(null);;
		myframe.setVisible(true);
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}

	public Connect5() {
//		try {
//			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			// UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		/**GAME MENU BAR*///Learned this from http://www.cs.fsu.edu/~jtbauer/cis3931/tutorial/ui/swing/menu.html

		//Create the menu bar.
		menuBar = new JMenuBar();
		//menuBar.setBackground(Color.black);
		setJMenuBar(menuBar);

		//Build the first menu.
		menu = new JMenu("Options");
		menuBar.add(menu);

		//a group of JMenuItems
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
		
		
		menu.addSeparator();
		//a group of radio button menu items
		
//		ButtonGroup group = new ButtonGroup();
//		rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
//		rbMenuItem.setSelected(true);
//		group.add(rbMenuItem);
//		menu.add(rbMenuItem);
//		rbMenuItem = new JRadioButtonMenuItem("Another one");
//		group.add(rbMenuItem);
//		menu.add(rbMenuItem);

		//a group of check box menu items
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("AI Goes First");
		menu.add(cbMenuItem);
		cbMenuItem.addActionListener(this);
		
		//a submenu
		menu.addSeparator();
		submenu = new JMenu("A submenu");
		menuItem = new JMenuItem("An item in the submenu");
		submenu.add(menuItem);
		menuItem = new JMenuItem("Another item");
		submenu.add(menuItem);
		menu.add(submenu);

		//Build second menu in the menu bar.
		menu = new JMenu("Another Menu");
		menuBar.add(menu);

		/**GAME STATISTICAL PANEL*/
		Sidespace = new JPanel();
		Sidespace.setBackground(Color.white);
		add(Sidespace);


		/**PLAYING BOARD PANEL*/
		board=new JPanel(new GridLayout(board_lenght_col,board_width_row));
		//board.setBackground(Color.BLUE);
		add(board);

		/**PLAYING BOARD SLOTS*/
		buttons=new JButton[board_lenght_col][board_width_row];//BUTTONSLOTS
		populateButtons();

	}

	 
	
	public void actionPerformed(ActionEvent e)
	{
		buttonpressed = e.getSource();
		
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

		//String input = buttonpressed.getActionCommand();
		//System.out.println(((JComponent) buttonpressed).getClientProperty("id"));
		//System.out.println(((JComponent) buttonpressed).getClientProperty("status"));
		//Computer_AI.score_position();
		
					NextSlotOpenner();
					run_Game();
		
		


	}
	public static void run_Game() {

		LogicalDecisionclass logic =new LogicalDecisionclass(board_lenght_col,board_width_row);
		logic.winningCondition();      //does the player win ? display win and end : continue

		
		
		if(LogicalDecisionclass.TurnDecider()=="computer" && AIyes)
		{
			System.out.println("Computer played");
			Computer_AI.moveComputer();//AI Computer's turn
		}
		
		
		
		
		//does computer win ? display win and end : continue
		//wait for next insert by player
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
				buttons[i][j].setText("place"+i+","+j);//REMOVE LATER
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


	public void NextSlotOpenner() {
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


	public static void displayResult(String playerWon)
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

	public static void startAgain_AKA_reset()
	{
		System.out.println("restared");
		try {Logging.finishtimeStamplog();} catch (IOException e) {e.printStackTrace();}

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
		AIyes=true;
		firsttimerun=true;
		whenAIGoesFirst=false;
		checkAIFirstMove();
		
	}



	//===================END OF CLASS============================\\
}
