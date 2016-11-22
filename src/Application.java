import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Application implements ActionListener{

    private JPanel field;
    private JButton add;
    private JButton hit;
    private JButton edit;
    private JButton reset;
    private JTextField nameF;
    
    private static final int WIDTH = 790;
    private static final int HEIGHT = 900;

    private static final int GAP = 5;
    

    private void displayGUI () {
        JFrame frame = new JFrame ( "" );
        frame.setDefaultCloseOperation ( JFrame.DISPOSE_ON_CLOSE );

        JPanel contentPane = new JPanel ();
        contentPane.setLayout (null);
        
        nameF = new JTextField("");
        contentPane.add(nameF);
        nameF.setBounds(10,845,250,30);
        
        add = new JButton ( "Add" );
        add.addActionListener (this);
        contentPane.add(add);
        add.setBounds(270, 840, 80, 40);
        
        hit = new JButton ( "Hit" );
        hit.addActionListener (this);
        contentPane.add (hit );
        hit.setBounds(355, 840, 80, 40);
        
        edit = new JButton ( "Edit" );
        edit.addActionListener (this);
        contentPane.add (edit);
        edit.setBounds(440, 840, 80, 40);

        
        field = new Field ();
        contentPane.add ( field );
        field.setBounds(0, 0, 790, 840);

        
       
        
   //     add = new JButton ( "Add" );
  //      add.addActionListener (this);
  //      contentPane.add (add, BorderLayout.PAGE_END );
       
        frame.setContentPane ( contentPane );
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationByPlatform ( true );
        frame.setVisible ( true );
    }

    public static void main ( String [] args ) {
        Runnable runnable = new Runnable () {
            @Override
            public void run () {
                new Application ().displayGUI ();
                Game.init();
            }
        };
        EventQueue.invokeLater ( runnable );
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if(source==add){
			Game.add(new Player(nameF.getText()));
		}else if(source == hit){
			Game.hit();
			field.repaint();
		}
			
		
		
		
		
	}



}