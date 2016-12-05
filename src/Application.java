import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class Application implements ActionListener {

	private Field field;
	private JButton add;
	private JButton hit;
	private JButton edit;
	private JButton reset;
	private JTextField nameF;
	private JTable homeStats;
	private JTable awayStats;
	private JList<String> eventLog;
	private JScrollPane eventScroll;
	private JPanel contentPane;

	private JScrollPane homeScroll;
	private JScrollPane awayScroll;

	private static final int WIDTH = 1440;
	private static final int HEIGHT = 900;


	private Game game;

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

	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				
				new Application().displayGUI();

			}
		};
		EventQueue.invokeLater(runnable);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();

		if (source == add) {
			
			game.add(new Player(nameF.getText()));
			homeStats.setModel(game.homeTeamTable());
			awayStats.setModel(game.awayTeamTable());
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

	private void doSelection() {
		if (game.getHalf()) {

			// awayStats.setModel(game.awayTeamTable());
			awayStats.setRowSelectionInterval(game.getAwayIndex(), game.getAwayIndex());
			homeStats.removeRowSelectionInterval(0, 8);
		} else {
			// homeStats.setModel(game.homeTeamTable());

			homeStats.setRowSelectionInterval(game.getHomeIndex(), game.getHomeIndex());
			awayStats.removeRowSelectionInterval(0, 8);
		}
	}

	

}