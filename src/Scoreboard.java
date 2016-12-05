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

public class Scoreboard extends JPanel {

  
    private static final int WIDTH = 300;
    private static final int HEIGHT = 200;
   
   
    private boolean isActive;
   

    public Scoreboard () {
        setOpaque ( true ); 
       
        isActive = false;
        
      
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
    	g.fillRect(0, 0, WIDTH, HEIGHT);
        
    }
}