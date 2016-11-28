import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
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
        g.drawImage(background, 0,0,this);
        g.setFont(new Font("TimesRoman", Font.BOLD, 30));
        g.setColor ( Color.BLUE );
        g.drawString("Home: "+Game.getHomeScore(), 10, 80);
        
      
        
        
        g.setColor ( Color.RED );
        g.drawString("Away: "+Game.getAwayScore(), 10, 120);
        g.drawString("Outs: "+Game.outs, 10, 720);
        if(Game.half==true)
        	g.drawString("Inning: "+Game.currentInning+ " ▲", 10, 760);
        else
        	g.drawString("Inning: "+Game.currentInning+" ▼", 10, 760);
        g.setFont(new Font("TimesRoman", Font.BOLD, 60));
        if(Game.bases[0]!=null)
        	g.fillRect(613, 397, 50, 50);
        if(Game.bases[1]!=null)
        	g.fillRect(370, 150, 50, 50);
        if(Game.bases[2]!=null)
        	g.fillRect(125, 397, 50, 50);
//        if(Game.currentEvent!=null){
//        	
//        
//        	g.drawString(Game.currentEvent.name(), 300, 480);
//        }
        
    }
}