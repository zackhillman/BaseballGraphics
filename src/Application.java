import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class Application implements ActionListener{

    private JPanel field;
    private JButton add;
    private JButton hit;
    private JButton edit;
    private JButton reset;
    private JTextField nameF;
    private JTable homeStats;
    private JTable awayStats;
    private JList<String> eventLog;
    
    JScrollPane eventScroll;
    JPanel contentPane;
  //  ListCellRenderer<String> test = eventLog;
    
    private static final int WIDTH = 1440;
    private static final int HEIGHT = 900;

    private static final int GAP = 5;
    

    private void displayGUI () {
        JFrame frame = new JFrame ("");
        frame.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setLayout (null);
        
        nameF = new JTextField("");
        contentPane.add(nameF);
        nameF.setBounds(800,55,250,30);
        
        add = new JButton ( "Add" );
        add.addActionListener (this);
        contentPane.add(add);
        add.setBounds(800, 110, 80, 40);
        
        hit = new JButton ( "Hit" );
        hit.addActionListener (this);
        contentPane.add (hit );
        hit.setBounds(890, 110, 80, 40);
        
        edit = new JButton ( "Edit" );
        edit.addActionListener (this);
        contentPane.add (edit);
        edit.setBounds(980, 110, 80, 40);
        
        field = new Field ();
        contentPane.add ( field );
        field.setBounds(0, 0, 790, 810);
        //new String[][]{"test"}{"test"},Game.getAllPlayers()
       
        homeStats =  new JTable();
        JScrollPane homeScroll = new JScrollPane(homeStats);
        contentPane.add (homeScroll);
        homeScroll.setBounds(800, 170, 600, 165);
        
        
        
        awayStats =  new JTable();
        JScrollPane awayScroll = new JScrollPane(awayStats);
        contentPane.add ( awayScroll );
        awayScroll.setBounds(800, 370, 600, 165);

        awayStats.setRowSelectionAllowed(true);
        homeStats.setRowSelectionAllowed(true);
        
        eventLog = new JList<String>(Game.gameLog);
        //eventOutput = new JTextArea ("");
         eventScroll = new JScrollPane(eventLog);
        contentPane.add (eventScroll);
        eventScroll.setBounds(800, 550, 600, 240);
        
        
       
       
        

       
        frame.setContentPane ( contentPane );
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationByPlatform ( true );
        frame.setVisible ( true );
    
    }

    public static void main ( String [] args ) {
        Runnable runnable = new Runnable () {
            @Override
            public void run () {
            	Game.init();
                new Application ().displayGUI ();
                
            }
        };
        EventQueue.invokeLater (runnable);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source==add){
			if(Game.awayTeamFull())
				add.setEnabled(false);
			Game.add(new Player(nameF.getText()));
			homeStats.setModel(Game.homeTeamTable());
			awayStats.setModel(Game.awayTeamTable());

		}else if(source == hit){
			Game.hit();
			field.repaint();
			JTable currentTeam = null;
			DefaultTableModel currentModel = null;

			if(Game.half){
				currentTeam = awayStats;
				currentModel = Game.awayTeamTable();
			}else{
				currentTeam = homeStats;
				currentModel = Game.awayTeamTable();
			}
			int nextIndex = getNextIndex(currentTeam.getSelectedRow());
			currentTeam.setModel(currentModel);
			currentTeam.setRowSelectionInterval(nextIndex, nextIndex);
			
			eventLog.setModel(Game.gameLog);
			eventLog.scrollRectToVisible(eventLog.getBounds());
			eventScroll.getVerticalScrollBar().setValue( eventScroll.getVerticalScrollBar().getMaximum());

			
			
		}else if(source == edit){
			Game.edit(new Player(nameF.getText()));
			homeStats.setModel(Game.homeTeamTable());
			awayStats.setModel(Game.awayTeamTable());
		}
			
		
		
		
		
	}
	
	private int getNextIndex(int current){
		if (current==8)
			return 0;
		else
			return current+1;
	}
	
	
	
	



}