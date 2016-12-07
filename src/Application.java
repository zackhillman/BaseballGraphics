import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;

public class Application implements ActionListener {

	private Field field; //Shows user real time information of players, and score
	private JButton add; //Adds a player to a team
	private JButton hit; //Simulates a hit in the game
	private JButton edit; //Substitutes a player in for the current player
	private JButton reset; //Resets the game
	private JTextField nameF; //Where the user enters each player's name
	private JTable homeStats; //Tracks the stats for the home team
	private JTable awayStats; //Tracks the stats for the away team
	private JList<String> eventLog; //Stores a log of all of the events
	private JScrollPane eventScroll; //Allows the log to scroll
	private JPanel contentPane; //Holds each of the GUI elements

	private JScrollPane homeScroll; //Allows the home team stats to scroll
	private JScrollPane awayScroll; //Allows the away team stats to scroll

	private final int WIDTH = 1440; //Final variable for the width of the game
	private final int HEIGHT = 900; //Final variable for the height of the game


	private Game game; //The game object, simulates all of the game mechanics

	/**
	 * This is the constructor method, it instantiates the instance variables and GUI objects
	 */
	public Application() {
		
		game = new Game();
		
		nameF = new JTextField("");

		field = new Field(game);

		add = new JButton("Add");
		edit = new JButton("Edit");
		hit = new JButton("Hit");
		reset = new JButton("Reset");

		add.addActionListener(this);
		hit.addActionListener(this);
		edit.addActionListener(this);
		reset.addActionListener(this);

		homeStats = new JTable();
		awayStats = new JTable();

		eventLog = new JList<String>();
		eventScroll = new JScrollPane(eventLog);
		homeScroll = new JScrollPane(homeStats);
		awayScroll = new JScrollPane(awayStats);
		
		hit.setEnabled(false);
		awayStats.setRowSelectionAllowed(true);
		homeStats.setRowSelectionAllowed(true);
	}

	/**
	 * This method is called to display the GUI. It adds each of the components
	 * to the contentPane JPanel. It also creates a JFrame to house the contentPane
	 */
	private void displayGUI() {
		JFrame frame = new JFrame("");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.add(nameF);
		contentPane.add(add);
		contentPane.add(hit);
		contentPane.add(edit);
		contentPane.add(reset);
		contentPane.add(field);
		contentPane.add(homeScroll);
		contentPane.add(awayScroll);
		contentPane.add(eventScroll);

		nameF.setBounds(800, 55, 250, 30);
		add.setBounds(800, 110, 80, 40);
		hit.setBounds(890, 110, 80, 40);
		edit.setBounds(980, 110, 80, 40);
		reset.setBounds(1060, 110, 80, 40);
		field.setBounds(0, 0, 790, 810);
		homeScroll.setBounds(800, 170, 600, 165);
		awayScroll.setBounds(800, 370, 600, 165);
		eventScroll.setBounds(800, 550, 600, 240);

		frame.setContentPane(contentPane);
		frame.setSize(WIDTH, HEIGHT);
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
	}

	/**
	 * This is the main method, it is called at the start of the program.
	 * @param args- String[] args
	 */
	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				
				new Application().displayGUI();

			}
		};
		EventQueue.invokeLater(runnable);
	}

	/**
	 * This method is called when an action occurs on the GUI
	 * ie. a button is pressed.
	 * @param- the ActionEvent object 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == add) {
			
			game.add(new Player(nameF.getText()));
			homeStats.setModel(game.homeTeamTable());
			awayStats.setModel(game.awayTeamTable());
			nameF.setText("");
			nameF.requestFocus(true);

			if (game.awayTeamFull()) {
				add.setEnabled(false);
				awayStats.setRowSelectionInterval(0, 0);
				hit.setEnabled(true);
				eventLog.setModel(game.getGameLog());
			}
			
		} else if (source == hit) {
			game.hit();
			field.repaint();
			awayStats.setModel(game.awayTeamTable());
			homeStats.setModel(game.homeTeamTable());
			doSelection();
			eventLog.setModel(game.getGameLog());
			eventLog.scrollRectToVisible(eventLog.getBounds());
			eventScroll.getVerticalScrollBar().setValue(eventScroll.getVerticalScrollBar().getMaximum());
		} else if (source == edit) {
			game.edit(new Player(nameF.getText()));
			homeStats.setModel(game.homeTeamTable());
			awayStats.setModel(game.awayTeamTable());
			doSelection();
		} else if (source == reset) {
			game = new Game();
			field.setGame(game);
			hit.setEnabled(false);
			add.setEnabled(true);
			awayStats.repaint();
			homeStats.repaint();
			homeStats.setModel(game.homeTeamTable());
			awayStats.setModel(game.awayTeamTable());
			eventLog.setModel(game.getGameLog());
			field.repaint();
		}
	}

	/**
	 * This method is responsible for selecting the correct table row. It shows the user
	 * who the current batter is.
	 */
	private void doSelection() {
		if (game.getHalf()) {
			awayStats.setRowSelectionInterval(game.getAwayIndex(), game.getAwayIndex());
			homeStats.removeRowSelectionInterval(0, 8);
		} else {
			homeStats.setRowSelectionInterval(game.getHomeIndex(), game.getHomeIndex());
			awayStats.removeRowSelectionInterval(0, 8);
		}
	}

	

}