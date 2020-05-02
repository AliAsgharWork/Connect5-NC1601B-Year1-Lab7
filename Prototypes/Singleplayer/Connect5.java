package Singleplayer;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;


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
	
	
	
	static JFrame myframe;
	JPanel  board;
	static JButton [][]buttons;
	static int board_lenght=7;
	static int board_width=8;
	static JPanel Sidespace;
	

	//ActionEvent Variables
	static JButton buttonpressed;
	

	//Button Icon Source 
	static ImageIcon emptyButton = new ImageIcon(Connect5.class.getResource("/resources/emptycoin.png"));
	static ImageIcon redButton = new ImageIcon(Connect5.class.getResource("/resources/red coin v2.png"));
	static ImageIcon yellowButton = new ImageIcon(Connect5.class.getResource("/resources/yellow coin v2.png"));

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		myframe = new Connect5();
		
		
		
		myframe.setTitle("Ali's Connect5");
		myframe.setSize(1000,650);//CHANGE THE SIZE WHEN DONE WITH THE AI
		myframe.setLocationRelativeTo(null);;
		myframe.setVisible(true);
		myframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public Connect5() {
		
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
		menuItem = new JMenuItem("Both text and icon", 
		new ImageIcon("images/middle.gif"));
		menu.add(menuItem);
		menuItem = new JMenuItem(new ImageIcon("images/middle.gif"));
		menu.add(menuItem);

		//a group of radio button menu items
		//menu.add("hi there").setEnabled(false);
		menu.addSeparator();
		ButtonGroup group = new ButtonGroup();
		rbMenuItem = new JRadioButtonMenuItem("A radio button menu item");
		rbMenuItem.setSelected(true);
		group.add(rbMenuItem);
		menu.add(rbMenuItem);
		rbMenuItem = new JRadioButtonMenuItem("Another one");
		group.add(rbMenuItem);
		menu.add(rbMenuItem);

		//a group of check box menu items
		menu.addSeparator();
		cbMenuItem = new JCheckBoxMenuItem("A check box menu item");
		menu.add(cbMenuItem);
		cbMenuItem = new JCheckBoxMenuItem("Another one");
		menu.add(cbMenuItem);

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
		LogicalDecisionclass hi =new LogicalDecisionclass(board_lenght,board_width);
		hi.winningCondition();//does the player win ? display win and end : continue
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
				buttons[i][j].setText("place"+i+","+j);//REMOVE LATER
				buttons[i][j].addActionListener(this);
				if(i!=board_lenght-1){buttons[i][j].setEnabled(false);}//Starting row should be active to play
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


	public static void displayResult(String playerWon)
	{
		JFrame theResultFrame = new JFrame();       
		if(playerWon=="1")
		{
			JOptionPane.showMessageDialog(theResultFrame,"\nWinner : Player 1\n\nPress OK to start new game.\n\n","Game Finised",JOptionPane.PLAIN_MESSAGE);
			 startAgain_AKA_reset(); 
		}
		else
		{
			JOptionPane.showMessageDialog(theResultFrame,"\nWinner : Player 2\n\nPress OK to start new game.\n\n","Game Finised",JOptionPane.PLAIN_MESSAGE); 
			startAgain_AKA_reset();    
		}
	}

	 public static void startAgain_AKA_reset()
	   {
	        for(int i=0; i<board_lenght; ++i)
	        {         
	            for(int j=0; j<board_width; ++j)
	            {
	            	buttons[i][j].putClientProperty("status","0");  		//Initial slot variable as neutral
	                buttons[i][j].setIcon(emptyButton);      				//Setup empty slot
	                if(i!=board_lenght-1){buttons[i][j].setEnabled(false);}	//disable buttons from 
	            }
	        }
	        LogicalDecisionclass.turnCounterVar=0;
	   }



	//===================END OF CLASS============================
}
