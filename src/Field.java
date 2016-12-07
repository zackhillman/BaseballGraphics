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

	private final int WIDTH = 790; //The set width for this JPanel
	private final int HEIGHT = 900; //The set height for this JPanel
	private Game game; //The current game object

	private Image background;// The image for the field

	/**
	 * This is the constructor. It instantiates the instance variables. 
	 * And gets the background from the file system
	 * @param g- the current game object
	 */
	public Field(Game g) {
		game = g;
		setOpaque(true);

		try {
			background = ImageIO.read(new File("backgroundbaseball.jpg"));
		} catch (IOException e) {
		}
	}

	/**
	 * This method sets the current game object.
	 * @param g- the new game object
	 */
	public void setGame(Game g) {
		game = g;
	}

	/**
	 * This method paints the field on the GUI
	 * @param- the graphics object of the JFrame
	 */
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(background, 0, 0, this);
		g.setFont(new Font("Kai", Font.BOLD, 30));

		g.setColor(Color.RED);
		g.setFont(new Font("Kai", Font.BOLD, 60));
		if (game.getBases()[0] != null)
			g.fillRect(613, 397, 50, 50);
		if (game.getBases()[1] != null)
			g.fillRect(370, 150, 50, 50);
		if (game.getBases()[2] != null)
			g.fillRect(125, 397, 50, 50);

		if (game.isGameOver()) {
			g.setColor(Color.GRAY);
			g.setFont(new Font("Kai", Font.PLAIN, 50));
			if (game.getHomeScore() > game.getAwayScore())
				g.drawString("Winner: Home Team", 160, 400);
			else if (game.getHomeScore() == game.getAwayScore())
				g.drawString("Tie", 350, 400);
			else
				g.drawString("Winner: Away Team", 160, 400);
		}
		drawScoreboard(g);
	}

	/**
	 * This method draws the actual scoreboard section of the GUI
	 * @param g- the JFrame graphics object
	 */
	private void drawScoreboard(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRoundRect(10, 650, 220, 130, 30, 30);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Kai", Font.PLAIN, 30));
		g.drawString("Home: " + game.getHomeScore(), 25, 685);
		g.drawString("Away: " + game.getAwayScore(), 25, 725);
		g.drawString("Outs: " + game.getOuts(), 25, 765);

		if (game.getHalf() == true)
			g.drawString(game.getCurrentInning() + "▲", 170, 765);
		else
			g.drawString(game.getCurrentInning() + "▼", 170, 765);
	}

}