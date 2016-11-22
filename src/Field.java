import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Field extends JPanel {

  
    private static final int WIDTH = 790;
    private static final int HEIGHT = 900;
    private static final int RADIUS = 50;
   
   
    private boolean isActive;
   
    private Image background;

    public Field () {
        setOpaque ( true ); 
       
        isActive = false;
        
        try{
			 background = ImageIO.read(new File("backgroundbaseball.jpg"));
		}catch(IOException e){}
    }

    public boolean setState () {        
      
    	
      repaint();
        
       

        return isActive;
    }

    @Override
    public Dimension getPreferredSize () {
        return new Dimension ( WIDTH, HEIGHT );
    }

    @Override
    protected void paintComponent ( Graphics g ) {
        super.paintComponent ( g );
        g.drawImage(background, 0,50,this);
//        g.drawRect ( 50, RADIUS, X, Y );
//        g.drawRect ( 100, RADIUS, X, Y );
//        g.drawRect ( 150, RADIUS, X, Y );
//        g.drawRect ( 200, RADIUS, X, Y );
//        g.drawRect ( 250, RADIUS, X, Y );
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));
        g.setColor ( Color.BLUE );
        g.drawString("Home: "+Game.getHomeScore(), 10, 100);
        
      
        
        
        g.setColor ( Color.RED );
        g.drawString("Away: "+Game.getAwayScore(), 10, 140);
        if(Game.half==true)
        	g.drawString("Inning: "+Game.currentInning+ " ▲", 10, 800);
        else
        	g.drawString("Inning: "+Game.currentInning+" ▼", 10, 800);
        g.setFont(new Font("TimesRoman", Font.BOLD, 60));
        if(Game.bases[0]!=null)
        	g.fillRect(613, 447, 50, 50);
        if(Game.bases[1]!=null)
        	g.fillRect(370, 200, 50, 50);
        if(Game.bases[2]!=null)
        	g.fillRect(125, 447, 50, 50);
        if(Game.currentEvent!=null)
        	g.drawString(Game.currentEvent.name(), 300, 500);
       
        
    }
}